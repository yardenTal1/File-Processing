package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * The class that print sections
 */
class Printer {

    /**
     * a method prints sections- a list of filtered and sorted files and warning
     * @param sections A link list of sections
     */

    void print(LinkedList<Section> sections){
        for (Section sec: sections){
            for (String warning : sec.getWarnings()) {
                System.out.println(warning);
            }
            for (File file: sec.getFiles()) {
                System.out.println(file.getName());
            }
        }
    }
}
