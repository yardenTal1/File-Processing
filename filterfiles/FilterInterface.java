package filesprocessing.filterfiles;

import java.io.File;
import java.util.LinkedList;

/**
 * an interface describing the methods a filter object must implement
 */
public interface FilterInterface {

    public LinkedList<File> filterFiles(LinkedList<File> files);
}
