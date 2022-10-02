package Tree;

import java.util.Stack;

public class postOrderTraversal {
    private Node root;

    private class Node{
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
    PostOrder Traversal of A Binary Tree :- left child -> right child -> root.
     */

//    Recursive method for postOrderTraversing of Binary Tree
    public void recursiveTraversal(Node root){
        if(root == null){
            return;
        }

        recursiveTraversal(root.left);
        recursiveTraversal(root.right);
        System.out.print(root.data + " ");
    }

//    Itterative method for postOrder Traversing of Binary Tree
    public void iterativeTraversal(Node root){
        Node current = root;
        Stack<Node> stack = new Stack<>();
        while(current != null || !stack.isEmpty()){
            if(current != null){
                stack.push(current);
                current = current.left;
            }else{
                Node temp = stack.peek().right;
                if(temp == null){
                    temp = stack.pop();
                    System.out.print(temp.data + " ");
                    while(!stack.isEmpty() && temp == stack.peek().right){
                        temp = stack.pop();
                        System.out.print( temp.data + " ");
                    }
                }else{
                    current = temp;
                }
            }
        }
    }

    //Main method
    public static void main(String[] args) {
        postOrderTraversal tree = new postOrderTraversal();
        tree.createBinaryTree();
        System.out.println();
        System.out.print("PostOrder Traversal of the Binary tree is : ");
//        tree.recursiveTraversal(tree.root);
        tree.iterativeTraversal(tree.root);
    }
}
