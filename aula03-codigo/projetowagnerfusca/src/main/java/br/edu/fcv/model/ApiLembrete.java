package br.edu.fcv.model;

import java.util.ArrayList;
import java.util.List;

public class ApiLembrete {
	private List<Lembrete> lembrete = new ArrayList<Lembrete>();

	public List<Lembrete> getLembrete() {
		return lembrete;
	}

	public void setLembrete(List<Lembrete> lembrete) {
		this.lembrete = lembrete;
	}

}
