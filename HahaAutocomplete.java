import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.util.*;

public class HahaAutocomplete {
    public static void main(String[] args) throws IOException {
        // ANTLRInputStream input = new ANTLRInputStream(System.in);
        // CharStream codePointCharStream = CharStreams.fromFileName("myfile.testlang");
        CharStream charStream = CharStreams.fromString("select name,age from employees;");

        HahaSqlLexer lexer = new HahaSqlLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HahaSqlParser parser = new HahaSqlParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new VerboseListener());
        parser.addParseListener(new TokenListener(11));

        parser.prog();
        System.out.println("============");
        List<Token> tokenList = tokens.getTokens();

        for (int i = 0; i < tokenList.size(); i++) {
            System.out.println("token : " + tokenList.get(i).getText() + "  type = "
                    + lexer.getVocabulary().getSymbolicName(tokenList.get(i).getType()));
        }

    }
}
