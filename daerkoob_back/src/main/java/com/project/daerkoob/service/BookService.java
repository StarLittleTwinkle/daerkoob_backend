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

    public void save(Book book) throws Exception{
        if(!bookRepository.existsByIsbn(book.getIsbn())){
            bookRepository.save(createBook(book.getIsbn()));
        }
        else{
            Optional<Book> findByIsbn = bookRepository.findByIsbn(book.getIsbn());
            Book resultBook = findByIsbn.get();
            resultBook.setTranscriptionCount(resultBook.getTranscriptionCount() + 1);
            bookRepository.save(resultBook);
        }
    }

    public boolean existsBook(String isbn){
        return bookRepository.existsByIsbn(isbn);
    }

    public Optional<Book> findBook(String isbn){
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        return book;
    }
    public Long getBookId(String isbn){
        Optional<Book> findByIsbn= bookRepository.findByIsbn(isbn);
        return findByIsbn.get().getId();
    }

    public Book createBook(String isbn) throws Exception{
        List<Book> bookList = getBook(isbn , "1");
        Book book = new Book();
        for(Book tempBook : bookList) {
            book.setTitle(tempBook.getTitle());
            book.setAuthor(tempBook.getAuthor());
            book.setPublisher(tempBook.getPublisher());
            book.setPubdate(tempBook.getPubdate());
            book.setIsbn(isbn);
            book.setImage(tempBook.getImage());
            book.setDescription(tempBook.getDescription());
            book.setTranscriptionCount(1L); //필사를 달면서 만드니까 1로 초기화
            book.setReviewCount(1L);
            book.setStar(0D);
            book.setStarCount(0L);
        }
        return book;
    }

    public List<Book> getBook(String title , String display) throws Exception {
        NaverApiService naverApiService = new NaverApiService();
        List<Book> bookList = new ArrayList<Book>();
        String id = "FZrwDPKOBRfo1BlN5tFY";
        String secret = "pBFNa86Va1";
//        String id = "lmnnx8RRbuqvKHD3QC_X";
//        String secret = "SFUhgfJCLl";
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
