
package com.company;

import java.io.*;
import java.util.*;

/**
 *
 * @author ajb
 */
public class DictionaryFinder {

    //String is the word, Int is the number of occurrences
    TreeMap<String, Integer> dict;

    public DictionaryFinder(){
    }
    /**
     * Reads all the words in a comma separated text document into an Array
     * @param file
     */
    public static ArrayList<String> readWordsFromCSV(String file) throws FileNotFoundException {
        Scanner sc=new Scanner(new File(file));
        sc.useDelimiter(" |,");
        ArrayList<String> words=new ArrayList<>();
        String str;
        while(sc.hasNext()){
            str=sc.next();
            str=str.trim();
            str=str.toLowerCase();
            words.add(str);
        }
        return words;
    }

    public static ArrayList<String> readWordsFromCSVNewLine(String file) throws FileNotFoundException {
        Scanner sc=new Scanner(new File(file));
        sc.useDelimiter("\n");
        ArrayList<String> words=new ArrayList<>();
        String str;
        while(sc.hasNext()){
            str=sc.next();
            str=str.trim();
            str=str.toLowerCase();
            words.add(str);
        }
        return words;
    }
    public static void saveCollectionToFile(Collection<?> c,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(Object w: c){
            printWriter.println(w.toString());
        }
        printWriter.close();
    }
    public TreeMap<String, Integer> formDictionary(List<String> words) throws FileNotFoundException {
        Collections.sort(words);
        dict = new TreeMap<String, Integer>();
        for(String word : words){
            //If word already exists, increment counter
            if(dict.containsKey(word)){
                dict.put(word, dict.get(word) + 1);
            }
            //Else, add word and set occurrences to 1
            else{
                dict.put(word, 1);
            }
        }
        return dict;
    }

public static void saveToFile(LinkedHashMap map,String file) throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    for(Object w: map.entrySet()){
        printWriter.println(w);
    }
    printWriter.close();
}

    public static void main(String[] args) throws Exception {
        DictionaryFinder df=new DictionaryFinder();
        //ArrayList<String> in=readWordsFromCSV("C:\\Teaching\\2017-2018\\Data Structures and Algorithms\\Coursework 2\\test.txt");
        //DO STUFF TO df HERE in countFrequencies
        //df.formDictionary(in);
        //df.saveToFile();

        Trie test = new Trie();
        System.out.println("Testing adding words to the trie - all should be T apart from the last");
        System.out.println(test.add("cheers"));
        System.out.println(test.add("cheese"));
        System.out.println(test.add("chat"));
        System.out.println(test.add("cat"));
        System.out.println(test.add("bat"));
        System.out.println(test.add("bat"));
        System.out.println("\n");
        System.out.println("Testing the contains method - should produce F,F,F,T,T");
        System.out.println(test.contains("chee"));
        System.out.println(test.contains("afc"));
        System.out.println(test.contains("ba"));
        System.out.println(test.contains("cheese"));
        System.out.println(test.contains("bat"));
        System.out.println("\n");
        System.out.println("Testing the breadth first search method - should produce bcaahttaetersse");
        System.out.println(test.outputBreadthFirstSearch());
        System.out.println("\n");
        System.out.println("Testing the depth first search method - should produce batcathateersse");
        System.out.println(test.outputDepthFirstSearch());
        System.out.println("\n");
        Trie subtrie = test.getSubTrie("ch");
        System.out.println("Testing the subTrie method via breadth search - should produce haetersse");
        System.out.println(subtrie.outputBreadthFirstSearch());
        System.out.println("\n");
        System.out.println("Testing getAllWords - should produce bat, cat, chat, cheers, cheese");
        System.out.println(test.getAllWords());

        System.out.println("\n");
        System.out.println("Now running AutoCompletion");
        AutoCompletion.main();

    }

}
