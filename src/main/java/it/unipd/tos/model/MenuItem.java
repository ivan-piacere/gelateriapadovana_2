////////////////////////////////////////////////////////////////////
// Ivan Piacere 1187524
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

public class MenuItem {
    ItemType itemType;
    String name;
    double price;

    public MenuItem(ItemType _itemType, String _name, double _price){
        itemType=_itemType;
        name=_name;
        price=_price;
    }

    public ItemType getItemType() {return itemType;}
    public String getName() {return name;}
    public double getPrice() {return price;}
}
