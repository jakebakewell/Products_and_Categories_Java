package com.jb.prodcat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jb.prodcat.models.Category;
import com.jb.prodcat.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	List<Product> findAll();
	Product findProductByNameContaining(String string);
	List<Product> findByCategoriesNotContains(Category category);
}
