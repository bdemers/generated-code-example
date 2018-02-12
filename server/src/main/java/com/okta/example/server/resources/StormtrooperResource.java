package com.okta.example.server.resources;

import com.okta.example.server.dao.StormtrooperDao;
import com.okta.example.server.model.Stormtrooper;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * Stormtrooper Resource.
 */
@Api(value = "Stormtrooper", tags = {""})
@Path("/troopers")
@Produces("application/json")
public class StormtrooperResource {

    private final StormtrooperDao trooperDao;

    @Inject
    public StormtrooperResource(StormtrooperDao trooperDao) {
        this.trooperDao = trooperDao;
    }

    /**
     * Returns a Collection of all Stormtroopers.
     * @return Returns a Collection of all Stormtroopers.
     */
    @GET
    public Collection<Stormtrooper> listTroopers() {
        return trooperDao.listStormtroopers();
    }

    @Path("/{id}")
    @GET
    public Stormtrooper getTrooper(@PathParam("id") String id) {

        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
        if (stormtrooper == null) {
            throw new NotFoundException();
        }
        return stormtrooper;
    }

    @POST
    public Stormtrooper createTrooper(Stormtrooper trooper) {

        return trooperDao.addStormtrooper(trooper);
    }

    @Path("/{id}")
    @POST
    public Stormtrooper updateTrooper(@PathParam("id") String id, Stormtrooper updatedTrooper) {

        return trooperDao.updateStormtrooper(id, updatedTrooper);
    }


    @Path("/{id}")
    @DELETE
    public void deleteTrooper(@PathParam("id") String id) {
        trooperDao.deleteStormtrooper(id);
    }


}
