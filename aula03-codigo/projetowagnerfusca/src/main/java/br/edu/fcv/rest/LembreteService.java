package br.edu.fcv.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.fcv.dao.LembreteDao;
import br.edu.fcv.model.ApiLembrete;
import br.edu.fcv.model.Lembrete;

@Path("/lembrete")
public class LembreteService {
	private LembreteDao dao = new LembreteDao();

	@GET
	@Path("/insert/{texto}")
	public Response criaLembrete(@PathParam("texto") String texto) {
		Lembrete lembrete = new Lembrete();
		lembrete.setDescricao(texto);
		dao.save(lembrete);
		return Response.status(200).entity("SALVOU!!!!").build();
	}

	@GET
	@Path("/listar")
	public Response listarLembretes() {
		List<Lembrete> list = dao.list();

		String result = "Quantidade de lembretes : " + list.size();
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/listajson")
	@Produces("application/json")
	public ApiLembrete getListaLembretes() {
		List<Lembrete> novaLista = new ArrayList<Lembrete>();
		for (Lembrete lembrete : dao.list()) {
			Lembrete novoLembrete = new Lembrete();
			novoLembrete.setId(lembrete.getId());
			novoLembrete.setDescricao(lembrete.getDescricao());
			novaLista.add(novoLembrete);
		}

		ApiLembrete apiResponse = new ApiLembrete();
		apiResponse.setLembrete(novaLista);

		return apiResponse;

	}

}
