package org.jzl.android.recyclerview.modules;

import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.core.IContext;
import org.jzl.android.recyclerview.core.IDataBinder;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.model.Book;
import org.jzl.android.recyclerview.util.DateUtils;

public class BookBinder implements IDataBinder<Book, DefaultViewHolder> {

    @Override
    public void binding(IContext context, DefaultViewHolder viewHolder, Book data) {
        context.getViewBinder()
                .linkify(R.id.tv_link)
                .setText(R.id.tv_name, data.getTitle())
                .setText(R.id.tv_subtitle, data.getSubtitle())
                .setText(R.id.tv_link, data.getLink())
                .setText(R.id.tv_refresh_time, DateUtils.yyyy_MM_dd.format(data.getRefreshTime()))
                .setImageResource(R.id.iv_book_icon, data.getIconUrl());
    }
}
