package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListOwners;

public class ListOwnersHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetList");

	public void insertOwner(ListOwners li) {
		String ownerEntered = li.getOwnerName();
		System.out.println("test inside loh.insertOwner :" + ownerEntered);

		List<ListOwners> matchOwners = searchForOwnerByName(ownerEntered);
		if (matchOwners.isEmpty()) {
			// if no match, add new author to the database then get that entry from the
			// database so we know the id. Insert if not already existing
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(li);
			em.getTransaction().commit();
			em.close();
		}
	}

	public List<ListOwners> showAllOwners() {
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<ListOwners> allOwners = em.createQuery("SELECT i FROM ListOwners i").getResultList();
		return allOwners;
	}

	public void deleteOwner(ListOwners toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListOwners> typedQuery = em.createQuery(
				"select li	from ListOwners	li	where li.ownerName	=	:selectedOwnerName", ListOwners.class);

		// Substitute parameter with actual data from the toDelete Author
		typedQuery.setParameter("selectedOwnerName", toDelete.getOwnerName());

		// we only want one result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new listAuthor
		// ADDED check if any result

		try {
			ListOwners result = typedQuery.getSingleResult();

			// remove it
			em.remove(result);
			em.getTransaction().commit();
			em.close();
		} catch (NoResultException none) {
			System.out.println("No matching entry was found.");
			return;
		}

	}

	public ListOwners searchForOwnerById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListOwners found = em.find(ListOwners.class, idToEdit);
		em.close();
		return found;
	}

	public void updateOwner(ListOwners toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListOwners> searchForOwnerByName(String ownerName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListOwners> typedQuery = em.createQuery(
				"select	li	from ListOwners	li	where	li.ownerName	=	:selectedOwnerName", ListOwners.class);
		typedQuery.setParameter("selectedOwnerName", ownerName);
		List<ListOwners> foundOwners = typedQuery.getResultList();
		em.close();
		return foundOwners;
	}

	public void cleanUp() {
		emfactory.close();
	}
}
