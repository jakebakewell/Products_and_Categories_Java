package com.jb.prodcat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jb.prodcat.models.Category;
import com.jb.prodcat.models.Product;
import com.jb.prodcat.repositories.CategoryRepository;
import com.jb.prodcat.repositories.ProductRepository;

@Service
public class ProdCatService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	public ProdCatService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public List<Product> allProducts() {
        return productRepository.findAll();
    }
	
	public Product createProduct(Product p) {
        return productRepository.save(p);
    }
	
	public Product findProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        else {
            return null;
        }
    }
	
	public void addCategoryToProduct(Product product, Category category) {
		  product.getCategories().add(category);
		  productRepository.save(product);
		}
	
	public List<Product> findProductsNotInCategory(Category category) {
		List<Product> products = productRepository.findByCategoriesNotContains(category);
		return products;
	}
	
    public void deleteProduct(Long id) {
    	productRepository.deleteById(id);
    }
    
    public List<Category> allCategories() {
    	return categoryRepository.findAll();
    }
    
    public Category createCategory(Category c) {
    	return categoryRepository.save(c);
    }
    
    public Category findCategory(Long id) {
    	Optional<Category> optionalCategory = categoryRepository.findById(id);
    	if(optionalCategory.isPresent()) {
    		return optionalCategory.get();
    	}
    	else {
    		return null;
    	}
    }
    
    public void addProductToCategory(Product product, Category category) {
    	  category.getProducts().add(product);
    	  categoryRepository.save(category);
    	}
    
    public List<Category> findCategoriesNotInProduct(Product product) {
    	List<Category> categories = categoryRepository.findByProductsNotContains(product);
    	return categories;
    }
    
    public void deleteCategory(Long id) {
    	categoryRepository.deleteById(id);
    }
}
