//Implement Trie prefix tree: https://leetcode.com/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=top-interview-150

/*
Beats 62.9%
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
class Trie {
    Node root;
    public Trie() {
        this.root = new Node('.',false);
    }
    
    public void insert(String word) {
        int n = word.length();
        Node curr = this.root;
        
        for(int i=0;i<n;i++){
            char ch = word.charAt(i);
            if(curr.children[ch-97] == null){
                curr.children[ch-97] = new Node(ch,false);
            }
            curr = curr.children[ch-97];
        }
        curr.isTerminal = true;
    }
    
    public boolean search(String word) {
        int n = word.length();
        Node curr = this.root;
        
        for(int i=0;i<n;i++){
            char ch = word.charAt(i);
            if(curr.children[ch-97] == null){
                return false;
            }
            curr = curr.children[ch-97];
        }
        return curr.isTerminal;
    }
    
    public boolean startsWith(String word) {
        int n = word.length();
        Node curr = this.root;
        
        for(int i=0;i<n;i++){
            char ch = word.charAt(i);
            if(curr.children[ch-97] == null){
                return false;
            }
            curr = curr.children[ch-97];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */