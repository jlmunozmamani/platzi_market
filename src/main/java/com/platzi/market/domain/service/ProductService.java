package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
        return getProduct(productId).map( product -> {
            productRepository.delete(productId);
        return true;
    }).orElse(false);
    }
/*
    public Product update(Product product) {
        return getProduct(product.getProductId()).map(productToUpdate -> {
            productToUpdate.setName(product.getName());
            productToUpdate.setCategoryId(product.getCategoryId());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStock(product.getStock());
            productToUpdate.setActive(product.isActive());
            return save(productToUpdate);
        }).orElse(null);
    }
    */

/*
    public Product update(Product product){
        int productId = product.getProductId();
        Product prod = getProduct(productId).map(p -> {
            BeanUtils.copyProperties(product,p);
            return p;
        }).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        return productRepository.save(prod);
    }
*/

    public Product update (int productId , Product product){

        //Product product1 = productRepository.getProduct(productId);
        Product product1 = getProduct(productId).get();
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setActive(product.isActive());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());
        return productRepository.save(product1);
    }



}
