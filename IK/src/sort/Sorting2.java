package sort;

import java.util.*;

public class Sorting2 {
    public static void main(String[] arg){
        int arr[] = new int[]{8,100,15,20,9,80,45,34};
       // heapSort(arr);
       // mergeSort(arr);
       // quickSort(arr);
       // countingSort(arr,100);
      //  bucketSort(arr);
        print(arr);
    }

    public static void bucketSort(int[] arr){
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(maxValue<arr[i]){
                maxValue=arr[i];
            }
        }
        int sqrt = (int)Math.sqrt(arr.length -1);
        List[] buckets = new List[sqrt+1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i <arr.length ; i++) {
            (buckets[hash(arr[i],sqrt,maxValue)]).add(arr[i]);
        }
        for (int i = 0; i < buckets.length; i++) {
           Collections.sort (buckets[hash(arr[i],sqrt,maxValue)]);
        }
        int count=0;
        for (List<Integer> bucket:buckets
             ) {
            for (Integer value:bucket
                 ) {
                arr[count] = value;
                count++;
            }
        }
    }

    public static int hash(int value,int sqrt,int maxValue){

        return (value/maxValue)*sqrt;
    }

    public static void countingSort(int[] arr,int maxValue){
        int[] counter= new int[maxValue+1];
        for (int input:arr
             ) {
            counter[input]++;
        }
        int ndx=0;
        for (int i = 0; i < counter.length; i++) {
            while(0<counter[i]){
                arr[ndx] = i;
                ndx++;
                counter[i]--;
            }
        }
    }
    public static void quickSort(int[] arr){
        quickSortHelper(arr,0,arr.length-1);
        print(arr);
    }
    public static void quickSortHelper(int[] arr,int start,int end){
        if(start == end || start > end){
            return;
        }
        int pivortIndex = start,pivortValue=arr[pivortIndex],left=start+1,right=end;
        while (left<=right){
            if(pivortValue > arr[left]){
                left++;
            }
            else{
                swap(arr,left,right);
                right--;
            }
        }
        pivortIndex = left  -1;
        swap(arr,pivortIndex,start);
        quickSortHelper(arr,start,pivortIndex-1);
        quickSortHelper(arr,pivortIndex+1,end);
    }

    public static void mergeSort(int[] arr){
        mergeSortHelper(arr,0,arr.length-1);
        print(arr);
    }
    public static void print(int[] arr){
        System.out.println("Print Array");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }

    public static void mergeSortHelper(int[] arr,int start,int end){
        if(start == end || start > end){
            return;
        }
        int mid = start + (end-start)/2;
        mergeSortHelper(arr,start,mid);
        mergeSortHelper(arr,mid+1,end);
        int[] sortArr = new int [arr.length];
        int left = start,right = mid+1,l=0;
        while(left<=mid && right <= end){
            if(arr[left]<= arr[right]){
                sortArr[l] = arr[left];
                left++;
                l++;
            }
            else{
                sortArr[l] = arr[right];
                right++;
                l++;
            }
        }
        while (left<=mid){
            sortArr[l] = arr[left];
            left++;
            l++;
        }
        while (right<=end){
            sortArr[l] = arr[right];
            right++;
            l++;
        }
        for (int i = start,j=0; i <= end ; i++,j++) {
            arr[i] = sortArr[j];
        }
    }
    public static void swap (int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public static void heapSort(int arr[]){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int a:arr
             ) {
            maxHeap.add(a);
        }
        System.out.println("Printing before sorting ");
        Iterator iterator = maxHeap.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("Printing after sorting");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxHeap.poll();
            System.out.println(arr[i]);
        }

    }
}
