package com.project.daerkoob.service;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.repository.BookRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book){
        if(!bookRepository.existsByIsbn(book.getIsbn())){ // 없으면
            bookRepository.save(book);
        }
    }

    public boolean existsBook(Book book){
        return bookRepository.existsByIsbn(book.getIsbn());
    }

    public Long getBookId(Book book){
        Optional<Book> findByIsbn= bookRepository.findByIsbn(book.getIsbn());
        return findByIsbn.get().getId();
    }

    public List<Book> getBook(String title , String display) throws Exception {
        NaverApiService naverApiService = new NaverApiService();
        List<Book> bookList = new ArrayList<Book>();
        String id = "FZrwDPKOBRfo1BlN5tFY";
        String secret = "pBFNa86Va1";
        String url = URLEncoder.encode(title, "UTF-8") + ("&display=" + display + "&start=1");
        String response = naverApiService.search(id, secret, url); //naver api에 request를 보내려면 ,clientId와 secret code가 필요하기 떄문에 다 같이 보내준다 , 그리고 내가 원하는 query와 함께

        JSONObject jObject = new JSONObject(response);
        JSONArray jArray = jObject.getJSONArray("items");

        for(int i =0; i <jArray.length(); i++){
            JSONObject obj = jArray.getJSONObject(i);
            Book tempBook = new Book();
            tempBook.setTitle(obj.getString("title"));
            tempBook.setAuthor(obj.getString("author"));
            tempBook.setPublisher(obj.getString("publisher"));
            tempBook.setPubdate(obj.getString("pubdate"));
            tempBook.setIsbn(obj.getString("isbn"));
            if(!obj.getString("image").equals("") && naverApiService.checkLink(obj.getString("image"))){ //이전에 연결 오류 났던 것은 value가 "" 일때도 넘어가서 그럼
                //그래서 obj.getString 으로 value값을 꺼낸다음 ""과 같은지 확인하고 그것이 같으면 바로 else로 가는 && 연산자의 특성을 이용해서 naverApiService.checkLink를 실행 못하도록 하였음
                //그리고 img값은 null이 아니고 정확히는 "" 였었다. (null이 아님)
                tempBook.setImage(obj.getString("image"));
            }
            else{
                tempBook.setImage(null);
            }
            tempBook.setDescription(obj.getString("description"));
            bookList.add(tempBook);
        }
        return bookList;
    }
}
