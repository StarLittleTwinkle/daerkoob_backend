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
        Optional<Book> findByIsbn = bookRepository.findByIsbn(book.getIsbn());
        Book resultBook = findByIsbn.orElse(null);
        if(resultBook == null){
            bookRepository.save(book);
        }
    }

    public List<Book> returnBook(String title , String display) throws Exception {
        NaverApiService naverApiService = new NaverApiService();
        List<Book> bookList = new ArrayList<Book>();
        String id = "FZrwDPKOBRfo1BlN5tFY";
        String secret = "pBFNa86Va1";
        String url = URLEncoder.encode(title, "UTF-8") + ("&display=" + display + "&start=1");
        String response = naverApiService.search(id, secret, url);

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
            tempBook.setImage(obj.getString("image"));
            tempBook.setGood(0L);
            tempBook.setStar(0D);
            bookList.add(tempBook);
        }
        return bookList;
    }
}
