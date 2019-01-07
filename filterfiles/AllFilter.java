package filesprocessing.filterfiles;

import java.io.File;
import java.util.LinkedList;

/**
 * This class for filter all - does not filter any files
 */
public class AllFilter implements FilterInterface {

    private boolean negative = false;

    /**
     * constructors a new all type filter
     * @param negative- a boolean parameter to indicate if implements negative filter or not
     */
    public AllFilter(boolean negative){
        this.negative = negative;
    }

    /**
     * This filter does not filter any files
     * @param files a linked list of flies to filter
     * @return if negative- return an empty linked list; else, return the files as is
     */
    public LinkedList<File> filterFiles(LinkedList<File> files) {
        if (negative){
            return new LinkedList<>();
        }
        return files;
    }
}
