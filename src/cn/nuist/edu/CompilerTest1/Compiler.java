package cn.nuist.edu.CompilerTest1;

import java.util.Scanner;



public class Compiler {
    private static String input_buffer = "";
    static Scanner s = new Scanner(System.in); 
    public static int yylineno = 0; 
	public static void main(String[] args) {
		while (true) {
            String line = s.nextLine();
            yylineno++;
            if (line.equals("#")) {  
                break;  
            }
            if(line.charAt(0)=='{'){
            	System.out.println(yylineno+":"+line);
            	continue;
            }
            else{           	
				Lexer lexer = new Lexer(line+="\n");
				System.out.println(yylineno+":");
				lexer.runLexer();
            }

        }  
        s.close();
          
        //Parser parser = new Parser(lexer);  
        //parser.statements();  
          
    }  
}
