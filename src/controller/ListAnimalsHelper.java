package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.ListAnimals;

public class ListAnimalsHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetList");

	public void insertAnimal(ListAnimals li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListAnimals> showAllAnimals() {
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<ListAnimals> allAnimals = em.createQuery("SELECT i FROM ListAnimals i").getResultList();
		return allAnimals;
	}

	public void deleteAnimal(ListAnimals toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListAnimals> typedQuery = em.createQuery(
				"select	li	from ListAnimals	li	where	li.owner	=	:selectedOwner	and	li.name	=	:selectedAnimal",
				ListAnimals.class);

		// Substitute parameter with actual data from the toDelete animal
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		typedQuery.setParameter("selectedAnimal", toDelete.getName());

		// we only want one result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new list animal
		// ADDED check if any result

		try {
			ListAnimals result = typedQuery.getSingleResult();

			// remove it
			em.remove(result);
			em.getTransaction().commit();
			em.close();
		} catch (NoResultException none) {
			System.out.println("No matching entry was found.");
			return;
		}

	}

	public ListAnimals searchForAnimalById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListAnimals found = em.find(ListAnimals.class, idToEdit);
		em.close();
		return found;
	}

	public void updateAnimal(ListAnimals toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListAnimals> searchForOwnerByAnimal(String animal) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListAnimals> typedQuery = em
				.createQuery("select li from ListAnimals li where li.name = :selectedName", ListAnimals.class);
		typedQuery.setParameter("selectedAnimal", animal);

		List<ListAnimals> foundAnimals = typedQuery.getResultList();
		em.close();
		return foundAnimals;
	}

	public void cleanUp() {
		emfactory.close();
	}
}
