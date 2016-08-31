package br.edu.fcv.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.fcv.dao.LembreteDao;
import br.edu.fcv.model.Lembrete;

@Path("/lembrete/")
public class LembreteRest {
	LembreteDao dao = new LembreteDao();

	@GET
	@Path("salvar/{param}")
	public Response salvarLembrete(@PathParam("param") String descricao) {
		Lembrete lembrete = new Lembrete();
		lembrete.setDescricao(descricao);

		dao.save(lembrete);

		return Response.status(200).entity("ok").build();
	}

	@GET
	@Path("listar")
	public Response listarLembretes() {
		List<Lembrete> list = dao.list();

		String result = "Quantidade de lembretes : " + list.size();
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("listajson")
	@Produces("application/json")
	public List<Lembrete> getListaLembretes(){
		return dao.list();
	}


}
