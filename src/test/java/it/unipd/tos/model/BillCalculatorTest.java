////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import it.unipd.tos.business.exception.TakeAwayBillException; 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class BillCalculatorTest {

    @Test(expected= TakeAwayBillException.class)
    public void BillCalculator_Zero_Elements_List_Test()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_Zero_Elements_List_Test");
        BillCalculator billCalc=new BillCalculator();
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        User user = new User(1,"ciao","lol",15);
        LocalDateTime date_time=LocalDateTime.parse("2020-12-03T15:25:30");
        billCalc.getOrderPrice(MenuItemList, user, date_time);
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
        LocalDateTime date_time=LocalDateTime.parse("2020-12-03T15:25:30");
        billCalc.getOrderPrice(MenuItemList, user, date_time);
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
        LocalDateTime date_time=LocalDateTime.parse("2020-12-03T15:25:30");
        resultPrice=billCalc.getOrderPrice(MenuItemList, user, date_time);
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
        LocalDateTime date_time=LocalDateTime.parse("2020-12-03T15:25:30");
        resultPrice=billCalc.getOrderPrice(MenuItemList, user, date_time);
        assertEquals(itemsQuantity*price,resultPrice,0);
    }
    
    @Test
    public void BillCalculator_More_Than_5_Gelati_Test()
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
        LocalDateTime date_time=LocalDateTime.parse("2020-12-03T15:25:30");
        resultPrice=billCalc.getOrderPrice(MenuItemList, user, date_time);
        double expectedPrice=15.+12.+7.+5.+4.+3./2;
        assertEquals(expectedPrice,resultPrice,0);
    }
    
    @Test
    public void BillCalculator_More_Than_50_Euros_Test()
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
        LocalDateTime date_time=LocalDateTime.parse("2020-12-03T15:25:30");
        resultPrice=billCalc.getOrderPrice(MenuItemList, user, date_time);
        double expectedBasePrice=itemsQuantity*price;
        double expectedPrice= expectedBasePrice * 0.9;
        assertEquals(expectedPrice,resultPrice,0);
    }
    
    @Test
    public void BillCalculator_Total_Less_Than_10_Euros_Test()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_Total_Less_Than_10_Euros_Test");
        BillCalculator billCalc=new BillCalculator();
        MenuItem menuItem = new MenuItem(ItemType.Budino, "Budino3", 6);
        List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
        MenuItemList.add(menuItem);
        User user = new User(4,"Carlo","Carli",35);
        LocalDateTime date_time=LocalDateTime.parse("2020-12-03T15:25:30");
        double resultPrice=0;
        resultPrice=billCalc.getOrderPrice(MenuItemList, user, date_time);
        assertEquals(6 + 0.5,resultPrice,0);
    }
    
    @Test
    public void BillCalculator_Ten_Free_Orders_On_Same_Date_Test()
            throws TakeAwayBillException{
        System.out.println("BillCalculator_Ten_Free_Orders_On_Same_Date_Test");
        
        BillCalculator billCalc=new BillCalculator();
        int freeOrdersGiven=0;
        while(freeOrdersGiven<10) {
            MenuItem menuItem = new MenuItem(ItemType.Budino, "Budino3", 6);
            List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
            MenuItemList.add(menuItem);
            User user = new User(5,"Carlotta","Carli",17);
            LocalDateTime date_time=LocalDateTime.parse("2020-12-03T18:25:30");
            double resultPrice;
            resultPrice=billCalc.getOrderPrice(MenuItemList, user, date_time);
            if(resultPrice==0) {
                freeOrdersGiven+=1;
            }
        }
        for(int i=0;i<10;i++) {
            MenuItem menuItem = new MenuItem(ItemType.Budino, "Budino3", 6);
            List<MenuItem> MenuItemList=new ArrayList<MenuItem>();
            MenuItemList.add(menuItem);
            User user = new User(5,"Carlotta","Carli",17);
            LocalDateTime date_time=LocalDateTime.parse("2020-12-03T18:25:30");
            double resultPrice;
            resultPrice=billCalc.getOrderPrice(MenuItemList, user, date_time);
            assertTrue(resultPrice>0);
        }
    }
    
    
}
