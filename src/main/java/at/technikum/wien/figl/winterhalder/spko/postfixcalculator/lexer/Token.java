package at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer;

/**
 * Created by richie on 07/10/16.
 */
public class Token {
    public TokenSubType subType;
    public TokenType type;
    public String data;

    public Token(TokenType type, TokenSubType subType, String data) {
        this.type = type;
        this.subType = subType;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("(%s %s)", type.name(), data);
    }
}
