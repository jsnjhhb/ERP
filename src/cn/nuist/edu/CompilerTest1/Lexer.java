package cn.nuist.edu.CompilerTest1;

import java.util.Arrays;



public class Lexer {  
    public static final int  EOI = 0;  //输入达到末尾 end of file
    public static final int  SEMI = 1; //; 
    public static final int  PLUS = 2; //+ 
    public static final int  MINUS = 3; //-
    public static final int  TIMES = 4; //*
    public static final int  EQUAL = 5; //=
    public static final int  LP = 6;  //(
    public static final int  RP = 7;  //)
    public static final int  NUM_OR_VAL = 8;
    public static final int  ID_OR_NAM = 9;
    public static final int  SLASH = 10; // / 
    public static final int  ReserveWord = 11;
    public static final int  Bigger = 12;
    public static final String[] reserve = new String[]{"if","then","else","end","repeat","until","read","write"};
    private int lookAhead = -1;  
        
    public String yytext = "";  //当前分析的子字符串
    public int yyleng = 0;  
    public int yylineno = 0;  
      
  
    private String current = "";  
      
    Lexer(String current) {
		super();
		this.current = current;
	}

	private boolean isAlnum(char c) {  
        if (Character.isAlphabetic(c) == true ||  
                Character.isDigit(c) == true) {  
            return true;  
        }  
          
        return false;  
    }  
      
    private int lex() {  
        while (true) {  
            
                if (current.length() == 0) {    
                    return EOI;  
                }  
                ++yylineno; //行号 
                current.trim();  //去除空格
                //current = current.replaceAll(" ", "");
//                System.out.println(current);
 
                 
                for (int i = 0; i < current.length(); i++) {  
                      
                    yyleng = 0;  
                    yytext = current.substring(0, 1);  
                    switch (current.charAt(i)) {  
                    case ';': current = current.substring(1); return SEMI;  
                    case '+': current = current.substring(1); return PLUS;
                    case '-': current = current.substring(1); return MINUS;
                    case '*': current = current.substring(1);return TIMES;
                    case '=': current = current.substring(1);return EQUAL; 
                    case '/': current = current.substring(1);return SLASH;
                    case '<': current = current.substring(1);return Bigger;
                    case '\n':  
                    case '\t':  
                    case ' ': current = current.substring(1); i =i-1;break;  
                    case '(': current = current.substring(1);return LP;  
                    case ')': current = current.substring(1);return RP;  
                    default:  
                        if (isAlnum(current.charAt(i)) == false) {  
                            System.out.println("Ignoring illegal input: " + current.charAt(i));  
                        }  
                        else {                                
                            while (isAlnum(current.charAt(i))) {  
                                i++;  
                                yyleng++;
                                
                            } // while (isAlnum(current.charAt(i)))  
                              
                            yytext = current.substring(0, yyleng);
//                            System.out.println(yytext);
                            if(isNumeric0(yytext)){
                            	current = current.substring(yyleng);
//                            	System.out.println(current);
                            	return NUM_OR_VAL;
                            }
                            else if(Arrays.asList(reserve).contains(yytext)){
                            	current = current.substring(yyleng);
//                            	System.out.println(current);
                            	return ReserveWord;                            	
                            }
                            else{                            	
                            	current = current.substring(yyleng);
                            	return ID_OR_NAM;
                            }
                              
                              
                        }  
                          
                        break;  
                          
                    } //switch (current.charAt(i))  
                }//  for (int i = 0; i < current.length(); i++)   
              
        }//while (true)   
    }//lex()  
      
    public boolean match(int token) {  
        if (lookAhead == -1) {  
            lookAhead = lex();  
        }  
          
        return token == lookAhead;  
    }  
    public boolean isNumeric0(String str){
    	  for(int i=str.length();--i>=0;){
    	   int chr=str.charAt(i);
    	   if(chr<48 || chr>57)
    	    return false;
    	  }
    	  return true;
    	 }  
    public void advance() {  
        lookAhead = lex();  
    }  
      
    public void runLexer() {  
        while (!match(EOI)) {  
            System.out.println("Token: " + Gettoken() + " ,Symbol: " + yytext );  
            advance();  
        }  
    }  
      
    private String Gettoken() {  
        String token = "";  
        switch (lookAhead) {  
        case EOI:  
            token = "EOI";  
            break;
        case SEMI:  
            token = "SEMI"; 
            break;
        case PLUS:  
            token = "PLUS";  
            break; 
        case MINUS:  
            token = "MINUS";  
            break;     
        case TIMES:  
            token = "TIMES";  
            break;
        case EQUAL:  
            token = "EQUAL";  
            break;
        case LP:  
            token = "LP";  
            break;  
        case RP:  
            token = "RP";  
            break;    
        case NUM_OR_VAL:  
            token = "NUM_OR_VAl";  
            break;  
        case ID_OR_NAM:  
            token = "ID_OR_NAM";  
            break;  
        case SLASH:  
            token = "SLASH";  
            break;     
        case ReserveWord:  
            token = "ReserveWord";  
            break;
        case Bigger:  
	        token = "Bigger";  
	        break;     
        }  
	     
        return token;  
    }  
}
