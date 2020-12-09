////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;
import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import java.time.LocalDateTime;

public interface TakeAwayBill {
    double getOrderPrice(List<MenuItem> itemsOrdered,
                         User user,
                         LocalDateTime date_time)
        throws TakeAwayBillException;
}