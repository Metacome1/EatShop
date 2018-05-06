package barBossHouse;

import java.time.LocalDateTime;

public interface Order {
    boolean add(MenuItem menuItem);

    String[] itemsName();

    int itemQuantity();

    int itemQuantity(String str);

    int itemQuantity(MenuItem menuItem);

    MenuItem[] getItems();

    boolean remove(MenuItem menuItem);

    boolean remove(String str);

    int removeAll(String str);

    int removeAll(MenuItem menuItem);

    MenuItem[] sortedItemsByCostDesc();

    int costTotal();

    Customer getCustomer();

    void setCustomer(Customer customer);

    public String toString();

    public boolean equals(Object obj);

    public int hashCode();

    LocalDateTime getLocalDateTime();

    void setLocalDateTime(LocalDateTime localDateTime);
}
