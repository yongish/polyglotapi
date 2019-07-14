package com.zhiyong.PolyglotAPI.resources;

import com.google.inject.Inject;
import com.zhiyong.PolyglotAPI.api.Article;
import com.zhiyong.PolyglotAPI.db.ArticleDAO;

import javax.ws.rs.*;
import java.util.List;

@Path("/article")
public class ArticleResource {
    private final ArticleDAO articleDAO;

    @Inject
    public ArticleResource(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GET
    @Path("/{userId}/{language}")
    @Produces("application/json")
    public List<Article> get(@PathParam("userId") int userId, @PathParam("language") String language) {
        return articleDAO.get(userId, language);
    }
}
