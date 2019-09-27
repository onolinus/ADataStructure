import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A climbing tree
 * A modified standardTree from @author Henry O'Brien
 * and make a new climbing tree data Structure
 * @param <E> the type of the tree's elements
 */

public class ClimbingTree <E> implements Tree<E>{
    private E root; // the element sitting at this tree's root node
    private List<ClimbingTree<E>> children; // the child subtrees of this tree's root node
    public int depth = 0;
    private ClimbingTree <E> parent;


    /**
     * Constructs a new tree with a single node
     *
     * @param root the element to store at this tree's root
     */
    public ClimbingTree(E root) {
        this.root = root;
        this.children = new LinkedList<>();
    }

    @Override
    public int size() {
        int size = 1;
        for (ClimbingTree<E> child : children) {
            size += child.size();
        }
        return size;
    }

    @Override
    public E getRoot() {
        return root;
    }

    @Override
    public boolean isLeaf() {
        return children.size() == 0;
    }

    @Override
    public List<Tree<E>> getChildren() {
        return new LinkedList<>(children);
    }

    @Override
    public boolean contains(E elem) {
        if (root.equals(elem)) {
            return true;
        }
        for (ClimbingTree<E> child : children) {
            if (child.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>(this);
    }

    /**
     * Adds a child to this tree's root node
     *
     * @param child the child subtree to add
     */
    public void addChild(ClimbingTree<E> child)
    {   child.parent = this;
        children.add(child);
        child.depth = this.depth + 1;
        recursiveAddDepth(child);
    }

    /**
     * Removes the given subtree if it exists as an immediate child of this tree's root
     *
     * @param child the subtree to remove
     * @return true on success, false if the subtree is not an immediate child of the tree's root
     */
    public boolean removeChild(ClimbingTree<E> child)
    {
        return children.remove(child);
    }

    public ClimbingTree<E> recursiveAddDepth(ClimbingTree<E> node) {

        if (node.getChildren().size() > 0) {
            for (ClimbingTree<E> child : node.children) {
                child.depth += 1;
                recursiveAddDepth(child);
                return child;
            }
        }
        return null;
    }


    public int calculateDistance(ClimbingTree<E> B) {
        if (this.getRoot() != B.getRoot()){
            int result = 0;
            ClimbingTree<E> A = this;
            int heightGap = this.depth - B.depth;
            while (true) {
                if (heightGap > 0) {
                    for (int i = 0; i < heightGap; i++) {
                        System.out.println("Pointer of node A move one level up to " + A.parent.getRoot());
                        A = this.parent;
                        result += 1;
                        heightGap -= 1;
                    }
                } else if (heightGap < 0) {
                    for (int i = 0; i < 0 - heightGap; i++) {
                        System.out.println("Pointer of node B move one level up to " + B.parent.getRoot());
                        B = B.parent;
                        result += 1;
                        heightGap += 1;
                    }
                } else if (heightGap == 0) {
                    if (A == B) {
                        return result;
                    } else {
                        while (A != B) {
                            System.out.println("Pointer of node A move one level up to " + A.parent.getRoot());
                            System.out.println("Pointer of node B move one level up to " + B.parent.getRoot());
                            A = A.parent;
                            B = B.parent;
                            result += 2;
                        }
                        System.out.println("\nShared ancestor found! It is the " + A.getRoot());
                        System.out.println("Distance between " + this.getRoot() + " and " + B.getRoot() + " is : " + result);
                        return result;
                    }
                }
            }
        }


        return 0;
    }
}
