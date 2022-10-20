package tools;

import lab02.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Data {
    public static ArrayList<String> read_data(String name) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader ReadF = null;
        System.out.println(System.getProperty("user.dir"));
        ReadF = new BufferedReader(new FileReader(name));
        String numstring = ReadF.readLine();
        try {
            while (numstring != null) {
                list.add(numstring);
                numstring = ReadF.readLine();
            }
        }catch (Exception er) {
            return null;
        }
        ReadF.close();

        for(String i: list){
            System.out.println(i);
        }
        return list;
    }

    public static ArrayList<Person> load_data_person(ArrayList<String> list){
        ArrayList<Person> listint = new ArrayList<>();
        return listint;
    }




}
