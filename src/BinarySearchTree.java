

/**
 * A class to represent a binary search tree.
 * This is a modified version of the original authors' code, 
 * the binary search tree now holds two generics. 
 * There is one object for data, and one object for a link variable.
 * This modification was done for bi-directional tree functionality
 * @param <E> represents the data object
 * @param <V> represents the link object
 * original work by: @author Koffman and Wolfgang
 * modified by: @author Ceyhun Ozbel
 */
public class BinarySearchTree<E extends Comparable<E>, V extends Comparable<V>> extends BinaryTree<E,V> {

    /**
     * Return value from the public add method.
     */
    protected boolean addReturn;
    /**
     * Return value from the public delete method.
     */
    protected E deleteReturn;

    /**
     * Checks whether the target data is contained in the tree
     * @param target
     * @return true if target exists in the tree
     */
    public boolean contains(E target){
        E match = find(target);
        if (match!=null){
            return true;
        }
        else{
            return false;
        } 
    }

    /**
     * Checks whether the target link is contained in the tree
     * @param target
     * @return true if target link exists in the tree
     */
    public boolean containsLink(V link){
        V match = findLink(link);
        if (match!=null){
            return true;
        }
        else{
            return false;
        } 
    }

    /**
     * Starter method find.
     *
     * @param target The Comparable object being sought
     * @return The object, if found, otherwise null
     * @pre The target object must implement
     * the Comparable interface.
     */
    E find(E target) {
        return find(root, target);
    }

    /**
     * Starter method find.
     *
     * @param target The Comparable object that acts as a link being sought
     * @return The object, if found, otherwise null
     * @pre The target object must implement
     * the Comparable interface.
     */
    V findLink(V target) {
        return findLink(root, target);
    }

    /**
     * Starter method to return corresponding link.
     *
     * @param data The Comparable object,
     * @return The link objetc stored in the node, if found, otherwise null
     * @pre The target object must implement
     * the Comparable interface.
     */
    V returnLink(E data) {
        return returnLink(root, data);
    }

    /**
     * Recursive find method.
     *
     * @param localRoot The local subtree's root
     * @param target    The object being sought
     * @return The object, if found, otherwise null
     */
    private E find(Node<E,V> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

      /**
     * Recursive find method that finds the node of a target object E, 
     * and returns the link stored in that node.
     * 
     *
     * @param localRoot The local subtree's root
     * @param target    The object being sought
     * @return The object, if found, otherwise null
     */
    private V returnLink(Node<E,V> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {
            return localRoot.link;
        } else if (compResult < 0) {
            return returnLink(localRoot.left, target);
        } else {
            return returnLink(localRoot.right, target);
        }
    }

     /**
     * Recursive find method to find a link object.
     *
     * @param localRoot The local subtree's root
     * @param target    The target link being sought
     * @return The link object, if found, otherwise null
     */
    private V findLink(Node<E,V> localRoot, V target) {
        if (localRoot == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.link);
        if (compResult == 0) {
            return localRoot.link;
        } else if (compResult < 0) {
            return findLink(localRoot.left, target);
        } else {
            return findLink(localRoot.right, target);
        }
    }

    /**
     * Starter method add.
     *
     * @param item The object being inserted
     * @param link The link object representig the item
     * @return true if the object is inserted, false
     * if the object already exists in the tree
     * @pre The object to insert must implement the
     * Comparable interface.
     */
    public boolean add(E item, V link) {
        root = add(root, null, item, link);
        return addReturn;
    }

    /**
     * Recursive add method.
     *
     * @param localRoot The local root of the subtree
     * @param item      The object to be inserted
     * @return The new local root that now contains the
     * inserted item
     * @post The data field addReturn is set true if the item is added to
     * the tree, false if the item is already in the tree.
     */
    private Node<E,V> add(Node<E,V> localRoot, Node<E,V> parent, E item,V link) {
        if (localRoot == null) {
            // item is not in the tree � insert it.
            addReturn = true;
            Node<E,V> newNode = new Node<E,V>(item,link);
            newNode.parent = parent;
            return newNode;
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, localRoot, item,link);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, localRoot, item,link);
            return localRoot;
        }
    }

    /**
     * Starter method delete.
     *
     * @param target The object to be deleted
     * @return The object deleted from the tree
     * or null if the object was not in the tree
     * @throws ClassCastException if target does not implement
     *                            Comparable
     * @post The object is not in the tree.
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Recursive delete method. Also deletes the link object stored in the node.
     *
     * @param localRoot The root of the current subtree
     * @param item      The item to be deleted
     * @return The modified local root that does not contain
     * the item
     * @post The item is not in the tree;
     * deleteReturn is equal to the deleted item
     * as it was stored in the tree or null
     * if the item was not found.
     */
    private Node<E,V> delete(Node<E,V> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            if (localRoot.left != null) {
                localRoot.left.parent = localRoot;
            }
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            if (localRoot.right != null) {
                localRoot.right.parent = localRoot;
            }
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data and the link with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    localRoot.link=localRoot.left.link;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    if (localRoot.left != null) {
                        localRoot.left.parent = localRoot;
                    }
                    return localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }


    /**
     * Find the node that is the
     * inorder predecessor and replace it
     * with its left child (if any).
     *
     * @param parent The parent of possible inorder
     *               predecessor (ip)
     * @return The data in the ip
     * @post The inorder predecessor is removed from the tree.
     */
    private E findLargestChild(Node<E,V> parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            if (parent.right != null) {
                parent.right.parent = parent;
            }
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }


}
