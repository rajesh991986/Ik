package sort;

public class MaxHeap {
   public int[] heap;
   public int size;
   public int maxSize;

   MaxHeap(int lenght){
       heap = new int[lenght];
       size=0;
       maxSize=lenght;
   }
   public boolean isLeafNode(int pos) {
       if(pos > size/2 && pos <=size) {
        return true;
       }
       return false;
   }
    public int leftChild(int pos) {
        if(isLeafNode(pos) || pos*2>size) {
            return -1;
        }
        else {
            return heap[pos*2];
        }
    }

    public int rightChild(int pos) {
        if(isLeafNode(pos) || pos*2 +1 > size) {
            return -1;
        }
        else {
            return heap[pos*2 +1];
        }
    }
    public int parent(int pos){
       if(pos == 1){
           return heap[1];
       }
       return heap[pos/2];
    }
    public void heapify(int pos){
       if(isLeafNode(pos)){
           return;
       }
       if(heap[pos] < leftChild(pos) || heap[pos] < rightChild(pos)){
           if(leftChild(pos) > rightChild(pos)){
                swap(pos*2,pos);
                heapify(pos*2);
           }
           else{
               swap(pos*2+1,pos);
               heapify(pos*2+1);
           }
       }
    }
    public void swap(int pos1,int pos2){
       int temp = heap[pos1];
       heap[pos1] = heap[pos2];
       heap[pos2] = temp;
    }
    public void insert(int element){
       size++;
       heap[size] = element;
       int current = size;
       while (heap[current] > parent(current)){
           swap(current,current/2);
           current = current/2;
       }
    }
    public int extractMax(){
       if(size<=0){
           return -1;
       }
      int max = heap[1];
       swap(1,size);
       size --;
       heapify(1);
       return max;
    }
    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(
                    " PARENT : " + heap[i]
                            + " LEFT CHILD : " + heap[2 * i ]
                            + " RIGHT CHILD :" + heap[2 * i +1]);
            System.out.println();
        }
    }
    public static void main(String[] arg)
    {
        // Display message for better readability
        System.out.println("The Max Heap is ");

        MaxHeap maxHeap = new MaxHeap(15);

        // Inserting nodes
        // Custom inputs
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);

        // Calling maxHeap() as defined above
        maxHeap.print();
        for (int i = 0; i < maxHeap.maxSize; i++) {
            System.out.println("The max val is "+ maxHeap.extractMax());
        }
        
        // Print and display the maximum value in heap

    }
}