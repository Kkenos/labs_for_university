package lab_9;

public class AVLTree extends BinaryTreeSearch{


    public static class AVLNode extends TreeNode{
        int height;

        AVLNode(int data){
            super(data);
            this.height = 1;
        }
    }
    @Override
    public void insert(int x){
        root = InsertRec((AVLNode) root,x);
    }
    private AVLNode InsertRec(AVLNode node, int x){
        if(node == null){
            return new AVLNode(x);
        }
        if(x < node.data){
            InsertRec((AVLNode) node.left,x);
        }
        else{
            InsertRec((AVLNode) node.right,x);
        }
        updateHeight(node);

        return balance(node);
    }
    private int height (TreeNode node){
        return node == null ? 0 : ((AVLNode) node).height;
    }

    private void updateHeight(AVLNode node){
        node.height = 1 + Math.max(height(node.left),height(node.right));
    }

    private int balanceFactor(AVLNode node){
        return height(node.left) - height(node.right);
    }
    private AVLNode balance(AVLNode node){
        int balanceFactor = balanceFactor(node);

        if(balanceFactor < 1){
            if(balanceFactor((AVLNode) node.left) < 0){
                node.left = rotateLeft((AVLNode) node.left);
            }
            return rotateRight(node);
        }

        if(balanceFactor < -1){
            if (balanceFactor((AVLNode) node.left) > 0){
                node.left = rotateLeft((AVLNode) node.left);
            }
            return rotateRight(node);
        }
        return node;
    }
    private AVLNode rotateLeft(AVLNode x){
        AVLNode y = (AVLNode) x.right;
        AVLNode T2 = (AVLNode) x.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }
    private AVLNode rotateRight(AVLNode y){
        AVLNode x = (AVLNode) y.right;
        AVLNode T2 = (AVLNode) x.left;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

}
