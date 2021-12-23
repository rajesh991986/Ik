package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Sorting5 {
    public static void main(String[] args){
        int[] arr = new int[]{8,20,9,40,1,6,6,9,1};
        print(arr);
    }
    //System.out.println(quickSearch(arr,99));
    public static int quickSearch(int[] arr,int target){
        int start =0,end = arr.length-1;
        while (start <=end) {
            int pivortIndex = start, pivortValue = arr[pivortIndex];
            int[] index = three_way_partition(arr, start, end, pivortValue);
            if (pivortValue == target) {
                return index[0];
            } else if (target > pivortValue) {
                start = index[1];
            } else {
                end = index[0] - 1;
            }
        }
        return -1;
    }
    // quickSort(arr,0,arr.length-1);
    //        System.out.println(binarySearch(arr,6));
    public static int binarySearch(int[] arr,int target){
        int start = 0,end = arr.length-1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
    //countingSort(arr);
    public static void countingSort(int[] arr){
        int max =0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        int[] list = new int[max+1];
        for (int i = 0; i < arr.length; i++) {
            list[arr[i]] ++;
        }
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while(list[i]>0){
                arr[index] = i;
                list[i]--;
                index++;
            }
        }
    }

    // bucketSort(arr);
    public static void bucketSort(int[] arr){
        int max =0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        int sqrt =(int) Math.sqrt(max);
        List[] buckets = new List[sqrt + 1];
        for (int i = 0; i < arr.length; i++) {
            int hash = hash(arr[i],max,sqrt);
            if(buckets[hash] == null){
                buckets[hash] = new ArrayList();
            }
            (buckets[hash]).add(arr[i]);
        }
        int index = 0;
        for(int i=0;i<buckets.length;i++){
            ArrayList<Integer> arrayList = (ArrayList<Integer>)buckets[i];
            if(null != arrayList) {
                Collections.sort(arrayList);
                for (Integer current : arrayList
                ) {
                    arr[index] = current;
                    index++;
                }
            }
        }
    }
    public static int hash(int value,int max, int sqrt){
        return (value/max)*sqrt;
    }
    // heapSort(arr);
    public static void heapSort(int[]  arr){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i =0;i<arr.length;i++){
            priorityQueue.add(arr[i]);
        }
        for(int i=0;i<arr.length;i++){
            arr[i] = priorityQueue.poll();
        }
    }
    //quickSort(arr,0,arr.length-1);
    public static void quickSort(int[] arr,int start,int end){
        if(start == end || start > end ){
            return;
        }
        int pivort = start,pivotValue = arr[pivort];
        int[] indexs = three_way_partition(arr,start,end,pivotValue);
        quickSort(arr,start,indexs[0]-1);
        quickSort(arr,indexs[1],end);
    }

    public static int[] three_way_partition(int[] arr,int start,int end,int pivortValue){
        int left = start,right = start,up = end ;
        while (right<=up){
            if(arr[right] < pivortValue){
                swap(arr,left,right);
                left++;
                right++;
            }
            else if(arr[right] > pivortValue){
                swap(arr,right,up);
                up--;
            }
            else{
                right++;
            }
        }
        return new int[]{left,right};
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
    //mergeSort(arr,0,arr.length-1);
    public static void mergeSort(int[] arr,int start,int end){
        if(start == end || start > end){
            return;
        }
        int mid = start + (end - start)/2;
        mergeSort(arr,start,mid);
        mergeSort(arr,mid+1,end);
        int[] auxArr = new int[arr.length];
        int left = start,right = mid+1,auxIndex = start;
        while(left <=mid && right <=end){
            if(arr[left] <= arr[right]){
                auxArr[auxIndex] = arr[left];
                left++;auxIndex++;
            }
            else {
                auxArr[auxIndex] = arr[right];
                right++;auxIndex++;
            }
        }
        while (left<=mid){
            auxArr[auxIndex] = arr[left];
            left++;auxIndex++;
        }
        while (right <=end){
            auxArr[auxIndex] = arr[right];
            right++;auxIndex++;
        }
        for(int i=start;i<=end;i++){
            arr[i] = auxArr[i];
        }
    }
}
