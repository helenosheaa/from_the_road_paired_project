package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SparkDataHandler {

    public static Map<Integer, List<Integer>> getPagesForList(List listOfItems, int numberOnAPage){
        Map<Integer, List<Integer>> pages = new HashMap<>();
        int numberOfArticles = listOfItems.size();
        int numberOfPages = numberOfArticles / numberOnAPage;
        int startIndex = 0;
        int endIndex = 1;
        for(int page = 1; page <= numberOfPages; page++){
            endIndex += numberOnAPage;
            List<Integer> startAndEnd = new ArrayList<>();
            startAndEnd.add(startIndex);
            startAndEnd.add(endIndex);
            pages.put(page, startAndEnd);
            startIndex = endIndex;
        }
        return pages;
    }
}
