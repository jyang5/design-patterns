package me.jyang5;

import me.jyang5.Observer.Observer;
import me.jyang5.Observer.Subject;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Created by jing on 4/6/16.
 */
public class PatternEx {
    public static void main(String[] args) throws IOException {
        Observer observer1 = new Observer("Jing");
        Observer observer2 = new Observer();

        Subject subject = new Subject();
        subject.register(observer1);
        subject.register(observer2);


        Path myPath = Paths.get("/home/jing");

        WatchService watchService = myPath.getFileSystem().newWatchService();
        WatchKey watchKey = myPath.register(watchService, ENTRY_CREATE, ENTRY_DELETE,ENTRY_MODIFY);
        System.out.println(myPath.toString());

        watchKey.reset();
        File file = new File("/home/jing/newfile.txt");
//        file.createNewFile();
        file.delete();
        try {
            watchKey = watchService.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WatchEvent<?>> event = watchKey.pollEvents();
        subject.setEvent(event);




    }
}
