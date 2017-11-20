package Exercicios.Ex4;

/**
 * Created by Pedro on 18/11/2017.
 */
public class Node implements Comparable<Node>{

    double probability;
    Node parent;
    Node right;
    Node left;

    public Node(double probability, Node parent, Node right, Node left) {
        this.probability = probability;
        this.parent=parent;
        this.right=right;
        this.left=left;
    }

    @Override
    public int compareTo(Node o) {
        if(this.probability-o.probability==0)
            return 0;
        else return this.probability-o.probability<0?-1:1;
    }
}
