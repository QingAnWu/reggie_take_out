package com.qingAn.reggie.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        //String.hashCode() 值会为空
        orderId = orderId < 0 ? -orderId : orderId;
        return orderId;
    }

    public static void main(String[] args){
        for (int i = 0; i<100; i++)
        System.out.println(UUIDUtils.getUUIDInOrderId());
    }
}