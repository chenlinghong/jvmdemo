package com.chensanwa.jvmdemo.deep_understanding.chapter2;

/**
 * 虚拟机栈和本地方法栈ＯＯＭ测试
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 18/09/23
 * Time: 17:12
 */
public class JVMStackSOF {

    /**
     * VM args: -Xss128k
     */

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JVMStackSOF oom = new JVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable throwable){
            System.out.print("stack length: " + oom.stackLength);
            throw throwable;
        }
    }

}
