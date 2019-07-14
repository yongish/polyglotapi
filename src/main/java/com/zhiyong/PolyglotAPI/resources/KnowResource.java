package com.zhiyong.PolyglotAPI.resources;

import com.google.inject.Inject;
import com.zhiyong.PolyglotAPI.api.Know;
import com.zhiyong.PolyglotAPI.db.KnowDAO;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.*;
import java.util.stream.Collectors;

@Path("/know")
public class KnowResource {
    private final KnowDAO knowDAO;

    @Inject
    public KnowResource(KnowDAO knowDAO) {
        this.knowDAO = knowDAO;
    }

    @POST
    @Path("/{userId}")
    @Consumes({ "application/json "})
    @Produces({ "application/json" })
    public List<Know> post(@PathParam("userId") int userId, @NotNull List<String> words)
            throws NotFoundException {
        List<Know> queryResult = knowDAO.post(userId, words);
        Set<String> resultWords = queryResult.stream().map(Know::getWord).collect(Collectors.toSet());
        Set<String> wordSet = new HashSet<>(words);
        wordSet.removeAll(resultWords);
        wordSet.forEach(word -> queryResult.add(new Know(word, 0)));
        return queryResult;
    }
}
