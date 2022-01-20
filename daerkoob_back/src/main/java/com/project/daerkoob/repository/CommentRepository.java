package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> findByReview(Review review);
    Long countByComment(Comment comment);
    Page<Comment> findByReview(Review review , Pageable pageable);
    default List<Comment> findByReview(Pagination pagination){
        /*
        pagination 을 이용해서 paging 된 정보를 받고
        그 다음에 list로 모든 레코드들을 받아서 totalCount를 계산해서 pagination 에다가 채워서 반환한다.
         */
        Page<Comment> page = this.findByReview((Review)pagination.getId(), PageRequest.of(pagination.getPageNumber() - 1, pagination.getPageSize(), Sort.Direction.ASC, "registerDate"));
        List<Comment> list = this.findByReview((Review)pagination.getId());
        int totalCount = 0;
        for(Comment comment : list){
            totalCount++;
            totalCount += this.countByComment(comment);
        }
        pagination.setTotalRecordCount(totalCount);
        return page.getContent();
    }
}
