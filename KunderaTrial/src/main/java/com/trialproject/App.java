package com.trialproject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		PersonMongo person = new PersonMongo();
		person.setPersonId("1");
		person.setPersonName("John Smith");
		person.setAge(32);

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("mongo_pu");
		EntityManager em = emf.createEntityManager();

		// Insert data
		em.persist(person);

		em.clear(); // Clear cache before finding record

		// Search for data
//		PersonMongo peronFound = em.find(PersonMongo.class, "1");

		// Update data
		person.setAge(33);
		em.merge(person);

		// Delete data
		// em.remove(person);

		em.close();
		emf.close();
	}
}
