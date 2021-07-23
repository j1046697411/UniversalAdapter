package org.jzl.android.recyclerview.util;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.model.Book;
import org.jzl.android.recyclerview.model.UniversalModel;

import java.util.Collection;
import java.util.Date;

public class BookUtils {

    public static Book randomBook() {
        Book book = new Book();
        book.setIconUrl(R.drawable.ic_launcher_background);
        book.setTitle("UniversalAdapter");
        book.setSubtitle("UniversalAdapter");
        book.setLink("https://www.jzlblog.cn/");
        book.setRefreshTime(new Date());
        return book;
    }

    public static void randomFillingBooks(Collection<Book> books, int size) {
        for (int i = 0; i < size; i++) {
            books.add(randomBook());
        }
    }

    public static void randomFilling(Collection<UniversalModel> universalModels, int size){
        for (int i = 0; i < size; i ++){
            universalModels.add(UniversalModel.build(randomBook())
                    .setId(i)
                    .setItemViewType(1)
                    .build());
        }
    }

}
