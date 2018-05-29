package barBossHouse;

import java.time.LocalDate;
import java.util.Collection;
//todo удали дубли методов отсюда и из реализации (методы Collection первичные). Подсказки смотри ниже в виде комментов
public interface OrdersManager extends Collection<Order> {

    int itemQuantity (String str);

    int itemQuantity (MenuItem menuItem);

    Order[] getOrders(); //toArray()

    int ordersCostSummary();

    int ordersQuantity(); //size()

    int countMenuItemsNowDay(LocalDate localDate);

    InternetOrderManager getMenuItemNowDay(LocalDate localDate);

    InternetOrderManager getMenuItemsCustomer(Customer customer);
}
