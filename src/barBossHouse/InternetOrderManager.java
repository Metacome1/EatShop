package barBossHouse;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.time.LocalDate;
import java.util.*;

public class InternetOrderManager implements OrdersManager, Deque<Order> {
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

    @Override
    public void addFirst(Order order) {
        QueueNode queueNode = new QueueNode();
        if (head != null) {
            queueNode.setValue(order);
            head.setPrev(queueNode);
            queueNode.setNext(head);
        }
        head = queueNode;
        size++;
    }


    @Override
    public void addLast(Order order) {
        QueueNode queueNode = new QueueNode();
        if (tail != null) {
            queueNode.setValue(order);
            tail.setNext(queueNode);
            queueNode.setPrev(tail);
        }
        else {tail.setPrev(head);}
        tail = queueNode;
        size++;
    }

    @Override
    public Order getFirst() {
        if (size == 0) throw new NoSuchElementException();
        return head.getValue();
    }

    @Override
    public Order getLast() {
        if (size == 0 || size == 1) throw new NoSuchElementException();
        return tail.getValue();
    }

    @Override
    public boolean offerFirst(Order order) {
        if (contains(order)) return false;
        addFirst(order);
        return true;
    }

    @Override
    public boolean offerLast(Order order) {
        if (contains(order)) return false;
        addLast(order);
        return true;
    }

    @Override
    public Order removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Order order = head.getValue();
        if (size == 1)  head = null;
        else {
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        size--;
        return order;
    }

    @Override
    public Order removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Order order = tail.getValue();
        if (size == 1)  tail = null;
        else {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        }
        size--;
        return order;
    }

    @Override
    public Order pollFirst() {
        if (size == 0) return null;
        Order order = head.getValue();
        if (size == 1)  head = null;
        else {
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        size--;
        return order;
    }

    @Override
    public Order pollLast() {
        if (size == 0) return null;
        Order order = tail.getValue();
        if (size == 1)  tail = null;
        else {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        }
        size--;
        return order;
    }

    @Override
    public Order peekFirst() {
        if (size == 0) throw new NoSuchElementException();
        return head.getValue();
    }

    @Override
    public Order peekLast() {
        if (size == 0) throw new NoSuchElementException();
        return tail.getValue();
    }

    @Override
    public Order pop() {
        if (size == 0) throw new NoSuchElementException();
        Order order = head.getValue();
        if (size == 1)  head = null;
        else {
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        size--;
        return order;
    }
    @Override
    public boolean removeFirstOccurrence(Object o) {
        QueueNode queueNode = head;

        while (queueNode != null) {
            if (queueNode.getValue().equals(o)) {
                queueNode.getNext().setPrev(null);
                head = queueNode.getNext();
            } else if (queueNode == tail) {
                queueNode.getPrev().setNext(null);
                tail = queueNode.getPrev();
            } else {
                queueNode.getPrev().setNext(queueNode.getNext());
                queueNode.getNext().setPrev(queueNode.getPrev());
            }
            size--;
            queueNode = queueNode.getNext();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        QueueNode queueNode = tail;

        while (queueNode != null) {
            if (queueNode.getValue().equals(o)) {
                queueNode.getNext().setPrev(null);
                head = queueNode.getNext();
            } else if (queueNode == tail) {
                queueNode.getPrev().setNext(null);
                tail = queueNode.getPrev();
            } else {
                queueNode.getPrev().setNext(queueNode.getNext());
                queueNode.getNext().setPrev(queueNode.getPrev());
            }
            size--;
            queueNode = queueNode.getPrev();
            return true;
        }
        return false;
    }

    @Override
    public boolean offer(Order order) {
        if (!contains(order)) {
            QueueNode queueNode = new QueueNode();
            if (tail != null) {
                queueNode.setValue(order);
                tail.setNext(queueNode);
                queueNode.setPrev(tail);
            } else {
                tail.setPrev(head);
            }
            tail = queueNode;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public Order remove() {
        if (size == 0) throw new NoSuchElementException();
        Order order = head.getValue();
        if (size == 1)  head = null;
        else {
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        size--;
        return order;
    }

    @Override
    public Order poll() {
        if (size == 0) return null;
        Order order = head.getValue();
        if (size == 1)  head = null;
        else {
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        size--;
        return order;
    }

    @Override
    public Order element() {
        if (size == 0) throw new NoSuchElementException();
        return head.getValue();
    }

    @Override
    public Order peek() {
        if (size == 0) return null;
        return head.getValue();
    }

    public void push(Order order) {
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
    }


    @Override
    public Iterator<Order> descendingIterator() {
        return null;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Order order: this) {
            if (order.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            QueueNode queueNode = head;
            int pos = 0;

            public boolean hasNext() {
                return size > pos;
            }

            public Order next() {
                Order order = queueNode.getValue();
                if (pos != size) {
                    queueNode = queueNode.getNext();
                    pos++;
                }
                return order;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return getOrders();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(getOrders(), size(), a.getClass());
    }

    @Override
    public boolean add(Order order) {
        QueueNode queueNode = new QueueNode();
        queueNode.setValue(order);
        if (head == null)
            head = queueNode;
        else {
            tail.setNext(queueNode);
            queueNode.setPrev(tail);
        }
        tail = queueNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        boolean addAll = true;
        for (Order order: c) {
            addAll &= add(order);
        }
        return addAll;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        QueueNode queueNode = head;
        boolean changed = false;
        while(queueNode != null) {
            QueueNode nextNode = queueNode.getNext();

            if(c.contains(queueNode.getValue())){
                remove(queueNode);
                changed = true;
            }
            queueNode = nextNode;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        QueueNode queueNode = head;
        boolean changed = false;
        while(queueNode != null) {
            QueueNode nextNode = queueNode.getNext();

            if(!c.contains(queueNode.getValue())){
                remove(queueNode);
                changed = true;
            }
            queueNode = nextNode;
        }
        return changed;
    }


    @Override
    public void clear() {
        QueueNode queueNode = head;
        while (queueNode != tail){
            QueueNode nextNode = queueNode.getNext();
            remove(queueNode);
            queueNode = nextNode;
        }
        head = null;
        tail = null;
    }
}
