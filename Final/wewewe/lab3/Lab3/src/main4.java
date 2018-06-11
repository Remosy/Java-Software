public class main4 {
    public static void main(String[] args) throws ParseException {
        MathTokenizer mathTokenizer = new MathTokenizer("     ((130-10   )  /-   2)   ");
        while (mathTokenizer.hasNext()){
            mathTokenizer.next();
            if(mathTokenizer.current()!= null)
            System.out.println(mathTokenizer.current());
        }
        MathTokenizer mathTokenizer1 = new MathTokenizer("((1+1)+(1/1))");
        Expression exp1 = Parser.parse(mathTokenizer1);
        System.out.println(exp1.evaluate());
        System.out.println(exp1.show());
    }
}
