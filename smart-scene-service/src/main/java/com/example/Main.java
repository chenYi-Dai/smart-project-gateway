package com.example;

public class Main {
    public static void main(String[] args) {

        boolean cebPaywaySwitchOn = SwitchUtils.isCebPaywaySwitchOn();
        System.out.println(cebPaywaySwitchOn ? "success" : "fail");
    }
}