import java.io.IOException;

import org.antlr.v4.parse.ANTLRParser.parserRule_return;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.RuleStartState;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.misc.IntervalSet;

public class HahaAutocomplete {
    public static void main(String[] args) throws IOException {
        // CharStream charStream = CharStreams.fromString("select name, age, max(salary)
        // from employees;");
        CharStream charStream = CharStreams.fromString("select name, age,  from employees;");

        HahaSqlLexer lexer = new HahaSqlLexer(charStream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HahaSqlParser parser = new HahaSqlParser(tokens);
        parser.removeErrorListeners();
        parser.addParseListener(new TokenListener(11, parser));
        parser.addErrorListener(new VerboseListener());
        parser.prog();

        System.out.println("======");
        HahaSqlParser.ProgContext ctx = new HahaSqlParser.ProgContext(parser.getContext(), parser.getState());
        parser.setContext(ctx);
        int curState = 23;
        parser.setState(curState);
        String ruleName = parser.getRuleNames()[parser.getRuleContext().getRuleIndex()];
        IntervalSet expetectedTokens = parser.getExpectedTokens();

        System.out.println("cur rule name: " + ruleName);
        for (int i = 0; i < expetectedTokens.size(); i++) {
            System.out.println(parser.getVocabulary().getDisplayName(expetectedTokens.get(i)));
        }

        Transition[] transitions = parser.getATN().states.get(curState).getTransitions();
        for (Transition transition : transitions) {
            System.out.println("rule: " + parser.getRuleNames()[transition.target.ruleIndex]);
        }
    }
}
