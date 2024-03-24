package algorithms.Tree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTree {
    public List<Integer> elementList = new ArrayList<>();

    public Node root;

    // protected static final Node nilNode = new Node(RedBlackTree.class, null, null, null, null, Node.Color.BLACK);

     /**
     * 以新插入节点6，节点4不平衡，需要左旋为案例
     * /----- 6
     * /----- 5
     * /----- 4
     * 2
     * \----- 1
     * <p>
     * 左旋操作
     * <p>
     * /----- 6
     * /----- 5
     * |       \----- 4
     * 2
     * \----- 1
     * <p>
     * 步骤；
     * 1. 左旋的作用，相当于通过向上迁移树高差大于1的右子节点来降低树高的操作。
     * 2. 通过节点4拿到父节点2和右子节点5，把父节点2和右子节点5建立关联
     * 3. 节点5的左子节点，相当于是大于4的那么一个值，只不过这里不体现。那么这个节点5的左子节点，应该被迁移到节点4的右子节点上。
     * 4. 整理节点5的关系，左子节点为4。左子节点4的父节点为5
     * 5. 如果说迁移上来的节点5无父节点，那么它就是父节点 root = temp
     * 6. 迁移上来的节点5，找到原节点4是对应父节点的左子节点还是右子节点，对应的设置节点5的左右位置
     */
    // protected Node rotateLeft(Node node) {
    //     Node temp = node.right;
    //     temp.parent = node.parent;

    //     node.right = temp.left;
    //     if (node.right != null && node.right != nilNode) {
    //         node.right.parent = node;
    //     }

    //     temp.left = node;
    //     node.parent = temp;

    //     if (temp.parent == null || temp.parent == nilNode) {
    //         root = temp;
    //     } else {
    //         if (temp.parent.left == node) {
    //             temp.parent.left = temp;
    //         } else {
    //             temp.parent.right = temp;
    //         }
    //     }
    //     return temp;
    // }

    /**
     * 以新插入节点1，节点3不平衡，需要右旋为案例
     * <p>
     * /----- 5
     * 4
     * \----- 3
     * \----- 2
     * \----- 1
     * <p>
     * 右旋操作
     * <p>
     * /----- 5
     * 4
     * |       /----- 3
     * \----- 2
     * \----- 1
     * <p>
     * 步骤；
     * 1. 右旋的作用，相当于通过向上迁移树高差大于1的右子节点来降低树高的操作。
     * 2. 通过节点3拿到父节点4和左子节点2，把父节点7和左子节点2建立关联
     * 3. 节点2的右子节点，相当于是大于2小于3的那么一个值，只不过这里不体现。那么这个节点2的右子节点，应该被迁移到节点3的左子节点上。
     * 4. 整理节点2的关系，右子节点为3。右子节点3的父节点为2
     * 5. 如果说迁移上来的节点2无父节点，那么它就是父节点 root = temp
     * 6. 迁移上来的节点2，找到原节点3是对应父节点的左子节点还是右子节点，对应的设置节点2的左右位置
     */
    // protected Node rotateRight(Node node) {
    //     Node temp = node.left;
    //     temp.parent = node.parent;

    //     node.left = temp.right;
    //     // 红黑树有空节点 nilNode
    //     if (node.left != null && node.left != nilNode) {
    //         node.left.parent = node;
    //     }

    //     temp.right = node;
    //     node.parent = temp;

    //     if (temp.parent == null || temp.parent == nilNode) {
    //         root = temp;
    //     } else {
    //         if (temp.parent.left == node) {
    //             temp.parent.left = temp;
    //         } else {
    //             temp.parent.right = temp;
    //         }
    //     }
    //     return temp;
    // }

    protected String printSubTree(Node node) {
        StringBuilder tree = new StringBuilder();
        if (node.right != null) {
            printTree(node.right, true, "", tree);
        }
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, "", tree);
        }
        return tree.toString();
    }

    private void printTree(Node node, boolean isRight, String indent, StringBuilder tree) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "), tree);
        }
        tree.append(indent);
        if (isRight) {
            tree.append(" /");
        } else {
            tree.append(" \\");
        }
        tree.append("----- ");
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "), tree);
        }
    }

    private void printNodeValue(Node node, StringBuilder tree) {
        if (null == node.value) {
            tree.append("<NIL>");
        } else {
            tree.append(node.value);
        //     if (root.clazz.equals(AVLTree.class)) {
        //         tree.append("(").append(node.height).append(")");
        //     } else if (root.clazz.equals(RedBlackTree.class)) {
        //         tree.append("(").append(node.color == Node.Color.BLACK ? "黑" : "红").append(")");
        //     }
        }
        tree.append("\r\n");
    }

}
