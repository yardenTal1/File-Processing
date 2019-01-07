package filesprocessing.filterfiles;

import java.io.File;
import java.util.LinkedList;

/**
 * filter according to specified size (greater then, etc.)
 */

public class SizeFilter implements FilterInterface {

    private double minVal;
    private double maxVal;
    private double value;
    private boolean negative;
    private boolean flag;
    private String typeVlue;
    private LinkedList<File> filterFilesList = new LinkedList<>();

    //A constructor for "between" filter
    SizeFilter(double minValue, double maxValue, boolean negative){
        minVal = minValue * 1024;
        maxVal = maxValue * 1024;
        this.negative = negative;
        flag = false;
    }

    //A constructor for "greater" and "smaller" filters
    SizeFilter(double val, String type, boolean negative){
        typeVlue = type;
        value = val * 1024;
        this.negative = negative;
        flag = true;
    }

    /**
     * filter according to specified size (greater then or smaller then)
     * @param files- a list of files to order
     * @return - files filter list
     */
    public LinkedList<File> filterFiles(LinkedList<File> files) {
        if (flag){
            if (typeVlue.equals("greater_than"))
                doFilter(files, 1, value);
            else if (typeVlue.equals("smaller_than"))
                doFilter(files, 0, value);
        }
        else
            doFilter(files, 2, minVal);
        return filterFilesList;
    }

    /**
     *
     * @param files - a list of files to filter
     * @param flag - indicates which kind of filter to do
     * @param value -  the value who is compared by the filter
     */

    private void doFilter(LinkedList<File> files, int flag, double value){
        for (File file : files){
            double  length = file.length();
            if (flag==0){
                if (!negative == length < value)
                    filterFilesList.add(file);
            }
            else if (flag==1){
                if (!negative == length > value)
                    filterFilesList.add(file);
            }
            else if (flag==2){
                if(!negative == length>= value && length<=maxVal)
                    filterFilesList.add(file);
            }
        }
    }
}
