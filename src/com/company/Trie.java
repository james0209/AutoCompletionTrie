package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Trie extends TrieNode {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    /* let current node = root node
    for each letter in the key
        find the child node of current node associated with that letter
        if there is no child node associated with that letter, create a new node and add it to current node as a child associated with the letter
        set current node = child node
    add value to current node */
    public boolean add(String key) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            if (currentNode != null) {
                TrieNode next = currentNode.getOffspring(currentChar);
                if (next == null) {
                    next = TrieNode.newNode(key.charAt(i));
                    currentNode.addNode(next);
                }
                currentNode = next;
            }
        }

        // returns false if key is already in the trie
        if (currentNode.isComplete()) {
            return false;
        }

        currentNode.setComplete(true);
        return true;
    }


    public boolean contains(String key) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            TrieNode next = currentNode.getOffspring(currentChar);
            if (next == null) {
                return false;
            } else {
                currentNode = next;
            }
        }
        return currentNode.isComplete();
    }

    public String outputBreadthFirstSearch() {
        //Breadth first done using a queue
        Queue<TrieNode> nodes = new LinkedList();
        ArrayList<Character> characterArrayList = new ArrayList();
        nodes.add(root); //add root node to the queue
        while (!nodes.isEmpty()) { //while queue isn't empty
            TrieNode next = nodes.poll(); //set next node to item front of queue

            if (next.getOffspring() != null) {
                for (TrieNode node : next.getOffspring()) {
                    //if node is not null
                    if (node != null) {
                        //add a node to nodes linkedlist
                        nodes.add(node);

                    }
                }
                characterArrayList.add(next.getCharacter()); //add character value of node to an arraylist
            }

        }

        //Adding the arraylist to a string
        String outputBreadth = "";
        for (char character : characterArrayList) {
            outputBreadth = outputBreadth + character;
        }
        return outputBreadth;
    }


    public String outputDepthFirstSearch(){
        StringBuilder builder = new StringBuilder();
        outputDepthFirstSearch(root, builder);
        return builder.toString();
    }

    private static void outputDepthFirstSearch(TrieNode next, StringBuilder str){
        next.visited = true;
        for(int i = 0; i < next.offspring.length-1; i++){
            if(next.offspring[i] != null && !next.offspring[i].visited){
                outputDepthFirstSearch(next.offspring[i],str);
            }
        }
        str.append(next.getCharacter());
    }

    public Trie getSubTrie(String prefix){
        TrieNode next = root;
        Trie newTrie = new Trie();
        for(int i = 0; i < prefix.length(); i++){
            int index = (int)prefix.charAt(i)-97;
            if(next == null){
                return null;
            }
            if(next.getOffspring(prefix.charAt(i)) != null){
                newTrie.root = next.getOffspring(prefix.charAt(i));
            }
            next = next.offspring[index];
        }
        return newTrie;
    }

    public List<String> getAllWords() {
        List<String> output = new LinkedList<>();
        getAllWords("", root, output);
        return output;
    }

    private void getAllWords(String wordSoFar, TrieNode currentNode,
                             List<String> nodes) {
        for (TrieNode temp : currentNode.getOffspring()) {
            if (temp != null) {
                String currentPrefix = wordSoFar + temp.getCharacter();
                getAllWords(currentPrefix, temp, nodes);
            }
        }

        if (currentNode.isComplete()) {
            nodes.add(wordSoFar);
        }
    }

}
