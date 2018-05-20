package barBossHouse;

import io.*;

public class SerializedFileBasedOrdersFactory extends OrdersFactory {

    private OrderManagerSerializedFileSource source;

    public SerializedFileBasedOrdersFactory() {
        source = new OrderManagerSerializedFileSource();
    }

    public void setPath(String path)
    {
        source.setPath(path);
    }
    public String getPath()
    {
        return source.getPath();
    }

    @Override
    Order createInternetOrder() {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder();
        source.create(controlledInternetOrder);
        return controlledInternetOrder;
    }

    @Override
    Order createInternetOrder(Customer customer, MenuItem[] menuItems) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(customer, menuItems);
        source.create(controlledInternetOrder);
        return controlledInternetOrder;
    }

    @Override
    Order createTableOrder(){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder();
        source.create(controlledTableOrder);
        return controlledTableOrder;
    }

    @Override
    Order createTableOrder(int dishCount, Customer customer){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(dishCount, customer);
        source.create(controlledTableOrder);
        return controlledTableOrder;

    }

    @Override
    Order createTableOrder(MenuItem[] dishesArray, Customer customer){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(dishesArray, customer);
        source.create(controlledTableOrder);
        return controlledTableOrder;
    }

    @Override
    OrdersManager createInternetOrderManager(){
        ControlledInternetOrderManager controlledInternetOrderManager = new ControlledInternetOrderManager();
        //todo  sourse.create(controlledInternetOrderManager) по-моему по заданию чего-то недописано
        return controlledInternetOrderManager;
    }

    @Override
    OrdersManager createInternetOrderManager(Order[] orders){
        ControlledInternetOrderManager controlledInternetOrderManager = new ControlledInternetOrderManager(orders);
        //todo  sourse.create(controlledInternetOrderManager) Или я тупой
        return controlledInternetOrderManager;
    }

    @Override
    OrdersManager createTableOrdersManager(){
        ControlledTableOrderManager controlledTableOrderManager = new ControlledTableOrderManager();
        //todo  sourse.create(controlledTableOrderManager) или слепой
        return controlledTableOrderManager;
    }

    @Override
    OrdersManager createTableOrdersManager(int tablesCount){
        ControlledTableOrderManager controlledTableOrderManager = new ControlledTableOrderManager(tablesCount);
        //todo  sourse.create(controlledTableOrderManager) все таки больше склоняюсь, что задание недописано
        return controlledTableOrderManager;
    }
}
