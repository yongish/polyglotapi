package com.zhiyong.PolyglotAPI.resources;

import com.google.inject.Inject;
import com.zhiyong.PolyglotAPI.db.KnowDAO;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/know")
public class KnowResource {
    private final KnowDAO knowDAO;

    @Inject
    public KnowResource(KnowDAO knowDAO) {
        this.knowDAO = knowDAO;
    }

    @GET
    @Produces({ "application/json" })
    public List<String> get() throws NotFoundException {
        return knowDAO.get();
    }
}
