package filesprocessing.filesorder;

import java.io.File;

/**
 * A Size order comparator.
 */
public class SizeOrder extends Order {

    private static final  SizeOrder instance = new SizeOrder();

    private SizeOrder(){}

    static SizeOrder getInstance(){
        return instance;
    }

    /**
     * compares sizes of two files, and if equal, compares the absolute path
     * @param file1 = file to be compared
     * @param file2 = file to be compared
     * @return = -1 if file1 is first in order, 1 second in order, 0 if is the same file.
     */
    public int compare(File file1, File file2) {
        int val =  Long.compare(file1.length(), file2.length());
        return (val != 0 ) ? val : AbsOrder.getInstance().compare(file1, file2);
    }
}
