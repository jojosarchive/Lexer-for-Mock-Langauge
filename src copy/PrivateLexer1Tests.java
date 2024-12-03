import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrivateLexer1Tests {

    @Test
    public void SimpleLexerTest() {
        var l = new Lexer("ababab cdcd ef gh ijij kl mnop");
        try {
            var res = l.Lex();
            Assertions.assertEquals(7, res.size());
            Assertions.assertEquals("ababab", res.get(0).getValue());
            Assertions.assertEquals("cdcd", res.get(1).getValue());
            Assertions.assertEquals("ef", res.get(2).getValue());
            Assertions.assertEquals("gh", res.get(3).getValue());
            Assertions.assertEquals("ijij", res.get(4).getValue());
            Assertions.assertEquals("kl", res.get(5).getValue());
            Assertions.assertEquals("mnop", res.get(6).getValue());
            for (var result : res)
                Assertions.assertEquals(Token.TokenTypes.WORD, result.getType());
        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }

    @Test
    public void MultilineLexerTest() {
        var l = new Lexer("ababab cdcd ef gh ijij kl mnop\nasdjkdsajkl\ndsajkdsa   asdjksald dsajhkl \n\n\n");
        try {
            var res = l.Lex();
            Assertions.assertEquals(16, res.size());
            Assertions.assertEquals("ababab", res.get(0).getValue());
            Assertions.assertEquals("cdcd", res.get(1).getValue());
            Assertions.assertEquals("ef", res.get(2).getValue());
            Assertions.assertEquals("gh", res.get(3).getValue());
            Assertions.assertEquals("ijij", res.get(4).getValue());
            Assertions.assertEquals("kl", res.get(5).getValue());
            Assertions.assertEquals("mnop", res.get(6).getValue());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(7).getType());
            Assertions.assertEquals("asdjkdsajkl", res.get(8).getValue());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(9).getType());
            Assertions.assertEquals("dsajkdsa", res.get(10).getValue());
            Assertions.assertEquals("asdjksald", res.get(11).getValue());
            Assertions.assertEquals("dsajhkl", res.get(12).getValue());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(13).getType());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(14).getType());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(15).getType());
        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }

