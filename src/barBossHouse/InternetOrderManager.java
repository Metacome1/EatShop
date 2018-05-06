package barBossHouse;

import java.time.LocalDate;

public class InternetOrderManager implements OrdersManager {
    private QueueNode head;
    private QueueNode tail;
    private int size = 0;

    public InternetOrderManager() {

    }

    public InternetOrderManager(Order[] orders) {
        for (Order order : orders) {
            push(order);
        }
    }

    public boolean push(Order order) {
        QueueNode queueNode = head;
        while (head != null) {
            if (queueNode.getValue().getCustomer().equals(order.getCustomer()) && queueNode.getValue().getLocalDateTime().equals(order.getLocalDateTime())) throw new AlreadyAddedException("Этот заказ уже существует");
            queueNode = queueNode.getNext();
        }
        queueNode = new QueueNode();
        queueNode.setValue(order);
        if (head == null) {
            head = queueNode;
        } else {
            tail.setNext(queueNode);
            queueNode.setPrev(tail);
        }
        tail = queueNode;
        size++;
        return true;
    }

    public Order getFirstOrder() {
        return head.getValue();
    }

    public Order pull() {
        if (size == 0) {
            return null;
        }
        Order order = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return order;
    }


    public int ordersQuantity() {
        return size;
    }

    public int ordersCostSummary() {
        int allCost;
        QueueNode queueNode;
        allCost = 0;
        queueNode = head;
        for (int i = 0; i < size; i++) {
            allCost += queueNode.getValue().costTotal();
            queueNode = queueNode.getNext();
        }
        return allCost;
    }

    public Order[] getOrders() {
        Order[] orders = new Order[size];
        QueueNode queueNode = head;
        while (queueNode != null) {
            for (int i = 0; i < orders.length; i++) {
                orders[i] = queueNode.getValue();
            }
            queueNode = queueNode.getNext();
        }
        return orders;
    }

    public int itemQuantity(String string) {
        int itemQuantity = 0;
        QueueNode queueNode = head;
        while (queueNode != null) {
            itemQuantity += queueNode.getValue().itemQuantity(string);
            queueNode = queueNode.getNext();
        }
        return itemQuantity;
    }

    public int itemQuantity(MenuItem menuItem) {
        int itemQuantity = 0;
        QueueNode queueNode = head;
        while (queueNode != null) {
            itemQuantity += queueNode.getValue().itemQuantity(menuItem);
            queueNode = queueNode.getNext();
        }
        return itemQuantity;
    }

    @Override
    public int countMenuItemsNowDay(LocalDate localDate) {
        int countMenuItemsNowDay = 0;
        QueueNode queueNode = head;
        while (queueNode != null) {
            if (localDate.equals(queueNode.getValue().getLocalDateTime().toLocalDate())) countMenuItemsNowDay++;
            queueNode = queueNode.getNext();
        }
        return countMenuItemsNowDay;
    }

    @Override
    public InternetOrderManager getMenuItemNowDay(LocalDate localDate) {
        InternetOrderManager internetOrderManager = new InternetOrderManager();
        QueueNode queueNode = head;
        while (queueNode != null) {
            if (localDate.equals(queueNode.getValue().getLocalDateTime().toLocalDate()))
                internetOrderManager.push(queueNode.getValue());
            queueNode = queueNode.getNext();
        }
        return internetOrderManager;
    }

    @Override
    public InternetOrderManager getMenuItemsCustomer(Customer customer){
        InternetOrderManager internetOrderManager = new InternetOrderManager();
        QueueNode queueNode = head;
        while (queueNode != null) {
            if (queueNode.getValue().getCustomer().equals(customer))
                internetOrderManager.push(queueNode.getValue());
            queueNode = queueNode.getNext();
        }
        return internetOrderManager;
    }
}
