package Tree;

import java.util.Stack;

public class preOdrTraversal {

    private Node root;

    private static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data){
            this.data = data;
        }
    }

//    Method to Creating a Tree
/*
                1
              /   \
             2     3
           /  \   /  \
          4    5  6   7
 */


    public void createBinaryTree(){
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);
        Node seventh = new Node(7);

        root = first;  //root --> first;

        // second <----- first -----> third.
        first.left = second;
        first.right = third;

        // fourth <------ second -------> fifth.
        second.left = fourth;
        second.right = fifth;

        //sixth <---------third ----------> seventh
        third.left = sixth;
        third.right = seventh;
    }
    /*
    Pre Order Traversal -: root -> left child -> right child.
     */
//Pre Order Traversing the Binary Tree using Recursive Method.
    public void preOrderTraversal(Node root){

        if(root == null) return;

        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }

//    Iterative Method for PreOrder Traversal of a Binary Tree
    public void iterativeTraversal(Node root){
        if(root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node temp = stack.pop();
            System.out.print(temp.data + " ");

            if(temp.right != null){
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
        }
    }

    public static void main(String[] args) {
        preOdrTraversal tree = new preOdrTraversal();
        tree.createBinaryTree();
//        tree.preOrderTraversal(tree.root);
        System.out.println();
        System.out.print("PreOrder Traversal of the Binary Tree is : ");
        tree.iterativeTraversal(tree.root);

    }
}
