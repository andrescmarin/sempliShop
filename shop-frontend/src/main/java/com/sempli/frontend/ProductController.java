package com.sempli.frontend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sempli.backend.ProductRepository;
import com.sempli.logic.Product;
import com.sempli.logic.ProductServiceImpl;

@RestController
public class ProductController {
	private ProductRepository productRepository;
	private ProductServiceImpl productService;

	public ProductController(ProductRepository productRepository, ProductServiceImpl productService) {
		this.productRepository = productRepository;
		this.productService = productService;
	}
	
    // ------------------- Delete All Products-----------------------------
	 
    @RequestMapping(value = "/")
    public String index() {
        return "<h1>hola mundo</h1>";
    }

	// -------------------Retrieve All Products------------------------------------------
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getProducts(){
		List<Product> products = this.productRepository.findAll();
		List<Product> productsAvalaible = new ArrayList<Product>();
		for (Iterator<Product> productIterator = products.listIterator(); productIterator.hasNext(); ) {
			Product singleProduct = productIterator.next();
			if (singleProduct.getQuantity() > 0) {
				productsAvalaible.add(singleProduct);
			}
			
		}
		return productsAvalaible;	
	}	

	// -------------------Retrieve Single Product------------------------------------------
 
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") long id) {
        //logger.info("Fetching Product with id {}", id);
        Product product = productService.findById(id);
        if (product == null) {
            //logger.error("Product with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Product with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
 
    // -------------------Create a Product-------------------------------------------
 
    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        //logger.info("Creating Product : {}", product);
 
        if (productService.isProductExist(product)) {
            //logger.error("Unable to create. A Product with name {} already exist", product.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Product with name " + 
            product.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        productService.saveProduct(product);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Product ------------------------------------------------
 
    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        //logger.info("Updating Product with id {}", id);
 
        Product currentProduct = productService.findById(id);
 
        if (currentProduct == null) {
            //logger.error("Unable to update. Product with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
			
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setQuantity(product.getQuantity());
		currentProduct.setInStock(product.isInStock());
 
        productService.updateProduct(currentProduct);
        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
    }
 
    // ------------------- Delete a Product-----------------------------------------
 
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        //logger.info("Fetching & Deleting Product with id {}", id);
 
        Product product = productService.findById(id);
        if (product == null) {
            //logger.error("Unable to delete. Product with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        productService.deleteProductById(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Products-----------------------------
 
    @RequestMapping(value = "/product/", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteAllProducts() {
        //logger.info("Deleting All Products");
 
        productService.deleteAllProducts();
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
	
	
}
