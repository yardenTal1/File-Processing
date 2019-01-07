package filesprocessing;

import filesprocessing.filesorder.Order;
import filesprocessing.filterfiles.FilterInterface;

import java.io.File;
import java.util.LinkedList;


/**
 * Section class, used to manipulate the files and store the data.
 */

public class Section {

    private Order order;
    private FilterInterface filter;
    private LinkedList<String> warnings = new LinkedList<>();
    private LinkedList<File> secFiles;
    boolean reverseOrder;

    Section(FilterInterface filter, Order order){
        this.order = order;
        this.filter = filter;
    }

    /**
     * @return - the sections files after filter
     */
    public LinkedList<File> getFiles(){
        return secFiles;
    }

    /**
     * @return - the sections warnings
     */
    LinkedList<String> getWarnings(){
        return warnings;
    }

    /**
     * @param warning - to be added to section's warnings
     */
    void addWarning(LinkedList<String> warning){
        warnings.addAll(warning);
    }

    /**
     * filter and orders the files.
     * @param files - list of files to filter and order
     */
    void process(LinkedList<File> files){

        secFiles = filter.filterFiles(files);

        if (reverseOrder){
            secFiles.sort(order.reversed());
        }
        else {
            secFiles.sort(order);
        }
    }

}
