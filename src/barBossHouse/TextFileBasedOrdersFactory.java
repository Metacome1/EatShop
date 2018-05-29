package barBossHouse;

import io.*;

//todo объясню на примере этой фабрики. В остальных аналогично.
public class TextFileBasedOrdersFactory extends OrdersFactory {

    //todo Во-первых, Здесь хранишь не source, а ссылку на PATH!!

    private OrderManagerTextFileSource source;

    public TextFileBasedOrdersFactory() {
        source = new OrderManagerTextFileSource();
    }

    public void setPath(String path)
    {
        source.setPath(path);
    }
    public String getPath()
    {
        return source.getPath();
    }

    //todo во-вторых, вызывать source.create() при создании Order в фабрике НЕ НАДО - его вызываешь в OrderManager, в вызовах методо Add, AddAll
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

    /*todo в третьих, в методах создания OrderManager-ов,
      1) нужно создавать соответсвующий source (путь берется из атрибута фабрики Path)
      2) в OrderManager ты передаешь ссылку на этот source, и на ЭТУ ФАБРИКУ (this)
      3) фабрика будет использоваться OrderManager-ом для создания ордеров
      4) а потом, логично нужно вызывать sourse.create(controlledInternetOrderManager)
      при вызове каждого метода создания Ордерменеджера будет создаваться новый сурс
      Далее смотри todoшки в controlledTableOrderManager
     */
    @Override
    OrdersManager createInternetOrderManager(){
        ControlledInternetOrderManager controlledInternetOrderManager = new ControlledInternetOrderManager();
        return controlledInternetOrderManager;
    }

    @Override
    OrdersManager createInternetOrderManager(Order[] orders){
        ControlledInternetOrderManager controlledInternetOrderManager = new ControlledInternetOrderManager(orders);
        return controlledInternetOrderManager;
    }

    @Override
    OrdersManager createTableOrdersManager(){
        ControlledTableOrderManager controlledTableOrderManager = new ControlledTableOrderManager();
        return controlledTableOrderManager;
    }

    @Override
    OrdersManager createTableOrdersManager(int tablesCount){
        ControlledTableOrderManager controlledTableOrderManager = new ControlledTableOrderManager(tablesCount);
        return controlledTableOrderManager;
    }

}
