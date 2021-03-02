package com.frame.poGenerate;

/**
 * @Author: lizi
 * @Date: 2020/12/31 上午10:49
 */
public class split1 {
    public static void main(String[] args) {
        String address="上海:上海市:闵行区:吴中路";
        String[] splitAddress=address.split("\\:");
        System.out.println(splitAddress[0]+splitAddress[1]+splitAddress[2]+splitAddress[3]);
    }
}
