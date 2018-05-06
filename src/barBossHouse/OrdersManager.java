package barBossHouse;

import java.time.LocalDate;

public interface OrdersManager {

    int itemQuantity (String str);

    int itemQuantity (MenuItem menuItem);

    Order[] getOrders();

    int ordersCostSummary();

    int ordersQuantity();

    int countMenuItemsNowDay(LocalDate localDate);

    InternetOrderManager getMenuItemNowDay(LocalDate localDate);

    InternetOrderManager getMenuItemsCustomer(Customer customer);
}
