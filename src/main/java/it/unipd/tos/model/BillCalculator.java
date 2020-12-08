////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import java.util.List;
public class BillCalculator implements TakeAwayBill {

    public double getOrderPrice(List<MenuItem> itemsOrder, User user)
            throws TakeAwayBillException {
        if(itemsOrder.size()<1) {
            throw new TakeAwayBillException();
        }else
        {
            double sum=0;
            for (MenuItem menuItem : itemsOrder) {
                sum+=menuItem.getPrice();
                }
            return sum;
        }
    }
}
