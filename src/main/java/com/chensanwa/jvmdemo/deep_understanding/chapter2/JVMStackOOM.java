package com.chensanwa.jvmdemo.deep_understanding.chapter2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建线程导致内存溢出异常
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 18/09/23
 * Time: 17:58
 */
@Controller
@RequestMapping("jvmstackoom")
public class JVMStackOOM {

    /**
     * VM args: -Xss2M
     */

    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    @GetMapping("/oom")
    @ResponseBody
    public void testOOM(){
        JVMStackOOM jvmStackOOM = new JVMStackOOM();
        jvmStackOOM.stackLeakByThread();
    }


}
