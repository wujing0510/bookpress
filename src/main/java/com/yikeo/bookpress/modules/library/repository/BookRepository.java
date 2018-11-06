package com.yikeo.bookpress.modules.library.repository;

import com.yikeo.bookpress.modules.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author WuJing
 * @since 2018-10-28
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
