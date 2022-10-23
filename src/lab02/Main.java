package lab02;
import tools.*;

import java.net.URL;
import java.util.*;

//Konrad Pempera 263948 22.10.2022
public class Main {


    public static void randomsearch(int[] assign, BestSolution bestSolution, Problem problem) {
        Random rand = new Random();
        for(int j = 0; j<=100000000; j++){
            for(int i = 0; i<=problem.list.size()-1;i++){
                int x = rand.nextInt(0,problem.list.get(i).size());
                assign[i+1]=problem.list.get(i).get(x);
            }
            boolean k=problem.admissibility(assign );
            if(k==false)continue;
            int satisfaction = problem.totalsatisfaction(assign);
            int dissatisfaction = problem.totaldissatisfaction(assign);
            Solution solution = new Solution(satisfaction, dissatisfaction, assign);
            bestSolution.update(dissatisfaction, satisfaction, solution);
        }
    }

    public static void bruteforce(int[] assign, BestSolution bestSolution, Systemofcalculating calcute, Problem problem){
        for(int j =0; j<=calcute.numberofsolutions();j++) {
            calcute.nextsolution();
            for(int i = 1; i<=calcute.n; i++){
                assign[i]=problem.list.get(i-1).get(calcute.numbers[i]);
            }
            boolean k=problem.admissibility(assign);
            if(k==true) {
                int satisfaction = problem.totalsatisfaction(assign);
                int dissatisfaction = problem.totaldissatisfaction(assign);
                Solution solution = new Solution(satisfaction, dissatisfaction, assign);
                bestSolution.update(dissatisfaction, satisfaction, solution);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Main obj = new Main();//Main-nazwa klasy głównej
        Class class1 = obj.getClass();
        URL urldrinks = class1.getResource("drinks.txt");
        URL urlpersons=class1.getResource("person.txt");


        Map<Integer, ArrayList<Drink>> map = new HashMap<>();

    String namedrinks = "drinks.txt";
    String namepersons= "person.txt";

    Problem problem = new Problem();
    problem.loaddatafromfile(urldrinks, urlpersons);

        int[] assign = new int[problem.numberofdrink()+1];//zamiast 11 powinno być numberofdrinks+1
        Systemofcalculating calcute = new Systemofcalculating(problem.list);
        BestSolution bestsolution = new BestSolution();
        int n = calcute.numberofsolutions();
        if(n<500000){
            bruteforce(assign, bestsolution, calcute, problem);
        }else{
            randomsearch(assign, bestsolution, problem);
        }

        assign= bestsolution.restoreassigment();
        if(assign==null){
            System.out.println("Not everyone can get drinks");
        }else {
            //drukowanie wyników
            for (int i = 1; i <= problem.numberofdrink(); i++) {
                if (map.containsKey(assign[i]) == false) {
                    map.put(assign[i], new ArrayList<Drink>());
                }
                map.get(assign[i]).add(problem.getDrink(i));
            }
            for (int i = 1; i <= problem.numberofperson(); i++) {
                System.out.print(problem.getperson(i).id + " ");
                if (map.containsKey(i) == true) {
                    for (Drink drink : map.get(i)) {
                        System.out.print("(" + drink.id + "," + drink.volume + ")" + " ");
                    }
                }
                System.out.println("");
            }
            bestsolution.bestsolution.writetoconsole();
        }
        }
}