package com.xl.bitcion;

import java.util.LinkedList;
import java.util.List;

/**
 * <br />
 * 创建于2018.03.29
 *
 * @author 谢山林
 * @version 1.0
 */
public class Rollback {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addFirst("1");
        list.addFirst("2");
        list.addFirst("3");
        list.addFirst("4");
        list.addFirst("5");
        list.addFirst("6");
        for (Object o : list) {
            System.out.println(o);
        }

    }

}
