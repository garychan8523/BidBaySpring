package com.fdmgroup.BidbaySpring.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.entities.Category;

@Component
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private EntityManagerFactory emf;

	public CategoryDaoImpl() {
		super();
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void initalizeCategories() {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		String[] categoryNames = { "Uncategorized", "Electronics", "Fashion", "Furniture", "Health and Beauty",
				"Kitchen", "Stationary" };
		Category category;

		for (String categoryName : categoryNames) {
			category = new Category();
			category.setCategoryname(categoryName);
			categoryList.add(category);
		}

		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		for (Category categoryObj : categoryList) {
			em.persist(categoryObj);
		}

		et.commit();
		em.close();

	}

	public void addCategory(Category category) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		em.persist(category);

		et.commit();
		em.close();
	}

	public Category getCategory(int categoryid) {
		Category category = new Category();
		EntityManager em = getEntityManager();
		try {
			category = em.find(Category.class, categoryid);
		} finally {
			em.close();
		}
		return category;
	}

	public List<Category> listCategories() {
		EntityManager em = getEntityManager();

		TypedQuery<Category> query = em.createQuery("SELECT c from Category c order by c.categoryid", Category.class);
		ArrayList<Category> resultList = (ArrayList<Category>) query.getResultList();

		em.close();

		if (resultList != null) {
			return resultList;
		} else {
			return null;
		}
	}

}
