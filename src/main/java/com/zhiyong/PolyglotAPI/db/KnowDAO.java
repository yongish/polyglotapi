package com.zhiyong.PolyglotAPI.db;

import com.zhiyong.PolyglotAPI.api.Know;
import com.zhiyong.PolyglotAPI.core.KnowMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface KnowDAO {
    @SqlQuery("select word, know from word_latest " +
            "where user_id = :userId and word in (:words)")
    @Mapper(KnowMapper.class)
    List<Know> post(@Bind("userId") int userId, @Bind("words") List<String> words);
}