//    @Test
//    public void NotEqualsTest() {
//        var l = new Lexer("!=");
//        try {
//            var res = l.Lex();
//            Assertions.assertEquals(1, res.size());
//        }
//        catch (Exception e) {
//            Assertions.fail("exception occurred: " +  e.getMessage());
//        }
//    }

    @Test
    public void IndentTest() {
        var l = new Lexer(
                "loop keepGoing\n" +
                        "    if n >= 15\n" +
                        "        keepGoing = false\n" +
                        "loop keepGoing\n" +
                        "    if n >= 15\n" +
                        "        keepGoing = false\n"
        );
        try {
            var res = l.Lex();
            Assertions.assertEquals(32, res.size());
        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }

    @Test
    public void FractionNumberTest() {
        var l = new Lexer(".2 0.5 -1.6 -.8");
        try {
            var res = l.Lex();
            Assertions.assertEquals(6, res.size());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(0).getType());
            Assertions.assertEquals(".2", res.get(0).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(1).getType());
            Assertions.assertEquals("0.5", res.get(1).getValue());
            Assertions.assertEquals(Token.TokenTypes.MINUS, res.get(2).getType());
            Assertions.assertEquals("", res.get(2).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(3).getType());
            Assertions.assertEquals("1.6", res.get(3).getValue());
            Assertions.assertEquals(Token.TokenTypes.MINUS, res.get(4).getType());
            Assertions.assertEquals("", res.get(4).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(5).getType());
            Assertions.assertEquals(".8", res.get(5).getValue());
        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }

    @Test
    public void EvilNumberTest() {
        var l = new Lexer("1.23 1.23 .45");
        try {
            var res = l.Lex();
            Assertions.assertEquals(3, res.size());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(0).getType());
            Assertions.assertEquals("1.23", res.get(0).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(1).getType());
            Assertions.assertEquals("1.23", res.get(1).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(2).getType());
            Assertions.assertEquals(".45", res.get(2).getValue());
        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }

    @Test
    public void NumberTest() {
        var l = new Lexer("123 456 -789 0");
        try {
            var res = l.Lex();
            Assertions.assertEquals(5, res.size());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(0).getType());
            Assertions.assertEquals("123", res.get(0).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(1).getType());
            Assertions.assertEquals("456", res.get(1).getValue());
            Assertions.assertEquals(Token.TokenTypes.MINUS, res.get(2).getType());
            Assertions.assertEquals("", res.get(2).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(3).getType());
            Assertions.assertEquals("789", res.get(3).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(4).getType());
            Assertions.assertEquals("0", res.get(4).getValue());
        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }

//    @Test
//    public void TwoCharacterTest() {
//        var l = new Lexer(">= > <= < = == !=");
//        try {
//            var res = l.Lex();
//            Assertions.assertEquals(7, res.size());
//            Assertions.assertEquals(Token.TokenTypes.GREATERTHANEQUAL, res.get(0).getType());
//            Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, res.get(1).getType());
//            Assertions.assertEquals(Token.TokenTypes.LESSTHANEQUAL, res.get(2).getType());
//            Assertions.assertEquals(Token.TokenTypes.LESSTHAN, res.get(3).getType());
//            Assertions.assertEquals(Token.TokenTypes.ASSIGN, res.get(4).getType());
//            Assertions.assertEquals(Token.TokenTypes.EQUAL, res.get(5).getType());
//            Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, res.get(6).getType());
//        }
//        catch (Exception e) {
//            Assertions.fail("exception occurred: " +  e.getMessage());
//        }
//    }

    @Test
    public void WordsNumbersMixedTest() {
        var l = new Lexer("word 1.3 mixed 57");
        try {
            var res = l.Lex();
            Assertions.assertEquals(4, res.size());
            Assertions.assertEquals(Token.TokenTypes.WORD, res.get(0).getType());
            Assertions.assertEquals("word", res.get(0).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(1).getType());
            Assertions.assertEquals("1.3", res.get(1).getValue());
            Assertions.assertEquals(Token.TokenTypes.WORD, res.get(2).getType());
            Assertions.assertEquals("mixed", res.get(2).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(3).getType());
            Assertions.assertEquals("57", res.get(3).getValue());

        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }

    @Test
    public void MixedTest() {
        var l = new Lexer("word 1.2 : ( ) \n\n -0.3\n3 word   5word");
        try {
            var res = l.Lex();
            Assertions.assertEquals(13, res.size());
            Assertions.assertEquals(Token.TokenTypes.WORD, res.get(0).getType());
            Assertions.assertEquals("word", res.get(0).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(1).getType());
            Assertions.assertEquals("1.2", res.get(1).getValue());
            Assertions.assertEquals(Token.TokenTypes.COLON, res.get(2).getType());
            Assertions.assertEquals(Token.TokenTypes.LPAREN, res.get(3).getType());
            Assertions.assertEquals(Token.TokenTypes.RPAREN, res.get(4).getType());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(5).getType());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(6).getType());
            Assertions.assertEquals(Token.TokenTypes.MINUS, res.get(7).getType());
            Assertions.assertEquals("", res.get(7).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(8).getType());
            Assertions.assertEquals("0.3", res.get(8).getValue());
            Assertions.assertEquals(Token.TokenTypes.NEWLINE, res.get(9).getType());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(10).getType());
            Assertions.assertEquals("3", res.get(10).getValue());
            Assertions.assertEquals(Token.TokenTypes.WORD, res.get(11).getType());
            Assertions.assertEquals("word", res.get(11).getValue());
            Assertions.assertEquals(Token.TokenTypes.NUMBER, res.get(12).getType());
            Assertions.assertEquals("5", res.get(12).getValue());
            Assertions.assertEquals(Token.TokenTypes.WORD, res.get(13).getType());
            Assertions.assertEquals("word", res.get(13).getValue());
        }
        catch (Exception e) {
            Assertions.fail("exception occurred: " +  e.getMessage());
        }
    }
    @Test
    public void SimpeLexerTest() throws Exception {
        var l = new Lexer("ababab.");

            var res = l.Lex();
            Assertions.assertEquals(2, res.size());
            Assertions.assertEquals(Token.TokenTypes.WORD, res.get(0).getType());
            Assertions.assertEquals(Token.TokenTypes.DOT, res.get(1).getType());
    }
}
