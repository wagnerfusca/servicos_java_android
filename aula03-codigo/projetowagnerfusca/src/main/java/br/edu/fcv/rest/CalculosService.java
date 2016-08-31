package br.edu.fcv.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/calculo")
public class CalculosService {

	@GET
	@Path("/impar/{valor1}")
	public Response verificaSeEhImpar(@PathParam("valor1")
		Integer valor) {
		Integer resto = valor % 2;
		if (resto == 0) {
			return Response.status(200).entity("Eh Par").build();
		}
		return Response.status(200).entity("Eh Impar").build();
	}















}
