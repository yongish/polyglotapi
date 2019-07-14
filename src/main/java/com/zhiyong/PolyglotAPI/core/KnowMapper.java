package com.zhiyong.PolyglotAPI.core;

import com.zhiyong.PolyglotAPI.api.Know;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KnowMapper implements ResultSetMapper<Know> {
    @Override
    public Know map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Know(resultSet.getString("word"), resultSet.getInt("know"));
    }
}
