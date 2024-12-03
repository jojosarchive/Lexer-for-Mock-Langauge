import AST.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class Parser {
    TranNode top;
    TokenManager tm;
    public Parser(TranNode top, List<Token> tokens) {
        this.top = top;
        this.tm = new TokenManager(tokens);
    }


    // Tran = { Class | Interface }
    public void Tran() throws Exception {
        boolean done = false;
        while (!done) {
            var i = interfaceP();
            if (i.isPresent()) {
                top.Interfaces.add(i.get());
            } else {
                var c = classP();
                if (c.isPresent())
                    top.Classes.add(c.get());
                else
                    done = true;
            }
        }
    }

    // Interface = "interface" Identifier NEWLINE INDENT {MethodHeader NEWLINE } DEDENT
    public Optional<InterfaceNode> interfaceP() throws Exception {
        if(tm.matchAndRemove(Token.TokenTypes.INTERFACE).isEmpty())
            return Optional.empty();
        var name = tm.matchAndRemove(Token.TokenTypes.WORD);
        if(name.isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        if(tm.matchAndRemove(Token.TokenTypes.NEWLINE).isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        if(tm.matchAndRemove(Token.TokenTypes.INDENT).isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        var i = new InterfaceNode();
        i.name = name.get().getValue();
        boolean done = false;
        while (!done) {
            var m = MethodHeader();
            if (m.isPresent()) {
                i.methods.add(m.get());
            }
            else
                done = true;
        }
        if(tm.matchAndRemove(Token.TokenTypes.DEDENT).isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        return Optional.of(i);
    }

        public Optional<ClassNode> classP() {
            //if(matchAndRemove
            return null;
        }

    //so a value is passed in to see if its a new line
    /*
    public void RequireNewLine(value){
        if(value == '\n'){
            return value;
        }
            return null;

    }
*/
    public Optional<VariableDeclarationNode> parseVariableDeclaration() throws Exception {
        var type = tm.matchAndRemove(Token.TokenTypes.WORD);
        if(type.isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        var name = tm.matchAndRemove(Token.TokenTypes.WORD);
        if(name.isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        var vdNode = new VariableDeclarationNode();
        vdNode.type = type.get().getValue();
        vdNode.name = type.get().getValue();

        return Optional.of(vdNode);//what if there are no variables in a certain instance
    }

    public Optional<MethodHeaderNode> MethodHeader() throws Exception {
        var type = tm.matchAndRemove(Token.TokenTypes.WORD);
        if(type.isEmpty())
            throw new Exception("");
        if(tm.matchAndRemove(Token.TokenTypes.LPAREN).isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>

        /*
        variable declarations
        // make a var, initialize with parseVariableDeclaration
        // ensure that it exists, if so continue, else throw new exception

        //questions, do i have to make a variable declarations node
         */

        if(tm.matchAndRemove(Token.TokenTypes.LPAREN).isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        if(tm.matchAndRemove(Token.TokenTypes.COLON).isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>
        var methodHeader = new MethodHeaderNode();

        var variableDeclaration = parseVariableDeclaration();
        if(variableDeclaration.isPresent()){
            methodHeader.parameters.add(variableDeclaration.get());
        }
        /*
        variable declarations
        // make a var, initialize with parseVariableDeclaration
        // ensure that it exists, if so continue, else throw new exception

        //questions, do i have to make a variable declarations node
         */
        if(tm.matchAndRemove(Token.TokenTypes.COMMA).isEmpty())
            throw new Exception(""); // TODO - where is syntaxerrorexception>

        return Optional.of(methodHeader);
    }















    //"if" BoolExp NEWLINE Statements ["else" NEWLINE (Statement | Statements)]

    /*
    public Optional<IfNode> parseIf() throws Exception{

        if(tm.matchAndRemove(Token.TokenTypes.IF).isEmpty())
            throw new Exception("");

        //To-do BoolExp

        if(tm.matchAndRemove(Token.TokenTypes.NEWLINE).isEmpty())
            throw new Exception("");

        //To-do statements

        //BELOW IS ["else" NEWLINE (Statement | Statements)]
        //since [] we dont have to check for errors, as it may or may not be an option

        if(tm.matchAndRemove(Token.TokenTypes.ELSE).isEmpty()){
            if(tm.matchAndRemove(Token.TokenTypes.NEWLINE)){
                var statement = parseStatment();{
                    if(statementNode.isPresent())
                }
                if (tm.matchAndRemove(Token)
            }
        }else{

        }

    }
*/
    public Optional<StatementNode> parseStatment() throws Exception{
    return null;
}

public Optional<BooleanOpNode> parseBoolExpTerm(){

        var boolExpTerm = new BooleanOpNode();
        return Optional.of(boolExpTerm);

    }
//[VariableReference "=" ] "loop" ( BoolExpTerm ) NEWLINE Statements

    public Optional<LoopNode> parseLoop() throws Exception {

        var variableRefrence = parseVariableReference();

        if (variableRefrence.isPresent()) {
            if (tm.matchAndRemove(Token.TokenTypes.EQUAL).isEmpty()) {
                if (tm.matchAndRemove(Token.TokenTypes.LOOP).isEmpty())
                    throw new Exception();
                    var boolExpTerm = parseBoolExpTerm();
                    if(boolExpTerm.isPresent()){

                    }
            }

        }else{

        }
        return Optional.of(null);
    }

    public Optional<VariableReferenceNode> parseVariableReference() throws Exception{
        var type = tm.matchAndRemove(Token.TokenTypes.WORD);
        if(type.isEmpty())
            throw new Exception("");
        var variableReferenceNode = new VariableReferenceNode();

        variableReferenceNode.name = type.get().getValue();

        return Optional.of(variableReferenceNode);
    }
}
