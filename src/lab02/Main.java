package lab02;

import tools.*;

import javax.swing.*;
import java.util.*;
import java.util.Collections;
import java.util.List;


//Konrad Pempera 263948 10.10.2022
public class Main {

    public static void randomsearch(int[] tabb, ParetoSet pareto, Problem problem) {
        Random rand = new Random();
        for(int j = 0; j<=10000000; j++){
            for(int i = 0; i<=problem.list.size()-1;i++){
                int x = rand.nextInt(1,problem.list.get(i).size()+1);
                tabb[i+1]=x;
            }
            boolean k=problem.admissibility(tabb );
            if(k==false)continue;
            int satisfaction = problem.totalsatisfaction(tabb);
            int dissatisfaction = problem.totaldissatisfaction(tabb);
            Solution solution = new Solution(satisfaction, dissatisfaction, tabb);
            pareto.update(dissatisfaction, satisfaction, solution);
        }
    }

    public static void bruteforce(int[] tabb, ParetoSet pareto, Systemofcalculating calcute, Problem problem){
        for(int j =0; j<=calcute.numberofsolutions();j++) {
            calcute.nextsolution();
            for(int i = 1; i<=calcute.n; i++){
                tabb[i]=problem.list.get(i-1).get(calcute.numbers[i]);
            }
            boolean k=problem.admissibility(tabb);
            if(k==true) {
                int satisfaction = problem.totalsatisfaction(tabb);
                int dissatisfaction = problem.totaldissatisfaction(tabb);
                Solution solution = new Solution(satisfaction, dissatisfaction, tabb);
                pareto.update(dissatisfaction, satisfaction, solution);
            }
        }
    }


    public static void main(String[] args) throws Exception {
    String namedrinks = "drinks.txt";
    String namepersons= "person.txt";

    Problem problem = new Problem();
    problem.loaddatafromfile(namedrinks, namepersons);

    int[] tabb = new int[11];

        Systemofcalculating calcute = new Systemofcalculating(problem.list);
        ParetoSet pareto = new ParetoSet();
        //randomsearch(tabb, pareto, problem);
        int x = calcute.numberofsolutions();
        bruteforce(tabb, pareto, calcute, problem);

        pareto.writetoconsole();
    }



}