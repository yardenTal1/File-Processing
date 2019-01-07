package filesprocessing.filterfiles;

import filesprocessing.WarningException;
import java.util.Arrays;

/**
 *his class creates the filter object according to input from the CommandReader
 */
public class FilterFactory {

    private static final FilterFactory instance = new FilterFactory();

    private FilterFactory(){}

    public static FilterFactory getInstance(){
        return instance;
    }

    /**
     *  attempts to choose the right filter to assign to the section according to the input.
     * @param type the type of the requested filter
     * @param linNum a line number to indicate about the warning
     * @return a specific filter object
     * @throws WarningException -  in case the string describing the filter to be assigned does not match
     * any type of order used by this program.
     */
    public FilterInterface makeFilter(String type, int linNum) throws WarningException{

        if (type == null)
            return new AllFilter(false);

        boolean negative = false;
        String[] listValuesFiles = type.split("#");
        if (Arrays.asList(listValuesFiles).contains("NOT")){
            negative = true;
        }
        String typeFilter = listValuesFiles[0];
        try {

            switch (typeFilter) {
                case "between":
                    try {
                        double minVal = Double.parseDouble(listValuesFiles[1]);
                        double maxVal = Double.parseDouble(listValuesFiles[2]);
                        if (maxVal < minVal || maxVal < 0 || minVal < 0)
                            throw new WarningException(linNum);
                        return new SizeFilter(minVal, maxVal, negative);
                    }
                    catch (NumberFormatException e){
                        throw new WarningException(linNum);
                    }

                case "greater_than":
                case "smaller_than":
                    try {
                        double val = Double.parseDouble (listValuesFiles[1]);
                        if (val < 0)
                            throw new WarningException(linNum);
                        return new SizeFilter(val, typeFilter, negative);
                    }
                    catch (NumberFormatException e) {
                        throw new WarningException(linNum);
                    }

                case "file":
                case "contains":
                case "prefix":
                case "suffix":
                    return new NameFilter(typeFilter, listValuesFiles[1], negative);

                case "writable":
                case "executable":
                case "hidden":
                    return new PermissionsFilter(typeFilter,listValuesFiles[1], negative, linNum);

                case "all":
                    return new AllFilter(negative);
            }
        }
        catch (IndexOutOfBoundsException e){
            throw new WarningException(linNum);
        }
        throw new WarningException (linNum);
    }
}