////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BillCalculator implements TakeAwayBill {
    
    int freeOrdersGiven;
    LocalDate lastDateOfFreeOrders;
    
    public BillCalculator() {
    
        freeOrdersGiven=0;
        lastDateOfFreeOrders=LocalDate.MIN;
    }
    
    public double getOrderPrice(List<MenuItem> itemsOrder,
                                User user,
                                LocalDateTime date_time)
            throws TakeAwayBillException {
        
        if(itemsOrder.size()<1 || itemsOrder.size()>30) {
            throw new TakeAwayBillException();
        }else
        {
            double sum=0;
            boolean freeOrder=false;
            if(user.age<18) {
                freeOrder=checkDateTimeAndSetValues(date_time);
            }
            if(freeOrder==false) {
                List<MenuItem> gelatiList=new ArrayList<MenuItem>();
                for (MenuItem menuItem : itemsOrder) {
                    if(menuItem.getItemType() == ItemType.Gelato) {
                        gelatiList.add(menuItem);
                    }
                    sum+=menuItem.getPrice();
                }
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
    
    private boolean checkDateTimeAndSetValues(LocalDateTime orderTime) 
            throws TakeAwayBillException{

        if(orderTime.toLocalTime().isAfter(LocalTime.parse("18:00"))
        && orderTime.toLocalTime().isBefore(LocalTime.parse("19:00"))) {
            if(orderTime.toLocalDate().isEqual(lastDateOfFreeOrders))
            {
                if(freeOrdersGiven<10) {
                    if(Math.random()<0.5)
                    {
                        freeOrdersGiven+=1;
                        return true;
                    }
                }
            }else if(orderTime.toLocalDate().isAfter(lastDateOfFreeOrders)){
                if(Math.random()>0.5) {
                    lastDateOfFreeOrders=orderTime.toLocalDate();
                    freeOrdersGiven=1;
                    return true;
                }
                
            }else {
                throw new TakeAwayBillException();
            }
        }
        return false;
    }
    
}
