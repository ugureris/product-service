package com.example.productservice.service.impl;

import com.example.productservice.enums.Language;
import com.example.productservice.exception.enums.FriendlyMessageCodes;
import com.example.productservice.exception.exceptions.ProductNotCreatedException;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.repository.entity.Product;
import com.example.productservice.request.ProductCreateRequest;
import com.example.productservice.request.ProductUpdateRequest;
import com.example.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
/*
* 5 adet log tipi var
* trace: izlemek istenen loglar için eklenir
* debug, info, warn, error*/
@Service //bu class'ta business logic'lerin bulunduğunu belli etmek için eklendi
@RequiredArgsConstructor //eklenen final class variable'ı constructor'da initialize etmek için
public class ProductRepositoryServiceImpl implements IProductRepositoryService {


    private final ProductRepository productRepository; // repository'deki database metotlarına ulaşmak için

    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        try{
            Product product = Product.builder() //builder() ile sadece gerekli alanları seçip setleyebiliyoruz
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
            Product productResponse = productRepository.save(product);
            log.debug("[{}][createdProduct] -> response: {}",this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception exception) {
            throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED, "product request: " + productCreateRequest.toString());
        }
    }

    @Override
    public Product getProduct(Language language, Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        return null;
    }
}
