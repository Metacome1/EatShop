package io;

import barBossHouse.InternetOrderManager;
import barBossHouse.Order;

import java.util.Collection;
import java.util.Iterator;

//todo аналогично ControlledTableOrderManager
public class ControlledInternetOrderManager extends InternetOrderManager {

    protected Source<Order> orderSource;

    public ControlledInternetOrderManager(){
        super();
    }

    public ControlledInternetOrderManager(Order[] orders){
        super(orders);
    }

    public Source<Order> getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Source<Order> orderSource) {
        this.orderSource = orderSource;
    }

    @Override
    public boolean add(Order order) {
        ControlledTableOrder controlledDepartment =
                new ControlledTableOrder(order);
        orderSource.create(controlledDepartment);

        return super.add(order);
    }

    @Override
    public int remove(Order order){
        orderSource.delete(order);

        return super.remove(order);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        for (Order order: c) {
            ControlledTableOrder controlledDepartment =
                    new ControlledTableOrder(order);
            orderSource.create(controlledDepartment);
        }
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o: c) {
            if(super.contains(o)){
                orderSource.delete((Order) o);
            }
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (Order o: (Order[]) super.toArray()) {
            if(!c.contains(o)){
                orderSource.delete((Order) o);
            }
        }
        return super.retainAll(c);
    }

    public void store(){
        Iterator iterator = super.iterator();
        while(iterator.hasNext()){
            ControlledTableOrder controlledTableOrder = (ControlledTableOrder) iterator.next();
            if(controlledTableOrder.isChanged)
                orderSource.store(controlledTableOrder);
        }
    }

    public void load(){
        Iterator iterator = super.iterator();
        while(iterator.hasNext()){
            ControlledTableOrder controlledTableOrder = (ControlledTableOrder) iterator.next();
            orderSource.load(controlledTableOrder);
        }
    }

}
