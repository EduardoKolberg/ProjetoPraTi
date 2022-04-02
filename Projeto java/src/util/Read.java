package util;

import java.util.Scanner;

public class Read {
    public static Scanner in = new Scanner(System.in);

    public static int readInt(){
        while(!in.hasNextInt()){
            String token = in.next();
            System.out.println( token + " não é uma opção válida!");
            System.out.println("Favor inserir um número válido!");
        }
        return in.nextInt();
    }

    public static void comeLinha(){ //Usado para tratar do erro que existe no java onde a linha não é consumida.
        in.nextLine();
    }

    public static String readString(){
        return in.nextLine();
    }

    public static double readDouble(){
        while(!in.hasNextDouble()){
            String token = in.next();
            System.out.println( token + " não é uma opção válida!");
            System.out.println("Favor inserir um número válido!");
        }
        return in.nextDouble();
    }

}
