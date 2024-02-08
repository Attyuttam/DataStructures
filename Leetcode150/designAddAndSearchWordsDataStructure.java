//design add and search words data structure: https://leetcode.com/problems/design-add-and-search-words-data-structure/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 60%
*/

class Node{
    char value;
    boolean isTerminal;
    Node[] children;

    public Node(char value, boolean isTerminal) {
        this.value = value;
        this.isTerminal = isTerminal;
        this.children = new Node[26];
    }
}
class WordDictionary {
    Node root;
    public WordDictionary() {
        this.root = new Node('.',false);
    }
    private int getIndex(Node[] ch){
        for(int i=0;i<26;i++){
            if(ch[i]!=null)return i;
        }
        return 0;
    }
    public void addWord(String word) {
        int n = word.length();
        Node curr = this.root;
        int index = -1;

        for(int i=0;i<n;i++){
            char ch = word.charAt(i);
            index = ch-97;
            if(curr.children[index] == null){
                curr.children[index] = new Node(ch,false);
            }
            curr = curr.children[index];
        }
        curr.isTerminal = true;
    }
    private boolean searchHelper(Node root, String word){
        int n = word.length();
        Node curr = root;
        int index = -1;
        for(int i=0;i<n;i++){
            char ch = word.charAt(i);
            if(ch == '.'){
                for(int j=0;j<26;j++){
                    if(curr.children[j]!=null && searchHelper(curr.children[j], word.substring(i+1))){
                        return true;
                    }
                }
                return false;
            }
            index = ch-97;
            if(curr.children[index] == null){
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isTerminal;
    }
    
    public boolean search(String word) {
        return searchHelper(root, word);
    }   
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */