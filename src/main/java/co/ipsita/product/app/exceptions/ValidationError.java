package co.ipsita.product.app.exceptions;

public class ValidationError{
    private String field;
    private String message;

    private ValidationError(){

    }

    public ValidationError(String message){
        this.message = message;
    }

    public ValidationError(String field, String message){
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
