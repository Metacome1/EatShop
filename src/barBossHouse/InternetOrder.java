package barBossHouse;

// todo переписать список СДЕЛАНО
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

    //TODO remove работают либо через жопу, либо не работают COMPLITED
    public boolean remove(String dishName) {
        //todo хранишь ссылку на предыдущий нод COMPLITED
        ListNode listNode = head;
        while (listNode != null) {
            if (head.getValue().getName().equals(dishName)) {
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

    public boolean remove(MenuItem menuItem) {
        //todo хранишь ссылку на предыдущий нод COMPLITED
        ListNode listNode = head;
        while (listNode != null) {
            if (head.getValue().equals(menuItem)) {

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
        //todo хранишь ссылку на предыдущий нод COMPLITED
        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
           if (remove(dishName)){
               remove(dishName);
               deletedDishCount++;
           }
        }
        return deletedDishCount;
    }

    public int removeAll(MenuItem menuItem) {
        //todo хранишь ссылку на предыдущий нод COMPLITED
        int deletedDishCount = 0;
        ListNode listNode = head;
        while (listNode != null) {
            if (remove(menuItem)){
                remove(menuItem);
                deletedDishCount++;
            }
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

    public int itemQuantity(String NameInArray){
        ListNode listNode;
        int allNameInArray;
        allNameInArray = 0;
        listNode = tail;
        for (int i = 0; i < size; i++){
            if (listNode.getValue().getName().equals(NameInArray))allNameInArray++;
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
        String[] itemsName = new String[0];
        if (size != 0) {
            //TODO нахрен массивы иди по листу COMPLITED
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
                if (strings[0].isEmpty()) strings[i] = listNode.getValue().getName();
                for (int j = 0; j < size; j++) {
                    if (strings[j].isEmpty()) break;
                    if (listNode.getValue().getName().equals(strings[j])) addName = true;
                }
                if (!addName) {
                    strings[index] = listNode.getValue().getName();
                    index++;
                }
                addName = false;
                listNode = listNode.getNext();
            }
            itemsName = new String[index];
            System.arraycopy(strings, 0, itemsName, 0, itemsName.length);
            return itemsName;
        }
        return itemsName;
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
