package br.edu.fcv.model;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
	private List<Produto> produtos = new ArrayList<Produto>();

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
