/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }
        
        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append('#');
                return;
            }
            sb.append(root.val).append(',');
            serialize(root.left, sb);
            sb.append(',');
            serialize(root.right, sb);
        }

        public TreeNode deserialize(String data) {
            return (TreeNode) deserealize(data.split(","), 0)[0];
        }

        private Object[] deserealize(String[] data, int i) {
            if (data[i].charAt(0) == '#') {
                return new Object[] {null, i};
            }
            TreeNode n = new TreeNode(Integer.parseInt(data[i]));
            Object[] left = deserealize(data, i + 1);
            n.left = (TreeNode) left[0];
            Object[] right = deserealize(data, (int) left[1] + 1);
            n.right = (TreeNode) right[0];
            return new Object[] {n, (int) (right[1])};
        }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));