package com.jb.prodcat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jb.prodcat.models.Category;
import com.jb.prodcat.models.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	List<Category> findAll();
	Category findCategoryByNameContaining(String string);
	List<Category> findByProductsNotContains(Product product);
}
