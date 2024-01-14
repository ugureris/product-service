package com.example.productservice.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/*
* REST API için tek tipte bir response yapısı oluşturup her türlü response için kullanılabilir.
* Farklı bir microservice uygulamasında da bu class kullanılabilir
* İçerisinde; response, hata mesajı. http status gibi ihtiyaç duyulan tüm alanları barındırır
* İhtiyaç halinde farklı bir alan eklenebilir
*
*  */


@Data
@Builder
public class InternalApiResponse<T> {
    private FriendlyMessage friendlyMessage;
    private T payload;
    private boolean hasError;
    private List<String> errorMessages;
    private HttpStatus httpStatus;
}
