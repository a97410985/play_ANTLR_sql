import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.ParserRuleContext;
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
        // System.out.println("TokenListener : " + node.getText() + " start position : "
        // + node.getSymbol().getStartIndex()
        // + " stop position : " + node.getSymbol().getStopIndex());
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

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        String exceptionMsg = "";
        if (ctx.exception != null) {
            exceptionMsg = ctx.exception.getMessage();
        }

        // System.out.println(
        // "< exitEveryRule - ctx.getText() :" + ctx.getText() + " ctx.invokingState : "
        // + ctx.invokingState
        // + " ctx.getRuleIndex() :" + ctx.getRuleIndex() + "
        // ctx.getStart().getTokenIndex():"
        // + ctx.getStart().getTokenIndex() + " ctx.getStop().getTokenIndex():"
        // + ctx.getStop().getTokenIndex() + " ctx.exception.getMessage()" +
        // exceptionMsg + " >");

        // System.out.println("< ctx.invokingState : " + ctx.invokingState + "
        // ctx.getRuleIndex() :"
        // + ctx.getRuleIndex() + " ctx.getStart().getTokenIndex():" +
        // ctx.getStart().getTokenIndex()
        // + " ctx.getStop().getTokenIndex():" + ctx.getStop().getTokenIndex()
        // + " ctx.exception.getMessage()" + exceptionMsg + " >");

        // System.out.println("parser.getState():" + parser.getState() + "
        // parser.getContext().getText(): "
        // + parser.getContext().getText() + "
        // parser.getRuleContext().getStart().getStartIndex() : "
        // + parser.getRuleContext().getStart().getStartIndex()
        // + " parser.getRuleNames()[parser.getContext().getRuleIndex()]: "
        // + parser.getRuleNames()[parser.getContext().getRuleIndex()]);

    }

    @Override
    public void exitSelectStmt(HahaSqlParser.SelectStmtContext ctx) {

        // if (ctx.TABLE != null && ctx.TABLE.getStartIndex() < this.position
        // && ctx.TABLE.getStopIndex() + 1 >= this.position) {
        // // System.out.println("cursor in the TABLE");
        // }
    }

    @Override
    public void exitColumnList(HahaSqlParser.ColumnListContext ctx) {
        for (int i = 0; i < ctx.columnRef().size(); i++) {
            // System.out.println("ctx.columnRef().get(i).getText():" +
            // ctx.columnRef().get(i).getText()
            // + " getStartIndex():" + ctx.columnRef().get(i).getStart().getStartIndex() + "
            // getStopIndex():"
            // + ctx.columnRef().get(i).getStart().getStopIndex());
        }

        // ctx.getTokens(ctx.NAME(0).getSymbol().getType());
        // System.out.println("===exitColumnList===");
        // for (int i = 0; i < ctx.COLUMN.size(); i++) {
        // System.out.println("ctx.COLUMN.get(i).getText() : " +
        // ctx.COLUMN.get(i).getText());
        // }
        // System.out.println("====================");
    }

}
