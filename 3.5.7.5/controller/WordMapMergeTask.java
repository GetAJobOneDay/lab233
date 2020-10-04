package controller;

import model.FileFreq;


import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordMapMergeTask implements Callable<LinkedHashMap<String,ArrayList<FileFreq>>> {
    private FileFreq fileFreq;
    private Map<String,FileFreq>[] wordMap;
    public WordMapMergeTask(Map<String,FileFreq>[] wordMap){
        this.wordMap=wordMap;
    }


    @Override
    public LinkedHashMap<String, ArrayList<FileFreq>> call() throws Exception {
        LinkedHashMap<String,ArrayList<FileFreq>> uniqueSet;
        List<Map<String,FileFreq>> wordMapList = new ArrayList<>(Arrays.asList(wordMap));
        uniqueSet =wordMapList.stream()
                .flatMap(m->m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        e->e.getKey(),
                        Collector.of(()->new ArrayList<FileFreq>(), (list,item)->list.add(item.getValue()),
                                (curr_list,new_item)->{
                                    curr_list.addAll(new_item);
                                    return curr_list;
                                })
                )).entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1,v2)->v1, LinkedHashMap::new));
        return uniqueSet;
    }
}
