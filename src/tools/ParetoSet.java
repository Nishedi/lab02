package tools;

import java.util.*;

public class ParetoSet {
    public Map<Integer, Solution> map = new HashMap<>();


    public void writetoconsole(){
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for(Integer key:keys) {
            map.get(key).writetoconsole();
        }
    }

    public void update(int dissatisfaction, int satisfaction, Solution solution){

        if (map.containsKey(dissatisfaction) == false) {
            map.put(dissatisfaction, solution);
        } else {
            if (solution.totalsatisfaction >= map.get(dissatisfaction).totalsatisfaction)
                map.put(dissatisfaction, solution);
        }
    }
}
