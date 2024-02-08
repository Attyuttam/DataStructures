//Snakes and ladders

class Cell{
    int x;
    int y;
    int hashCode;


    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cell that = (Cell) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
class Solution {
    HashMap<Integer, Cell> numToCell;
    HashMap<Cell, Integer> cellToNum;

    private void initializeMap(int n){
        numToCell = new HashMap<>();
        cellToNum  = new HashMap<>();
        int v = 1;
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<n;j++){
                Cell c = new Cell(i,j);
                numToCell.put(v,c);
                cellToNum.put(c,v);
                v++;
            }
        }
    }
    private void bfs(int[][] board){
        int minMoves = Integer.MAX_VALUE;
		Queue<Integer> q = new LinkedList<>();
		
		q.add(1);
		moves = 0;
		while(!q.isEmpty()){
			moves++;
			int currCell = q.poll();
			Cell c = numToCell.get(currCell);
			
			if(c.x == 0 && c.y == 0){
				minMoves = Math.min(minMoves, moves);
			}else if(board[c.x][c.y] == -1){
				for(int i=currCell+1;i<=Math.min(currCell+6,Math.pow(board.length,2));i++){
					q.add(i);
				}
			}else{
				q.add(board[c.x][c.y]);
			}
		}
		return minMoves;
    }
    public int snakesAndLadders(int[][] board) {
        initializeMap(board.length);
        return bfs(board);
    }
}

/*
Got this from the solution list

The idea is that when we are going to jump to a cell, we check if the cell jumps to a different cell, if yes we update the number of steps required for that cell

The idea of this kind of BFS is that you know where to jump OR what the next possible cell is.
Only when there is a plethora of options to be selected and you need to try all of them out, does the concept of backtracking comes up and
that is when you use the visited concept. And it is critical to handle visited properly because when you backtrack a path you need to ensure
that you are reversing the value of that visited element and making it available. Handling this concept via queue seems to be difficult to me as of now !!!
*/

class Solution {

    public int snakesAndLadders(int[][] board) {
        int n = board.length, k = 1, steps = 0,res = -1, b[];
            
        b = new int[n*n+1];
        int map[] = new int[n*n+1];
        for(int i = 0; i < n; i++) {
            for(int j = (i%2==0)?0:n-1; j>=0 && j<n; ) {
                b[k++] = board[n-i-1][j];
                if(i%2==0) j++; else j--;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while(!q.isEmpty()) {
            int size = q.size();
            Queue<Integer> temp = new LinkedList<>();

            for(int j=0;j<size;j++){
                int curr = q.poll();
                for(int i = curr + 1; i <= curr + 6; i++) {
                    int ind = (b[i] == -1)? i: b[i];
                    if(map[ind] == 0){
                        map[ind] = steps + 1;
                        if(ind == n*n) return map[ind];
                        q.add(ind);
                    }
                }
            }
        
            steps++;
        }
        return -1;
    }
}