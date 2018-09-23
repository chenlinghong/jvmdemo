package com.chensanwa.jvmdemo.deep_understanding.chapter2;

import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致的内存溢出异常
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 18/09/23
 * Time: 18:27
 */
@Controller
@RequestMapping("/runtimeoom")
public class RuntimeConstantPoolOOM {

    /**
     * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
     */

    @GetMapping("/oom")
    @ResponseBody
    public String testOOM(){
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10MB的PermSize在integer范围内足够产生ＯＯＭ
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
            /**
             * String.intern()是一个Native方法：如果字符串常量池中已经包含一个等于次String对象的字符串，则返回代表池中这个字符串的String
             * 对象；否则，将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用
             */
        }
    }
}
