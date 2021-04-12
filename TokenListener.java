import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.atn.Transition;

public class TokenListener extends HahaSqlBaseListener {
    int position = 0;
    HahaSqlParser parser = null;

    public TokenListener(int position, HahaSqlParser parser) {
        super();
        this.position = position;
        this.parser = parser;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        String type = parser.getVocabulary().getSymbolicName(node.getSymbol().getType());
        System.out.println("parser.getState(): " + parser.getState() + "    node.getSymbol(): " + node.getSymbol()
                + "    " + parser.getRuleNames()[parser.getRuleContext().getRuleIndex()] + "    " + type);
        Transition[] transitions = parser.getATN().states.get(parser.getState()).getTransitions();
        for (Transition transition : transitions) {
            System.out.println("next state: " + transition.target.stateNumber);

        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        System.out.println("visitErrorNode");
        System.out.println(node.getText());
        System.out.println(parser.getState());

    }

}
