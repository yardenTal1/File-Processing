package filesprocessing.filesorder;

import java.io.File;
import java.util.Comparator;

/**
 *  An abstract class describing an order object
 */

public abstract class Order implements Comparator<File> {

    public abstract int compare(File file1, File file2);
}

