package at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by richie on 07/10/16.
 */
public class Lexer {

    public static ArrayList<Token> lex(String input) {
        // The tokens to return
        ArrayList<Token> tokens = new ArrayList<Token>();

        //Lexer logic begins here
        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

        StringBuffer subTokenPatternsBuffer = new StringBuffer();
        for (TokenSubType tokenSubTypeType : TokenSubType.values())
            subTokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenSubTypeType.name(), tokenSubTypeType.pattern));
        Pattern subTokenPatterns = Pattern.compile(new String(subTokenPatternsBuffer.substring(1)));

        // Begin matching tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if (matcher.group(TokenType.NUMBER.name()) != null) {
                tokens.add(new Token(TokenType.NUMBER, null, matcher.group(TokenType.NUMBER.name())));
                continue;
            } else if (matcher.group(TokenType.BINARY.name()) != null) {
                String binaryPattern = matcher.group(TokenType.BINARY.name());
                Matcher subMatcher = subTokenPatterns.matcher(binaryPattern);
                while (subMatcher.find()) {
                    if (subMatcher.group(TokenSubType.OPADD.name()) != null) {
                        tokens.add(new Token(TokenType.BINARY, TokenSubType.OPADD, binaryPattern));
                        continue;
                    } else if (subMatcher.group(TokenSubType.OPSUB.name()) != null) {
                        tokens.add(new Token(TokenType.BINARY, TokenSubType.OPSUB, binaryPattern));
                        continue;

                    } else if (subMatcher.group(TokenSubType.OPMUL.name()) != null) {
                        tokens.add(new Token(TokenType.BINARY, TokenSubType.OPMUL, binaryPattern));
                        continue;

                    } else if (subMatcher.group(TokenSubType.OPDIV.name()) != null) {
                        tokens.add(new Token(TokenType.BINARY, TokenSubType.OPDIV, binaryPattern));
                        continue;
                    }
                }

            }  if (matcher.group(TokenType.UNARY.name()) != null) {
                tokens.add(new Token(TokenType.UNARY, TokenSubType.OPNEG, matcher.group(TokenType.UNARY.name())));
                continue;

            } else if (matcher.group(TokenType.WHITESPACE.name()) != null)
                continue;
        }

        return tokens;
    }
}
