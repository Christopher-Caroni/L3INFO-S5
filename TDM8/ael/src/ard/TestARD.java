package ard;

import java.io.StringReader;
import java.util.Scanner;

public class TestARD {

    public static void main(String[] args) throws SyntaxException, ParserException {
        Scanner input = new Scanner(System.in);
        System.out.print("mot ? > ");
        while (input.hasNextLine()) {
            String word = input.nextLine();
            ArdNew parser = new ArdNew(new StringReader(word));
            try {
                parser.parse();
                System.out.println("OK:" + parser.getExpandedExpression());
            } catch (SyntaxException e) {
                System.out.println("Erreur : " + e.getMessage());
                //throw e;
            }
            System.out.print("mot ? > ");
        }
        input.close();
    }

}
