package com.test.product.controller;


import com.test.product.dto.ResponseDTO;
import com.test.product.entity.Product;
import com.test.product.service.ProductService;
import java.util.List;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> getProductByID(@PathVariable String idProduct) {
        try {
            if (idProduct != null && idProduct.length() > 0) {
                Product response = productService.getProductByID(idProduct);
                if (response == null || response.getId()==null) {
                    return ResponseEntity.badRequest().body(response);
                }
                return ResponseEntity.ok(response);
            } else {
                Product product = new Product();
                return ResponseEntity.badRequest().body(product);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProductList() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            return ResponseEntity.ok(productService.getProductList());
        } catch (Exception e) {
            throw e;
        }

    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveProduct(@RequestBody Product product) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = productService.saveProduct(product);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            throw e;
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateProduct(@RequestBody Product product) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = productService.saveProduct(product);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            throw e;
        }

    }

    @PostMapping("/belete")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable String idProduct) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO = productService.deleteProduct(idProduct);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            throw e;
        }

    }
}
