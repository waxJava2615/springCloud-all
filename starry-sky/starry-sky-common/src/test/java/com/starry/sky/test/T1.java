package com.starry.sky.test;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author wax
 * @description: TODO
 * @date 2021-09-10
 */
public class T1 {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        list.add("w");
        list.add("z");
        list.add("a");
        list.add("d");
        list.add("ff");
        list.add("cc");
        Collections.sort(list, Comparator.comparing(String::trim));
        System.out.println(list);
    }
}
