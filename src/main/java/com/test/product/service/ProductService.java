package com.test.product.service;

import com.test.product.dto.ResponseDTO;
import com.test.product.entity.Product;
import com.test.product.repository.ProductRepository;
import com.test.product.util.Constants;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductByID(String idProduct) {
        try {
            Optional<Product> response = productRepository.findById(Long.valueOf(idProduct));
            if (response.isEmpty()) {
                Product product = new Product();
                return product;
            }
            return response.get();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Product> getProductList() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw e;
        }

    }

    public ResponseDTO saveProduct(Product product) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product response = productRepository.save(product);
            responseDTO.setStatusCode(Constants.STATUS_CODE_200);
            responseDTO.setMessage(Constants.SUCCESSFUL_MESSAGE);
            return responseDTO;
        } catch (Exception e) {
            throw e;
        }

    }

    public ResponseDTO updateProduct(Product product) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<Product> exist = productRepository.findById(product.getId());
            if (exist.isEmpty()) {
                responseDTO.setStatusCode(Constants.STATUS_CODE_400);
                responseDTO.setMessage(Constants.ERROR_MESSAGE);
                return responseDTO;
            }
            productRepository.save(product);
            responseDTO.setStatusCode(Constants.STATUS_CODE_200);
            responseDTO.setMessage(Constants.SUCCESSFUL_MESSAGE);
            return responseDTO;
        } catch (Exception e) {
            throw e;
        }

    }

    public ResponseDTO deleteProduct(@PathVariable String idProduct) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<Product> exist = productRepository.findById(Long.valueOf(idProduct));
            if (exist.isEmpty()) {
                responseDTO.setStatusCode(Constants.STATUS_CODE_400);
                responseDTO.setMessage(Constants.ERROR_MESSAGE);
                return responseDTO;
            }
            productRepository.deleteById(Long.valueOf(idProduct));
            responseDTO.setStatusCode(Constants.STATUS_CODE_200);
            responseDTO.setMessage(Constants.SUCCESSFUL_MESSAGE);
            return responseDTO;
        } catch (Exception e) {
            throw e;
        }

    }
}
