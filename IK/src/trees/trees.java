package trees;

public class trees {
    public static void main(String[] args){
        TreeNode root = new TreeNode();
        int[] arr = new int[]{8,20,9,40,1,6,19,7,30,16};
        root = root.insertNode(null,arr[0]);
        for (int i = 1; i <arr.length ; i++) {
            root = root.insertNode(root,arr[i]);
        }
        //root.search(root,7);
       // root.predecessor(root,6);
       // root.successor(root,99);
       // root.delete(root,8,null);
        //root.bfs(root);
       // root.preOrder(root);
       // root.preOrderIteration(root);
        root.inOrderIteration(root);
        root.print(root);
       // root.bfsWithLevel(root);
    }

}
