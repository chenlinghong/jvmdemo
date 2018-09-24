package com.chensanwa.jvmdemo.deep_understanding.chapter3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一次对象自我拯救的演示
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 18/09/24
 * Time: 12:11
 */
@RestController
@RequestMapping("/finalize")
public class FinalizeEscapeGC {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    /**
     * 此代码演示两点：
     * １、对象可以在被ＧＣ时自我拯救
     * ２、这种自救的机会只有一次，因为对象的finalize()方法最多只会被系统自动调用一次
     */

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, I am still alive: ");
    }

    @GetMapping("/test")
    public String testController() throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停等待
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead:");
        }

        //第二次失败拯救
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停等待
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead:");
        }
        return "success";
    }

}
