package com.clsa.controller;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clsa.model.Product;

@RestController
@RequestMapping("products")
public class ProductController{
 
    @RequestMapping(value="/find/{id}",method=RequestMethod.GET, produces="application/json")
    public Product findById(@PathParam("id") Integer id){
        Product p = new Product("Mobile", "Apple Mobile", 120000.0);
        return p;
    }

}