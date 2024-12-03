import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

public class Lexer {
    private TextManager TextManagerInstance;
    private HashMap<String, Token.TokenTypes> punctuationHashMap;
    private HashMap<String, Token.TokenTypes> keywordHashMap;
    private List<Integer> blockHistory; //used to track the levels of indent or dedent in the current session of lexing
    int lineNumber = 1;
    int columnNumber = 0;


    private int levelOfBlock = 0;
    public Lexer(String input) {
        TextManagerInstance = new TextManager(input);
        punctuationHashMap = new HashMap<>();
        keywordHashMap = new HashMap<>();
        blockHistory = new LinkedList<>();
        blockHistory.add(0);

        punctuationHashMap.put("=", Token.TokenTypes.ASSIGN);
        punctuationHashMap.put("(", Token.TokenTypes.LPAREN);
        punctuationHashMap.put(")", Token.TokenTypes.RPAREN);
        punctuationHashMap.put(":", Token.TokenTypes.COLON);
        punctuationHashMap.put(".", Token.TokenTypes.DOT);

        punctuationHashMap.put("+", Token.TokenTypes.PLUS);
        punctuationHashMap.put("-", Token.TokenTypes.MINUS);
        punctuationHashMap.put("*", Token.TokenTypes.TIMES);
        punctuationHashMap.put("/", Token.TokenTypes.DIVIDE);
        punctuationHashMap.put("%", Token.TokenTypes.MODULO);
        punctuationHashMap.put(",", Token.TokenTypes.COMMA);

        punctuationHashMap.put("+", Token.TokenTypes.PLUS);
        punctuationHashMap.put("-", Token.TokenTypes.MINUS);


        punctuationHashMap.put(":", Token.TokenTypes.COLON);
        punctuationHashMap.put(",", Token.TokenTypes.COMMA);

        keywordHashMap.put("ACCESSOR", Token.TokenTypes.ACCESSOR);
        keywordHashMap.put("MUTATOR", Token.TokenTypes.MUTATOR);
        keywordHashMap.put("IMPLEMENTS", Token.TokenTypes.IMPLEMENTS);
        keywordHashMap.put("CLASS", Token.TokenTypes.CLASS);
        keywordHashMap.put("INTERFACE", Token.TokenTypes.INTERFACE);
        keywordHashMap.put("LOOP", Token.TokenTypes.LOOP);
        keywordHashMap.put("IF", Token.TokenTypes.IF);
        keywordHashMap.put("ELSE", Token.TokenTypes.ELSE);
        keywordHashMap.put("/n", Token.TokenTypes.NEWLINE);


    }

    public List<Token> Lex() throws Exception {
        var retVal = new LinkedList<Token>();
        char character;

        while (!TextManagerInstance.isAtEnd()) {          //if word isn't finished it keeps going for that session
            character = TextManagerInstance.getCharacter();        // call to the text managers method getCharacter
            columnNumber++;



                if(character == '\n') {
                    lineNumber++;
                    columnNumber = 0;
                    retVal.add(new Token(Token.TokenTypes.NEWLINE, lineNumber, columnNumber));

                    int spaces = countSpaces(); //spaces = the number of spaces in the line


                    if (spaces > levelOfBlock){//INDENT if
                        levelOfBlock = spaces; //current amount of spaces
                        blockHistory.add(levelOfBlock); //store the amount of spaces detected in a linked list

                        retVal.add(new Token(Token.TokenTypes.INDENT, lineNumber, columnNumber));

                    }else if(spaces < levelOfBlock){//DEDENT else if

                        while(!blockHistory.isEmpty() && spaces < blockHistory.get(blockHistory.size() - 1)){
                            blockHistory.remove(blockHistory.size() - 1);// remove the previously entered node in blockHistroy
                            levelOfBlock = blockHistory.get(blockHistory.size() - 1);

                            retVal.add(new Token(Token.TokenTypes.DEDENT, lineNumber, columnNumber));

                        }

                    }

                }

                if(character == '.'){
                    char next = TextManagerInstance.peekCharacter();
                    if(!TextManagerInstance.isAtEnd() && Character.isDigit(next)) {
                        retVal.add(parseDigit(character, lineNumber, columnNumber));
                    }else{
                        retVal.add(parsePunctuation(character, lineNumber, columnNumber));
                    }
                    continue;
                }


            if (Character.isLetter(character)) {
                retVal.add(parseWord(character, lineNumber, columnNumber));
            }else if (Character.isDigit(character )) {
                retVal.add(parseDigit(character, lineNumber, columnNumber));
            } else {
                Token punctuation = parsePunctuation(character, lineNumber, columnNumber);
                if(punctuation != null) {
                    retVal.add(punctuation);
                }
            }


        }
        return retVal;
    }


    private Token parseWord(char character, int lineNumber, int columnNumber) {
        String currentWord = "";


        if (Character.isLetter(character)) {
            currentWord += character;
            columnNumber++;
        }

        while (!TextManagerInstance.isAtEnd()) {


            character = TextManagerInstance.getCharacter();
            if (!Character.isLetter(character)) {
                TextManagerInstance.fixPosition();

                break; //if both of these if statements are checked then the character is not a letter to the
            } else {
                currentWord += character;
                columnNumber++;
            }
        }

        if (keywordHashMap.containsKey(currentWord)){
            Token.TokenTypes type = keywordHashMap.get(currentWord);
            return new Token(type, lineNumber,columnNumber);
        }else{
            return new Token(Token.TokenTypes.WORD, lineNumber, columnNumber, currentWord);
        }
    }

    private Token parseDigit(char character, int lineNumber, int columnNumber) {
        String currentDigit = "";


        if (Character.isDigit(character) || character == '.') {
            currentDigit += character;
        }
        while (!TextManagerInstance.isAtEnd()) {
            character = TextManagerInstance.getCharacter();


                if (Character.isDigit(character)) {
                    currentDigit += character;
                    columnNumber++;

                } else if (character == '.')  {

                        currentDigit += character;
                        columnNumber++;


                } else {
                    TextManagerInstance.fixPosition();

                    break;
                }



        }
        return new Token(Token.TokenTypes.NUMBER, lineNumber, columnNumber, currentDigit);
    }

    private Token parsePunctuation(char character, int lineNumber, int columnNumber) {
        String punctuation = String.valueOf(character);

        if (punctuationHashMap.containsKey(punctuation)) {
            Token.TokenTypes type = punctuationHashMap.get(punctuation);

            return new Token(type, lineNumber, columnNumber);

        }
        return null;
    }

    private int countSpaces() {
        int spaces = 0;

        while(!TextManagerInstance.isAtEnd()) {
            char nextChar = TextManagerInstance.peekCharacter(); //peeked character

            if(nextChar == ' ') {//check for an empty space
                spaces++;

                TextManagerInstance.getCharacter(); //return the character
                columnNumber++;

            }else{
                break;
            }
        }

        return spaces;
    }

}
