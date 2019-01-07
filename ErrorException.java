package filesprocessing;

/**
 * This class is used when an unchecked exception occurs.
 */
public class ErrorException extends Exception{

    private String message;

    ErrorException(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
