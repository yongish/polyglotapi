package com.zhiyong.PolyglotAPI.resources;

import com.google.inject.Inject;
import com.zhiyong.PolyglotAPI.api.Entry;
import com.zhiyong.PolyglotAPI.db.WordDAO;

import javax.ws.rs.*;

@Path("/word/{userId}")
public class WordResource {
    private final WordDAO wordDAO;

    @Inject
    public WordResource(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }

    @GET
    @Path("/{language}")
    public void get(@PathParam("userId") int userId, @PathParam("language") String language) {
        wordDAO.get(userId, language);
    }

    @PUT
    public void put(@PathParam("userId") int userId, Entry word) {
        wordDAO.put(userId, word.getTitle(), word.getUrl(), word.getWord(), word.getPinyin(), word.getLanguage(),
                word.getKnow());
    }

    @DELETE
    @Path("/{language}/{word}")
    public void delete(@PathParam("userId") int userId, @PathParam("word") String word,
                       @PathParam("language") String language) {
        wordDAO.delete(userId, word, language);
    }
}
