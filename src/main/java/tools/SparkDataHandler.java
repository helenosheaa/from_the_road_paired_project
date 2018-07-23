package tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SparkDataHandler {

    public static Map<Integer, Map<String, Integer>> getPagesForList(List listOfItems, int numberOnAPage){
        Map<Integer, Map<String, Integer>> pages = new HashMap<>();
        int numberOfArticles = listOfItems.size();
        int numberOfPages = numberOfArticles / numberOnAPage;
        int extraArticles = numberOfArticles % numberOnAPage;
        int startIndex = 0;
        int endIndex = 0;
        for(int page = 1; page <= numberOfPages; page++){
            endIndex += numberOnAPage;
            Map<String, Integer> startAndEnd = new HashMap<>();
            startAndEnd.put("start", startIndex);
            startAndEnd.put("end", endIndex);
            pages.put(page, startAndEnd);
            startIndex = endIndex;
        }
        if (extraArticles != 0){
            int page = numberOfPages + 1;
            if(numberOfPages == 0){
                startIndex = 0;
            }else {
                startIndex = pages.get(numberOfPages).getOrDefault("end", 0);
            }
            endIndex = startIndex + extraArticles;
            Map<String, Integer> startAndEnd = new HashMap<>();
            startAndEnd.put("start", startIndex);
            startAndEnd.put("end", endIndex);
            pages.put(page, startAndEnd);
        }
        return pages;
    }
}
