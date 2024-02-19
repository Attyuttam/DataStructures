//Find median in a data stream:https://leetcode.com/problems/find-median-from-data-stream/description/?envType=study-plan-v2&envId=top-interview-150

/*
TLE solution
*/

class MedianFinder {
    List<Integer> arr;

    public MedianFinder() {
        arr = new ArrayList<>();   
    }
    
    public void addNum(int num) {
        arr.add(num);
    }
    
    public double findMedian() {
        Collections.sort(arr);
        if(arr.size()%2 == 0){
            return (arr.get(arr.size()/2) + arr.get((arr.size()/2)-1))/2.0;
        }else{
            return arr.get(arr.size()/2);
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
 
 /*
 My idea. Beat 68%
 
 all elements should be inserted first in max heap
 max element in max heap should be min element in min heap, else we move an element from max heap to min heap
 min heap size should never exceed max heap size, else we move an element from min to max heap
 max size of max heap can be min heap size + 1, else we move an element from max to min heap
 
 */
 
 class MedianFinder {
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;
    
    public MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>((a,b) -> b-a);        
    }
    
    private void rebalance(){
        if(max.size() > min.size() + 1){
            min.offer(max.poll());
        }
        if(!max.isEmpty() && !min.isEmpty() && max.peek() > min.peek()){
            min.offer(max.poll());
        }
        if(min.size() > max.size()){
            max.offer(min.poll());
        }
    }
    public void addNum(int num) {
        max.offer(num);
        rebalance();
    }
    
    public double findMedian() {
        if((min.size() + max.size())%2==0){
            return (min.peek() + max.peek())/2.0;
        }
        return 1.0 * max.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */