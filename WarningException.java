package filesprocessing;

/**
 * This class is used when an checked exception occurs.
 */
public class WarningException extends Exception {

    private String warning;

    public WarningException(int line){
        this.warning = "Warning in line " + line;
    }

    String getWarning(){
        return this.warning;
    }
}
