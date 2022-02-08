package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Notice;
import com.project.daerkoob.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findAllByOrderByRegisterDateDesc(Pageable pageable);
    default List<Notice> findAllByOrderByRegisterDateDesc(Pagination pagination){
        Page<Notice> page = findAllByOrderByRegisterDateDesc(PageRequest.of(pagination.getPageNumber() - 1 , pagination.getPageSize() , Sort.Direction.DESC , "registerDate"));
        pagination.setTotalRecordCount((int)page.getTotalElements());
        return page.getContent();

    }
}
