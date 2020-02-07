package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListAnimals;

public class ListAnimalHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetList");
	
	public void insertAnimal(ListAnimals li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListAnimals>showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<ListAnimals>allItems = em.createQuery("SELECT i FROM ListAnimals i").getResultList();
		return allItems;
	}
	
	public void deleteAnimal(ListAnimals toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListAnimals>typedQuery = em.createQuery("select li from ListAnimals li where li.name = :selectedName and li.type = :selectedType and li.owner = :selectedOwner", ListAnimals.class);
		
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		typedQuery.setMaxResults(1);
		
		ListAnimals result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public ListAnimals searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListAnimals found = em.find(ListAnimals.class, idToEdit);
		em.close();
		return found;
	}

	public void updateItem(ListAnimals toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	public List<ListAnimals> searchForItemByName(String petName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListAnimals>typedQuery = em.createQuery("select li from ListAnimals li where li.name = :selectedName", ListAnimals.class);
		typedQuery.setParameter("selectedName", petName);
		
		List<ListAnimals>foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ListAnimals> searchForItemByOwner(String ownerName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListAnimals>typedQuery = em.createQuery("select li from ListAnimals li where li.owner = :selectedOwner", ListAnimals.class);
		typedQuery.setParameter("selectedOwner", ownerName);
		
		List<ListAnimals>foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}
