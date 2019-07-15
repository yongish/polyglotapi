package com.zhiyong.PolyglotAPI.db;

import com.zhiyong.PolyglotAPI.api.Word;
import com.zhiyong.PolyglotAPI.core.WordMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.time.Instant;
import java.util.List;

public abstract class WordDAO {
    @SqlQuery("select w.word, pinyin, know, url, title " +
            "from word_latest w left join article a on w.user_id = a.user_id and w.word = a.word " +
            "where w.user_id = :userId and language = :language")
    @Mapper(WordMapper.class)
    public abstract List<Word> get(@Bind("userId") int userId, @Bind("language") String language);

    @SqlUpdate("insert into word values (:ts, :userId, :word, :pinyin, :language, :know)")
    abstract void insertWord(@Bind("ts") long ts, @Bind("userId") int userId, @Bind("url") String url,
                             @Bind("title") String title, @Bind("word") String word, @Bind("pinyin") String pinyin,
                             @Bind("language") String language, @Bind("know") int know);

    @SqlUpdate("replace into word_latest values (:ts, :userId, :word, :pinyin, :language, :know)")
    abstract void insertWordLatest(
            @Bind("ts") long ts, @Bind("userId") int userId, @Bind("url") String url, @Bind("title") String title,
            @Bind("word") String word, @Bind("pinyin") String pinyin, @Bind("language") String language,
            @Bind("know") int know);

    // Accesses Entry table, but place here because it this insertion may occur when a word is inserted.
    @SqlUpdate("insert ignore into article values (:ts, :userId, word, url, title)")
    abstract void insertArticle(@Bind("ts") long ts, @Bind("userId") int userId, @Bind("String") String word,
                                @Bind("url") String url, @Bind("title") String title);

    @Transaction
    public void put(@Bind("userId") int userId, @Bind("title") String title, @Bind("url") String url,
             @Bind("word") String word, @Bind("pinyin") String pinyin, @Bind("language") String language,
                    @Bind("know") int know) {
        long unixTimestamp = Instant.now().getEpochSecond();
        insertWord(unixTimestamp, userId, url, title, word, pinyin, language, know);
        insertWordLatest(unixTimestamp, userId, url, title, word, pinyin, language, know);
        if (title != null && url != null && !title.isEmpty() && !url.isEmpty()) {
            insertArticle(unixTimestamp, userId, word, url, title);
        }
    }

    @SqlUpdate("delete from word_latest where user_id = :userId and word = :word and language = :language")
    public abstract void delete(@Bind("userId") int userId, @Bind("word") String word,
                                @Bind("language") String language);
}
