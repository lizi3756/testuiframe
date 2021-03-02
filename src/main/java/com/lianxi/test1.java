package com.lianxi;

/**
 * @Author: lizi
 * @Date: 2020/12/23 下午6:55
 */
public class test1 {
    public static void main(String[] args) {
        String beanId="916350000010070003";
        //System.out.println(beanId.toUpperCase().hashCode());
        System.out.println( Math.abs(beanId.toUpperCase().hashCode()) % 16);
    }
}
