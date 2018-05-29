package io;

import barBossHouse.Customer;
import barBossHouse.MenuItem;
import barBossHouse.Order;
import barBossHouse.TableOrder;

import java.time.LocalDateTime;

public class ControlledTableOrder extends TableOrder {

    protected boolean isChanged = false;

    public ControlledTableOrder() {
        super();
    }

    public ControlledTableOrder(int dishCount, Customer customer) {
        super(dishCount, customer);

    }

    public ControlledTableOrder(MenuItem[] dishesArray, Customer customer){
        super(dishesArray, customer);
    }

    public ControlledTableOrder(Order order){
        super(order.getItems(), order.getCustomer());
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
