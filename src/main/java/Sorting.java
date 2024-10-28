import java.util.*;
public class Sorting {
    public static String[] sortingRun(String[][] rankGenres) {
        //converting genre data into a list
        for (int i = 0; i < selenium.ARTISTNUM; i++) {
            System.out.println(Arrays.toString(rankGenres[i]));
        }
        String temp = "";
        List<String> genreList = new ArrayList<>();
        for (int i = 0; i < selenium.ARTISTNUM; i++) {
            for (int j = 0; j < rankGenres[i].length; j++) {
                temp = rankGenres[i][j];
                genreList.add(temp);
            }
        }

        String[] Ranking = new String[selenium.ARTISTNUM];

        //putting data into hashmap and counting number of each
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : genreList) {
            if (map.keySet().contains(str)) map.put(str, map.get(str) + 1);
            else map.put(str, 1);
        }

        //future implementation? checking if minimum of five genres *not sure if possible

        //selection sorting to find most common
        int maxVal = 0;
        String top = "";
        for (int i = 0; i < selenium.ARTISTNUM; i++) {
            maxVal = 0;
            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer val = entry.getValue();
                if (val > maxVal) {
                    maxVal = val;
                    top = key;
                    //incase tie
                } else if (val == maxVal && top.compareTo(key) > 0){
                    top = key;
                }
            }
            map.remove(top);
            Ranking[i] = top;
        }
        return Ranking;
    }
}
