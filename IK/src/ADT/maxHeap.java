package ADT;

public class maxHeap {
    public static void main(String[] args){
      int[] arr = new int[15];
      int lastIndex = 0;
     lastIndex= insert(arr,10,lastIndex);
     lastIndex= insert(arr,7,lastIndex);
        lastIndex= insert(arr,8,lastIndex);
         lastIndex= insert(arr,11,lastIndex);
        lastIndex= insert(arr,6,lastIndex);
      //  print(arr,lastIndex);
        lastIndex= insert(arr,15,lastIndex);
     print(arr,lastIndex);
    lastIndex = removeRoot(arr,lastIndex);
     System.out.println("After Removing Max Element");
     print(arr,lastIndex);
    }
    public static int removeRoot(int[] arr,int lastIndex){
        arr[1]=arr[lastIndex];
        lastIndex--;
        maintainHeap(arr,lastIndex);
        return lastIndex;
    }
   /* public static void maintainHeapforRemove(int[] arr,int lastIndex){
        for (int i = 1; i < lastIndex ; i++) {
            if((arr[i] < arr[2*i])&& (arr[i] < arr[(2*i)+1])){
                if(arr[2*i] >arr[(2*i)+1] ){
                    swap(arr,i,2*i);
                }
                else{
                    swap(arr,i,2*i+1);
                }
            }
            else if(arr[i] < arr[2*i]){
                swap(arr,i,2*i);
            }
            else if (arr[i] < arr[(2*i)+1]){
                swap(arr,i,2*i+1);
            }
        }
    }*/
    public static int insert(int[] arr,int value,int lastIndex){
        arr[lastIndex+1] = value;
        lastIndex=lastIndex+1;
        maintainHeap(arr,lastIndex);
      //  print(arr,lastIndex);
      return  lastIndex;
    }
    public static void print(int[] arr,int lastIndex){
        System.out.println(arr[1]);
        for (int i = 2,h=1; i <= lastIndex; h++) {
            for (int j = 0; j < Math.pow(2, h) &&  i <= lastIndex ; j++) {
                System.out.print(" "+arr[i]);
                i++;
            }
            System.out.println("");
        }
    }
    public static void maintainHeap(int[] arr,int lastIndex){
        for (int i = lastIndex; i >=1 ; i = i/2) {
           // System.out.println("i = "+i);
            if(2*i <= lastIndex || ((2*i)+1) <= lastIndex) {
              //  System.out.println("### i = "+i);
                if ((arr[i] < arr[2 * i]) && (arr[i] < arr[(2 * i) + 1])) {
                    if (arr[2 * i] > arr[(2 * i) + 1]) {
                        swap(arr, i, 2 * i);
                    } else {
                        swap(arr, i, 2 * i + 1);
                    }
                } else if (arr[i] < arr[2 * i]) {
                    swap(arr, i, 2 * i);
                } else if (arr[i] < arr[(2 * i) + 1]) {
                  //  System.out.println("***# i = "+i);
                    swap(arr, i, 2 * i + 1);
                }
            }
            /*if(i/2 > 0) {
                if (arr[i / 2] < arr[i]) {
                    swap(arr, i / 2, i);
                }
            }*/
        }
    }
    public static void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
