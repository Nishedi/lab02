package tools;

public class Solution {
    public int totalsatisfaction = 0;
    public int totaldissatisfaction = 0;
    public int[] tab = null;
    public Solution(int totalsatisfaction, int totaldissatisfaction, int[] tab){
        this.totalsatisfaction=totalsatisfaction;
        this.totaldissatisfaction=totaldissatisfaction;
        this.tab=new int[tab.length];
        for(int i = 0; i <= tab.length-1; i++){
            this.tab[i]=tab[i];
        }
    }
    public void writetoconsole(){
        for(int i = 1; i<=tab.length-1;i++){


            System.out.print(tab[i]+" ");
        }
        System.out.println("");
        System.out.println("totsat="+totalsatisfaction+" "+"totdist="+totaldissatisfaction);
    }
}
