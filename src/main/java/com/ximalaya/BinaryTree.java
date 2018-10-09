package com.ximalaya;

/*
 * Created by qinzhikui on 20180916
 */


public class BinaryTree<AnyType extends Comparable<? super AnyType>> {

    private class TreeNode<AnyType> {

        private AnyType data;
        private TreeNode<AnyType> left;
        private TreeNode<AnyType> right;

        public TreeNode(AnyType data) {
            this(data, null, null);
        }

        public TreeNode(AnyType value, TreeNode<AnyType> left, TreeNode<AnyType> right) {
            this.data = value;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode<AnyType> root;

    public BinaryTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private boolean contains(AnyType x, TreeNode<AnyType> node) {
        if (node == null) {
            return false;
        }

        int compareResult = x.compareTo(node.data);

        if (compareResult < 0) {
            return contains(x, node.left);
        } else if (compareResult > 0) {
            return contains(x, node.right);
        } else {
            return true;
        }
    }

    public AnyType findMin() {
        if (isEmpty()) {
            /*
             * throw a exception
             */
            ;
        }
        return findMin(root).data;
    }

    private TreeNode<AnyType> findMin(TreeNode<AnyType> node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    public AnyType findMax() {
        if (isEmpty()) {
            /*
             * throw a exception
             */
        }
        return findMax(root).data;
    }

    private TreeNode<AnyType> findMax(TreeNode<AnyType> node) {

        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private TreeNode<AnyType> insert(AnyType x, TreeNode<AnyType> node) {
        if (node == null) {
            node = new TreeNode(x);
            return node;
        }

        int compareResult = x.compareTo(node.data);

        if (compareResult < 0) {
            node.left = insert(x, node.left);
        } else if (compareResult > 0) {
            node.right = insert(x, node.right);
        } else {
            /*
             * do nothing
             */
        }
        return node;
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    private TreeNode<AnyType> remove(AnyType x, TreeNode<AnyType> node) {
        if (node == null) {
            return node;
        }
        int compareResult = x.compareTo(node.data);
        if (compareResult < 0) {
            node.left = remove(x, node.left);
        } else if (compareResult > 0) {
            node.right = remove(x, node.right);
        } else if (node.left != null && node.right != null) {
            node.data = findMin(node.right).data;
            node.right = remove(node.data, node.right);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }
}