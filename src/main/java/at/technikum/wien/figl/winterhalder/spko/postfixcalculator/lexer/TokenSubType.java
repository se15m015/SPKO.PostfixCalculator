package at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer;

/**
 * Created by richie on 07/10/16.
 */
public enum TokenSubType {
    OPADD("[+]"),
    OPSUB("[-]"),
    OPMUL("[*]"),
    OPDIV("[/]"),
    OPNEG("[~]");

    public final String pattern;

    private TokenSubType(String pattern) {
        this.pattern = pattern;
    }
}
