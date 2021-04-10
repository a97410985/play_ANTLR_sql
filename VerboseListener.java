import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.Transition;

import java.util.*;

public class VerboseListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        System.err.println("rule stack: " + stack);
        System.err.println("line" + line + ":" + charPositionInLine + " at" + offendingSymbol + ": " + msg);

        System.err.println("e : " + e);

        if (e != null) {
            System.err.println("e.getCtx().getText() : " + e.getCtx().getText());

            System.err.println("e.getExpectedTokens() :");

            var tokenInternalSet = e.getExpectedTokens();

            for (int i = 0; i < tokenInternalSet.size(); i++) {
                var typeNum = tokenInternalSet.get(i);
                System.err.println("getLiteralName : " + ((Parser) recognizer).getVocabulary().getLiteralName(typeNum));
                System.err.println("token's type : " + ((Parser) recognizer).getVocabulary().getSymbolicName(typeNum));
            }

        }
    }
}
