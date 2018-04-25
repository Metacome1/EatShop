package barBossHouse;

public interface OrdersManager {

    int itemQuantity (String str);

    int itemQuantity (MenuItem menuItem);

    Order[] getOrders();

    int ordersCostSummary();

    int ordersQuantity();
}
