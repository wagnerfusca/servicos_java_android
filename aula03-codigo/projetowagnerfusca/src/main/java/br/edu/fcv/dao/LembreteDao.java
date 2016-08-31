package br.edu.fcv.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.fcv.model.Lembrete;

public class LembreteDao extends DAO{

	public void save(Lembrete lembrete){
		EntityManager manager = getEntityManager();

		manager.getTransaction().begin();
		manager.persist(lembrete);
		manager.getTransaction().commit();
	}

	public List<Lembrete> list(){
		EntityManager manager = getEntityManager();

		return manager.createQuery("from Lembrete").getResultList();
	}







}
