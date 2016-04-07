package me.jyang5.Observer;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import static java.nio.file.StandardWatchEventKinds.*;



/**
 * Created by jing on 4/6/16.
 */
public class Subject {

    List<WatchEvent<?>> event;
    List<Observer> observerList = new ArrayList<Observer>();

    public List<WatchEvent<?>> getEvent() {
        return event;
    }

    public void setEvent(List<WatchEvent<?>> event) {
        this.event = event;
        System.out.println("setEvent");
        notifyObserver();

    }

    public void register(Observer observer) {
        observerList.add(observer);
        System.out.println("Observer registered");
    }

    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    public void notifyObserver() {
        for(Observer observer : observerList) {
            for(WatchEvent we : event) {
                if (we.kind() == ENTRY_CREATE) {
                    observer.updateCreate( we.context().toString());
                }
                if (we.kind() == ENTRY_DELETE) {
                    observer.updateDelete( we.context().toString());
                }
                if (we.kind() == ENTRY_MODIFY) {
                    observer.updateModify( we.context().toString());
                }
            }
        }
    }
}
