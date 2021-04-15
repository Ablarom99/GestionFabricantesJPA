package model.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Principal {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionFabricantesJPA");
		EntityManager em = factory.createEntityManager();
		Query  q = em.createNativeQuery("select * from coche where id = 1", Coche.class);
		Coche c = (Coche) q.getSingleResult();
		if (c != null) {
			System.out.println("Bastidor: " + c.getBastidor());
		}
	}

}
