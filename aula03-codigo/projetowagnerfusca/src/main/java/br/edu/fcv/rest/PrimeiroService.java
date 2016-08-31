package br.edu.fcv.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/primeiro")
public class PrimeiroService {

	@GET
	@Path("/exemplo")
	public Response primeiroMetodo() {
		return Response.status(200).entity("DEU CERTO!!!").build();
	}

	@GET
	@Path("/exercicio/{parametro}/{parametro2}")
	public Response exercicio(
			@PathParam("parametro") String parametro,
			@PathParam("parametro2") String parametro2) {
		String retorno = "O texto digitado foi: " 
			+ parametro + " e " + parametro2;
		return Response.status(200).entity(retorno).build();
	}
	
	@POST
	@Path("/exercicioPost/{parametro}/{parametro2}")
	public Response exercicioPost(
			@PathParam("parametro") String parametro,
			@PathParam("parametro2") String parametro2) {
		String retorno = "O texto digitado foi: " 
			+ parametro + " e " + parametro2;
		return Response.status(200).entity(retorno).build();
	}
	
	
	
	
	
	
	
	

}
