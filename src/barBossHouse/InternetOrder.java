package barBossHouse;

import java.time.LocalDateTime;
import java.util.Arrays;


// odo переписать список СДЕЛАНО
public class InternetOrder implements Order {
    private int size;
    private ListNode head;
    private ListNode tail;
    private Customer customer;
    private LocalDateTime localDateTime;

    public InternetOrder() {
    }

    public InternetOrder(Customer customer) {
        this.localDateTime = LocalDateTime.now();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(MenuItem menuItem) {
        ListNode listNode = new ListNode();
        listNode.setValue(menuItem);
        if (head == null)
            head = listNode;
        else {
            tail.setNext(listNode);
            listNode.setPrev(tail);
        }
        tail = listNode;
        size++;
        return true;
    }

    public boolean remove(String dishName) {
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().getName().equals(dishName)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
                return true;
            }
            listNode = listNode.getNext();
        }
        return false;
    }

    public boolean remove(MenuItem menuItem) {
        //odo хранишь ссылку на предыдущий нод COMPLITED
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().equals(menuItem)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
                return true;
            }
            listNode = listNode.getNext();
        }
        return false;
    }

    public int removeAll(String dishName) {
        //todo хранишь ссылку на предыдущий нод COMPLITED
        //todo без вызова remove - идешь по нодам, сравниваешь имена и сразу удаляешь COMPLITED
        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().getName().equals(dishName)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
            }
            listNode = listNode.getNext();
        }
        return deletedDishCount;
    }

    public int removeAll(MenuItem menuItem) {
        //todo хранишь ссылку на предыдущий нод COMPLITED
        //todo без вызова remove - идешь по нодам, сравниваешь имена и сразу удаляешь COMPLITED
        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().equals(menuItem)) {
                if (listNode == head) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                } else if (listNode == tail) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                } else {
                    listNode.getPrev().setNext(listNode.getNext());
                    listNode.getNext().setPrev(listNode.getPrev());
                }
                size--;
            }
            listNode = listNode.getNext();
        }
        return deletedDishCount;
    }

    public int itemQuantity() {
        return size;
    }

    public MenuItem[] getItems() {
        ListNode listNode;
        MenuItem[] menuItems = new MenuItem[size];
        listNode = tail;
        for (int i = 0; i < size; i++) {
            menuItems[i] = listNode.getValue();
            listNode = listNode.getNext();
        }
        return menuItems;
    }

    public int costTotal() {
        int allCost;
        ListNode listNode;
        allCost = 0;
        listNode = tail;
        for (int i = 0; i < size; i++) {
            allCost += listNode.getValue().getCost();
            listNode = listNode.getNext();
        }
        return allCost;
    }

    public int itemQuantity(String NameInArray) {
        ListNode listNode;
        int allNameInArray;
        allNameInArray = 0;
        listNode = tail;
        for (int i = 0; i < size; i++) {
            if (listNode.getValue().getName().equals(NameInArray)) allNameInArray++;
            listNode = listNode.getNext();
        }
        return allNameInArray;
    }

    public int itemQuantity(MenuItem menuItem) {
        ListNode listNode;
        int allDishInArray;
        allDishInArray = 0;
        listNode = tail;
        for (int i = 0; i < size; i++) {
            if (listNode.getValue().equals(menuItem)) allDishInArray++;
            listNode = listNode.getNext();
        }
        return allDishInArray;
    }

    public String[] itemsName() {
        String[] strings = new String[0];
        if (size != 0) {
            //TODO рефакторь COMPLITED
            int index = 0;
            strings = new String[size];
            ListNode listNode = head;
            boolean addName = false;
            listNode = head;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (strings[j] == null) break;
                    if (listNode.getValue().getName().equals(strings[j])) {
                        addName = true;
                        break;
                    }
                }
                if (!addName) {
                    strings[index] = listNode.getValue().getName();
                    index++;
                }
                addName = false;
                listNode = listNode.getNext();
            }
            strings = cutString(strings);
        }
        return strings;
    }

    public String[] cutString(String[] strings)
    {
        int index = 0;
        String[] cutString = new String[0];
        for (int i = 0; i < strings.length; i++) {
            if(strings[i] == null) break;
            else index++;
        }
        if (index != 0) {cutString = new String[index];
            System.arraycopy(strings, 0, cutString, 0, index);
        }
        return cutString;
    }

    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] menuItems = getItems();
        TableOrder.quickSortDishes(menuItems, 0, size - 1);
        return menuItems;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(":").append(size).append("\n").append(getCustomer().toString()).append("\n");
        ListNode listNode = head;
        while (listNode != null) {
            stringBuilder.append(listNode.getValue().toString()).append("\n");
            listNode = listNode.getNext();
        }
        return stringBuilder.toString();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        InternetOrder internetOrder = (InternetOrder) obj;

        return (customer.equals(((TableOrder) obj).getCustomer()) & internetOrder.size == internetOrder.itemQuantity());
    }

    @Override
    public int hashCode() {
        return customer.hashCode()
                ^ size
                ^ head.getValue().hashCode()
                ^ tail.getValue().hashCode()
                ^ localDateTime.hashCode();
    }
}
