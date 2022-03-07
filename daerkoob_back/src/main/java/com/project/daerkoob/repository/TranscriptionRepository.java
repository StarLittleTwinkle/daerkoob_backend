package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.daerkoob.domain.Book;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TranscriptionRepository extends JpaRepository<Transcription , Long> {
    long count();

    Page<Transcription> findByBook(Book book , Pageable pageable);

    default List<Transcription> findByBook(Pagination pagination){
        Page<Transcription> page = this.findByBook((Book)pagination.getId() , PageRequest.of(pagination.getPageNumber() - 1, pagination.getPageSize() , Sort.Direction.ASC , "registerDate"));
        pagination.setTotalRecordCount((int)page.getTotalElements());
        return page.getContent();
    }
    List<Transcription> findByUser(User user);

    List<Transcription> findTop8ByOrderByRegisterDateDesc();

    List<Transcription> findByUserAndRegisterDateBetweenOrderByRegisterDateAsc(User user , LocalDateTime registerDate1 , LocalDateTime registerDate2);

    Page<Transcription> findByBookIdOrderByRegisterDateDesc(Long bookId , Pageable pageable);
}
