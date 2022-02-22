


import java.io.Serializable;


/**
 * Class for a binary tree that stores type E and type V objects.
 * !!!modified version of original code, so that two generic comparable 
 * types are stored per node rather than one!!!
 * @param <E> represents the data object
 * @param <V> represents the link object
 * original code by @author Koffman and Wolfgang
 * modified by @author Ceyhun Ozbel
 **/
public class BinaryTree<E,V> implements Serializable {

    /**
     * Class to encapsulate a tree node.
     */
    protected static class Node<E,V> implements Serializable {

        /**
         * The information stored in this node.
         */
        public E data;
        public V link;
        /**
         * Reference to the left child.
         */
        
        public Node<E,V> left;
        /**
         * Reference to the right child.
         */
        public Node<E,V> right;

        /**
         * Reference to the parent
         */
        public Node<E,V> parent;


        /**
         * Construct a node with given data and no children.
         *
         * @param data The data to store in this node
         */
        public Node(E data,V link) {
            this.data = data;
            this.link = link;
            left = null;
            right = null;
            parent = null;
        }

        /**
         * Returns a string representation of the node.
         *
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return ("("+data.toString()+ ", " + link.toString()+")");
        }
    }

    // Data Field
    /**
     * The root of the binary tree
     */
    protected Node<E,V> root;

    /**
     * Construct an empty BinaryTree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a BinaryTree with a specified root.
     * Should only be used by subclasses.
     *
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<E,V> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root, a link for bidirectional functionality,
     * leftTree as its left subtree and rightTree as its right subtree.
     * 
     */
    public BinaryTree(E data,V link, BinaryTree<E,V> leftTree,
                      BinaryTree<E,V> rightTree) {
        root = new Node<E,V>(data, link);
        if (leftTree != null) {
            root.left = leftTree.root;
            leftTree.root.parent = root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
            rightTree.root.parent = root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     *
     * @return The left subtree or null if either the root or
     * the left subtree is null
     */
    public BinaryTree<E,V> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E,V>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     *
     * @return the right sub-tree or
     * null if either the root or the
     * right subtree is null.
     */
    public BinaryTree<E,V> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E,V>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     *
     * @return the data field of the root
     * or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Return the link field of the root
     *
     * @return the link field of the root
     * or null if the root is null
     */
    public V getLink() {
        if (root != null) {
            return root.link;
        } else {
            return null;
        }
    }

    /**
     * Performs an inorder traversal of the binary tree
     * Formats the data and links as a string: (data,link), (data,link)....
     * Format can be (link,data)... depending on the tree being passed
     * @param sb string bilder to append
     * @param valurOrkey boolean to check which tree is being passed
     * @return inorder traversal as string
     *
     */
    public String inOrder(StringBuilder sb, boolean valueOrkey) {
        inOrder(root,sb,valueOrkey);
        sb.delete(sb.length()-2, sb.length()); // trims last ", "
        return sb.toString();
    }

    /**
     * Determine whether this tree is a leaf.
     *
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    /**
     * Performs an inorder traversal and subsequently adds the data and link to a string builder.
     * The string builder is appended in (data,link) format if the tree passed is the keyTree
     * @param node  The local root
     * @param sb    The string buffer to save the output
     * @param valueTree boolean check to see whether the passed tree is the value tree or the key tree
     * 
     */
     private void inOrder(Node<E,V> node,StringBuilder sb , boolean valueTree) {
        if (node != null) {
            if(valueTree==false){
                inOrder(node.left,sb,valueTree);
                sb.append("("+node.data+ ", " + node.link+"), ");
                inOrder(node.right, sb,valueTree);
            }
            else{
                inOrder(node.left,sb,valueTree);
                sb.append("("+node.link+ ", " + node.data+"), ");
                inOrder(node.right, sb,valueTree);
            }
        } 
        else {
            return;
        }
    }

}

