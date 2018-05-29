package io;

import barBossHouse.*;

import java.io.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class OrderManagerBinaryFileSource extends OrderManagerFileSource {
    public String getPath() {
        return super.getPath();
    }

    public void setPath(String path) {
        super.setPath(path);
    }
    public void load(Order order) {
        File file = new File(super.getPath());
        try(DataInputStream in = new DataInputStream(new FileInputStream("data.bin"))) {
            switch (in.readUTF()) {
                case "InternetOrder":
                    order = new InternetOrder();
                    break;
                case "TableOrder":
                    order = new TableOrder();
                    break;
            }
            //todo вот это логично было в метод Customer readCustomer(DataInputStream) вынести - метод возвращает ссылку на экземпляр Customer
            order.setCustomer(new Customer(
                    in.readUTF(),
                    in.readUTF(),
                    LocalDate.parse(in.readUTF()),
                    new Address(in.readUTF(),
                            in.readInt(),
                            in.readUTF(),
                            in.readInt(),
                            in.readChar(),
                            in.readInt())));

            int size = in.readInt();

            for (int i = 0; i < size; i++) {
                //todo вот это логично было в метод MenuItem readItem(DataOutputStream) вынести - метод возвращает ссылку на экземпляр MenuItem
                switch (in.readUTF()) {
                    case "Dish":
                        order.add(new Dish(
                                in.readUTF(),
                                in.readUTF(),
                                in.readInt()));
                        break;
                    case "Drink":
                        order.add(new Drink(
                                in.readUTF(),
                                in.readUTF(),
                                in.readInt(),
                                in.readDouble(),
                                DrinkTypeEnum.valueOf(in.readUTF())));
                        break;
                }

            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void store(Order order) {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(super.getPath()))) {
            //todo вот это логично было в метод writeCustomer(Сustomer, DataOutputStream) вынести
            out.writeUTF(order.getCustomer().getFirstName());
            out.writeUTF(order.getCustomer().getSecondName());
            out.writeInt(order.getCustomer().getAge());
            out.writeUTF(order.getCustomer().getAddress().getCityName());
            out.writeInt(order.getCustomer().getAddress().getZipCode());
            out.writeUTF(order.getCustomer().getAddress().getStreetName());
            out.writeInt(order.getCustomer().getAddress().getBuildingNumber());
            out.writeChar(order.getCustomer().getAddress().getBuildingLetter());
            out.writeInt(order.getCustomer().getAddress().getApartamentNumber());

            out.writeInt(order.size());

            for (MenuItem menuItem : order) {
                //todo вот это логично было в метод writeItem(MenuItem, DataOutputStream) вынести
                out.writeUTF(menuItem.getClass().getName());
                out.writeUTF(menuItem.getName());
                out.writeInt(menuItem.getCost());
                out.writeUTF(menuItem.getDescription());
                if (menuItem instanceof Drink) {
                    out.writeDouble(((Drink) menuItem).getAlcoholVol());
                    out.writeUTF(((Drink) menuItem).getType().toString());
                }
            }
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
