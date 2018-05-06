package barBossHouse;

import java.time.LocalDate;
import java.util.function.Predicate;

public class TableOrdersManager implements OrdersManager {
    private static final  int MIDLE_COST = 500;
    private static final int DEFAULT_CAPACITY_VALUE = 0;
    private TableOrder[] orders;
    private int capacity = DEFAULT_CAPACITY_VALUE;

    public TableOrdersManager(int tablesCount) {
        orders = new TableOrder[tablesCount];
    }

    public void addOrder(int tableNumber, TableOrder newOrder) {
        if (newOrder != null & isValidNumber(tableNumber)) {
            orders[tableNumber] = newOrder;
            capacity++;
        }
    }

    public TableOrder getOrderByTableNumber(int tableNumber) {
        if (isValidNumber(tableNumber)) {
            return orders[tableNumber];
        } else return null;
    }

    public boolean addDishToOrder(int tableNumber, MenuItem menuItem) {
        if(menuItem instanceof Drink) {
            Drink drink = (Drink) menuItem;
            if(drink.getAlcoholVol()>0 && orders[tableNumber].getCustomer().getAge()<18) throw new UnlawfulActionException("Тебе нет 18");
        }
        if (orders[tableNumber] != null) throw new NoFreeTableException("Столик под номером:" + tableNumber + " занят");
        if (menuItem != null) {
            if (isValidNumber(tableNumber)) {
                orders[tableNumber].add(menuItem);
                return true;
            }
        }
        return false;
    }

    public void freeTable(int tableNumber) {
        if (isValidNumber(tableNumber)) {
            orders[tableNumber] = null;
            capacity--;
        }
    }

    public int getFirstFreeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) return i;
        }
        throw new NoFreeTableException("Нет свободных столиков");
    }

    public int removeTableOrder(TableOrder tableOrder) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(tableOrder)) {
                orders[i] = null;
                return i;
            }
        }
        return (-1);
    }

    public int removeAllTableOrder(TableOrder tableOrder) {
        int removeAllMenuItems;
        removeAllMenuItems = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(tableOrder)) {orders[i] = null;
            removeAllMenuItems++;
            }
        }
        if(removeAllMenuItems > 0) return removeAllMenuItems;
        return (-1);
    }

    public int[] getFreeTables() {
        return getTables(x -> x == null);
    }

    public int[] getNonFreeTables() {
        return getTables(x -> x != null);
    }

    public int[] getTables(Predicate<TableOrder> predicate){
        int[] result = null;
        if (capacity !=0) {
            result = new int[capacity];
            int j = 0;
            for (int i = 0; i < capacity; i++) {
                if (predicate.test(orders[i])) {
                    result[j] = i;
                    j++;
                }
            }
        }
        return result;
    }

public int midlCost500(){
        int midlCost500;
    midlCost500 =0;
        for (int i = 0; i < capacity; i++){
            if (orders[i].costTotal() / orders[i].itemQuantity()  >= MIDLE_COST  ) midlCost500++;
        }
        return midlCost500;
}


    public TableOrder[] getOrders() {
        TableOrder[] getOrders = null;
        if (capacity != 0) {
            getOrders = new TableOrder[capacity];
            int j = 0;
            for (int i = 0; i < orders.length; i++) {
                if (orders[i] != null) {
                    getOrders[j] = orders[i];
                    j++;
                }
            }
            return getOrders;
        }
        return getOrders;
    }

    public int ordersCostSummary() {
        int cents = 0;
        TableOrder[] ordersTempArray = getOrders();
        if (ordersTempArray != null) {
            for (int i = 0; i < ordersTempArray.length; i++) {
                cents += ordersTempArray[i].costTotal();
            }
        }
        return cents;
    }

    public int ordersQuantity() {
        int ordersQuantity;
        ordersQuantity = 0;
        for (int i = 0; i < orders.length ; i++)
            if (orders[i] != null) ordersQuantity++;
        return ordersQuantity;
    }

    public int getDishesCountInOrders() {
        int getDishesCountInOrders = 0;
        TableOrder[] ordersTempArray = getOrders();
        if (ordersTempArray != null) {
            for (int i = 0; i < ordersTempArray.length; i++) {
                getDishesCountInOrders += ordersTempArray[i].itemQuantity();
            }
        }
        return getDishesCountInOrders;
    }



    private boolean isValidNumber(int number) {
        if (number > -1 & number < orders.length) {
            return true;
        } else {
            return false;
        }
    }

    private double transferInRub(int cents) {
        return (((double) cents) / 100);
    }

    //odo аналогично InternetOrderManager COMPLITED
    public int itemQuantity(String name)
    {
        int itemQuantity = 0;
        TableOrder[] ordersTmp = getOrders();
        if(ordersTmp != null)
        {
            for(int i=0; i<ordersTmp.length; i++)
            {
                itemQuantity += ordersTmp[i].getDishCount(name);
            }
            return itemQuantity;
        }
        return itemQuantity;
    }

    //odo аналогично InternetOrderManager COMPLITED

    public int itemQuantity(MenuItem menuItem)
    {
        int itemQuantity = 0;
        TableOrder[] ordersTmp = getOrders();
        if(ordersTmp != null)
        {
            for(int i=0; i<ordersTmp.length; i++)
            {
                itemQuantity += ordersTmp[i].getDishCount(menuItem);
            }
            return itemQuantity;
        }
        return itemQuantity;
    }

    @Override
    public int countMenuItemsNowDay(LocalDate localDate) {
        int countMenuItemsNowDay = 0;
        LocalDate help;
        help = orders[1].getLocalDateTime().toLocalDate();
        for (int i = 0; i < orders.length ; i++) {
            if (localDate.equals(orders[i].getLocalDateTime().toLocalDate()))countMenuItemsNowDay++;
        }
        return countMenuItemsNowDay;
    }

    @Override
    public InternetOrderManager getMenuItemNowDay(LocalDate localDate){
        InternetOrderManager internetOrderManager = new InternetOrderManager();
        for (int i = 0; i < orders.length; i++) {
            if(orders[i].getLocalDateTime().toLocalDate().equals(localDate)) internetOrderManager.push(orders[i]);
        }
        return internetOrderManager;
    }

    public InternetOrderManager getMenuItemsCustomer(Customer customer){
        InternetOrderManager internetOrderManager = new InternetOrderManager();
        for (int i = 0; i < orders.length; i++) {
            if(orders[i].getCustomer().equals(customer))internetOrderManager.push(orders[i]);
        }
        return internetOrderManager;
    }
}
