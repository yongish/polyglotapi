package com.zhiyong.PolyglotAPI.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;

import java.time.Instant;

public abstract class WordDAO {
    @SqlUpdate("insert into word values (:ts, :userId, :url, :title, :word, :language, :know)")
    abstract void insertWord(@Bind("ts") long ts, @Bind("userId") int userId, @Bind("url") String url,
                             @Bind("title") String title, @Bind("word") String word, @Bind("language") String language,
                             @Bind("know") int know);

    @SqlQuery("select count(1) from word where userId = :userId and word = :word")
    abstract int get1Word(@Bind("userId") int userId, @Bind("word") String word);

    @SqlUpdate("insert into word_latest values (:ts, :userId, :url, :title, :word, :language, :know)")
    abstract void insertWordLatest(@Bind("ts") long ts, @Bind("userId") int userId, @Bind("url") String url,
                             @Bind("title") String title, @Bind("word") String word, @Bind("language") String language,
                             @Bind("know") int know);

    @SqlUpdate("update word_latest set ts = :ts, know = :know " +
            "where user_id = :userId and word = :word and language = :language")
    abstract void updateWordLatest(@Bind("ts") long ts, @Bind("userId") int userId, @Bind("word") String word,
                                   @Bind("language") String language, @Bind("know") int know);

    @Transaction
    public void put(@Bind("userId") int userId, @Bind("title") String title, @Bind("url") String url,
             @Bind("word") String word, @Bind("language") String language, @Bind("know") int know) {
        long unixTimestamp = Instant.now().getEpochSecond();
        insertWord(unixTimestamp, userId, url, title, word, language, know);
        if (get1Word(userId, word) > 0) {
            insertWordLatest(unixTimestamp, userId, url, title, word, language, know);
        } else {
            updateWordLatest(unixTimestamp, userId, word, language, know);
        }
    }

    @SqlUpdate("delete from word_latest where user_id = :userId and word = :word and language = :language")
    public abstract void delete(@Bind("userId") int userId, @Bind("word") String word,
                                @Bind("language") String language);
}
