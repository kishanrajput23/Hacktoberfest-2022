package Tree;

import java.util.Scanner;

public class ImplementingBinarySearchTree {
    Scanner sc = new Scanner(System.in);

    private Node root;

    private static class Node{
        private final int data;
        private Node left;
        private Node right;

        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

//    recursive method to add data to the Binary Search Tree
    public void insert(int data){
        root = insert(root, data);
    }
    public Node insert(Node root, int value){
        if(root == null){ //Base Case
            root = new Node(value);
            return root;
        }
        if(value < root.data){
            root.left = insert(root.left, value);
        }else{
            root.right = insert(root.right, value);
        }
        return root;
    }

//    Method to Print the tree in InOrder Traversal
    public void print(Node root){
        if(root == null){
            return;
        }
        print(root.left);
        System.out.print(root.data + " ");
        print(root.right);
    }

//    Method to search element in a Given Binary search Tree.
    public boolean searchElement(int target){
        Node temp = searchElement(root, target);
        if(temp == null || temp.data != target){
            return false;
        }else{
            return true;
        }
    }

    public Node searchElement(Node root, int target){
        if(root == null || root.data == target){
            return root;
        }
        if(target < root.data){
            return searchElement(root.left, target);
        }else{
            return searchElement(root.right, target);
        }
    }

//    Method to validate if the given tree is BST or not.
    public boolean isValidBST(Node root, long min, long max){
        if(root == null){
            return true;
        }
//        Remember ->  min < root.data < max.
        if(root.data <= min || root.data >= max){
            return false;
        }
        boolean left = isValidBST(root.left, min, root.data);
        if(left){
            boolean right = isValidBST(root.right, root.data, max);
            return right;
        }
        return false;
    }
//    Diver Code
    public static void main(String[] args) {
        ImplementingBinarySearchTree bst = new ImplementingBinarySearchTree();

        while(true){
            System.out.println("""

                    Please Insert Your Choice :\s
                    Press 1 To insert into Binary Search Tree.\s
                    Press 2 To Print the Binary Search Tree\s
                    Press 3 To Search Element\s
                    Press 4 To Check BST is valid or not\s
                    Press 0 To Exit""");
            int choice = bst.sc.nextInt();
            switch (choice){
                case 0 -> {
                    System.exit(choice);
                }
                case 1 -> {
                    int value = bst.sc.nextInt();
                    bst.insert(value);
                }
                case 2 -> {
                    bst.print(bst.root);
                }
                case 3 ->{
                    int target = bst.sc.nextInt();
                    System.out.println(bst.searchElement(target));
                }
                case 4 -> {
                    System.out.println(bst.isValidBST(bst.root, Long.MIN_VALUE, Long.MAX_VALUE));
                }
            }
        }
    }
}
