package com.jb.prodcat.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jb.prodcat.models.Category;
import com.jb.prodcat.models.Product;
import com.jb.prodcat.services.ProdCatService;

@Controller
public class ProdCatController {
	private final ProdCatService prodCatService;
	public ProdCatController(ProdCatService prodCatService) {
		this.prodCatService = prodCatService;
	}
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/product/new";
	}
	
	@RequestMapping("/product/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "newProduct.jsp";
	}
	
	@RequestMapping(value="/product/new", method=RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "newProduct.jsp";
        }
        else {
            prodCatService.createProduct(product);
            return "redirect:/category/new";
        }
    }
	
	@RequestMapping(value="/product/{prodId}/addcategory", method=RequestMethod.POST)
	public String addCategory(@PathVariable("prodId") Long prodId, @RequestParam(value="category") Long catId) {
		Product product = prodCatService.findProduct(prodId);
		Category category = prodCatService.findCategory(catId);
		prodCatService.addCategoryToProduct(product, category);
		String redirect = String.format("redirect:/product/%d", prodId);
		return redirect;
	}
	
	@RequestMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable("id") Long id) {
		Product product = prodCatService.findProduct(id);
		List<Category> categories = prodCatService.findCategoriesNotInProduct(product);
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		return "product.jsp";
	}
	
	@RequestMapping("/category/new")
	public String newCategory(Model model) {
		model.addAttribute("category", new Category());
		return "newCategory.jsp";
	}
	
	@RequestMapping(value="/category/new", method=RequestMethod.POST)
    public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "newCategory.jsp";
        }
        else {
            prodCatService.createCategory(category);
            return "redirect:/product/new";
        }
    }
	
	@RequestMapping(value="/category/{catId}/addproduct", method=RequestMethod.POST)
	public String addProduct(@RequestParam(value="product") Long prodId, @PathVariable("catId") Long catId) {
		Product product = prodCatService.findProduct(prodId);
		Category category = prodCatService.findCategory(catId);
		prodCatService.addProductToCategory(product, category);
		String redirect = String.format("redirect:/category/%d", catId);
		return redirect;
	}
	
	@RequestMapping("/category/{id}")
	public String showCategory(Model model, @PathVariable("id") Long id) {
		Category category = prodCatService.findCategory(id);
		List<Product> products = prodCatService.findProductsNotInCategory(category);
		model.addAttribute("category", category);
		model.addAttribute("products", products);
		return "category.jsp";
	}
}
