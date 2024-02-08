//Word search 2: https://leetcode.com/problems/word-search-ii/description/?envType=study-plan-v2&envId=top-interview-150
/*
This is the simple backtracking solution that I came up with but it gives a TLE at the 62nd test case
*/
class Solution {
    private boolean dfs(char[][] board, int x,int y, String word,int wp, boolean[][] visited){
        if(wp>=word.length())return true;
        if(wp == word.length()-1 && board[x][y] == word.charAt(wp))return true;
            
        if(board[x][y] != word.charAt(wp))return false;
        visited[x][y] = true;
        if(x-1>=0 && visited[x-1][y]==false){
            if(dfs(board,x-1,y,word,wp+1,visited))return true;
        }
        if(y-1>=0 && visited[x][y-1]==false){
            if(dfs(board,x,y-1,word,wp+1,visited))return true;
        }
        if(x+1<board.length && visited[x+1][y]==false){
            if(dfs(board,x+1,y,word,wp+1,visited))return true;
        }
        if(y+1<board[0].length && visited[x][y+1]==false){
            if(dfs(board,x,y+1,word,wp+1,visited))return true;
        }
        visited[x][y] = false;
        return false;
        
    }
    private boolean findWord(String word, char[][] board){
        char fc = word.charAt(0);
        boolean[][] visited = new boolean[board.length][board[0].length];

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j] == fc && dfs(board,i,j,word,0,visited)){
                    return true;
                }
            }
        }
        return false;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for(String word : words){
            if(findWord(word,board)){
                res.add(word);
            }
        }
        return res;
    }
}

/*
This is the same backtracking solution but with trie attached
*/

class Treenode{
    public Treenode next[];
    public String word;
    public Treenode(){
        this.next = new Treenode[26];
        this.word = null;
    }
}
class Solution {
    Set<String> res = new HashSet<>();
    private Treenode buildTrie(String[] words){
        Treenode root = new Treenode();
        for(String word : words){
            Treenode curr = root;
            for(char c : word.toCharArray()){
                if(curr.next[c-'a'] == null){
                    curr.next[c-'a'] = new Treenode();
                }
                curr = curr.next[c-'a'];
            }
            curr.word = word;
        }
        return root;
    }
    public void dfs(char[][] board, int i,int j, Treenode root){
        char c = board[i][j];
        if(board[i][j] == '#' || root.next[c-'a'] == null)return;
        
        root = root.next[c-'a'];
        if(root.word!=null){
            res.add(root.word);
            root.word = null;//to ensure that I am not adding this word again
        }
        board[i][j]='#';
        if(i-1>=0){
            dfs(board,i-1,j,root);
        }
        if(i+1<board.length){
            dfs(board,i+1,j,root);
        }
        if(j-1>=0){
            dfs(board,i,j-1,root);
        }
        if(j+1<board[0].length){
            dfs(board,i,j+1,root);
        }
        board[i][j]=c;

    }
    public List<String> findWords(char[][] board, String[] words) {
        Treenode root = buildTrie(words);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                dfs(board,i,j,root);
            }
        }
        return new ArrayList<>(res);
    }
}