import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeTest {

    @Test(timeout=1000)
    public void calculageDistance() {

        ClimbingTree<Integer> root = new ClimbingTree<>(1);
        ClimbingTree<Integer> two = new ClimbingTree<>(2);
        ClimbingTree<Integer> five = new ClimbingTree<>(5);
        ClimbingTree<Integer> four = new ClimbingTree<>(4);
        ClimbingTree<Integer> three = new ClimbingTree<>(3);
        ClimbingTree<Integer> six = new ClimbingTree<>(6);
        ClimbingTree<Integer> seven= new ClimbingTree<>(7);

        root.addChild(two);
        root.addChild(five);
        two.addChild(three);
        two.addChild(four);
        five.addChild(six);
        five.addChild(seven);

        Iterator<Integer> iter = root.iterator();
        for (int i = 1; i <= 7; i++) {
            assertEquals(i, (int)iter.next());
        }

        assertEquals(4,three.calculateDistance(six));
    }


}
