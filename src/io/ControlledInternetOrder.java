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



}
