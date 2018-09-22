package com.chensanwa.jvmdemo.deep_understanding.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Ｊａｖａ堆内存溢出异常测试
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 18/09/22
 * Time: 20:38
 */
public class HeapOOM {

    /**
     * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */

    static class OOMObject{

    }

    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true){
            list.add(new OOMObject());
        }
    }

}
