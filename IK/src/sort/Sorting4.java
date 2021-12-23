package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Sorting4 {
    public static void main(String[] args){
        int arr[] = new int[]{5,1,1,2,0,0};
        //heapSort(arr);
        //mergeSort(arr);
         // quickSort(arr);
        // countingSort(arr,100);
        // bucketSort(arr);
        //countingSort(arr);
      //  quickSearch(arr,4);
         print(arr);
    }
    public static void countingSort(int[] arr){
        int max = 0;
        for (int element:arr
             ) {
            if(max<element){
                max = element;
            }
        }
        int[] lists = new int[max+1];
        for (int element:arr
             ) {
            lists[element]++;
        }
        int index = 0;
        for(int i=0;i<max+1;i++){
            while(lists[i] > 0){
               arr[index] = i;
               lists[i]--;
               index++;
            }
        }
    }

    public static void bucketSort(int[] arr){
        int max = 0;List[] lists = new List[max];
        for (int element:arr
             ) {
            if(max < element){
                max = element;
            }
        }
        int sqrt = (int)Math.sqrt(max-1);
        for (int i = 0; i < lists.length ; i++) {
            lists[i] = new ArrayList<Integer>();
        }
        for (int element:arr) {
            int hash = hash(element,max,sqrt);
            lists[hash].add(element);
        }
        int index =0;
        for (List<Integer> list:lists) {
            Collections.sort(list);
            if(null != list){
                for (Integer element:list
                     ) {
                    arr[index] = element;
                    index++;
                }
            }
        }
    }
    public static int hash(int value,int max,int sqrt){
        return  (value/max)*sqrt;
    }
    public static void quickSearch(int[] arr,int k){
        quickSearchHelper(arr,k,0,arr.length-1);
        System.out.println(arr[k]);
    }
    public static void quickSearchHelper(int[] arr,int k,int start,int end){
        if(start == end || start > end ){
            return ;
        }
        int pivortIndex = ((int) Math.random() * (end - start)) + start;
        int[] indexes = three_way_partation(arr,arr[pivortIndex],start,end);
        if(k >= indexes[0]&& k <= (indexes[1]-1)){
            return;
        }
        if(k< indexes[0]){
            quickSearchHelper(arr,k,start,indexes[0]-1);
        }
        else{
            quickSearchHelper(arr,k,indexes[1],end);
        }
    }
    private static void quickSort(int[] arr) {
        quickSortHelper(arr,0,arr.length-1);
    }

    private static void quickSortHelper(int[] arr, int start, int end) {
        if(start == end || start > end){
            return;
        }
        int pivortValue = arr[start];
        int[] indexs = three_way_partation(arr,pivortValue,start,end);
        quickSortHelper(arr,start,indexs[0]-1);
        quickSortHelper(arr,indexs[1],end);
    }
    private static int[] three_way_partation(int[] arr,int pivort,int start,int end){
        int [] returnIndexs = new int[]{-1,-1};
        int l=start,r=start,h=end;
        while(r<=h){
            if(pivort<arr[r]){
                swap(arr,h,r);
                h--;
            }
            else if(pivort> arr[r]){
                swap (arr,l,r);
                l++;
                r++;
            }
            else{
                r++;
            }
        }
        returnIndexs[0]=l;
        returnIndexs[1]=r;

        return  returnIndexs;
    }


    private static void mergeSort(int[] arr) {
        mergeSortHelper(arr,0,arr.length-1);
    }
    private static void mergeSortHelper(int[] arr,int start,int end) {
        if(start == end || start> end){
            return;
        }
        int mid = start + (end - start)/2;
        mergeSortHelper(arr,start,mid);
        mergeSortHelper(arr,mid+1,end);
        int[] auxArr = new int[arr.length];
        int left = start,right = mid+1,auxIndex =0;
        while(left<=mid && right <= end){
            if(arr[left]<= arr[right]){
                auxArr[auxIndex] = arr[left];
                auxIndex++;
                left++;
            }
            else{
                auxArr[auxIndex] = arr[right];
                auxIndex++;
                right++;
            }
        }
        while(left<=mid){
            auxArr[auxIndex] = arr[left];
            auxIndex++;
            left++;
        }
        while(right<=end){
            auxArr[auxIndex] = arr[right];
            auxIndex++;
            right++;
        }
        auxIndex=0;
        for (int i = start; i <=end ; i++,auxIndex++) {
            arr[i] = auxArr[auxIndex];
        }
    }

    public static void heapSort(int arr[]){
        PriorityQueue<Integer> priorityQueu = new PriorityQueue<Integer>();
        for (Integer element:arr
             ) {
            priorityQueu.add(element);
        }
        int i=0;
        while(!priorityQueu.isEmpty()){
            arr[i] = priorityQueu.poll();
            i++;
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
