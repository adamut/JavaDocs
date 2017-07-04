package ro.teamnet.zerotohero.oop.exception;


import java.io.*;
import java.util.Scanner;

public class ExampleException extends Exception {
    public ExampleException(String message) {
        super(message);
    }

    public String deschidereFisier(String path) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) throws ExampleException {
        int l = 2;
        try {
            if (l == 2)
                throw new ExampleException("l este 2");
        } catch (ExampleException e) {
            System.out.println(e);
        }



        Object[] localArgs = (Object[]) args;
        try {
            Integer[] numbers = (Integer[]) localArgs;
        } catch (ClassCastException originalException) {
            try {
                throw new ExampleException("Horrible exception!");
            } catch (ExampleException e) {
                System.out.println("am prins exceptia");
            }
        }


        Scanner sc = null;
        try{
            sc= new Scanner(new File("ana.txt"));
            System.out.println("mos craciun si prietenii sai");
            while(sc.hasNext()){
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            sc.close();
        }


        try{
            System.out.println(sc.nextLine());
            if(1==0)
                throw new IOException("can't read anymore");
            else
                throw new ExampleException("boom");
        }catch (IOException | ExampleException e2){
            System.out.println("am prins o exceptie");
        }
    }
}

