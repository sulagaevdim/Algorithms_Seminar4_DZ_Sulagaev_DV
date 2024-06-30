public class Tree {
    Node root;

    class Node {
        private Integer value;
        private Node leftChild;
        private Node rightChild;
        Color color;

    }

    enum Color{
        BLACK,
        RED
    }
    public void add(int value){
        if (root != null) {
            add(root, value);
            root = balance(root);
        } else {
            root = new Node();
            root.value = value;
        }
        root.color = Color.BLACK;
    }
    public void add(Node node, int value) {
        if (node.value != value) {
            if(node.value < value) {
                if (node.rightChild == null) {
                    node.rightChild = new Node();
                    node.rightChild.value = value;
                    node.rightChild.color = Color.RED;
                } else {
                    add(node.rightChild, value);
                    node.rightChild = balance(node.rightChild);
                }
            } else {
                if (node.leftChild == null) {
                    node.leftChild = new Node();
                    node.leftChild.value = value;
                    node.leftChild.color = Color.RED;
                } else {
                    add(node.leftChild, value);
                    node.leftChild = balance(node.leftChild);
                }
            }
        }
    }

    public Node find(int value){
        return find(root, value);
    }
    private Node find(Node node, int value){
        if (node == null) return null;
        if (node.value == value) return node;
        if (node.value < value) return find(node.rightChild, value);
        else return find(node.leftChild, value);
    }

    private Node leftRotation(Node node) {
        Node cur = node.rightChild;
        node.rightChild = cur.leftChild;
        cur.leftChild = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }
    private Node rightRotation(Node node) {
        Node cur = node.leftChild;
        node.leftChild = cur.rightChild;
        cur.rightChild = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }

    private void swapColors(Node node){
        node.color = (node.color == Color.RED ? Color.BLACK : Color.RED);
        node.leftChild.color = Color.BLACK;
        node.rightChild.color = Color.BLACK;
    }

    private Node balance(Node node){
        boolean flag = true;
        Node cur = node;
        do {
            flag = false;

            if (cur.rightChild != null &&
                    cur.rightChild.color == Color.RED &&
                    (cur.leftChild == null || cur.leftChild.color == Color.BLACK)) {
                cur = leftRotation(cur);
                flag = true;
            }

            if (cur.leftChild != null &&
                    cur.leftChild.color == Color.RED &&
                    cur.leftChild.leftChild != null &&
                    cur.leftChild.leftChild.color == Color.RED) {
                cur = rightRotation(cur);
                flag = true;
            }

            if (cur.leftChild != null &&
                    cur.leftChild.color == Color.RED &&
                    cur.rightChild != null &&
                    cur.rightChild.color == Color.RED) {
                swapColors(cur);
                flag = true;
            }

        } while (flag);
        return cur;
    }
}
