import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An iterator for the tree ADT that performs a preorder traversal
 */
public class TreeIterator<E> implements Iterator<E> {

    /**
     * Constructs a new tree iterator from the root of the tree to iterate over
     * <p>
     * You are welcome to modify this constructor but cannot change its signature
     * This method should have O(1) time complexity
     */

    private Stack<Tree<E>> s = new Stack<>();

    public TreeIterator(Tree<E> root) {
        if (root != null) {
            s.push(root);
        }
    }

    // TODO: implement this iterator

    /**
     * Big O Notation for Next  is O(1) or constant because it just call one method empty or not empty
     * The amortised cost for this method for 1 operation is O(1)
     */

    @Override
    public boolean hasNext() {

        return !s.empty();
    }

    /**
     * The worst case of next() is O(n) => 1 operation (has next) + 1 operation (pop) + n -1 (loop) + n(s.push)
     * + 1 operation (get.root) = 2n + 3 or O(n)
     * hasnext is O(1)
     * s.pop is O(1)
     * loop is O(n)
     * push is O(n)
     * return is O(1)
     * Based on this measure of every n there will be O(n) + O(n) + O(n) + O(n)
     * So the amortised cost for this method is 4(n) = O(n)/n item or O(1)
     * The loop in this method depend on pop from stack so when the children only has 1 node it will push
     * to stack and return at most once operation
     */

    @Override
    public E next() {

        if (!hasNext()) {
            throw new NoSuchElementException("All visited");
        }

        Tree<E> node = s.pop();

        int i = node.getChildren().size();
        while (i > 0) {
            i--;
            s.push(node.getChildren().get(i));
        }
        return node.getRoot();
    }
}
