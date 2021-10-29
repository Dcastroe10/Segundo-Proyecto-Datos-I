public class Sub_trees {
    public Node Sub_trees(Node root, Node left, Node right){
        root.setLeft(left);
        root.setRight(right);
        return root;
    }
}
