package model.entities.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entities.Coche;
import model.entities.Concesionario;

public class ControladorCoche {

	private static ControladorCoche instance = null;

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionFabricantesJPA"); 
	
	/**
	 * 
	 * @return
	 */
	public static ControladorCoche getInstance () {
		if (instance == null) {
			instance = new ControladorCoche();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	public ControladorCoche() {
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Coche findPrimero () {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche order by id limit 1", Coche.class);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Coche findUltimo () {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche order by id desc limit 1", Coche.class);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Coche findSiguiente (int idActual) {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche where id > ? order by id limit 1", Coche.class);
		q.setParameter(1, idActual);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;
	}
	

	/**
	 * 
	 * @return
	 */
	public Coche findAnterior (int idActual) {
		Coche c = null;
		
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from tutorialjavacoches.coche where id < ? order by id desc limit 1", Coche.class);
		q.setParameter(1, idActual);
		c = (Coche) q.getSingleResult();
		em.close();
		
		return c;		
	}
	

	
	/**
	 * 
	 * @return
	 */
	public boolean guardar (Coche c) {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			if (c.getId() == 0) {
				em.persist(c);
			}
			else {
				em.merge(c);
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
	public void borrar(Coche c) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		c=em.merge(c);
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
	public List<Coche> findAll () {
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM coche", Coche.class);
		
		List<Coche> list = (List<Coche>) q.getResultList();
		em.close();
		return list;
	}
	
	

}
