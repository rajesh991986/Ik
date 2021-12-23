package sort;

import java.util.ArrayList;
import java.util.Random;

public class Sorting {
    public static void main(String[] arg){
      //  int[] arr = new int[] {6,4,3,5,8,1,2}; // 66538,46538
      //  int[] sarr = new int[] {1,4,7,8,13,15,20,25};
       // insection(arr);
      //  selectionSort(arr);
       // bubble(arr);
       // arr =  mergeSort(arr,0,arr.length -1);
      //  arr =  quickSort(arr,0,arr.length -1);
      //  arr =  lomotoQuickSort(arr,0,arr.length -1);
     //   HoaresoQuickSort(arr,0,arr.length-1);
        //int[] num= new int[]{1,2,3,4,5};
       // System.out.println(find_rotate_index(num,0,4));
      //  System.out.println(binarySearch(sarr,15));
        //ArrayList<Integer> reader = new ArrayList<>();
        //reader.add(5);
        /*
        reader.add(0);
        reader.add(3);
        reader.add(4);
        reader.add(9);
        reader.add(12);*/
      //  System.out.println(search(reader,2));
       /* String arr[] = {"for", "geeks", "", "", "", "", "ide",
                "practice", "", "", "", "quiz"};
        String x = "practice";
        System.out.println(sparseSearch(arr,x,0,arr.length-1));*/
        // print(arr);
        //  System.out.println(arr);
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

    public static int sparseSearch(String[] arr,String target,int left,int right){
            if(left==right){
                return -1;
            }
            int pivort = (left+right)/2;
            int index = -1;
            if(arr[pivort].equalsIgnoreCase(target)){
                return pivort;
            }
            if(arr[pivort].equalsIgnoreCase("")){
               int index1= sparseSearch(arr,target,left,pivort-1);
               if(index1 > -1){
                   return index1;
               }
                int index2= sparseSearch(arr,target,pivort+1,right);
                if(index2 > -1){
                    return index2;
                }
            }
            if(target.charAt(0) <= arr[pivort].charAt(0)){
                index = sparseSearch(arr,target,left,pivort-1);
            }
            else{
                index = sparseSearch(arr,target,pivort+1,right);
            }
        return  index;
    }
    public static int search(ArrayList<Integer> reader, int target) {

        if(reader.get(0) == Integer.MAX_VALUE || reader.get(1) == Integer.MAX_VALUE){
            return -1;
        }
        int lenght=1;
       /* while (reader.get(2^lenght)!=Integer.MAX_VALUE){
            lenght++;
        }
        int left=2^(lenght-1),right=2^(lenght),maxLenght=0;
        while(left<=right){
            int pivort = (left+right)/2;
            if(reader.get(pivort)!= Integer.MAX_VALUE && reader.get(pivort+1)== Integer.MAX_VALUE){
                maxLenght=pivort;
                break;
            }
            if(reader.get(pivort)== Integer.MAX_VALUE){
                right = pivort-1;
            }
            else {
                left = pivort +1;
            }
        }*/

      int left = 0 , right=reader.size()-1;
       while(left<=right){
           int pivort= (left+right)/2;
           int pivortValue =reader.get(pivort);
           if(pivortValue == target){
               return pivort;
           }
           if(target <= pivortValue){
                right = pivort -1;
           }
           else {
               left = pivortValue +1;
           }
       }

        return -1;
    }
    public static int binarySearch(int[] arr,int target){
        int left=0,right=arr.length-1;
        while(left<=right){
            int pivort=(left+right)/2;
            if(target == arr[pivort]){
                return pivort;
            }
            if(target<=arr[pivort]){
                right=pivort-1;
            }
            else{
                left = pivort+1;
            }
        }
        return -1;
    }
    public static int find_rotate_index(int [] nums,int left, int right) {
        if (nums[left] < nums[right])
            return 0;

        while (left <= right) {
            int pivot = (left + right) / 2;
            if (nums[pivot] > nums[pivot + 1])
                return pivot + 1;
            else {
                if (nums[pivot] < nums[left])
                    right = pivot - 1;
                else
                    left = pivot + 1;
            }
        }
        return 0;
    }




    public static void HoaresoQuickSort(int[] arr,int start, int end){
        if(start == end){
            return ;
        }
      //  System.out.println("start: "+start +" :end: "+end);
        int pindex = start; //(int) (Math.random()*((end+1)-start)) + start;
        int pivort = arr[pindex];
        int smaller = start+1,bigger=end;
        while((smaller)<bigger){

            if(arr[smaller] < pivort){
                smaller ++;
            }
            if(arr[bigger] > pivort){
                bigger --;
            }
            if(arr[smaller] > pivort && arr[bigger] < pivort){
                int temp= arr[bigger];
                arr[bigger] = arr[smaller];
                arr[smaller] = temp;
                smaller++;
                bigger--;
            }
        }
        if(smaller != (start+1)) {
            int temp = arr[start];
            arr[start] = arr[smaller];
            arr[smaller] = temp;
            pindex = smaller;
        }
   //     print(arr);
       // System.out.println("pinex: "+pindex);
        if(pindex>start)
            HoaresoQuickSort(arr,start,pindex-1);
        if(pindex<end)
            HoaresoQuickSort(arr,pindex+1,end);
    }
    public static void print(int[] arr){
        for (int a:arr
        ) {
            System.out.print(a +",");
        }
    }
    //Quick sort with out temp array
    public static int[] lomotoQuickSort(int[] arr,int start, int end){
        if(start == end){
            return arr;
        }

      /*  System.out.println("start");
        print(arr);
        //Random pivort index
        int rindex =(int) (Math.random()*((end+1)-start)) + start;
        int t = arr[start];
        arr[start] = arr[rindex];
        arr[rindex] = t;
        System.out.println("end");
        print(arr);*/
        int pindex = 0; //(int) (Math.random()*((end+1)-start)) + start;
        int pivort = arr[pindex];
        int smaller = start,bigger=start;
       for(;bigger<=end;bigger++){
           if(arr[bigger]<pivort){
               //swap arr[smaller],arr[bigger]
               int temp= arr[bigger];
               arr[bigger] = arr[smaller];
               arr[smaller] = temp;
               smaller++;
           }
       }
       //swap pivert,arr[smaller]
       pindex=smaller;
       if(pindex>start)
       arr= lomotoQuickSort(arr,start,pindex-1);
       if(pindex<end)
       arr = lomotoQuickSort(arr,pindex+1,end);

        return arr;
    }

    public static int[] quickSort(int[] arr,int start, int end){ // best case : O(nlogn) median case : O(nlogn) worstCase : O n2 (where array is already sorted)
        if(start == end){
            return arr;
        }
        int[] sarr = new int[arr.length];
        int pivort=arr[start];
        int lindex=start,rindex=end;
        for (int i = start+1; i <= end; i++) {
            if(arr[i]<pivort){
                sarr[lindex] = arr[i];
                lindex++;
            }
            else{
                sarr[rindex] = arr[i];
                rindex--;
            }
        }
        int pIndex = lindex;
        sarr[pIndex] = pivort;
        for (int i = 0; i <= end ; i++) {
            arr[i] = sarr[i];
        }
        if(pIndex>start) {
            arr = quickSort(arr, start, pIndex - 1);
        }
        if(pIndex< end ) {
            arr = quickSort(arr, pIndex + 1, end);
        }

        return arr;
    }


    // 6435812 ->> 643(0,2) & 5812 (3,7)
    // 643(0,2) >> 6(0,0) & 43 (1,2)
    // 43(1,2) >> (1,0) & (1,2)   (1,1) (2,2)
    //64 >> 6 2
    public static int[] mergeSort(int[] arr,int start, int end){ // best case : O(nlogn) worstcase : O(nlogn)
        if(start == end ){
            return arr;
        }
        int mid = (start+end)/2;
        arr=  mergeSort(arr,start,mid); //
        arr = mergeSort(arr, mid+1, end); //

        int[] sarr= new int[arr.length];
        int i = start;
        int j = mid+1;
        int sindex = start;
        while (i <= mid && j <= end){
            if(arr[i] < arr[j]){
                sarr[sindex] = arr[i];
                i++;
                sindex++;
            }
            else{
                sarr[sindex] = arr[j];
                j++;
                sindex++;
            }
        }
        if(i<=mid){
            for (int k = i; k <= mid ; k++) {
                sarr[sindex] = arr[k];
                sindex++;
            }
        }
        if(j<= end){
            for (int k = j; k <= end ; k++) {
                sarr[sindex] = arr[k];
                sindex++;
            }
        }
        for (int k = start; k <= end; k++) {
            arr[k] = sarr[k];
        }
        return arr;
    }

    /*
    3 8 6 9 2
    3 8 6 2 9
    3 8 2 6 9
    3 2 8 6 9
     */
    public static int[] bubble(int[] arr){ //bestcase/worstcase : O(n^2)
        for (int i = arr.length-1; i >= 0; i--) {
            for (int j = arr.length -1; j > i; j--) {
                if(arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
    public static int[] selectionSort(int[] arr){//bestcase/worstcase : O(n^2)
        for (int i = 0; i < arr.length ; i++) {
            int min=arr[i];
            for (int j = i; j < arr.length ; j++) {
                if(arr[j] < min){
                    min = arr[j];
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }
    public static int[] insection(int[] arr){
        //divide and concure
        // bottom up because we go from 2 to n
     /*for(int i=1;i<arr.length;i++){ // 3,4,6,5,8 // i =3
                int temp=arr[i];// temp = 5
                for(int j=i-1;j>=0;j--){ // j = 1
                    if(arr[j] > temp){ // 4>5
                        arr[j+1] = arr[j]; // 3,4,6,6,8
                    }
                    else if (arr[j] < temp){ // 4 <5
                        arr[j+1] = temp; // 3,4,5,6,8
                        break;
                    }
                    if(j==0){
                        arr[j] = temp; // 3,4,6,5,8
                    }
                }
            }*/
        for(int i=0;i<arr.length;i++){ //i=2, 4,6,3
            int temp=arr[i]; // 3
            int index = i -1; // 1
            while( index >= 0 && arr[ index] > temp){
                arr[index +1 ] = arr[index]; // 4,4,6
                index --;
            }
            arr[index+1] = temp;
        }
        return arr;
    }
}


//6,4,3,5,8
//temp=4
//6,6,3,5,8
//4,6,3,5,8
//temp=3
//4,6,6,5,8 //6>3 a[2]=a[2-1]
//4,4,6,58 // 4>3 a[1]=a[1-1]
//3,4,6,5,8 // i=0 a[0] = temp 3