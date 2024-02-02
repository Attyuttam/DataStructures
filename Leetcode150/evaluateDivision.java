//Evaluate division: https://leetcode.com/problems/evaluate-division/description/?envType=study-plan-v2&envId=top-interview-150

/*
The solution was extremely tricky and I found it very difficult
*/
class NodeVal{
    String name;
    double val;

    public NodeVal(String name, double val){
        this.name = name;
        this.val = val;
    }
}
class Solution {
    private double dfs(String node1, String node2, Map<String, List<NodeVal>> adj, Map<String,Boolean> vis, double val){
        vis.put(node1, true);
        List<NodeVal> neighbors = adj.get(node1);
        for(int i=0;i<neighbors.size();i++){
            String t = neighbors.get(i).name;
            if(t.equals(node2))return val*neighbors.get(i).val;

            if(vis.get(t) == false){ 
                
                double temp = dfs(t, node2, adj, vis, val*neighbors.get(i).val);
                if(temp!=Double.MAX_VALUE)return temp;
            }
        }
        return Double.MAX_VALUE;
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //form an adjacency list
        Map<String, List<NodeVal>> adj = new HashMap<>();
        for(int i=0;i<equations.size();i++){
            List<NodeVal> nv1 = adj.getOrDefault(equations.get(i).get(0), new ArrayList<>());
            List<NodeVal> nv2 = adj.getOrDefault(equations.get(i).get(1), new ArrayList<>());
            nv1.add(new NodeVal(equations.get(i).get(1), values[i]));
            nv2.add(new NodeVal(equations.get(i).get(0), 1.0/values[i]));

            adj.put(equations.get(i).get(0), nv1);
            adj.put(equations.get(i).get(1), nv2);
        }
        Map<String,Boolean> visited = new HashMap<>();
        
        double[] result = new double[queries.size()];
        for(int i=0;i<queries.size();i++){
            for(String key : adj.keySet()){
                visited.put(key, false);
            }
            String node1 = queries.get(i).get(0);
            String node2 = queries.get(i).get(1);

            if(adj.get(node1) == null || adj.get(node2) == null){
                result[i] = -1.0;
            }else if(node1.equals(node2)){
                result[i] = 1.0;
            }else{
                double v = dfs(node1, node2, adj, visited, 1.0);
                if(v != Double.MAX_VALUE)
                    result[i] = v;
                else
                    result[i] = -1;
            }
        }

        return result;
    }
}