package com.zhiyong.PolyglotAPI.core;

import com.zhiyong.PolyglotAPI.api.Word;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WordMapper implements ResultSetMapper<Word> {
    @Override
    public Word map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Word(resultSet.getString("word"), resultSet.getString("pinyin"),
                resultSet.getInt("know"), resultSet.getString("url"),
                resultSet.getString("title"));
    }
}
