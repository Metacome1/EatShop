package io;

import barBossHouse.Customer;
import barBossHouse.InternetOrder;
import barBossHouse.MenuItem;

public class ControlledInternetOrder extends InternetOrder {

    protected boolean isChanged = false;

    public ControlledInternetOrder() {
        super();
    }

    public ControlledInternetOrder(Customer customer, MenuItem[] menuItems) {
        super(customer, menuItems);
    }

    public ControlledInternetOrder(InternetOrder internetOrder) {
        super(internetOrder.getCustomer(), internetOrder.getItems());
    }

    protected boolean isChanged(){
        return isChanged;
    }

/* todo по заданию
   а также переопределяет методы, которые так или иначе меняют состояние класса
   (изменение, добавление, удаление позиций заказа, изменение клиента, времени заказа)
   – в этих методах сначала вызывается реализация метода в суперклассе, а затем изменяется isChanged на true.
 */

}
