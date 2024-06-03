package com.scaler.demospringboot.service;

import com.scaler.demospringboot.controller.ProductController;
import com.scaler.demospringboot.exceptions.ProductNotFound;
import com.scaler.demospringboot.model.Category;
import com.scaler.demospringboot.model.Product;
import com.scaler.demospringboot.repositories.CategoryRepository;
import com.scaler.demospringboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private ProductController productController;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

//    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
//
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFound {
        Optional<Product> p=productRepository.findById(productId);
        if(p.isPresent()){
           return p.get();
        }
        throw new ProductNotFound("Product not found");
    }

    @Override
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {

        Category cat= categoryRepository.findByTitle(product.getCategory().getTitle());
        if (cat==null){

            Category newCat=new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow= categoryRepository.save(newCat);
            product.setCategory(newRow);
            System.out.println("new category created");
        }

        Product savedProduct=productRepository.save(product);
        return  savedProduct;
    }

    @Override
    public Product updateProduct(Product product, long id) {

        Optional<Product>optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product p=optionalProduct.get();
            p.setPrice(p.getPrice()/10);
            return p;

        }
        return product;

    }

    @Override
    public String removeProduct(long productId) {

        productRepository.deleteById(productId);
//        re/turn ""

//        return "delete ";


//        Optional<Product>removedProduct=productRepository.findById(productId);

//        if (removedProduct.isPresent()){
//            removedProduct.deleteById(productId);
//        }
//        else{
//            return "Product not found";
//        }
//
        return "Product with id "+productId+" removed";
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {

        Category category=categoryRepository.findByTitle(categoryName);
        List<Product> listOfProducts=category.getProducts();
//        System.out.println(listOfProducts);
        return listOfProducts;
    }

    @Override
    public String removeCategory(long categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Category with "+categoryId+" is deleted successfully ";
    }
}
