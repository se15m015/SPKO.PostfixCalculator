package at.technikum.wien.figl.winterhalder.spko.postfixcalculator;

import antlr.build.ANTLR;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.Lexer;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.Token;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.TokenSubType;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.TokenType;
import org.junit.Test;
import sun.security.pkcs11.Secmod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by richie on 07/10/16.
 */
public class PostfixCalculator {

    public static void main(String[] args) {
    }

    public Double parse(String input) {
        Stack stack = new Stack();
        HashMap<String, Double> vars = new HashMap<String, Double>();
        Double arg1;
        Double arg2;
        String var;
        Double result = null;

        if (input.isEmpty()) {
            System.out.println("Enter: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                input = br.readLine();
            } catch (IOException e) {
            }
        }

        try {
            ArrayList<Token> tokens = Lexer.lex(input);
            for (Token token : tokens) {
                if (token.type == TokenType.NUMBER) {
                    stack.push(token.data);
                }else if (token.type == TokenType.VAR) {
                    stack.push(token.data);
                }
                else if (token.type == TokenType.BINARY) {
                    arg2 = Double.parseDouble((String) stack.pop());
                    String val = (String) stack.pop();
                    try {
                        arg1 =  Double.parseDouble(val);
                    }catch(NumberFormatException ex){
                        arg1 = vars.get(val);
                        if(arg1 == null){
                            throw new IllegalArgumentException();
                        }
                    }

                    Double res = null;
                    if (token.subType == TokenSubType.OPADD) {
                        res = arg1 + arg2;
                    } else if (token.subType == TokenSubType.OPSUB) {
                        res = arg1 - arg2;
                    } else if (token.subType == TokenSubType.OPMUL) {
                        res = arg1 * arg2;
                    } else if (token.subType == TokenSubType.OPDIV) {
                        res = arg1 / arg2;
                    }
                    stack.push(Double.toString(res));
                } else if (token.type == TokenType.ASSIGN) {
                    arg2 = Double.parseDouble((String) stack.pop());
                    var = (String) stack.pop();
                    vars.put(var, arg2);

                } else if (token.type == TokenType.UNARY) {
                    arg1 = (Double) stack.pop();
                    stack.push(Double.toString(arg1 * -1));
                }
            }
            result = Double.parseDouble((String ) stack.pop());

            if (stack.isEmpty()) {
                System.out.println(result);
            } else {
                System.out.printf("Input is not valid");
                throw new IllegalArgumentException();
            }
        } catch (EmptyStackException ex) {
            System.out.printf("Input is not valid");
            throw new IllegalArgumentException();
        }
        return result;
    }
}
