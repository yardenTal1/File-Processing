package filesprocessing.filterfiles;

import filesprocessing.WarningException;
import java.io.File;
import java.util.LinkedList;

/**
 *  filter according to specified permission
 */
public class PermissionsFilter implements FilterInterface {
    private String typeFilter;
    private boolean negative;
    private LinkedList<File> filterFilesList = new LinkedList<>();

    /**
     *  filter according to specified permission (writable, executable or hidden)
     * @param type - writable, executable or hidden
     * @param permission - YES if its permission or NOT if its not
     * @param negative indicate if do a negative filter
     * @param lineNum indicate the line number for the WarningException
     * @throws WarningException  in case the string describing the filter to be assigned does not match
     * any type of order used by this program
     */
    PermissionsFilter(String type, String permission, boolean negative, int lineNum) throws WarningException {
        typeFilter = type;
        if (!permission.equals("YES") && !permission.equals("NO"))
            throw new WarningException(lineNum);
        else if (permission.equals("YES"))
            this.negative = negative;
        else this.negative = !negative;
    }

    public LinkedList<File> filterFiles(LinkedList<File> files) {

        switch (typeFilter) {
            //check if "writable" filter
            case "writable":
                for (File file : files) {
                    if (!negative == file.canWrite())
                        filterFilesList.add(file);
                }
                break;

            //check if "executable" filter
            case "executable":
                for (File file : files) {
                    if (!negative == file.canExecute())
                        filterFilesList.add(file);
                }
                break;

            //check if "hidden" filter
            case "hidden":
                for (File file : files) {
                    if (!negative == file.isHidden())
                        filterFilesList.add(file);
                }
                break;
        }
        return filterFilesList;
    }
}
