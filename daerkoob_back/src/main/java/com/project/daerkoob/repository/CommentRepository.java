package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> findByReview(Review review);
//    Page<Comment> findByReview(Review review , Pageable pageable);
//
//    public default List<Comment> findByReview(Review review , Pagination pagination){
//        if (pagination.get() == 0) return findAll(pagination);
//        Page<Student> page = this.findByDepartmentId(pagination.getDi(), PageRequest.of(pagination.getPg() - 1, pagination.getSz(), Sort.Direction.ASC, "id"));
//        pagination.setRecordCount((int)page.getTotalElements());
//        return page.getContent();
//    }
}
