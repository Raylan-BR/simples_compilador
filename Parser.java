class Parser {
    
    private Scanner scan;
    private Token currentToken;
    private StringBuilder out = new StringBuilder();

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
    }
    private void nextToken () {
        currentToken = scan.nextToken();
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        }else {
            throw new Error("syntax error");
        }
   }
    void number () {
        out.append("push " + currentToken.lexeme).append(System.lineSeparator());
        //System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }
    void expr() {
        term();
        oper();
    }

    void oper () {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();
            out.append("add").append(System.lineSeparator());
            //System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            out.append("sub").append(System.lineSeparator());
            //System.out.println("sub");
            oper();
        } 
    }

    void term () {
        if (currentToken.type == TokenType.NUMBER)
            number();
        else if (currentToken.type == TokenType.IDENT) {
            out.append("push "+currentToken.lexeme).append(System.lineSeparator());
            //System.out.println("push "+currentToken.lexeme);
            match(TokenType.IDENT);
        }
        else
            throw new Error("syntax error");
    }

    public void parse() {
        statements();
    }

    void letStatement () {
        match(TokenType.LET);
        var id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        out.append("pop "+id).append(System.lineSeparator());        
        //System.out.println("pop "+id);
        match(TokenType.SEMICOLON);
    }

    void printStatement () {
        match(TokenType.PRINT);
        expr();
        out.append("print").append(System.lineSeparator()); 
        //System.out.println("print");
        match(TokenType.SEMICOLON);
    }

    void statement () {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    void statements () {  
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    String output () {
        return out.toString();
    }
}