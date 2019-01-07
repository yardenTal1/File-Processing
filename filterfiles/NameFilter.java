package filesprocessing.filterfiles;

import java.io.File;
import java.util.LinkedList;

/**
 * This filter class is used to filter according to files names
 */
public class NameFilter implements FilterInterface {

    private String typeFilter;
    private String val;
    private boolean negative;
    private LinkedList<File> filterFilesList = new LinkedList<>();

    /**
     * filter a list of files by different attributes of the files name
     * @param type of the specific filter
     * @param value - the value that checked by the filter
     * @param negative - indicate if do a negative filter
     */

    NameFilter(String type, String value, boolean negative){
        typeFilter = type;
        val = value;
        this.negative = negative;
    }

    public LinkedList<File> filterFiles(LinkedList<File> files) {

        switch (typeFilter) {

            //check if "file" filter
            case "file":
                for (File file: files){
                    if (!negative == file.getName().equals(val))
                        filterFilesList.add(file);
                }
                break;
            //check if "contains" filter
            case "contains":
                for (File file : files) {
                    if (!negative == file.getName().contains(val))
                        filterFilesList.add(file);
                }
                break;
            //check if "prefix" filter
            case "prefix":
                for (File file : files) {
                    if (!negative == file.getName().startsWith(val))
                        filterFilesList.add(file);
                }
                break;
            //check if "suffix" filter
            case "suffix":
                for (File file : files) {
                    if (!negative == file.getName().endsWith(val))
                        filterFilesList.add(file);
                }
                break;
        }
        return filterFilesList;
    }
}
