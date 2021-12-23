package sort;

import java.util.*;

public class Sorting3 {
    public static void main(String[] arg){
        int arr[] = new int[]{5,1,1,2,0,0};
        quickSearch(arr,5);
        //heapSort(arr);
         //mergeSort(arr);
       //  quickSort(arr);
        // countingSort(arr,100);
         // bucketSort(arr);
        //countingSort(arr);
       // print(arr);
        //List 1


    }
    public static void quickSearch(int[] arr,int k){
      int value=  quickSearchHelper(arr,k,0,arr.length-1);
        System.out.println(value);
    }

    private static int quickSearchHelper(int[] arr, int k, int start, int end) {
        int pivortValue = arr[start];
       int[] lrIndex =  three_way_partition(arr,pivortValue,start,end);
       int kValue = -1;
       if(k >= lrIndex[0] && k < lrIndex[1] ){
           return pivortValue;
       }
       else if(k < lrIndex[0]){
           kValue= quickSearchHelper(arr,k,start,lrIndex[0]-1);
       }
       else{
           kValue= quickSearchHelper(arr,k,lrIndex[1],end);
       }
       return  kValue;
    }
    public static int[] three_way_partition(int[] arr,int pivortValue,int start,int end){
        int l=start,r=start,u=end;
        while(r<=u){
            if(pivortValue< arr[r]){
                swap(arr,r,u);
                u--;
            }
            else if(pivortValue > arr[r]){
                swap(arr,l,r);
                l++;
                r++;
            }
            else{
                r++;
            }
        }
        return new int[]{l,r};
    }

    public static List<Integer> find_intersection(List<Integer> arr1, List<Integer> arr2, List<Integer> arr3) {
        // Write your code here
        List<Integer> intersection = find_intersection(arr1,arr2);
        if(!intersection.isEmpty()){
            intersection = find_intersection(intersection,arr3);
        }
        if(intersection.isEmpty()){
            intersection.add(-1);
        }
        return intersection;
    }
    public static List<Integer> find_intersection(List<Integer> arr1, List<Integer> arr2){
        List<Integer> intersection = new ArrayList<Integer>();
        int arr1Index =0,arr2Index =0;
        while (arr1Index < arr1.size() && arr2Index < arr2.size()){
            while((arr1Index < arr1.size() && arr2Index < arr2.size()) && arr1.get(arr1Index).intValue() == arr2.get(arr2Index).intValue()){
                intersection.add(arr1.get(arr1Index));
                arr1Index++;
                arr2Index++;
            }
            if(arr1Index < arr1.size() && arr2Index < arr2.size()){
                if (arr1.get(arr1Index) < arr2.get(arr2Index)){
                    arr1Index++;
                }
                else{
                    arr2Index++;
                }
            }
        }
        return intersection;
    }

