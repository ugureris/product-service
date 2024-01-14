package com.example.productservice.exception.handler;

import com.example.productservice.exception.enums.FriendlyMessageCodes;
import com.example.productservice.exception.exceptions.ProductNotCreatedException;
import com.example.productservice.exception.utils.FriendlyMessageUtils;
import com.example.productservice.response.FriendlyMessage;
import com.example.productservice.response.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice //Exception handling yapabilmek için. ExceptionHandler'ları tek bir genel hata componentinde birleştirmeye olanak sağlar
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) //requestin uygun gönderilmemesi
    @ExceptionHandler(ProductNotCreatedException.class) //RestControllerAdvice eklendiği için bütün handler'lara eklenmeli
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException exception){
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(exception.getMessage()))
                .build();
    }
}
