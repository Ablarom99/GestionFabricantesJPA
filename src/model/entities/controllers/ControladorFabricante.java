package model.entities.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entities.Coche;
import model.entities.Fabricante;

public class ControladorFabricante {

	private static ControladorFabricante instance = null;
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionFabricantesJPA"); 
	
	/**
	 * 
	 * @return
	 */
	public static ControladorFabricante getInstance () {
		if (instance == null) {
			instance = new ControladorFabricante();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	public ControladorFabricante() {
	}
	
	
	public Fabricante find(int id) {
		Fabricante f = null;
		EntityManager em = factory.createEntityManager();
		f = (Fabricante) em.find(Fabricante.class, id);
		em.close();
		return f;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Fabricante findPrimero () {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante order by id limit 1", Fabricante.class);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Fabricante findUltimo () {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante order by id desc limit 1", Fabricante.class);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Fabricante findSiguiente (int idActual) {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante where id > ? order by id limit 1", Fabricante.class);
		q.setParameter(1, idActual);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Fabricante findAnterior (int idActual) {
		Fabricante c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.fabricante where id < ? order by id desc limit 1", Fabricante.class);
		q.setParameter(1, idActual);
		c = (Fabricante) q.getSingleResult();
		em.close();
		
		return c;		
	}
	

	
	/**
	 * 
	 * @return
	 */
	public boolean guardar (Fabricante f) {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			if (f.getId() == 0) {
				em.persist(f);
			}
			else {
				em.merge(f);
			}
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void borrar(Fabricante f) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		f=em.merge(f);
		em.remove(f);
		em.getTransaction().commit();
		em.close();
	}

	
	/**
	 * 
	 * @return
	 */
	public List<Fabricante> findAll () {
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM fabricante", Fabricante.class);
		
		List<Fabricante> list = (List<Fabricante>) q.getResultList();
		em.close();
		return list;
	}
	

}
