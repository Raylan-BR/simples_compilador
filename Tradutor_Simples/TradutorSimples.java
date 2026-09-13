package Tradutor_Simples;

public class TradutorSimples {
    public static void main(String[] args) throws Exception {
        String input = "8+5-7+9";
        Parser p = new Parser (input.getBytes());
        p.parse();

    }
}