package com.zhiyong.PolyglotAPI.db;

import com.zhiyong.PolyglotAPI.api.Article;
import com.zhiyong.PolyglotAPI.core.ArticleMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ArticleDAO {
    @SqlQuery("select title, url, word, language, know from word_latest where user_id = :userId and language = :language")
    @Mapper(ArticleMapper.class)
    List<Article> get(@Bind("userId") int userId, @Bind("language") String language);
}
