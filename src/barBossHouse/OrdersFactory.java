package barBossHouse;

public abstract class OrdersFactory {

    abstract Order createInternetOrder();
    abstract Order createInternetOrder(Customer customer, MenuItem[] menuItems);

    abstract Order createTableOrder();
    abstract Order createTableOrder(int dishCount, Customer customer);
    abstract Order createTableOrder(MenuItem[] dishesArray, Customer customer);


    abstract OrdersManager createInternetOrderManager();
    abstract OrdersManager createInternetOrderManager(Order[] orders);

    abstract OrdersManager createTableOrdersManager();
    abstract OrdersManager createTableOrdersManager(int tablesCount);


    static public OrdersFactory getOrdersFactory(OrdersFactoryTypesEnumeration type){
        switch (type){
            case ORDINARY_ORDERS_FACTORY:
                return new OrdinaryOrdersFactory();
            case TEXT_FILE_BASED_ORDERS_FACTORY:
                return new TextFileBasedOrdersFactory();
            case BINARY_FILE_BASED_ORDERS_FACTORY:
                return new BinaryFileBasedOrdersFactory();
            case SERIALIZED_FILE_BASED_ORDERS_FACTORY:
                return new SerializedFileBasedOrdersFactory();
                //
            case SOCKET_BASED_ORDERS_FACTORY:
                //todo И все же непонятно, что тут происходит и должно происходить
                //todo  ОТВЕТ - этой ветви пока вообще быть не должно, это на будущее - лабу по сокетам
                return new OrdinaryOrdersFactory();
        }
        return new OrdinaryOrdersFactory();
    }

}
