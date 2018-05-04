package barBossHouse;

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
        QueueNode queueNode = new QueueNode();
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
            itemQuantity+= queueNode.getValue().itemQuantity(string);
            queueNode = queueNode.getNext();
        }
        return itemQuantity;
    }

    public int itemQuantity(MenuItem menuItem) {
        int itemQuantity = 0;
        QueueNode queueNode = head;
        while (queueNode != null) {
            itemQuantity+= queueNode.getValue().itemQuantity(menuItem);
            queueNode = queueNode.getNext();
        }
        return itemQuantity;
    }
}
