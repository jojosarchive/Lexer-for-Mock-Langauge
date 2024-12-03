import java.util.List;
import java.util.Optional;

public class TokenManager {
    private List<Token> retVal;

    //constructor token manager takes in a list of Tokens from the lexer and populates this.retVal with the list of tokens
    public TokenManager(List<Token> tokens) {
        this.retVal = tokens;
    }
    public boolean done() {
        if(!retVal.isEmpty()){
            return false;
        }
        return true;
    }

    //match and remove is a check done on every successive token, it determines if the token matches a rule of the created ebnf, if it does removes it from the list and returns it.
    public Optional<Token> matchAndRemove(Token.TokenTypes t) {
        Token next = retVal.get(0);

        if(t == next.getType()){ //check if token matches the type

            Token token = retVal.removeFirst();
            return Optional.of(next);
        }

        return Optional.empty(); //return empty if it does not match
    }

    //used to look at the token, nothing else
    public Optional<Token> peek(int i) {

        if( i >= retVal.size() || i <0){//check for out of bounds
            return Optional.empty();
        }
        return Optional.of(retVal.get(i));

    }

    public Optional<Token> nextTwoTokensMatch(){



        return null;
    }


}