    public static int bs(List<Integer> arr,int target){
        if(arr.size() <=0){
            return -1;
        }
        int start =0,end = arr.size()-1,mid=0;
        while(start<=end){
            mid=start + (end-start)/2;
            if(arr.get(mid) == target){
                return mid;
            }
            else if (target < arr.get(mid)){
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }
        return -1;
    }

    /*  List<Integer> namesList = Arrays.asList(10, 10,1,100,1,100,10,10);

           //List 2
           ArrayList<Integer> otherList = new ArrayList<>();

           //Copy all items from list 1 to list 2
           otherList.addAll(namesList);
           // ArrayList<Integer> arr = find_top_k_frequent_elements(otherList,3);
           List<Integer> arr = online_median(otherList);
           System.out.println(arr.toString());*/
    // Complete the function below.
    public static List<Integer> online_median(List<Integer> stream) {
        List<Integer> newStream = new ArrayList<>();
        List<Integer> returnList= new ArrayList<>();
        for(Integer ele:stream){
            insert(newStream,ele);
            returnList.add(median(newStream));
        }
        return returnList;
    }
    public static void insert(List<Integer> stream,int element){
        int streamSize = stream.size();
        if(streamSize==0 || stream.get(streamSize-1)<= element){
            stream.add(element);
            return;
        }
        if(stream.get(0)>= element){
            stream.add(0,element);
            return;
        }
        int i=0;
        while(i<streamSize){
            int  mid= i + (streamSize - i)/2;
            if(stream.get(mid)<=element && stream.get(mid+1) > element){
                stream.add(mid+1,element);
                return;
            }

            if(element<stream.get(mid)){
                streamSize = mid;
            }
            else{
                i = mid;
            }
        }
    }
    public static Integer median(List<Integer> stream){
    /* PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for(Integer ele:stream){
            minHeap.offer(ele);
        }
        int i=0;
        List<Integer> returnList = new ArrayList<Integer>();
        while(!minHeap.isEmpty()){
            stream.set(i,minHeap.poll());
            i++;
        }*/
        int size = stream.size();
        if(size%2 == 1){
            int value = stream.get(size/2);
            return value;
        }
        else{
            int value=(stream.get(size/2)+stream.get(size/2-1))/2;
            return value;
        }
    }

    static ArrayList<Integer> find_top_k_frequent_elements(ArrayList<Integer> arr, Integer k) {
        // Write your code here.
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        for(Integer element:arr){
            if(freqMap.get(element) == null){
                freqMap.put(element,1);
            }
            else{
                freqMap.put(element, freqMap.get(element)+1);
            }
        }
        HashMap<Integer,ArrayList> freqEleme = new HashMap<>();
        int maxFrequency =0;
        for(Integer element:arr){
            int freq= freqMap.get(element);
            if(maxFrequency<freq){
                maxFrequency=freq;
            }
            if(null == freqEleme.get(freq)){
                ArrayList<Integer> eleArr= new ArrayList<>();
                eleArr.add(element);
                freqEleme.put(freq,eleArr );
            }
            else{
                (freqEleme.get(freq)).add(element);
            }
        }
        ArrayList<Integer> returnList= new ArrayList();
        for(int i=maxFrequency;(i>0) && (returnList.size()<k);i--){
            ArrayList<Integer> eleArr= freqEleme.get(i);
            if(eleArr==null){
                continue;
            }
            for(Integer element:eleArr){
                returnList.add(element);
                if(returnList.size()==k){
                    return returnList;
                }
            }
        }
        return new ArrayList();
    }
    /*List<List<Integer>> intervals = new ArrayList<>();
           List<Integer> list = new ArrayList<>();
           list.add(1);
           list.add(5);
           List<Integer> list2 = new ArrayList<>();
           list2.add(5);
           list2.add(8);
           List<Integer> list3 = new ArrayList<>();
           list3.add(10);
           list3.add(15);
           intervals.add(list);
           intervals.add(list2);
           intervals.add(list3);
           System.out.println(can_attend_all_meetings(intervals));*/
    public static int can_attend_all_meetings(List<List<Integer>> intervals) {
        PriorityQueue<List<Integer>> times
                = new PriorityQueue<List<Integer>>(intervals.size(),new TimeComparitor());
        for(List<Integer> time :intervals){
            times.add(time);
        }
        int prevMeetingEndTime = 0;
        while(!times.isEmpty()){
            List<Integer> currentMeetTime = times.poll();
            System.out.println("[" + currentMeetTime.get(0)+","+currentMeetTime.get(1)+"]");
            if(prevMeetingEndTime>currentMeetTime.get(0)){
                return 0;
            }
            prevMeetingEndTime= currentMeetTime.get(1);
        }
        return 1;
    }

    static class TimeComparitor implements Comparator<List<Integer>>{

        public int compare(List<Integer> time1,List<Integer> time2){
            if (null != time1 && null != time2){
                if(time1.get(0)<time2.get(0)){
                    return -1;
                }
                else if(time1.get(0)>time2.get(0)){
                    return 1;
                }
                else{
                    return 0;
                }
            }
            return 1;
        }

    }
    public static void countingSort(int[] arr) {
        int max=0;
        for(int i=0;i<arr.length;i++){
            if(max<arr[i]){
                max=arr[i];
            }
        }
        int[] countingArray= new int[max+1];
        for(int i=0;i<countingArray.length;i++){
            countingArray[i]= Integer.MAX_VALUE;
        }
        for(int i=0;i<arr.length;i++){
            if(countingArray[arr[i]] < Integer.MAX_VALUE){
                countingArray[arr[i]] = countingArray[arr[i]] +1;
            }
            else{
                countingArray[arr[i]] = 1;
            }

        }
        int count=0;
        for(int i=0;i<=max;i++){
            if(countingArray[i] < Integer.MAX_VALUE){
                while(countingArray[i]>0){
                    arr[count] = i;
                    count++;
                    countingArray[i]--;
                }

            }
        }
    }
    public static void bucketSort(int[] arr){
        int max = 0;
        if(arr.length == 1){
            return;
        }
        for(int i=0;i<arr.length;i++){
            if(max<arr[i]){
                max=arr[i];
            }
        }
        int numOfBuckets =(int) Math.sqrt(arr.length -1);
        List[] list = new List[numOfBuckets+1];
        for(int i=0;i<list.length;i++){
            list[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<arr.length;i++){
            int hashValue = hash(arr[i],max,numOfBuckets);
            if(hashValue<0){
                hashValue=0;
            }
            (list[hashValue]).add(arr[i]);
        }
        for(int i=0;i<list.length;i++){
            Collections.sort(list[i]);
        }
        int index=0;
        for(int i=0;i<list.length;i++){
            for(int j=0;j<list[i].size();j++){
                arr[index] = (int)list[i].get(j);
                index++;
            }
        }

    }
    public static int hash(int number,int max,int sqrt){
        return Math.abs((number/max) * sqrt);
    }
    public static void quickSort(int[] arr){
        quickSortHelper(arr,0,arr.length-1);
    }
    public static void quickSortHelper(int[] arr, int start,int end ){
        if(start == end || start > end ){
            return;
        }
        int pivort=arr[start], pivortIndex = start,left=start+1,right=end;
        while(left<=right){
            if(pivort>arr[left]){
                left++;
            }
            else{
                swap(arr,left,right);
                right--;
            }
        }
        swap(arr,left-1,pivortIndex);
        pivortIndex = left-1;
        quickSortHelper(arr,start,pivortIndex-1);
        quickSortHelper(arr,pivortIndex+1,end);
    }
    public static void mergeSort(int[] arr){
        mergeSortHelper(arr,0,arr.length-1);
    }
    public static void mergeSortHelper(int[] arr,int start,int end){
        if(start == end || start > end){
            return;
        }
        int mid = start + (end-start)/2;
        mergeSortHelper(arr,start,mid);
        mergeSortHelper(arr,mid+1,end);
        int[] auxArr = new int[arr.length];
        int left=start,right = mid+1,auxIndex=0;
        while (left<=mid && right <=end ){
            if(arr[left] <= arr[right]){
                auxArr[auxIndex] = arr[left];
                left++;
                auxIndex++;
            }
            else{
                auxArr[auxIndex] = arr[right];
                right++;
                auxIndex++;
            }
        }
        while(left<=mid){
            auxArr[auxIndex] = arr[left];
            left++;
            auxIndex++;
        }
        while(right<=end){
            auxArr[auxIndex] = arr[right];
            right++;
            auxIndex++;
        }
        auxIndex=0;
        for (int i = start; i <= end; i++,auxIndex++) {
            arr[i] = auxArr[auxIndex];
        }
    }
    public static void heapSort(int[] arr){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
        }
        Iterator iterator = maxHeap.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for (int i = 0; i < arr.length; i++) {
           arr[i] = maxHeap.poll();
        }
    }
    public static void swap (int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public static void print(int[] arr){
        System.out.println("Print Array");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }
}
