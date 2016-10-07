package at.technikum.wien.figl.winterhalder.spko.postfixcalculator;

import antlr.build.ANTLR;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.Lexer;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.Token;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.TokenSubType;
import at.technikum.wien.figl.winterhalder.spko.postfixcalculator.lexer.TokenType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by richie on 07/10/16.
 */
public class PostfixCalculator {

    public static void main(String[] args) throws IOException {
        lexer("3 4 5+*");
    }

    private static void lexer(String input) throws IOException {
        Stack stack = new Stack();
        double arg1;
        double arg2;
        double result;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter: ");
        if(input.isEmpty()) {
            input = br.readLine();
        }

        try {
            ArrayList<Token> tokens = Lexer.lex(input);
            for (Token token : tokens) {
                if (token.type == TokenType.NUMBER) {
                    stack.push(Double.parseDouble(token.data));
                } else if (token.type == TokenType.BINARY){
                    arg2 = (Double) stack.pop();
                    arg1 = (Double) stack.pop();

                    if (token.subType == TokenSubType.OPADD) {
                        stack.push(arg1 + arg2);
                    } else if (token.subType == TokenSubType.OPSUB) {
                        stack.push(arg1 - arg2);
                    } else if (token.subType == TokenSubType.OPMUL) {
                        stack.push(arg1 * arg2);
                    }else if (token.subType == TokenSubType.OPDIV) {
                        stack.push(arg1 / arg2);
                    }
                }  else if (token.type == TokenType.UNARY) {
                    arg1 = (Double) stack.pop();
                    stack.push(arg1 * -1);
                }
            }
            result = (Double) stack.pop();

            if (stack.isEmpty()){
                System.out.println(result);
            }else {
                System.out.printf("Input is not valid");
            }
        }catch (EmptyStackException ex){
            System.out.printf("Input is not valid");
        }
    }
}
