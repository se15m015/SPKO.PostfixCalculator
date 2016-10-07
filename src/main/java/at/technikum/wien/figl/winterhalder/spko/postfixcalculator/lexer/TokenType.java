package at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer;

/**
 * Created by richie on 07/10/16.
 */
public enum TokenType {
    NUMBER("-?[0-9]+(\\.[0-9]+)?"),
    VAR("[a-z]+"),
    BINARY("[+|-|*|/]"),
    ASSIGN("[=]"),
    UNARY("[~]"),
    WHITESPACE("[ \t\f\r\n]+");

    public final String pattern;

    private TokenType(String pattern) {
        this.pattern = pattern;
    }
}
