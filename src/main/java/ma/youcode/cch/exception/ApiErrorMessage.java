package ma.youcode.cch.exception;

import lombok.Getter;

@Getter
public class ApiErrorMessage {

    private int status;
    private String message;
    private String timestamp;

    public ApiErrorMessage(int status , String message , String timestamp){
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiErrorMessage(){}


}
