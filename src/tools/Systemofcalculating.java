package tools;

import java.util.ArrayList;

public class Systemofcalculating {
    public int[] numbers = null;
    public static int[] ranges = null;
    public Systemofcalculating(ArrayList<ArrayList<Integer>> list){
        int n=list.size();
        this.numbers=new int[n+1];
        for(int i = 1; i<=n;i++){
           numbers[i]=0;
        }
        this.ranges = new int[n+1];
        for(int i = 1; i<=n; i++){
            ranges[i]=list.get(i-1).size();
        }

    }
    public static int numberofsolutions(){
        int z = 1;
        for(int i = 1; i<=ranges.length-1; i++)
        z = z * ranges[i];
        return z;
    }




}
