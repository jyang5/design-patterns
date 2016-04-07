package me.jyang5.Observer;

/**
 * Created by jing on 4/6/16.
 */
public class Observer {
    String name = new String();
    public Observer() {
        name = "Observer";
    }
    public Observer(String name) {
        this.name = name;
    }
    public void updateCreate(String fileName) {
        System.out.println(name + ", File Created : " + fileName);
    }
    public void updateDelete(String fileName) {
        System.out.println(name + ", File Deleted : " + fileName);
    }
    public void updateModify(String fileName) {
        System.out.println(name + ", File Modified : " + fileName);
    }
}
