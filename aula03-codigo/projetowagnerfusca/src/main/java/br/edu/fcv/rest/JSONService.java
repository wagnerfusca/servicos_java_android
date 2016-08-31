package br.edu.fcv.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.fcv.model.ApiResponse;
import br.edu.fcv.model.Produto;

@Path("/json")
public class JSONService {

	@GET
	@Path("/get")
	@Produces("application/json")
	public Produto getProdutoJSON() {

		Produto produto = new Produto();
		produto.setNome("Moto X - Android");
		produto.setQuantidade(10);

		return produto;

	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response criarProdutoJSON(Produto produto) {

		String result = "Product created : " + produto.toString();
		return Response.status(201).entity(result).build();

	}

	@GET
	@Path("/listajson")
	@Produces("application/json")
	public ApiResponse getListaProdutos() {
		List<Produto> novaLista = new ArrayList<Produto>();

		Produto produto = new Produto();
		produto.setNome("Moto X - Android");
		produto.setQuantidade(10);
		novaLista.add(produto);

		Produto produto2 = new Produto();
		produto2.setNome("Moto G - Android");
		produto2.setQuantidade(20);
		novaLista.add(produto2);

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setProdutos(novaLista);

		return apiResponse;

	}

}
