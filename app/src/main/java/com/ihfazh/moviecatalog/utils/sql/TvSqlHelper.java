package com.ihfazh.moviecatalog.utils.sql;

import androidx.sqlite.db.SimpleSQLiteQuery;

public class TvSqlHelper {
    public static SimpleSQLiteQuery getListBookmarked(Sort sort) {

        StringBuilder builder = new StringBuilder();
        builder.append("select * from tv_show where bookmarked = 1 ");

        switch (sort){
            case ASC:
                builder.append("order by title ASC");
                break;
            case DESC:
                builder.append("order by title DESC");
                break;
            case RANDOM:
                builder.append("order by RANDOM()");
                break;
        }

        return new SimpleSQLiteQuery(builder.toString());
    }
}
