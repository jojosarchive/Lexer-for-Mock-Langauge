/*
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {




    @Test
    public void testWithOneString() {
        Lexer lexer = new Lexer("hi");
        List<Token> tokens = lexer.Lex();
        //Assert.assertEquals(1, tokens.size());
        Assert.assertEquals("hi", tokens.get(0).getValue());
    }

    @Test
    public void testWithTwoStringsWithSpaces() {
        Lexer lexer = new Lexer("hi hello");
        ArrayList<Token> tokens = lexer.Lex();
        Assert.assertEquals(2, tokens.size());
        Assert.assertEquals("hi", tokens.get(0).getValue());
        Assert.assertEquals("hello", tokens.get(1).getValue());
    }

    @Test
    public void testWithSeveralStringsWithSpaces() {
        Lexer lexer = new Lexer("hi hello how are you doing");
        ArrayList<Token> tokens = lexer.lex();
        Assert.assertEquals(6, tokens.size());
        Assert.assertEquals("hi", tokens.get(0).getValue());
        Assert.assertEquals("hello", tokens.get(1).getValue());
        Assert.assertEquals("how", tokens.get(2).getValue());
        Assert.assertEquals("are", tokens.get(3).getValue());
        Assert.assertEquals("you", tokens.get(4).getValue());
        Assert.assertEquals("doing", tokens.get(5).getValue());
    }

    @Test
    public void testWithMultpleLines() {
        Lexer lexer = new Lexer("hi \n hello \n how is your \n day so far");
        ArrayList<Token> tokens = lexer.lex();
        Assert.assertEquals(8, tokens.size());
        Assert.assertEquals("hi", tokens.get(0).getValue());
        Assert.assertEquals("hello", tokens.get(1).getValue());
        Assert.assertEquals("is", tokens.get(3).getValue());
        Assert.assertEquals("far", tokens.get(7).getValue());

    }
    @Test
    public void testPunctuation() {
        Lexer lexer = new Lexer(",, ! . ? =");
        ArrayList<Token> tokens = lexer.lex();
        Assert.assertEquals(6, tokens.size());
        Assert.assertEquals(Token.TokenType.COMMA , tokens.get(0).getType());
        Assert.assertEquals(Token.TokenType.COMMA , tokens.get(1).getType());
        Assert.assertEquals(Token.TokenType.ASSIGN , tokens.get(5).getType());
        Assert.assertEquals(Token.TokenType.PERIOD , tokens.get(3).getType());
    }

    @Test
    public void testWithTwoStringWithCommas() {
        Lexer lexer = new Lexer("hi, hello, how have you BEEN");
        ArrayList<Token> tokens = lexer.lex();
        Assert.assertEquals(8, tokens.size());
        Assert.assertEquals(Token.TokenType.COMMA , tokens.get(1).getType());
        Assert.assertEquals("how", tokens.get(4).getValue());

    }

    @Test
    public void testWithDigit() {
        Lexer lexer = new Lexer("1");
        ArrayList<Token> tokens = lexer.lex();
        Assert.assertEquals(1, tokens.size());
        Assert.assertEquals("1", tokens.get(0).getValue());
    }

    @Test
    public void testWithDigits() {
        Lexer lexer = new Lexer("13 23 164545");
        ArrayList<Token> tokens = lexer.lex();
        Assert.assertEquals(3, tokens.size());
        Assert.assertEquals("13", tokens.get(0).getValue());
        Assert.assertEquals("23", tokens.get(1).getValue());
        Assert.assertEquals("164545", tokens.get(2).getValue());
    }

    @Test
    public void testWithDigitAndString() {
        Lexer lexer = new Lexer("hi 1 hello15 1453y");
        ArrayList<Token> tokens = lexer.lex();
        Assert.assertEquals(6, tokens.size());
        Assert.assertEquals("hi", tokens.get(0).getValue());
        Assert.assertEquals("1", tokens.get(1).getValue());
        Assert.assertEquals("15", tokens.get(3).getValue());
        Assert.assertEquals("y", tokens.get(5).getValue());
    }

   @Test
    public void testWithStringNumberPunctuation() {
       Lexer lexer = new Lexer("hello, is! the 1231 in; store?");
       ArrayList<Token> tokens = lexer.lex();
       Assert.assertEquals(10, tokens.size());
       Assert.assertEquals("hello", tokens.get(0).getValue());
       Assert.assertEquals("is", tokens.get(2).getValue());
       Assert.assertEquals(Token.TokenType.COMMA , tokens.get(1).getType());
       Assert.assertEquals("1231", tokens.get(5).getValue());
       Assert.assertEquals("1231", tokens.get(5).getValue());
       Assert.assertEquals(Token.TokenType.QUESTION , tokens.get(9).getType());
       Assert.assertEquals(Token.TokenType.SEMICOLON , tokens.get(7).getType());
   }
}

 */