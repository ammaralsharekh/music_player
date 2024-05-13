class MusicNode {
    String file;
    MusicNode next;

    public MusicNode(String file) {
        this.file = file;
        this.next = null;
    }
}

class CircularLinkedList {
    private MusicNode head = null;
    private MusicNode tail = null;

    public void add(String file) {
        MusicNode newNode = new MusicNode(file);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;  // Makes the list circular
    }

    public MusicNode getHead() {
        return head;
    }
}
