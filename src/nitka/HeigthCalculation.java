package nitka;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeigthCalculation {

    static int calculateHeight(Node root){
        if(root == null)return 0;
        int maxHeight = 0;
        for (Node child : root.children){
            int currentHeight = calculateHeight(child);
            if(currentHeight > maxHeight) maxHeight = currentHeight;
        }
        return maxHeight+1;
    }

    static class Node{
        @SuppressWarnings("unused")
        private Node parent;
        private List<Node> children = new ArrayList<>();
        public void addChild(Node child){
            child.parent = this;
            children.add(child);
        }
    }

    @Test
    public void test1() {
        Node root = new Node();
        root.addChild(new Node());

        Node child = new Node();
        child.addChild(new Node());

        root.addChild(child);
        assertEquals(calculateHeight(root),3);
    }

    @Test
    public void test2() {
        Node one = new Node();
        assertEquals(calculateHeight(one),1);
    }

    @Test
    public void test3() {
        assertEquals(calculateHeight(null),0);
    }

}
