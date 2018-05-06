package barBossHouse;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;


//ODO никаких result - переименовывай COMPLITED
public class TableOrder implements Order {
    private int size;
    private static final int DEFAULT_SIZE = 16;
    private MenuItem[] menuItems;
    private Customer customer;
    private LocalDateTime localDateTime;

    public TableOrder() {
        this.size = 0;
        this.menuItems = new MenuItem[DEFAULT_SIZE];
        this.localDateTime = LocalDateTime.now();
    }


    public TableOrder(int dishCount, Customer customer) {
        this(new MenuItem[dishCount], customer);
        this.localDateTime = LocalDateTime.now();
    }

    public TableOrder(MenuItem[] dishesArray, Customer customer) {
        this.customer = customer;
        menuItems = dishesArray;
        size = getSize();
        this.localDateTime = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean add(MenuItem menuItem) {
        if (menuItem instanceof Drink) {
            Drink drink = (Drink) menuItem;
            if (drink.getAlcoholVol() > 0 && customer.getAge() < 18) throw new UnlawfulActionException("Тебе нет 18");
        }
        if (size > menuItems.length) {
            MenuItem[] newDish = new MenuItem[size * 2];
            System.arraycopy(menuItems, 0, newDish, 0, size);
            menuItems = newDish;
        }
        menuItems[size] = menuItem;
        size++;
        return true;
    }

    public boolean remove(String dishName) {
        for (int i = 0; i < size; i++) {
            if (menuItems[i].getName().equals(dishName)) {
                for (int j = i; j < size; j++) {
                    menuItems[j] = menuItems[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean remove(MenuItem menuItem) {
        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(menuItem)) {
                for (int j = i; j < size; j++) {
                    menuItems[j] = menuItems[j + 1];
                }
                menuItems[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }


    public int removeAll(String dishName) {
        int deletedDishCount = 0;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i].getName().equalsIgnoreCase(dishName)) {
                menuItems[i] = null;
                deletedDishCount++;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            if (menuItems[i] == null) {
                j = i;
                while ((menuItems[j] == null) & j < size) {
                    shiftArray(menuItems, j);
                    j++;
                }
            }
        }
        size -= deletedDishCount;
        return deletedDishCount;
    }

    public int removeAll(MenuItem menuItem) {
        int deletedDishCount = 0;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(menuItem)) {
                menuItems[i] = null;
                deletedDishCount++;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            if (menuItems[i] == null) {
                j = i;
                while ((menuItems[j] == null) & j < size) {
                    shiftArray(menuItems, j);
                    j++;
                }
            }
        }
        size -= deletedDishCount;
        return deletedDishCount;
    }

    public int itemQuantity() {
        return size;
    }

    public int itemQuantity(String str) {
        int itemQuantity;
        itemQuantity = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i].getName().equals(str)) itemQuantity++;
        }
        return itemQuantity;
    }


    public int itemQuantity(MenuItem menuItem) {
        int itemQuatity;
        itemQuatity = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(menuItem)) itemQuatity++;
        }
        return itemQuatity;
    }

    public MenuItem[] getItems() {
        MenuItem[] getDishes = new MenuItem[size];
        System.arraycopy(menuItems, 0, getDishes, 0, size);
        return getDishes;
    }

    public int costTotal() {
        int costTotal = 0;
        for (int i = 0; i < size; i++) {
            costTotal += menuItems[i].getCost();
        }
        return costTotal;
    }

    public int getDishSumPrice(String name) {
        int getDishSumPrice = 0;
        for (int i = 0; i < size; i++) {
            if (menuItems[i].getName().equalsIgnoreCase(name)) getDishSumPrice += menuItems[i].getCost();
        }
        return getDishSumPrice;
    }

    public String[] itemsName() {
        if (size != 0) {
            String[] buffer = new String[size];
            boolean isAdded = false;
            int indx = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < indx; j++) {
                    if (buffer[j].equalsIgnoreCase(menuItems[i].getName())) {
                        isAdded = true;
                        break;
                    }
                }
                if (isAdded) {
                    isAdded = false;
                    continue;
                } else {
                    buffer[indx] = menuItems[i].getName();
                    indx++;
                }
            }
            String[] dishesNames = new String[indx];
            System.arraycopy(dishesNames, 0, buffer, 0, buffer.length);
            return dishesNames;
        }
        return null;
    }

    public MenuItem[] sortedItemsByCostDesc() {
        if (size != 0) {
            MenuItem[] sortedItemsByCostDesc = getItems();
            quickSortDishes(sortedItemsByCostDesc, 0, size - 1);
            return sortedItemsByCostDesc;
        }
        return null;
    }

    public int getDishCount(MenuItem menuItem) {
        if (size != 0) {
            int getDishCount = 0;
            for (int i = 0; i < size; i++) {
                if (menuItems[i].equals(menuItem))
                    getDishCount++;
            }
            return getDishCount;
        } else return 0;
    }

    public int getDishCount(String name) {
        if (size != 0) {
            int getDishCount = 0;
            for (int i = 0; i < size; i++) {
                if (menuItems[i].getName().equals(name))
                    getDishCount++;
            }
            return getDishCount;
        } else return 0;
    }

    private int getSize() {
        for (int i = 0; i < menuItems.length; i++) {
            if (menuItems[i] == null) return i;
        }
        return -1;
    }

    public static void quickSortDishes(MenuItem[] array, int lowIndx, int highIndx) {
        MenuItem dish = array[(lowIndx + highIndx) / 2];
        int dishPrice = dish.getCost();

        while (lowIndx <= highIndx) {
            while (array[lowIndx].getCost() > dishPrice) lowIndx++;
            while (array[highIndx].getCost() < dishPrice) highIndx--;
            if (lowIndx <= highIndx) {
                MenuItem tmpDish = array[highIndx];
                array[highIndx] = array[lowIndx];
                array[lowIndx] = tmpDish;
                lowIndx++;
                highIndx--;
            }
            if (lowIndx < highIndx) quickSortDishes(array, lowIndx, highIndx);
            if (highIndx > lowIndx) quickSortDishes(array, lowIndx, highIndx);
        }
    }


    private static void shiftArray(MenuItem[] array, int indx) {
        for (int i = indx; i < array.length + 1; i++)
            array[i] = array[i + 1];
    }

    @Override
    public String toString() {
        MenuItem[] menuItems = getItems();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(":").append(size).append("\n");
        for (int i = 0; i < menuItems.length; i++) {
            stringBuilder.append(menuItems[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        TableOrder tableOrder = (TableOrder) obj;

        if (this.size != tableOrder.getSize())
            return false;

        int hashCodeTableOrder = 0;
        int hashCodeEquals = 0;

        for (int i = 0; i < size; i++) {
            hashCodeTableOrder += this.menuItems[i].hashCode();
            hashCodeEquals += tableOrder.menuItems[i].hashCode();
        }
        return (customer.equals(((TableOrder) obj).getCustomer()) & hashCodeEquals == hashCodeTableOrder);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        MenuItem[] menuItems = getItems();
        for (int i = 0; i < menuItems.length; i++) {
            hash ^= menuItems.hashCode();
        }
        return customer.hashCode()
                ^ size
                ^ hash
                ^ localDateTime.hashCode();
    }
}
