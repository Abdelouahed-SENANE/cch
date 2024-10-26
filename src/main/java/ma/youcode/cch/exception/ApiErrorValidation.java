package ma.youcode.cch.exception;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ApiErrorValidation {

    private int status;
    private Map<String , String> errors;
    private String timestamp;

    public ApiErrorValidation(int status , Map<String , String> errors , String timestamp){
        this.status = status;
        this.errors = errors;
        this.timestamp = timestamp;
    }

    public ApiErrorValidation(){}


}
