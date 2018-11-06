package com.yikeo.bookpress.modules.library.service.impl;

import com.yikeo.bookpress.modules.library.model.Book;
import com.yikeo.bookpress.modules.library.repository.BookRepository;
import com.yikeo.bookpress.modules.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuJing
 * @since 2018-10-28
 */

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public void saveAll(List<Book> allBooks) {
        bookRepository.saveAll(allBooks);
    }
}
