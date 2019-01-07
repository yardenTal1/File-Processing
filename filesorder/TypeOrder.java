package filesprocessing.filesorder;

import java.io.File;

/**
 * A Type order comparator.
 */
public class TypeOrder extends Order {

    private static final  TypeOrder instance = new TypeOrder();

    private TypeOrder(){}

    static TypeOrder getInstance(){
        return instance;
    }

    /**
     * compares types of two files, and if equal, compares the absolute path
     * @param file1 = file to be compared
     * @param file2 = file to be compared
     * @return = -1 if file1 is first in order, 1 second in order, 0 if is the same file.
     */
    public int compare(File file1, File file2) {
        String type1 = file1.getName().substring(file1.getName().lastIndexOf("."));
        String type2 = file2.getName().substring(file2.getName().lastIndexOf("."));
        int val = type1.compareTo(type2);
        return (val != 0) ? val : AbsOrder.getInstance().compare(file1, file2);
    }
}
