package Snowflake;

class DesignTextEditor {
    public static class Node {
        Character ch;
        Node next;
        Node prev;

        public Node(Character ch) {
            this.ch = ch;
            this.next = this.prev = null;
        }
    }

    Node cursor;
    int length;
    public DesignTextEditor() {
        this.cursor = new Node('|');
        this.length = 0;
    }

    public void addText(String text) {
        for(char c : text.toCharArray()) {
            Node newNode = new Node(c);
            insertAfter(cursor, newNode);
            cursor = newNode;
            length++;
        }
    }

    public int deleteText(int k) {
        int del = 0;
        while(k > 0 && cursor.prev != null) {
            Node toDelete = cursor;
            cursor = cursor.prev;
            deleteNode(toDelete);
            k--;
            del++;
            length--;
        }
        return del;
    }

    public String cursorLeft(int k) {
        while(k > 0 && cursor.prev != null) {
            cursor = cursor.prev;
            k--;
        }
        return getLeftText();
    }

    public String cursorRight(int k) {
        while(k > 0 && cursor.next != null) {
            cursor = cursor.next;
            k--;
        }
        return getLeftText();
    }

    public void insertAfter(Node cursor, Node newNode) {
        newNode.prev = cursor;
        newNode.next = cursor.next;
        if(cursor.next != null) {
            cursor.next.prev = newNode;
        }
        cursor.next = newNode;
    }

    public void deleteNode(Node node) {
        if(node.prev != null) {
            node.prev.next = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }
    }

    public String getLeftText() {
        StringBuilder ret = new StringBuilder();
        int cnt = 10;
        Node curr = cursor;
        while(curr.prev != null && cnt > 0) {
            ret.append(curr.ch);
            curr = curr.prev;
            cnt--;
        }
        return ret.reverse().toString();
    }

    public static void main(String[] args) {
        DesignTextEditor editor = new DesignTextEditor();
        editor.addText("leetcode");
        System.out.println(editor.deleteText(3)); // Deletes "code"
        System.out.println(editor.cursorLeft(2)); // Moves cursor left leetc|
        System.out.println(editor.cursorRight(2)); // Moves cursor right
    }
}