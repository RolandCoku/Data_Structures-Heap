import java.util.Comparator;

class Node {
    int data;
    Node next;
    Node(int key) {
        data = key;
        next = null;
    }
}
// Comparator per te krahasuar vlerat e nyjeve te listes
class NodeComparator implements Comparator<Node> {
    public int compare(Node k1, Node k2) {
        if (k1.data > k2.data)
            return 1;
        else if (k1.data < k2.data)
            return -1;
        return 0;
    }
}

public class MergeList {
// Funksioni mergeList per te bashkuar te gjithe listat
    static Node mergeList(Node[] arr, int K) {
        PriorityQueue<Node> queue= new PriorityQueue<>(new NodeComparator());

        for (int i = 0; i < K; i++){
            queue.add(arr[i]);
        }

        Node head = new Node(0);
        Node lastAdded = head;

        while (!queue.isEmpty()){
            Node min = queue.remove();

            lastAdded.next = min;
            lastAdded = lastAdded.next;

            if (min.next != null){
                queue.add(min.next);
            }
        }

        head = head.next;

        return head;
    }
//Funksion per te printuar nyjet e listes
    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
    public static void main(String[] args) {
        int N = 4;
// Nje vektor per te ruajtur header te listes
        Node[] a = new Node[N];
// Linkedlist1
        Node head1 = new Node(1);
        a[0] = head1;
        head1.next = new Node(2);
        head1.next.next = new Node(3);// Limkedlist2
        Node head2 = new Node(4);
        a[1] = head2;
        head2.next = new Node(5);
// Linkedlist3
        Node head3 = new Node(5);
        a[2] = head3;
        head3.next = new Node(6);
// Linkedlist4
        Node head4 = new Node(7);
        a[3] = head4;
        head4.next = new Node(8);
        Node res = mergeList(a, N);
        if (res != null)
            printList(res);
    }
}
