package lab_9;

public class BinaryTreeSearch {
    public static class TreeNode {
        private int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    TreeNode root;

    public void insert(int x) {
        if (root == null) {
            root = new TreeNode(x);
        } else {
            TreeNode current = root;
            while (current != null) {
                if (x < current.data) {
                    if (current.left == null) {
                        current.left = new TreeNode(x);
                        return;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = new TreeNode(x);
                        return;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
    }

    public void delete(int x) {
        if (root == null) return;


        TreeNode parent = null;
        TreeNode current = root;
        while (current != null && current.data != x) {
            parent = current;
            if (x < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) return;


        if (current.left != null && current.right != null) {

            TreeNode successorParent = current;
            TreeNode successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.data = successor.data;

            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
            return;
        }


        TreeNode child = null;
        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }


        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void has(int x) {
        TreeNode node = root;
        while (node != null) {
            if (x == node.data) {
                System.out.println("t");
                return;
            } else if (x > node.data) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        System.out.println("f");
    }

    public Integer next(int x) {
        TreeNode candidate = root;
        TreeNode ans = null;
        while (candidate != null) {
            if (candidate.data > x) {
                ans = candidate;
                candidate = candidate.left;
            } else {
                candidate = candidate.right;
            }
        }
        if (ans == null) {
            System.out.println("f");
        } else {
            System.out.println(ans.data);
        }
        return null;
    }

    public Integer prev(int x) {
        TreeNode candidate = root;
        TreeNode ans = null;
        while (candidate != null) {
            if (candidate.data < x) {
                ans = candidate;
                candidate = candidate.right;
            } else {
                candidate = candidate.left;
            }
        }
        if (ans == null) {
            System.out.println("f");
        } else {
            System.out.println(ans.data);
        }
        return null;
    }

}
