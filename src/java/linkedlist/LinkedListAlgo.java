package java.linkedlist;

import java.util.HashMap;

/**
 * 1）单链表反转
 * 2）链表中环检测
 * 3）有序链表合并
 * 4）删除链表中倒数第n个结点
 *
 *
 * @author MagicQ
 * @version 1.0
 * @date 2019/1/11
 */
public class LinkedListAlgo {
    /**
     * 单链表反转
     *
     * @param list
     * @return
     */
    public static Node reverse(Node list) {
        Node headNode = null;

        Node previousNode = null;
        Node currentNode = list;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            if (nextNode == null) {
                headNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return headNode;
    }

    /**
     * 单链表反转
     *
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {
        Node prev = null;
        while (head != null) {
            Node tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    /**
     * 头节点插入法实现链表反转
     *
     * @param current 当前节点
     * @return
     */
    public static Node reverseByHeadInsert(Node current) {
        Node dummy = new Node(-1);
        Node pCur = current;
        while (pCur != null) {
            Node pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        return dummy.next;
    }

    /**
     * 1->2->3->NULL
     * 3->2->1->NULL
     * 递归实现单链表反转
     *
     * @param head
     * @return
     */
    public static Node reverseListByRecursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = reverseListByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    /**
     * 判断链表中存在环：快慢指针法
     *
     * @param headNode
     * @return
     */
    public static boolean hasCircle(Node headNode) {

        if (headNode == null) {
            return false;
        }
        Node p = headNode;
        Node q = headNode.next;

        while (q != null && q.next != null) {
            p = p.next; // 遍历一个节点
            q = q.next.next; // 遍历两个个节点

            // 已到链表末尾
            if (q == null) {
                return false;
            } else if (p == q) {
                // 快慢指针相遇，存在环
                return true;
            }
        }

        return false;
    }

    /**
     * 判断链表中存在环:足迹法
     *
     * @param list
     * @return
     */
    public static Boolean hasCircle1(Node list) {
        HashMap map = new HashMap();
        while (list != null) {
            if (map.containsKey(list)) {
                return true;
            }
            map.put(list, list.data);
            list = list.next;
        }
        return false;
    }

    /**
     * 删除倒数第n个元素：将链表反转，删除正数第n个元素后再次反转
     *
     * @param list 当前结点
     * @return
     */
    public static Node delIndexNode(Node list, int index) {
        Node currentNode = list;
        Node reversNode = null;
        if (currentNode != null && currentNode.next != null) {
            reversNode = reverseByHeadInsert(currentNode);
        }
        int i = 0;
        Node indexNode = null;
        if (i >= index - 2) {
            return reversNode;
        }
        while (reversNode != null && i < (index - 2)) {
            //index 前一个元素
            indexNode = reversNode.next;
            Node delNode = indexNode.next;
            indexNode.next = delNode.next;
            delNode = null;
            i++;
            reversNode = reversNode.next;
        }
        reversNode = reverseByHeadInsert(currentNode);
        return reversNode;
    }

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        // printAll(node3);
        // Node reversed = reverse(node3);
        // Node reversed = reverseList(node3);
        Node reversed = reverseListByRecursion(node3);
        // Node reversed = reverseByHeadInsert(node3);
        printAll(reversed);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node4;
        // System.out.printf("has cirecle : %s\n", hasCircle(node7));
        Node node = delIndexNode(node3, 2);
        // printAll(node);

    }
}
