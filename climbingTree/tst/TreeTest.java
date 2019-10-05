import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeTest {


    @Test(timeout=500)
    public void testNext() {
        BinaryTree<Integer> root = new BinaryTree<>(1);
        BinaryTree<Integer> left = new BinaryTree<>(2);
        BinaryTree<Integer> leftLeft = new BinaryTree<>(3);

        root.setLeft(left);
        left.setLeft(leftLeft);

        Iterator<Integer> iter = root.iterator();
        for (int i = 1; i <= 3; i++) {
            assertEquals(i, (int)iter.next());
        }
    }


    @Test(timeout=5000)
    public void calculageDistanceBinary() {

        BinaryTree<Integer> root = new BinaryTree<>(1);
        BinaryTree<Integer> two = new BinaryTree<>(2);
        BinaryTree<Integer> five = new BinaryTree<>(5);
        BinaryTree<Integer> four = new BinaryTree<>(4);
        BinaryTree<Integer> three = new BinaryTree<>(3);
        BinaryTree<Integer> six = new BinaryTree<>(6);
        BinaryTree<Integer> seven= new BinaryTree<>(7);

        root.setLeft(two);
        root.setRight(five);
        two.setLeft(three);
        two.setRight(four);
        five.setLeft(six);
        five.setRight(seven);


        Iterator<Integer> iter = root.iterator();
        for (int i = 1; i <= 7; i++) {
            assertEquals(i, (int)iter.next());
        }

        assertEquals(4,root.distance(three,six));
    }

}
