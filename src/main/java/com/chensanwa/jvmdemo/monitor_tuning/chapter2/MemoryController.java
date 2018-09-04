package com.chensanwa.jvmdemo.monitor_tuning.chapter2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MemoryController {

    private List<User> userList = new ArrayList<>();
    private List<Class<?>> classList = new ArrayList<>();

    /**
     * -Xmx32M -Xms32M
     * 构造堆内存溢出
     * @return
     */
    @GetMapping("/heap")
    public String heap(){

        int i = 0;

        while(true){
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

    /**
     * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
     * 构造非堆内存溢出（Metaspace）
     * @return
     */
    @GetMapping("/nonheap")
    public String nonHeap(){
        while(true){
            classList.addAll(Metaspace.createClasses());

        }
    }

}
