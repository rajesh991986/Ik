package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Sorting1 {
    public static void main(String[] args){
        //heapImplementation();
       // heapPriorityQueue();
       // mergeSort();
       // quickSort();
        ArrayList<Integer> auxArr = new ArrayList();
        auxArr.add( 7);
        auxArr.add( 5);
        auxArr.add( 8);
        auxArr.add( 9);
        auxArr.add( 4);
        auxArr.add( 1);
        auxArr.add( 7);

        merge_sort(auxArr);
    }
    public static void quickSort(){
        int arr[] = new int[]{10,5,20,80,15,19,7};
        quickSortHelper(arr,0,arr.length-1);
        print(arr);
    }


    static ArrayList<Integer> merge_sort(ArrayList<Integer> arr) {
        // Write your code here.
        mergeSortHelper(arr,0,arr.size()-1);
        for (Integer I:arr
             ) {
            System.out.println(I);
        }
        return arr;
    }
    static void mergeSortHelper(ArrayList<Integer> arr,int start,int end){
        if(start == end || start <0 || end > arr.size()-1 ){
            return;
        }
        int mid = start + (end-start)/2;
        mergeSortHelper(arr,start,mid);
        mergeSortHelper(arr,mid+1,end);
        ArrayList<Integer> auxArr = new ArrayList();
        int left = start,right=mid+1;
        while (left <= mid && right <= end ){
            if(arr.get(left) <= arr.get(right)){
                auxArr.add(arr.get(left));
                left++;
            }
            else{
                auxArr.add(arr.get(right));
                right++;
            }
        }
        while(left<=mid){
            auxArr.add(arr.get(left));
            left++;
        }
        while(right<=end){
            auxArr.add(arr.get(right));
            right++;
        }
        for(int i = start,j=0;i<=end;i++,j++){
            arr.set(i,auxArr.get(j));
        }
    }


    public static void quickSortHelper(int[] arr,int start,int end ){
        if(start == end || start > end){
            return;
        }
        int pivortIndex = start;
        int pivortValue = arr[pivortIndex];
        int left = start+1,right = end;
        while(left<=right){
            if(pivortValue>arr[left]){
                left++;
            }
            else{
                swap(arr,left,right);
                right--;
            }
        }
        pivortIndex=left-1;
        swap(arr,start,pivortIndex);
        System.out.println("Start: "+start+" End: "+end+" Pivort Value: "+ pivortValue);
        print(arr);
        quickSortHelper(arr,start,pivortIndex);
        quickSortHelper(arr,pivortIndex+1,end);
    }


   /* public static void quickSortHelper(int[] arr,int start,int end ){
        if(start==end || start> end){
            return;
        }
        int pivortIndex = start;
        int pivortValue = arr[pivortIndex];
        System.out.println("Start: "+start+" End: "+end+" Pivort Value: "+ pivortValue);
        int left = start+1,right = end;
        while(left<=right){
            if(pivortValue > arr[left]){
                left++;
            }
            else{
               swap(arr,left,right);
               right--;
            }
        }
        pivortIndex = left-1;
        System.out.println("PivortIndex:"+pivortIndex);
        swap(arr,start,pivortIndex);
        print(arr);
        quickSortHelper(arr,start,pivortIndex);
        quickSortHelper(arr,pivortIndex+1,end);
    }*/
    public static void mergeSort(){
        int arr[] = new int[]{10,5,20,80,15,19,7};
        mergeSortHelper(arr,0,arr.length-1);
        print(arr);
    }
    public static void print(int[] arr){
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
        System.out.println();
    }
    public static void mergeSortHelper(int arr[],int start,int end){
        if(start >= end ){
            return;
        }
        int mid = start + (end-start)/2;
        mergeSortHelper(arr,start,mid);
        mergeSortHelper(arr,mid+1,end);
        int[] b= new int[arr.length];
        int i=start,j=mid+1,k=start;
        while (i<=mid && j <= end){
            if(arr[i]<=arr[j]){
                b[k]= arr[i];
                k++;
                i++;
            }
            else{
                b[k] = arr[j];
                k++;j++;
            }
        }
        while (i<=mid){
            b[k]= arr[i];
            k++;
            i++;
        }
        while (j<=end){
            b[k] = arr[j];
            k++;j++;
        }
        for (int l = start,m=0; l <=end; l++,m++) {
            arr[l]=b[l];
        }
    }

    public static void heapPriorityQueue(){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        maxHeap.add(40);
        maxHeap.add(50);
        printPriorityQueue(maxHeap);
        //Remove Max Element
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());
        //Print
        printPriorityQueue(maxHeap);
    }
    public static void printPriorityQueue(PriorityQueue<Integer> maxHeap){
        System.out.println("Printing PriorityQueue");
        Iterator heapIterator = maxHeap.iterator();
        while (heapIterator.hasNext()){
            System.out.println(heapIterator.next());
        }
    }
    public static void heapImplementation(){
        int[] arr = new int[] {-1,6,4,3,5,8,1,2,10};
        for (int i = 1; i < arr.length ; i++) {
            createHeap(arr, i, arr[i]);
        }
        for (int i = arr.length-1; i >0 ; i--) {
            extractHeap(arr,i);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void extractHeap(int [] arr,int lindex){
        swap(arr,1,lindex);
        lindex--;
        heapify(arr,lindex,lindex);
    }

    public static void createHeap(int[] arr,int lindex,int newNumber){
        arr[lindex] = newNumber;
        heapify(arr,arr.length-1,lindex);
    }
    public static void heapify(int[] arr,int maxLastIndex,int lastIndex){
        if(lastIndex ==0){
            return;
        }
        if(lastIndex*2+1 < maxLastIndex){
            if(arr[lastIndex]< arr[2*lastIndex] &&
                    arr[lastIndex ]< arr[2*lastIndex + 1]){
                if(arr[2*lastIndex + 1] > arr[2*lastIndex]){
                    swap(arr,2*lastIndex + 1,lastIndex);
                    heapify(arr,maxLastIndex,2*lastIndex+1);
                }
                else {
                    swap(arr,2*lastIndex,lastIndex);
                    heapify(arr,maxLastIndex,2*lastIndex);
                }
            }
           else if(arr[lastIndex]< arr[2*lastIndex]){
                swap(arr,2*lastIndex,lastIndex);
                heapify(arr,maxLastIndex,2*lastIndex);
            }
            else if(arr[lastIndex]< arr[2*lastIndex+1]){
                swap(arr,2*lastIndex + 1,lastIndex);
                heapify(arr,maxLastIndex,2*lastIndex+1);
            }
        }
        else if (lastIndex*2 < maxLastIndex){
            if(arr[2*lastIndex] > arr[lastIndex]){
                swap(arr,2*lastIndex,lastIndex);
                heapify(arr,maxLastIndex,2*lastIndex);
            }
        }
        if(lastIndex>1) {
            if (arr[lastIndex] > arr[lastIndex / 2]) {
                swap(arr, lastIndex / 2, lastIndex);
            }
            heapify(arr,maxLastIndex,lastIndex / 2);
        }
    }
    public static void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
