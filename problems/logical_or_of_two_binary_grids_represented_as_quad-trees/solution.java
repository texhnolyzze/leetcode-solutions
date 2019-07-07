/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        Node n;
        if (quadTree1.isLeaf) {
            if (quadTree2.isLeaf) 
                return new Node(quadTree1.val || quadTree2.val, true, null, null, null, null);
            else {
                if (quadTree1.val) 
                    return new Node(true, true, null, null, null, null);
                else 
                    n = new Node(
                    false, 
                    false, 
                    quadTree2.topLeft, 
                    quadTree2.topRight, 
                    quadTree2.bottomLeft, 
                    quadTree2.bottomRight
                );
            }
        } else {
            if (quadTree2.isLeaf) {
                if (quadTree2.val) 
                    return new Node(true, true, null, null, null, null);
                else
                    n = new Node(
                    false, 
                    false, 
                    quadTree1.topLeft, 
                    quadTree1.topRight, 
                    quadTree1.bottomLeft, 
                    quadTree1.bottomRight
                );
            }
            else
                n = new Node(
                    false, 
                    false, 
                    intersect(quadTree1.topLeft, quadTree2.topLeft),
                    intersect(quadTree1.topRight, quadTree2.topRight),
                    intersect(quadTree1.bottomLeft, quadTree2.bottomLeft),
                    intersect(quadTree1.bottomRight, quadTree2.bottomRight)
                );
        }
        if (n.topLeft.isLeaf && n.topRight.isLeaf && n.bottomLeft.isLeaf && n.bottomRight.isLeaf) {
            if (n.topLeft.val == n.topRight.val && n.topRight.val == n.bottomLeft.val && n.bottomLeft.val == n.bottomRight.val) {
                n.isLeaf = true;
                n.val = n.topLeft.val;
                n.topLeft = null;
                n.topRight = null;
                n.bottomLeft = null;
                n.bottomRight = null;
            }
        }
        return n;
    }
    
    private Node reduce(Node n) {
        if (n.isLeaf)
            return n;
        n.topLeft = reduce(n.topLeft);
        n.topRight = reduce(n.topRight);
        n.bottomLeft = reduce(n.bottomLeft);
        n.bottomRight = reduce(n.bottomRight);
        return n;
    }
}