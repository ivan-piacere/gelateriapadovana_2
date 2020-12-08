////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MenuItemTest {

    @Test
    public void getItemType_Test() {
        MenuItem mi=new MenuItem(ItemType.Gelato,"ciao",2.5);
        assertEquals(ItemType.Gelato, mi.getItemType());
    }
    @Test
    public void tgetName_Test() {
        MenuItem mi=new MenuItem(ItemType.Gelato,"ciao",2.5);
        assertEquals("ciao", mi.getName());
    }
    @Test
    public void getPrice_Test() {
        MenuItem mi=new MenuItem(ItemType.Gelato,"ciao",2.5);
        assertEquals(2.5, mi.getPrice(),0);
    }
}
