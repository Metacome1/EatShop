package barBossHouse;

import java.time.LocalDate;
import java.util.Collection;

public interface OrdersManager extends Collection<Order> {

    int itemQuantity (String str);

    int itemQuantity (MenuItem menuItem);

    Order[] getOrders();

    int ordersCostSummary();

    int ordersQuantity();

    int countMenuItemsNowDay(LocalDate localDate);

    InternetOrderManager getMenuItemNowDay(LocalDate localDate);

    InternetOrderManager getMenuItemsCustomer(Customer customer);
}
