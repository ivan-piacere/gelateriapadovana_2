////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import it.unipd.tos.business.exception.TakeAwayBillException; 

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class BillCalculatorTest {

    @Test(expected= TakeAwayBillException.class)
    public void BillCalculator_Zero_Elements_List_Test()
            throws TakeAwayBillException{
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        User user = new User(1,"ciao","lol",15);
        billCalc.getOrderPrice(MenuItemList, user);
    }
    
    @Test
    public void BillCalculator_Single_Input_Test()
            throws TakeAwayBillException{
        BillCalculator billCalc=new BillCalculator();
        MenuItem menuItem = new MenuItem(ItemType.Gelato, "Biancaneve", 12);
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        MenuItemList.add(menuItem);
        User user = new User(1,"ciao","lol",15);
        double resultPrice=0;
        resultPrice=billCalc.getOrderPrice(MenuItemList, user);
        assertEquals(resultPrice,12,0);
    }
    
    @Test
    public void BillCalculator_Multiple_Items_Input_Test()
            throws TakeAwayBillException{
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        for(int x=0;x<10;x++) {
            MenuItem menuItem = new MenuItem(
                    ItemType.Gelato, 
                    "Biancaneve", 
                    12);
            MenuItemList.add(menuItem);
        }
        User user = new User(1,"ciao","lol",15);
        double resultPrice=0;
        resultPrice=billCalc.getOrderPrice(MenuItemList, user);
        assertEquals(resultPrice,12*10,0);
    }
    
}
