package org.dieschnittstelle.ess.jrs;

import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/touchpoints")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface ITouchpointCRUDService {


	// Get //touchpoint -> Liste von TP in JSON Format
	@GET
	List<StationaryTouchpoint> readAllTouchpoints();

	// Get /touchpoint/${id} -> ein TP in JSON Format
	@GET
	@Path("/{id}")
	StationaryTouchpoint readTouchpoint(@PathParam("id") long id);

	// POST /touchpoint mit TP als JSON in Request Body -> TP in JSON Format
	@POST
	StationaryTouchpoint createTouchpoint(StationaryTouchpoint touchpoint);

	//DELETE /touchpoint/${id} -> boolean Wert
	@DELETE
	@Path("/{id}")
	boolean deleteTouchpoint(@PathParam("id") long id);
		
	/*
	 * TODO JRS1: add a new annotated method for using the updateTouchpoint functionality of TouchpointCRUDExecutor and implement it
	 */

}
