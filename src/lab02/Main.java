package lab02;

import tools.Data;
import tools.ParetoSet;
import tools.Solution;
import tools.Systemofcalculating;

import javax.swing.*;
import java.util.*;
import java.util.Collections;
import java.util.List;


//Konrad Pempera 263948 10.10.2022
public class Main {

    public static int totalsatisfaction(ArrayList<Person> listofpersons, ArrayList<Drink> listofdrinks, int[] tab, int n){
        int sum=0;
        for(int i =1 ; i<=n; i++) {
            sum = sum + listofpersons.get(tab[i] - 1).satisfaction(listofdrinks.get(i - 1).taste, listofdrinks.get(i - 1).volume);
        }
    return sum;
    }

    public static int totaldissatisfaction(ArrayList<Person> listofpersons, ArrayList<Drink> listofdrinks, int[] tab, int n){
        int sum = 0;
        int[] firsttastefullfill= new int[listofpersons.size()+1];
        for(int i = 1; i<=listofpersons.size(); i++){
            firsttastefullfill[i]=0;
        }
        for(int i = 1; i<=n;i++) {
            int person = tab[i];
            int preferention =listofpersons.get(tab[i]-1).preferention.get(0);
            int taste = listofdrinks.get(i-1).taste;
            int volume = listofdrinks.get(i-1).volume;
            if(preferention==taste) firsttastefullfill[person]=volume;
        }


        for(int i = 1 ; i<=listofpersons.size();i++){
            sum= sum+Math.max(400- firsttastefullfill[i], 0);

        }
      return sum;
    }

    public static boolean admissibility(ArrayList<Person> listofpersons, ArrayList<Drink> listofdrinks, int[] tab, int n){
        for(int i = 1; i<=n;i++){

            if(listofpersons.get(tab[i]-1).satisfaction(listofdrinks.get(i - 1).taste, listofdrinks.get(i - 1).volume)==-1)
                return false;
        }

        Set<Integer> set = new HashSet<>();
        for(int i =1; i<=n;i++ ){
            int person = tab[i];
            int taste = listofdrinks.get(i-1).taste;
            int x = person *1000 + taste;
            if(set.contains(x))
            return false;
            set.add(x);

        }



        return true;
    }



    public static void randomsearch(ArrayList<ArrayList<Integer>> list, int[] tabb, ArrayList<Person> listofpersons, ArrayList<Drink> listofdrinks, ParetoSet pareto ) {
        Random rand = new Random();
        for(int j = 0; j<=10000000; j++){
            for(int i = 0; i<=list.size()-1;i++){
                int x = rand.nextInt(1,list.get(i).size()+1);
                tabb[i+1]=x;
            }
            boolean k=admissibility(listofpersons, listofdrinks, tabb,10 );
            if(k==false)continue;
            int satisfaction = totalsatisfaction(listofpersons, listofdrinks, tabb,10);
            int dissatisfaction = totaldissatisfaction(listofpersons, listofdrinks, tabb,10);
            Solution solution = new Solution(satisfaction, dissatisfaction, tabb);
            pareto.update(dissatisfaction, satisfaction, solution);
        }
    }

    public static void nextsolution(int[] tab, int tablenght, int[] numbers){
        for(int i = 1; i<=tablenght; i++){
            tab[i]++;
            if(tab[i]<numbers[i])return;
            tab[i]=0;
        }
    }

    public static void bruteforce(int[] tab2x, int[] tabb, ArrayList<Person> listofpersons, ArrayList<Drink> listofdrinks, ParetoSet pareto, int[] numberxs, ArrayList<ArrayList<Integer>> list, Systemofcalculating calcute){
        for(int j =0; j<=23708160;j++) {
            nextsolution(calcute.numbers, 10, calcute.ranges);
            for(int i = 1; i<=10; i++){
                tabb[i]=list.get(i-1).get(calcute.numbers[i]);
            }
            boolean k=admissibility(listofpersons, listofdrinks, tabb,10 );
            if(k==true) {
                int satisfaction = totalsatisfaction(listofpersons, listofdrinks, tabb, 10);
                int dissatisfaction = totaldissatisfaction(listofpersons, listofdrinks, tabb, 10);
                Solution solution = new Solution(satisfaction, dissatisfaction, tabb);
                pareto.update(dissatisfaction, satisfaction, solution);
            }
        }
    }


    public static void main(String[] args) throws Exception {
    String namedrinks = "drinks.txt";
    String namepersons= "person.txt";

    ArrayList<String> listnonparse_drinks=Data.read_data(namedrinks);
    ArrayList<Drink> listofdrinks=new ArrayList<>();
    for(String s: listnonparse_drinks){
        listofdrinks.add(new Drink(s));
    }

    ArrayList<String> listnonparse_persons = Data.read_data(namepersons);
    ArrayList<Person> listofpersons = new ArrayList<>();
    for(String s: listnonparse_persons){
        listofpersons.add(new Person(s));
    }

    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    ArrayList list1 = new ArrayList<>();
        ArrayList list2 = new ArrayList<>();
        ArrayList list3 = new ArrayList<>();
        ArrayList list4 = new ArrayList<>();
        ArrayList list5 = new ArrayList<>();
        ArrayList list6 = new ArrayList<>();
        ArrayList list7 = new ArrayList<>();
        ArrayList list8 = new ArrayList<>();
        ArrayList list9 = new ArrayList<>();
        ArrayList list10 = new ArrayList<>();
    list1.add(1);list1.add(2);list1.add(3);list1.add(4);list1.add(5);list1.add(6);list1.add(8);
    list2.add(1);list2.add(2);list2.add(3);list2.add(4);list2.add(5);list2.add(6);list2.add(8);
    list3.add(2);list3.add(3);list3.add(4);list3.add(5);list3.add(6);list3.add(7);
    list4.add(2);list4.add(4);list4.add(7);list4.add(8);
    for(int i = 1; i<=5; i++)
        list5.add(i);

        list6.add(1);list6.add(2);list6.add(3);list6.add(4);list6.add(5);list6.add(6);list6.add(8);
        list7.add(2);list7.add(3);list7.add(4);list7.add(5);list7.add(6);list7.add(7);
        list8.add(1);list8.add(4);list8.add(5);list8.add(8);
        list9.add(2);list9.add(3);list9.add(4);list9.add(5);list9.add(6);list9.add(7);
        list10.add(2);list10.add(4);list10.add(7);list10.add(8);



    list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        list.add(list5);
        list.add(list6);
        list.add(list7);
        list.add(list8);
        list.add(list9);
        list.add(list10);

        int[] tabb = new int[11];

        Systemofcalculating calcute = new Systemofcalculating(list);

        ParetoSet pareto = new ParetoSet();

        //randomsearch(list,tabb,listofpersons, listofdrinks, pareto);



        int x = calcute.numberofsolutions();
        bruteforce(calcute.numbers, tabb, listofpersons, listofdrinks, pareto, calcute.ranges, list, calcute);

        pareto.writetoconsole();
    }



}