package com.yikeo.bookpress.modules.library.web;

import com.yikeo.bookpress.modules.library.model.Book;
import com.yikeo.bookpress.modules.library.service.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author WuJing
 * @since 2018-08-09
 */

@RestController("library.rest.book")
@RequestMapping("/api/book")
public class BookRestController {

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Autowired
    BookService bookService;

    @RequestMapping("list")
    public List<Book> list(Model model) throws Exception {

        String parentPath = "/Users/wujing/Desktop/乱78糟/opf";
        File file = new File(parentPath);
        String[] fileNames = file.list();
        int maxLength = 0;
        List<Book> allBooks = new LinkedList<Book>();
        for (String fileName : fileNames) {
            Book book = doGetBook(parentPath, fileName);
            if(book.getTitle() != null) {
                allBooks.add(book);
            }
        }

        bookService.saveAll(allBooks);

        return null;
    }

    public Book doGetBook(String parentPath, String fileName) throws Exception {

        File file = new File(parentPath + "/" + fileName);
        Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");

        Book book = new Book();

        Elements metadatas = doc.select("metadata");
        if (metadatas != null && metadatas.size() > 0) {
            Elements dcs = metadatas.get(0).children();
            if (dcs != null && dcs.size() > 0) {
                for (Element dc : dcs) {
                    String tagName = dc.tagName();
                    if (tagName.startsWith("dc:")) {
                        tagName = tagName.replace("dc:", "");
                        if ("date".equals(tagName)) {
                            String sDate = dc.text();
                            if(sDate != null) {
                                sDate = sDate.replace("T"," ");
                                sDate = sDate.replace("+00:00","");
                                book.setDate(sdf.parse(sDate));
                            }
                        } else if ("creator".equals(tagName)) {
                            book.setAuthor(dc.text());
                        } else if ("subject".equals(tagName)) {
                            book.setSubject(dc.text());
                        } else if ("publisher".equals(tagName)) {
                            book.setPublisher(dc.text());
                        } else if ("description".equals(tagName)) {
                            book.setDescription(dc.text());
                        } else if ("language".equals(tagName)) {
                            String language = dc.text();
                            if(language !=null) {
                                if("zho".equals(language)) {
                                    language = "zh_CN";
                                } else if("eng".equals(language)) {
                                    language = "en_US";
                                } else if("jpn".equals(language)) {
                                    language = "ja-JP";
                                } else {
                                    throw new RuntimeException(language);
                                }
                            }
                            book.setLanguage(language);
                        } else if ("title".equals(tagName)) {
                            book.setTitle(dc.text());
                        }
                    }
                }
            }

        }

        return book;
    }

}
