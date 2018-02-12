package com.okta.example.server.resources;

import com.okta.example.server.dao.TieCraftDao;
import com.okta.example.server.model.TieCraft;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * Tie Craft Resource.
 */
@Api(value = "TIE Craft")
@Path("/tie")
@Produces("application/json")
public class TieResource {

    private final TieCraftDao tieCraftDao;

    @Inject
    public TieResource(TieCraftDao tieCraftDao) {
        this.tieCraftDao = tieCraftDao;
    }

    /**
     * Returns a Collection of all TieCraft.
     * @return Returns a Collection of all TieCraft.
     */
    @GET
    public Collection<TieCraft> listTieCraft() {
        return tieCraftDao.listTieCrafts();
    }

    @Path("/{serial}")
    @GET
    public TieCraft getTieCraft(@PathParam("serial") String serial) {
    TieCraft tieCraft = tieCraftDao.getTieCraft(serial);
        if (tieCraft == null) {
            throw new NotFoundException();
        }
        return tieCraft;
    }
}
