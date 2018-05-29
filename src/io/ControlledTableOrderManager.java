package io;

import barBossHouse.MenuItem;
import barBossHouse.Order;
import barBossHouse.TableOrdersManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

public class ControlledTableOrderManager extends TableOrdersManager {


    protected Source<Order> orderSource;

    public ControlledTableOrderManager() {
        super();
    }

    public ControlledTableOrderManager(int tablesCount) {
        super(tablesCount);
    }
    //todo добавь коструктор, который принимает source и OrderFactory, и храни их в полях класса

    public Source<Order> getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Source<Order> orderSource) {
        this.orderSource = orderSource;
    }

    @Override
    public boolean add(Order order) {
        //todo копипастер хренов! Какой нафиг controlledDepartment
        ControlledTableOrder controlledDepartment =
                new ControlledTableOrder(order);//todo а вот создание объекта - с помощью фабрики делается, а не конструктора
        orderSource.create(controlledDepartment); //todo вот именно в этом классе и вызываются методы source, а не в фабрике!
        return super.add(order);
    }


    @Override
    public int remove(Order order) {
        orderSource.delete(order);

        return super.remove(order);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        //todo копипастер хренов! Какой нафиг controlledDepartment
        for (Order order : c) {
            ControlledTableOrder controlledDepartment =
                    new ControlledTableOrder(order);//todo а вот создание объекта - с помощью фабрики делается, а не конструктора
            orderSource.create(controlledDepartment);
        }

        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (super.contains(o)) {
                orderSource.delete((Order) o);
            }
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (Order o : super.getOrders()) {
            if (!c.contains(o)) {
                orderSource.delete((Order) o);
            }
        }
        return super.retainAll(c);
    }

    /*todo нада все методы, добавления\удаления\изменения\создания переопределить:
    @Override
    public boolean addDishToOrder(int tableNumber, MenuItem menuItem) {
        return super.addDishToOrder(tableNumber, menuItem);
    }

    @Override
    public void freeTable(int tableNumber) {
        super.freeTable(tableNumber);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        return super.addAll(index, c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Order remove(int index) {
        return super.remove(index);
    }

    @Override
    public List<Order> subList(int fromIndex, int toIndex) {
        return super.subList(fromIndex, toIndex);
    }
    */


    public void store() {
        Iterator iterator = super.iterator();
        while (iterator.hasNext()) {
            ControlledTableOrder controlledTableOrder = (ControlledTableOrder) iterator.next();
            if (controlledTableOrder.isChanged)
                orderSource.store(controlledTableOrder);
        }
    }

    public void load() {
        Iterator iterator = super.iterator();
        while (iterator.hasNext()) {
            ControlledTableOrder controlledTableOrder = (ControlledTableOrder) iterator.next();
            orderSource.load(controlledTableOrder);
        }
    }

}
