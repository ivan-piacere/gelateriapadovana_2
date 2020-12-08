////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import java.util.List;
import java.util.ArrayList;
public class BillCalculator implements TakeAwayBill {

    public double getOrderPrice(List<MenuItem> itemsOrder, User user)
            throws TakeAwayBillException {
        if(itemsOrder.size()<1 || itemsOrder.size()>30) {
            throw new TakeAwayBillException();
        }else
        {
            List<MenuItem> gelatiList=new ArrayList<MenuItem>();
            double sum=0;
            for (MenuItem menuItem : itemsOrder) {
                if(menuItem.getItemType() == ItemType.Gelato) {
                    gelatiList.add(menuItem);
                }
                sum+=menuItem.getPrice();
            }
            System.out.println(sum);
            if(gelatiList.size()>5) {
                double halfPriceCheaperGelato=getCheaper(gelatiList)
                                              .getPrice()
                                              /2;
                sum-=halfPriceCheaperGelato;
            }else if(sum>50) {
                sum -=sum/10;
            }else if(sum<10) {
                sum+= 0.5; 
            }
            return sum;
        }
    }
    
    private MenuItem getCheaper(List<MenuItem> list) {
        double minPrice = Double.POSITIVE_INFINITY;
        MenuItem cheaper=null;
        for(MenuItem mi: list) {
            if(mi.getPrice()<minPrice) {
                minPrice=mi.getPrice();
                cheaper=mi;
            }
        }
        return cheaper;
    }
}
