package Momento;

import java.util.Stack;

class TextEditor {

    private String text;

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public Momentoo save() {
        return new Momentoo(text);
    }
    public void restore(Momentoo momento) {
        this.text = momento.getText();
    }
}

class Momentoo {

    private String text;

    public Momentoo(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}

class EditorHistory {

    private Stack<Momentoo> history = new Stack<>();

    public void push(Momentoo momento) {
        history.push(momento);
    }
    public Momentoo pop() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}

class Momento {

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();
        // Initial text
        editor.setText("Hello");
        System.out.println("Current text: " + editor.getText());
        history.push(editor.save());
        // User types something new
        editor.setText("Hello, World!");
        System.out.println("Current text: " + editor.getText());
        history.push(editor.save());
        // Another change
        editor.setText("Hello, World! Welcome to Memento Pattern.");
        System.out.println("Current text: " + editor.getText());
        // Undo the last change
        Momentoo previousState = history.pop();
        editor.restore(previousState);
        System.out.println("After undo, text: " + editor.getText());
        // Undo to the initial state
        previousState = history.pop();
        editor.restore(previousState);
        System.out.println("After second undo, text: " + editor.getText());

    }
}
