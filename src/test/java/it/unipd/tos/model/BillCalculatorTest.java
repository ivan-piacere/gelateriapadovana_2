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
        System.out.println("BillCalculator_Zero_Elements_List_Test");
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        User user = new User(1,"ciao","lol",15);
        billCalc.getOrderPrice(MenuItemList, user);
    }
    
    @Test(expected= TakeAwayBillException.class)
    public void BillCalculator_More_Than_30_Elements_List_Test()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_More_Than_30_Elements_List_Test");
        
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        int itemsQuantity=31;
        int price=3;
        for(int x=0;x<itemsQuantity;x++) {
            MenuItem menuItem = new MenuItem(
                    ItemType.Budino,
                    "Budino2", 
                    price);
            MenuItemList.add(menuItem);
        }
        User user = new User(3,"Francesco","Carli",19);
        billCalc.getOrderPrice(MenuItemList, user);
    }
    
    @Test
    public void BillCalculator_Single_Input_Test()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_Single_Input_Test");
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
    public void BillCalculator_Multiple_Items_Less_Or_Equals_to_5_Gelati_Test()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_Multiple_Items_Input_Test");
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        int itemsQuantity=4;
        int price=6;
        for(int x=0;x<itemsQuantity;x++) {
            MenuItem menuItem = new MenuItem(
                    ItemType.Bevanda,
                    "Biancaneve", 
                    price);
            MenuItemList.add(menuItem);
        }
        User user = new User(1,"ciao","lol",15);
        double resultPrice=0;
        resultPrice=billCalc.getOrderPrice(MenuItemList, user);
        assertEquals(itemsQuantity*price,resultPrice,0);
    }
    
    @Test
    public void BillCalculator_More_Than_5_Gelati()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_More_Than_5_Gelati");
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        
        MenuItem menuItem = new MenuItem(ItemType.Gelato, "Pinguino", 12);
        MenuItemList.add(menuItem);
        menuItem = new MenuItem(ItemType.Gelato, "Biancaneve", 15);
        MenuItemList.add(menuItem);
        menuItem = new MenuItem(ItemType.Gelato, "Pinguino", 4);
        MenuItemList.add(menuItem);
        menuItem = new MenuItem(ItemType.Gelato, "Biancaneve", 7);
        MenuItemList.add(menuItem);
        menuItem = new MenuItem(ItemType.Gelato, "Biancaneve", 5);
        MenuItemList.add(menuItem);
        menuItem = new MenuItem(ItemType.Gelato, "Biancaneve", 3);
        MenuItemList.add(menuItem);
        
        User user = new User(1,"ciao","lol",15);
        double resultPrice=0;
        resultPrice=billCalc.getOrderPrice(MenuItemList, user);
        double expectedPrice=15.+12.+7.+5.+4.+3./2;
        assertEquals(expectedPrice,resultPrice,0);
    }
    
    @Test
    public void BillCalculator_More_Than_50_Euros()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_More_Than_50_Euros");
        
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        int itemsQuantity=10;
        int price=6;
        for(int x=0;x<itemsQuantity;x++) {
            MenuItem menuItem = new MenuItem(
                    ItemType.Budino,
                    "Budino1", 
                    price);
            MenuItemList.add(menuItem);
        }
        User user = new User(2,"Carlo","Franchi",15);
        double resultPrice=0;
        resultPrice=billCalc.getOrderPrice(MenuItemList, user);
        double expectedBasePrice=itemsQuantity*price;
        double expectedPrice= expectedBasePrice * 0.9;
        assertEquals(expectedPrice,resultPrice,0);
    }
}
