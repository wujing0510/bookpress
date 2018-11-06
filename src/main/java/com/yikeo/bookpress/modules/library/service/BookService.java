package com.yikeo.bookpress.modules.library.service;

import com.yikeo.bookpress.modules.library.model.Book;

import java.util.List;

/**
 * @author WuJing
 * @since 2018-10-28
 */
public interface BookService {
    void saveAll(List<Book> allBooks);
}
