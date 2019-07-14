package com.zhiyong.PolyglotAPI.core;

import com.zhiyong.PolyglotAPI.api.Article;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements ResultSetMapper<Article> {
    @Override
    public Article map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Article(
                resultSet.getString("title"), resultSet.getString("url"),
                resultSet.getString("word"), resultSet.getString("language"),
                resultSet.getInt("know")
        );
    }
}
