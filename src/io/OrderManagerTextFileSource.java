package io;

import barBossHouse.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

//todo аналогично BinaryFileSOurce
public class OrderManagerTextFileSource extends OrderManagerFileSource {

    public String getPath() {
        return super.getPath();
    }

    public void setPath(String path) {
        super.setPath(path);
    }
    public void load(Order order) {
        File file = new File(super.getPath());
        try(Scanner sc = new Scanner(file)) {
            switch (sc.nextLine().trim()) {
                case "InternetOrder":
                    order = new InternetOrder();
                    break;
                case "TableOrder":
                    order = new TableOrder();
                    break;
            }
            order.setCustomer(new Customer(sc.nextLine(),
                    sc.nextLine(),
                    LocalDate.parse(sc.nextLine()),
                    new Address(sc.nextLine(),
                            Integer.parseInt(sc.nextLine()),
                            sc.nextLine(),
                            Integer.parseInt(sc.nextLine()),
                            sc.nextLine().charAt(0),
                            Integer.parseInt(sc.nextLine()))));

            int size = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < size; i++) {
                switch (sc.nextLine().trim()) {
                    case "Dish":
                        order.add(new Dish(sc.nextLine(),
                                sc.nextLine(),
                                Integer.parseInt(sc.nextLine())));
                        break;
                    case "Drink":
                        order.add(new Drink(sc.nextLine(),
                                sc.nextLine(),
                                Integer.parseInt(sc.nextLine()),
                                Double.parseDouble(sc.nextLine()),
                                DrinkTypeEnum.valueOf(sc.nextLine())));
                        break;
                }

            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void store(Order order) {
        StringBuilder stringBuilder = new StringBuilder(order.getClass().getName());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getFirstName());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getSecondName());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getAge());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getAddress().getCityName());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getAddress().getZipCode());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getAddress().getStreetName());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getAddress().getBuildingNumber());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getAddress().getBuildingLetter());
        stringBuilder.append('\n');
        stringBuilder.append(order.getCustomer().getAddress().getApartamentNumber());
        stringBuilder.append('\n');

        stringBuilder.append(order.size());

        for (MenuItem menuItem : order) {
            stringBuilder.append('\n')
                    .append(menuItem.getClass().getName())
                    .append('\n')
                    .append(menuItem.getName())
                    .append('\n')
                    .append(menuItem.getCost())
                    .append('\n')
                    .append(menuItem.getDescription());
            if (menuItem instanceof Drink) {
                stringBuilder.append('\n')
                        .append(((Drink) menuItem).getAlcoholVol())
                        .append(((Drink) menuItem).getType());

            }
        }
        try(PrintWriter printWriter = new PrintWriter(new File(super.getPath()))){
            printWriter.write(stringBuilder.toString());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(Order order) {
        order.clear();
        try {
            File file = new File(super.getPath());
            file.delete();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void create(Order order) {
        store(order);
    }
}
