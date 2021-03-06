package io;

import barBossHouse.Order;

import java.io.*;

public class OrderManagerSerializedFileSource extends OrderManagerFileSource {

    public String getPath() {
        return super.getPath();
    }

    public void setPath(String path) {
        super.setPath(path);
    }

    public void load(Order order) {
        try{
            FileInputStream fileInputStream = new FileInputStream(new File(super.getPath()));
            ObjectInputStream in = new ObjectInputStream(fileInputStream);
            /* todo не верно сделал. Здесь нужно восстановить состояние объекта, переданного в качестве параметра
             * то есть банально сделать
             * Order newOrder = (Order) in.readObject();
             * order.clear();
             * order.addAll(newOrder);
             * ну и еще кастомера так же установить
             */
            order = (Order) in.readObject(); // а это фигня
            in.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void store(Order order) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(super.getPath()));
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(order);
            out.flush();
            out.close();
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
