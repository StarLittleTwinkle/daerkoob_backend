package com.project.daerkoob.repository;

import org.springframework.data.repository.CrudRepository;
import com.project.daerkoob.domain.*;

public interface BookRepository extends CrudRepository<Book, Long> {
}
