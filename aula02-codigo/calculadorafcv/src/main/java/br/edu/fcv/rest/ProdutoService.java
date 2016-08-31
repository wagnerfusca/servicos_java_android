package br.edu.fcv.rest;

import javax.annotation.Priority;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.fcv.model.Produto;

@Path("/produto")
public class ProdutoService {

	@GET
	@Path("exibeok")
	public Response metodoQueExibeOk() {
		return Response.status(200).entity("ok").build();
	}

	@GET
	@Path("json")
	@Produces("application/json")
	public Produto retornaJson() {
		Produto produto = new Produto();
		produto.setNome("Notebook");
		produto.setMarca("Acer");
		produto.setCodigo(1235);
		return produto;
	}

	@POST
	@Path("jsonpost")
	@Consumes("application/json")
	public Response salvarNoBancoDeDados(Produto produto) {

		return Response.status(200)
				.entity("Salvo Com sucesso. " + produto.getNome()).build();
	}

}
