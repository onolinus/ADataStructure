import java.sql.SQLOutput;
import java.util.*;

/**
 * A binary tree, where each node contains at most two children
 * Each root node contains a value and references to its left and right children (if they exist)
 * A method to find distance and lowest common ancestor
 *
 * @param <E> the type of the tree's elements
 */
public class BinaryTree<E> implements Tree<E> {
    private E root; // the element sitting at this tree's root node
    private BinaryTree<E> left; // the left child (subtree)
    private BinaryTree<E> right; // the right child (subtree)
    private Queue<BinaryTree<E>> shorTestPath;

    /**
     * Constructs a new binary tree with a single node
     *
     * @param root the element to store at this tree's root
     */
    public BinaryTree(E root) {
        this.root = root;
        this.left = null;
        this.right = null;
        shorTestPath = new PriorityQueue<BinaryTree<E>>();
    }

    @Override
    public int size() {
        return 1 + (left != null ? left.size() : 0) + (right != null ? right.size() : 0);
    }

    @Override
    public E getRoot() {
        return root;
    }

    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public List<Tree<E>> getChildren() {
        List<Tree<E>> children = new ArrayList<>();
        if (left != null) {
            children.add(left);
        }
        if (right != null) {
            children.add(right);
        }
        return children;
    }

    @Override
    public boolean contains(E elem) {
        if (root.equals(elem)) {
            return true;
        }
        return (left != null && left.contains(elem)) || (right != null && right.contains(elem));
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>(this);
    }

    /**
     * Sets the left child of this tree's root to the given subtree
     * Any existing left child will be overridden
     *
     * @param left the new left child
     */
    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    /**
     * Sets the right child of this tree's root to the given subtree
     * Any existing right child will be overridden
     *
     * @param right the new right child
     */
    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    /**
     * @return the left child subtree of this tree's root node
     */
    public BinaryTree<E> getLeft() {
        return left;
    }

    /**
     * @return the right child subtree of this tree's root node
     */
    public BinaryTree<E> getRight() {
        return right;
    }


    public static <T extends Comparable<T>> boolean traverseTree(BinaryTree<T> tree, BinaryTree<T> left, BinaryTree<T> right) {

        if (tree == null)
            return false;

        if (tree.isLeaf() && tree.getRoot() == null) {
            return true;
        }


        if (left != null) {
            if (tree.getRoot().compareTo(left.getRoot()) <= 0) {
                return false;
            }
        }

        if (right != null) {
            if (tree.getRoot().compareTo(right.getRoot()) >= 0) {
                return false;
            }
        }

        return (traverseTree(tree.getLeft(), left, tree) && traverseTree(tree.getRight(), tree, right));
    }

    /**
     * Determines whether the parameter tree is a binary search tree or not
     * This is determined by the definition of a binary search tree provided in the lectures
     *
     * @param tree the tree to check
     * @return true if this tree is a BST, otherwise false
     * <p>
     * The complexity of this implementation is also O(n^2) as it will traverse and divide every node to left and right or call
     * a method twice
     * The amortised cost of this method in n operation is  n + n + n +n + n^2 = 4n + n^2 = (4n+n^2)/n = (4 + n) =  O(n)
     * The amortised cost has difference with worst case because assume the right child is null so it will call once and
     * the cost is O(n)
     */

    public static <T extends Comparable<T>> boolean isBST(BinaryTree<T> tree) {

        // TODO: implement this method
        if (tree.getRoot() != null) {
            return traverseTree(tree, null, null);
        }
        return false;
    }


    public BinaryTree<E> lowestCommonAncestor(BinaryTree<E> root, BinaryTree a, BinaryTree b) {

        if (root == null) {
            return null;
        }


        if(root.getRoot() == a.getRoot()){
            System.out.println(root.getRoot().toString());
            return root;
        }

        if(root.getRoot() == b.getRoot()){
            System.out.println(root.getRoot().toString());
            return root;
        }


        BinaryTree<E> left = lowestCommonAncestor(root.getLeft(), a, b);
        BinaryTree<E> right = lowestCommonAncestor(root.getRight(), a, b);

        if (left != null && right != null) return root;

        if (left != null || right != null){
            System.out.println(root.getRoot().toString());
        }

        return left!=null?left:right;
    }

    public <E extends Comparable<E>> int findDist(BinaryTree<E> lca, BinaryTree<E> a,int dist){

        if(lca == null)
            return -1;

        if(lca.getRoot().compareTo(a.getRoot()) ==0){
            return dist;
        }

        int leftDist = findDist(lca.getLeft(),a,dist+1);

        return leftDist!= -1 ? leftDist:findDist(lca.getRight(),a,dist+1);
    }

    public <E extends Comparable<E>> int distance(BinaryTree<E> a, BinaryTree<E> b) {

        BinaryTree lca = lowestCommonAncestor(this, a, b);

        int dist = findDist(lca,a,0)+ findDist(lca,b,0);

        System.out.println(dist);

        return dist;
    }
}
