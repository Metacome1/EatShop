package barBossHouse;

import java.time.LocalDateTime;
import java.util.List;

//todo удали дубли методов отсюда и из реализации (методы List первичные). Подсказки смотри ниже в виде комментов
public interface Order extends List<MenuItem> {
    boolean add(MenuItem menuItem);  //метод добавления в конец есть в интерфейсе List

    String[] itemsName();

    int itemQuantity();// в интерфейсе List это size()

    int itemQuantity(String str);

    int itemQuantity(MenuItem menuItem);

    MenuItem[] getItems();// в интерфейсе List это toArray()

    boolean remove(MenuItem menuItem);// в интерфейсе List есть метод remove

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
