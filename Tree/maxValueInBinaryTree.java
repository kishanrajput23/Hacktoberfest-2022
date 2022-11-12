package Tree;

public class maxValueInBinaryTree {
    private Node root;

    private class Node{
        private int data;
        private Node left;
        private Node right;
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    /*
    Creating the Below Tree in the method createBinaryTree.
            4
           / \
          3   7
         / \
        5   8
     */
    public void createBinaryTree(){
        Node first = new Node(4);
        Node second = new Node(3);
        Node third = new Node(21);
        Node fourth = new Node(5);
        Node fifth = new Node(8);

        root = first;

        first.left = second;
        first.right = third;

        second.left = fourth;
        second.right = fifth;
    }

//    Recursive Method to find the max value in Binary Tree.
    public int findMax(Node root){
        if (root == null){
            return Integer.MIN_VALUE;
        }
        int result = root.data;
        int left = findMax(root.left);
        int right = findMax(root.right);
        if(left > result){
            result = left;
        }
        if(right > result){
            result = right;
        }
        return result;
    }

//    Method to Print the Tree in PreOrder traversal
    public void preOrderTraversal(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }
//    Main Driver Code.

    public static void main(String[] args) {
        maxValueInBinaryTree maxvalue = new maxValueInBinaryTree();
        maxvalue.createBinaryTree();
        System.out.println("Given Tree is : ");
        maxvalue.preOrderTraversal(maxvalue.root);
        System.out.println("\nMaximum Value = " + maxvalue.findMax(maxvalue.root));


    }
}
