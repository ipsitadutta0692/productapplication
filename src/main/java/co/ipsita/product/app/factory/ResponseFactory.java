package co.ipsita.product.app.factory;

import co.ipsita.product.app.exceptions.ValidationError;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {

    private ResponseFactory(){

    }

    public static <T> ResponseEntity<T> createSuccessResponse(T responseObject){
        if(responseObject == null){
            return new ResponseEntity<>(responseObject, HttpStatus.NOT_FOUND);
        }else{
            boolean fromCache = BooleanUtils.toBoolean(MDC.get("FROM-CACHE"));
            return fromCache ? new ResponseEntity<>(responseObject, HttpStatus.NON_AUTHORITATIVE_INFORMATION) : new ResponseEntity<>(responseObject,HttpStatus.OK);
        }
    }

}
