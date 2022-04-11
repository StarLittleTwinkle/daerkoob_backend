package com.project.daerkoob.service;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void save(Book book , Long judgeNumber) throws Exception{
        if(!bookRepository.existsByIsbn(book.getIsbn())){
            Book dtoBook = createBook(book.getIsbn());
            if(judgeNumber == 1){//이건 리뷰
                dtoBook.setReviewCount(dtoBook.getReviewCount() + 1);
            }
            if(judgeNumber == 2) {//이건 필사
                dtoBook.setTranscriptionCount(dtoBook.getTranscriptionCount() + 1);
            }
            bookRepository.save(dtoBook);
        }
        else{
            Optional<Book> findByIsbn = bookRepository.findByIsbn(book.getIsbn());
            Book resultBook = findByIsbn.get();
            if(judgeNumber == 1){ //이건 리뷰
                resultBook.setReviewCount(resultBook.getReviewCount() + 1);
            }
            if(judgeNumber == 2){ //이건 필사
                resultBook.setTranscriptionCount(resultBook.getTranscriptionCount() + 1);
            }
            bookRepository.save(resultBook);
        }
    }

    public boolean existsBook(String isbn){
        return bookRepository.existsByIsbn(isbn);
    }

    public List<Book> getBest(){
        return bookRepository.findTop10ByOrderByTranscriptionCountDesc();
    }

    public Optional<Book> findBook(String isbn){
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        return book;
    }

    public Long getBookId(String isbn){
        Optional<Book> findByIsbn= bookRepository.findByIsbn(isbn);
        return findByIsbn.get().getId();
    }

    public Long countAll(){
        return bookRepository.count();
    }

    public Book createBook(String isbn) throws Exception{
        // 이미 책이 존재하는 경우 , 그냥 바로 있던 책의 정보를 반환
        if(existsBook(isbn)) {
            return bookRepository.findByIsbn(isbn).get();
        }

        // 책이 존재하지 않는 경우 , 저장과 동시에 반환한다.
        Book book = getBook(isbn , "1").get(0);

        return bookRepository.save(Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .pubdate(book.getPubdate())
                .isbn(isbn)
                .image(book.getImage())
                .description(book.getDescription())
                .transcriptionCount(0L)
                .reviewCount(0L)
                .star(0d)
                .starCount(0L)
                .build());
    }

    public List<Book> getBook(String title , String display) throws Exception {
        NaverApiService naverApiService = new NaverApiService();
        List<Book> bookList = new ArrayList<Book>();
        String id = "FZrwDPKOBRfo1BlN5tFY";
        String secret = "pBFNa86Va1";
        String url = URLEncoder.encode(title, "UTF-8") + ("&display=" + display + "&start=1");
        String response = naverApiService.search(id, secret, url);

        JSONObject jObject = new JSONObject(response);
        JSONArray jArray = jObject.getJSONArray("items");

        for(int i =0; i <jArray.length(); i++){
            Thread thread = new Thread(new CheckLinkThread(jArray.getJSONObject(i) , bookList));
            thread.start();
        }
        while(bookList.size() != jArray.length()){Thread.sleep(100);}
        return bookList;
    }

    public static class CheckLinkThread implements Runnable{ // checkLink 속도를 개선해주기 위한 thread
        private JSONObject obj;
        private List<Book> bookList;

        public CheckLinkThread(JSONObject obj , List<Book> bookList){
            this.obj = obj;
            this.bookList = bookList;
        }

        @Override
        public void run(){
            NaverApiService naverApiService = new NaverApiService();
            Book tempBook = new Book();
            tempBook.setTitle(obj.getString("title").replace("<b>", "").replace("</b>" , ""));
            tempBook.setAuthor(obj.getString("author").replace("<b>", "").replace("</b>" , ""));
            tempBook.setPublisher(obj.getString("publisher").replace("<b>", "").replace("</b>" , ""));
            tempBook.setPubdate(obj.getString("pubdate").replace("<b>", "").replace("</b>" , ""));
            tempBook.setIsbn(obj.getString("isbn").replace("<b>", "").replace("</b>" , ""));
            if(!obj.getString("image").equals("") && naverApiService.checkLink(obj.getString("image"))){
                tempBook.setImage(obj.getString("image").replace("<b>", "").replace("</b>" , ""));
            }
            else{
                tempBook.setImage(null);
            }
            tempBook.setDescription(obj.getString("description").replace("<b>", "").replace("</b>" , ""));
            bookList.add(tempBook);
        }
    }

}
