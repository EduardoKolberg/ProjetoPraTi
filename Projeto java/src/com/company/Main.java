package com.company;

import view.Menu;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean continua = true;
        while (continua){
            continua = menu.menuInicial();
        }
    }
}
