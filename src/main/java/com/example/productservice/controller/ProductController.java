package com.example.productservice.controller;

import com.example.productservice.enums.Language;
import com.example.productservice.exception.enums.FriendlyMessageCodes;
import com.example.productservice.exception.utils.FriendlyMessageUtils;
import com.example.productservice.repository.entity.Product;
import com.example.productservice.request.ProductCreateRequest;
import com.example.productservice.response.FriendlyMessage;
import com.example.productservice.response.InternalApiResponse;
import com.example.productservice.response.ProductResponse;
import com.example.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*controller class'ı spring boot mikroservis mimarili projelerde bir web servisin cevapları alıp döndürmesi için kullanılır
* bu class'ta web servisin gerçekleştireceği işlemleri yöneten metotlar bulunur
* */


@Slf4j
@RestController
/*bir java class'ının bir restful web servisi olarak işlev gördüğünü belirtmek için kullanılır
* sınıfın içinde bulunan tüm metotların http isteklerine cevap verebilecek şekilde yapılandığını gösterir
* bu anotasyonun kullanılması restful web servisinin oluşturulması sürecini kolaylaştırır
* bu anotasyon eklenince servisin request ve response'u JSON tipinde oluyor */

@RequestMapping(value = "/api/1.0/product")
/* Spring boot web uygulamalarında bir url'e istek yapılınca o isteği nasıl işleyeceğini belirmek için kullanılır
* bu anotasyon sayesinde belirtilen url'e yapılan isteği bir controller'a bağlayabiliriz
* value'nun içine yazılan 1.0 proje versiyonu
* eğer proje mikroservis değil monolit yapıda olsaydı birden fazla controller olabilirdi
*  */

@RequiredArgsConstructor
class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")

    /* @ResponseStatus web servisin döndürdüğü yanıtın http durum kodunu belirtmek için kullanılır
    * @PostMapping web servisin bir url'e yapılan http post requestlerini algılamasını ve bu istekleri işlemesini sağlar
    * @PathVariable bir web servisin url'inde bulunan değişkenleri algılamasını ve bu değişkenlere ulaşabilmesini sağlar
    * @RequestBody bir web servise yapılan isteklerin içeriğinin algılanması ve işlenmesini sağlar
    * */
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language") Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest) {

        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime()) //tarihi milisaniye cinsinden vereceği için long olan response'a bu şekilde verebiliriz
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                .build();
    }
}
