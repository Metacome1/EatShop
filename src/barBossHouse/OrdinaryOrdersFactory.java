package barBossHouse;

import io.OrderManagerFileSource;

public class OrdinaryOrdersFactory extends OrdersFactory {

    private OrderManagerFileSource source;


    public OrdinaryOrdersFactory() {
        this.source = source;
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
    Order createInternetOrder(){
        InternetOrder internetOrder = new InternetOrder();
        source.create(internetOrder);
        return internetOrder;
    }

    @Override
    Order createInternetOrder(Customer customer, MenuItem[] menuItems){
        InternetOrder internetOrder = new InternetOrder(customer, menuItems);
        source.create(internetOrder);
        return internetOrder;
    }

    @Override
    Order createTableOrder(){
        TableOrder tableOrder = new TableOrder();
        source.create(tableOrder);
        return tableOrder;
    }

    @Override
    Order createTableOrder(int dishCount, Customer customer){
        TableOrder tableOrder = new TableOrder(dishCount, customer);
        source.create(tableOrder);
        return tableOrder;

    }

    @Override
    Order createTableOrder(MenuItem[] dishesArray, Customer customer){
        TableOrder tableOrder = new TableOrder(dishesArray, customer);
        source.create(tableOrder);
        return tableOrder;
    }

    @Override
    OrdersManager createInternetOrderManager(){
        InternetOrderManager internetOrderManager = new InternetOrderManager();
        //todo  sourse.create(internetOrderManager) по-моему по заданию чего-то недописано
        return internetOrderManager;
    }

    @Override
    OrdersManager createInternetOrderManager(Order[] orders){
        InternetOrderManager internetOrderManager = new InternetOrderManager(orders);
        //todo  sourse.create(internetOrderManager) Или я тупой
        return internetOrderManager;
    }

    @Override
    OrdersManager createTableOrdersManager(){
        TableOrdersManager tableOrdersManager = new TableOrdersManager();
        //todo  sourse.create(tableOrdersManager) или слепой
        return tableOrdersManager;
    }

    @Override
    OrdersManager createTableOrdersManager(int tablesCount){
        TableOrdersManager tableOrdersManager = new TableOrdersManager(tablesCount);
        //todo  sourse.create(tableOrdersManager) все таки больше склоняюсь, что задание недописано
        return tableOrdersManager;
    }

}
