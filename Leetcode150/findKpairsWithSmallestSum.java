//Find k pairs with smallest sums : https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/?envType=study-plan-v2&envId=top-interview-150

/*
I had to see this solution, but this only beats 7%.
I need to practice more heap based questions
*/
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a,b) -> (b.get(0)+b.get(1)) - (a.get(0)+a.get(1)));

        for(int i=0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                List<Integer> curr = pq.peek();
                if(pq.size() == k && curr.get(0)+curr.get(1) <= nums1[i] + nums2[j]){
                    break;
                }
                pq.offer(Arrays.asList(nums1[i],nums2[j]));
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
        return new ArrayList<>(pq);
    }
}
/*
Took this solution that I took from GFG.
Pretty intuitive as at every point, it is taking both possible elements from that point.

Beats 31%
*/
class Element{
    int sum;
    int ind1;
    int ind2;

    public Element(int sum, int ind1, int ind2){
        this.sum = sum;
        this.ind1 = ind1;
        this.ind2 = ind2;
    }
}
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Set<String> s = new HashSet<>();
        PriorityQueue<Element> pq = new PriorityQueue<>((a,b)->a.sum-b.sum);

        pq.add(new Element(nums1[0]+nums2[0],0,0));
        s.add("0,0");

        List<List<Integer>> res = new ArrayList<>();
        
        int c = 0;
        while(c<k && !pq.isEmpty()){
            Element curr = pq.poll();
            res.add(Arrays.asList(nums1[curr.ind1],nums2[curr.ind2]));
            String currs1 = (curr.ind1+1)+","+curr.ind2;
            String currs2 = curr.ind1+","+(curr.ind2+1);

            
            if(curr.ind1+1<nums1.length && !s.contains(currs1)){
                pq.add(new Element(nums1[curr.ind1+1]+nums2[curr.ind2],curr.ind1+1,curr.ind2));
                s.add(currs1);
            }
            if(curr.ind2+1<nums2.length && !s.contains(currs2)){
                pq.add(new Element(nums1[curr.ind1]+nums2[curr.ind2+1],curr.ind1,curr.ind2+1));
                s.add(currs2);
            }
            c++;
        }
        
        return res;
    }
}

/*
Beautiful solution, I have tears in my eyes.

See explanation in notebook for more details on how this solution works

Beats 61.86%
*/
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
        List<List<Integer>> pairs = new ArrayList<> ();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return pairs;
        }
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<> ((arr1, arr2) -> arr1[0] + arr1[1] - arr2[0] - arr2[1]);
        
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer (new int[] {nums1[i], nums2[0], 0});
        }
        
        while (k-- != 0 && !minHeap.isEmpty ()) {
            int[] curr = minHeap.poll ();
            pairs.add (List.of (curr[0], curr[1]));
            if (curr[2] + 1 < nums2.length) {
                minHeap.offer (new int[] {curr[0], nums2[curr[2] + 1], curr[2] + 1});
            }
        }
        
        return pairs;
    }
}

/*

People who used Pair object instead of an array in the above solution beat 90%
*/
class Pair {
        int sum;
        int num1Idx;
        int num2Idx;

        public Pair(int sum, int num1Idx, int num2Idx) {
            this.sum = sum;
            this.num1Idx = num1Idx;
            this.num2Idx = num2Idx;
        }
    }

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> kSmallestPairs = new ArrayList<>();

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.sum));

        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.add(new Pair(nums1[i] + nums2[0], i, 0));
        }

        while (k-- > 0) {
            Pair p = minHeap.remove();

            kSmallestPairs.add(Arrays.asList(nums1[p.num1Idx], nums2[p.num2Idx]));

            int newNum2Idx = p.num2Idx + 1;
            if (newNum2Idx < nums2.length) {
                minHeap.add(new Pair(nums1[p.num1Idx] + nums2[newNum2Idx], p.num1Idx, newNum2Idx));
            }
        }

        return kSmallestPairs;
    }
}