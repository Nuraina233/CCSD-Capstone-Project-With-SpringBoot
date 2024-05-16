package com.example.ecommerce;

public class increment {
    public static void main(String[] args) {
        int j=127,i;
        while(true) {
            for (i = 25; i < 44; i++) {
                System.out.println("(" +j++ +",'" + i + "','37','10'),");
                System.out.println("(" +j++ +",'" + i + "','37.5','10'),");
                System.out.println("(" +j++ +",'" + i + "','38','10'),");
                System.out.println("(" +j++ +",'" + i + "','38.5','10'),");
                System.out.println("(" +j++ +",'" + i + "','39','10'),");
                System.out.println("(" +j++ +",'" + i + "','39.5','10'),");
                System.out.println("(" +j++ +",'" + i + "','40','10'),");
            }
            if(i==44)
                break;
        }
    }
}

// 37, 37.5 , 38 , 38.5 , 39 , 39.5 , 40
// 14,20-24
