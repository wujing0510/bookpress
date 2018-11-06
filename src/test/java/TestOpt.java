import com.yikeo.bookpress.modules.library.model.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WuJing
 * @since 2018-10-28
 */
public class TestOpt {

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws Exception {
        String parentPath = "/Users/wujing/Desktop/乱78糟/opf";
        File file = new File(parentPath);
        String[] fileNames = file.list();
        int maxLength = 0;
        for (String fileName : fileNames) {
            Book book = getBook(parentPath, fileName);
            if(book.getTitle() != null) {
                int length = book.getTitle().length();
                if(length > maxLength) {
                    maxLength = length;
                }
            }
            System.out.println(book);
        }

        System.out.println(maxLength);
    }

    public static void doGetKeys(String parentPath, String fileName, Set<String> keys) throws Exception {

        File file = new File(parentPath + "/" + fileName);
        Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");

        Elements metadatas = doc.select("metadata");
        if (metadatas != null && metadatas.size() > 0) {
            Elements dcs = metadatas.get(0).children();
            if (dcs != null && dcs.size() > 0) {
                for (Element dc : dcs) {
                    String tagName = dc.tagName();
                    if (tagName.startsWith("dc:")) {
                        tagName = tagName.replace("dc:", "");
                        keys.add(tagName);
                    }
                }
            }
        }
    }

    public static Book getBook(String parentPath, String fileName) throws Exception {

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
