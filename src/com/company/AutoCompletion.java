package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static com.company.DictionaryFinder.readWordsFromCSV;
import static com.company.DictionaryFinder.readWordsFromCSVNewLine;

public class AutoCompletion {

    public static void main() throws FileNotFoundException,
            IOException {

        DictionaryFinder df = new DictionaryFinder();

        NavigableMap<String, Integer> storeStringMap
                = new TreeMap<String, Integer>();

        LinkedHashMap<String, Float> finalList = new LinkedHashMap<>();

        String auto;

        //list of words from dictionary file
        List<String> dictWordsList = readWordsFromCSV("lotr.csv");
        TreeMap<String, Integer> dictWordsMap = df.formDictionary(dictWordsList);

        // dictionary trie
        Trie dictTrie = new Trie();
        for (String s : dictWordsList){
            dictTrie.add(s);
        }


        // list of words from query file
        List<String> LotrQueries = readWordsFromCSVNewLine("lotrQueries.csv");


        //for each prefix in query file
        for(String s : LotrQueries){
            //get a subtrie rooted at the prefix
            Trie temp = dictTrie.getSubTrie(s);
            //create a list of words from the new trie - will be missing the prefix though as new root: e.g. eese
            List<String> list = temp.getAllWords();
            //re-add the prefix onto the list: e.g. ch + eese = cheese
            for (String listString : list){
                auto = s.trim() + listString.trim();

                for (Map.Entry<String, Integer> entry : dictWordsMap.entrySet()) {
                    //if words that are in auto equal to the words in entry map
                        if (auto.equals(entry.getKey())) {
                            //store those words in a storeAuto map
                            storeStringMap.put(entry.getKey(), entry.getValue());
                        }
                }

            }

            LinkedHashMap<String, Float> tempList  = sortByFrequency(storeStringMap);
            finalList.putAll(tempList);
            storeStringMap.clear();
        }
        DictionaryFinder.saveToFile(finalList, "lotrMatches.csv");
    }


    public static LinkedHashMap<String, Float> sortByFrequency(NavigableMap<String, Integer> dictionary) throws IOException {
        List<Map.Entry<String, Integer>> dictList = new LinkedList<>(dictionary.entrySet());
        LinkedHashMap<String, Float> map = new LinkedHashMap<>();

        dictList.sort(Comparator.comparingInt(Map.Entry::getValue));
        Collections.reverse(dictList);

        float totalFreq = 0;
        for(Map.Entry<String, Integer> item : dictList){
            totalFreq = totalFreq + item.getValue();
        }

        for(int i=0; i < 3; i++){
            try{
                float probability = (float)dictList.get(i).getValue() / totalFreq;
                map.put(dictList.get(i).getKey(), probability);
                System.out.println(dictList.get(i).getKey() + " (probability " + probability + ")");
            }
            catch (Exception ignored){
            }

        }

        return map;
    }


}

