package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.daerkoob.domain.Book;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
    List<Review> findTop8ByOrderByRegisterDateDesc();
    Page<Review> findByBook(Book book , Pageable pageable);
    default List<Review> findByBook(Pagination pagination){
        Page<Review> page = this.findByBook((Book)pagination.getId() , PageRequest.of(pagination.getPageNumber() - 1 , pagination.getPageSize() , Sort.Direction.ASC , "registerDate"));
        //pagination의 속성들을 Integer로 선언하는 것도 중요하다 PageRequest.of method 에 받는 형식이 int이기 때문이다.
        pagination.setTotalRecordCount((int)page.getTotalElements());
        return page.getContent();
    }
    long count();
    long countByBook(Book book);
    boolean existsByBookId(Long bookId);

    Page<Review> findByBookIdOrderByRegisterDateDesc(Long bookId , Pageable pageable);
}
