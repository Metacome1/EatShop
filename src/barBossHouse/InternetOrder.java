package barBossHouse;

import java.lang.reflect.Array;
import java.util.Arrays;

public class InternetOrder implements Order {
    private int size;
    private ListNode head;
    private ListNode tail                                                                                                                                       ;
    private Customer customer;

    public InternetOrder() {
    }

    public InternetOrder(Customer customer) {
        this.customer = customer;
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
        ListNode listNode = head;
        while (listNode != null) {
            if (listNode.getValue().equals(menuItem)) {
                if (listNode.getPrev() == null) {
                    listNode.getNext().setPrev(null);
                    head = listNode.getNext();
                }
                if (listNode.getNext() == null) {
                    listNode.getPrev().setNext(null);
                    tail = listNode.getPrev();
                }
                if (listNode.getPrev() != null && listNode.getNext() != null) {
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
        //todo хранишь ссылку на предыдущий нод
        //todo без вызова remove - идешь по нодам, сравниваешь имена и сразу удаляешь
        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
           if (remove(dishName)){
               deletedDishCount++;
           }
            listNode = listNode.getNext();
        }
        return deletedDishCount;
    }

    public int removeAll(MenuItem menuItem) {
        //todo хранишь ссылку на предыдущий нод
        //todo без вызова remove - идешь по нодам, сравниваешь имена и сразу удаляешь
        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
            if (remove(menuItem)){
                deletedDishCount++;
            }
            listNode = listNode.getNext();
        }
        return deletedDishCount;
    }

    public int itemQuantity() {
        return size;
    }

    public MenuItem[] getItems(){
        ListNode listNode;
        MenuItem[] menuItems = new MenuItem[size];
        listNode = tail;
        for (int i = 0; i < size; i++){
            menuItems[i] = listNode.getValue();
            listNode = listNode.getNext();
        }
        return  menuItems;
    }

    public int costTotal(){
        int allCost;
        ListNode listNode;
        allCost = 0;
        listNode = tail;
        for (int i = 0; i < size; i++){
            allCost += listNode.getValue().getCost();
            listNode = listNode.getNext();
        }
        return allCost;
    }

    public int itemQuantity(String name){
        ListNode listNode;
        int allNameInArray;
        allNameInArray = 0;
        listNode = tail;
        for (int i = 0; i < size; i++){
            if (listNode.getValue().getName().equals(name))allNameInArray++;
            listNode = listNode.getNext();
        }
        return allNameInArray;
    }

    public int itemQuantity(MenuItem menuItem){
        ListNode listNode;
        int allDishInArray;
        allDishInArray = 0;
        listNode = tail;
        for (int i = 0; i < size; i++){
            if (listNode.getValue().equals(menuItem))allDishInArray++;
            listNode = listNode.getNext();
        }
        return allDishInArray;
    }

    public String[] itemsName() {
        String[] itemsName = new String[size];
        String[] helpMe = new String[0];
        if (size != 0) {
            //TODO рефакторь
            int index = 0;
            String[] strings = new String[size];
            ListNode listNode = head;
            while (listNode != null) {
                strings[index] = listNode.getValue().getName();
                listNode = listNode.getNext();
                index++;
            }
            index = 0;
            boolean addName = false;
             listNode = head;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (itemsName[j] == null) break;
                    if (listNode.getValue().getName().equals(strings[j])) addName = true;
                }
                if (!addName) {
                    itemsName[index] = listNode.getValue().getName();
                    index++;
                }
                addName = false;
                listNode = listNode.getNext();
            }
            helpMe = new String[index];
            System.arraycopy(itemsName, 0, helpMe, 0, index);
            return helpMe;
        }
        return helpMe;
    }

    public Customer getCustomer (){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public MenuItem[] sortedItemsByCostDesc(){
            MenuItem[] menuItems = getItems();
            TableOrder.quickSortDishes(menuItems, 0, size - 1);
            return menuItems;
    }

    @Override
    public String toString() {
        String string = new String();
        ListNode listNode = head;
        while (listNode != null) {
            string += listNode.getValue().toString() + "\n";
            listNode = listNode.getNext();
        }
        return getClass().getSimpleName()
                + ":"
                + (size)
                + "\n"
                + (getCustomer().toString())
                + "\n"
                + string;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        InternetOrder internetOrder = (InternetOrder) obj;

        return (customer.equals(((TableOrder) obj).getCustomer()) & internetOrder.size == internetOrder.itemQuantity());
    }
@Override
    public int hashCode(){
    return customer.hashCode()
            ^size
            ^ head.getValue().hashCode()
            ^ tail.getValue().hashCode();
}
}
