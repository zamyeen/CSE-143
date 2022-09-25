// A LinkedIntList stores a list of integers
public class LinkedIntList {
    private ListNode front;

    public void surroundWith(int x, int y) {
        
    }

    public static void main(String[] args) {
        LinkedIntList list = new LinkedIntList(1);
        list.surroundWith(1, 4);
        System.out.println(list);
    }

    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }
/////////////////////////////////////////////////////////

    // Constructs a list containing the given elements
    public LinkedIntList(int... elements) {
        if (elements.length > 0) {
            front = new ListNode(elements[0]);
            ListNode current = front;
            for (int i = 1; i < elements.length; i++) {
                current.next = new ListNode(elements[i]);
                current = current.next;
            }
        }
    }

    // post: Returns true if o is a LinkedIntList with the same values
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof LinkedIntList)) {
            return false;
        } else {
            return this.toString().equals(o.toString());
        }
    }

    // post: Returns a text representation of the list
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            ListNode current = front;
            String result = "[";
            while (current.next != null) {
                result += current.data + ", ";
                current = current.next;
            }
            result += current.data + "]";
            return result;
        }
    }

    // A ListNode represents a single node in a linked list
    private static class ListNode {
        public final int data;
        public ListNode next;

        // post: Constructs a node with data 0 and null link
        public ListNode() {
            this(0, null);
        }

        // post: Constructs a node with given data and null link
        public ListNode(int data) {
            this(data, null);
        }

        // post: Constructs a node with given data and given link
        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}