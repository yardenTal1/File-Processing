package filesprocessing;

import filesprocessing.filesorder.AbsOrder;
import filesprocessing.filesorder.OrderFactory;
import filesprocessing.filesorder.Order;
import filesprocessing.filterfiles.AllFilter;
import filesprocessing.filterfiles.FilterFactory;
import filesprocessing.filterfiles.FilterInterface;
import java.util.LinkedList;

/**
 * section factory class used to create the sections to be used by the program.
 */
class SectionFactory {

    private static final SectionFactory instance = new SectionFactory();

    private SectionFactory(){}

    static SectionFactory getInstance(){
        return instance;
    }

    /**
     * creates a section object.
     * @param filter - string describing the type filter to assign to section
     * @param order - string describing the type order to assign to section
     * @param lineNum - line where section starts to help with warnings generation.
     * @return - section object.
     */
    Section makeSection(String filter, String order, int lineNum){
        Order secOrder;
        FilterInterface secFilter;
        boolean reverse = false;
        LinkedList<String> warnings = new LinkedList<>();

        try {
            secFilter = FilterFactory.getInstance().makeFilter(filter, lineNum + 1);
        }
        catch (WarningException e){
            warnings.add(e.getWarning());
            secFilter = new AllFilter(false);
        }

        try {
            if (order != null && order.endsWith("#REVERSE")) {
                reverse = true;
            }
            secOrder = OrderFactory.getInstance().makeOrder(order, lineNum + 3);
        }
        catch (WarningException e){
            warnings.add(e.getWarning());
            secOrder = AbsOrder.getInstance();
            reverse = false;
        }
        Section section = new Section(secFilter, secOrder);
        section.addWarning(warnings);
        section.reverseOrder = reverse;

        return section;
    }

}
