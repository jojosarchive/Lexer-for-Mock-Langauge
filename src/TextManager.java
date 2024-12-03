public class TextManager {
    private String input;
    private int position;

    public TextManager(String input) {
        this.input = input;
        this.position = 0;
    }

    public char getCharacter() {

        if (input.length() > position) {
            return input.charAt(position++);

        } else {
            return 'n';
        }
    }

    public boolean isAtEnd() {
        return input.length() <= position;

    }
    public void fixPosition(){
        position--;
    }

    public char peekCharacter() {
        return input.charAt(position);

    }
}