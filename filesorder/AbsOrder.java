package filesprocessing.filesorder;

import java.io.File;

/**
 * An absolute path order comparator.
 */
public class AbsOrder extends Order{

    private static final  AbsOrder instance = new AbsOrder();

    private AbsOrder(){}

    public static AbsOrder getInstance(){
        return instance;
    }

    /**
     * compares the absolute path of tw files
     * @param file1 = file to be compared
     * @param file2 = file to be compared
     * @return = -1 if file1 is first in order, 1 second in order, 0 if is the same file.
     */
    public int compare(File file1, File file2) {
        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }
}
