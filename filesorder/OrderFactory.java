package filesprocessing.filesorder;

import filesprocessing.WarningException;

/**
 * Order Factory used to assign the "right" order to the section according to the input.
 */
public class OrderFactory {

    private static final OrderFactory instance = new OrderFactory();

    private OrderFactory(){}

    public static OrderFactory getInstance(){
        return instance;
    }

    /**
     * attempts to choose the right order to assign to the section according to the input.
     * @param orderString - string describing the order to be assigned to the section
     * @return - order to be assigned.
     * @throws WarningException - in case the string describing the order to be assigned does not match
     * any type of order used by this program.
     */
    public Order makeOrder(String orderString, int line) throws WarningException {

        if (orderString == null) {
            return AbsOrder.getInstance();
        }

        String type = orderString.split("#")[0];

        switch (type){

            case "abs":
                return AbsOrder.getInstance();

            case "type":
                return TypeOrder.getInstance();

            case "size":
                return SizeOrder.getInstance();

        }
        throw new WarningException(line);
    }
}
