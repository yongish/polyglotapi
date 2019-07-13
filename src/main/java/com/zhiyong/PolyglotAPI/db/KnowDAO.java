package com.zhiyong.PolyglotAPI.db;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.List;

public interface KnowDAO {
    // todo: filter.
    @SqlQuery("select word from word")
    List<String> get();
}
