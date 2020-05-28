package com.company;

public class TrieNode {

    private char character;
    TrieNode[] offspring = new TrieNode[26];
    private boolean isComplete;
    Boolean visited;

    public TrieNode(){
        this.offspring = new TrieNode[26];
        visited = false;
    }

    public TrieNode(char character){
        this.character = character;
        this.offspring = new TrieNode[26];
        for(int i  = 0; i < offspring.length; i++){
            offspring[i] = null;
        }
        visited = false;
    }


    public boolean isComplete() {
        return this.isComplete;
    }

    public char getCharacter() {
        return character;
    }

    public TrieNode getNode(char c) {
        return this.offspring[getCharIndex(c)];
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setOffspring(TrieNode[] offspring) {
        this.offspring = offspring;
    }

    public static int getCharIndex(char c) {
        return c - 'a';
    }

    public static TrieNode newNode(char c){
        TrieNode newNode = new TrieNode();
        newNode.isComplete = false;
        newNode.character = c;
        for (int i = 0; i < newNode.offspring.length; i++){
            newNode.offspring[i] = null;
        }
        return newNode;
    }
    public void addNode(TrieNode next){
        int index = (int)next.character-97;
        offspring[index] = next;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public TrieNode[] getOffspring(){
        return this.offspring;
    }

    public TrieNode getOffspring(char c){
        for(int i = 0; i < offspring.length; i++){
            if (offspring[i] != null && offspring[i].character == c){
                return offspring[i];
            }
        }
        return null;
    }

}
