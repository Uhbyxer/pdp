package com.pdp.etag.controller;



import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.pdp.etag.dao.UserDao;
import com.pdp.etag.dao.stub.UserDaoStub;

@Path("/user-service")
public class UserController {
	
	private UserDao userDao = new UserDaoStub();
	
	@GET
	@Path("/users/{id}")
	public Response getUserById(@PathParam("id") int id, @Context Request req, @Context HttpHeaders headers) {
		CacheControl cc = new CacheControl();
		cc.setMaxAge(86400);

		Response.ResponseBuilder rb = null;

		EntityTag etag = new EntityTag(userDao.getLastModifiedById(id).hashCode() + "");
		rb = req.evaluatePreconditions(etag);

		if (rb != null) {
			System.out.println("get from cache");
			System.out.println("headers:");
			System.out.println("if-none-match = " + headers.getRequestHeaders().getFirst("if-none-match"));

			return rb.cacheControl(cc).tag(etag).build();
		}

		System.out.println("get new");
		rb = Response.ok(userDao.getById(id)).cacheControl(cc).tag(etag);
		return rb.build();
	}

	@PUT
	@Path("/users/{id}")
	public Response updateUserById(@PathParam("id") int id) {
		userDao.update(id);
		return Response.status(200).build();
	}
}
