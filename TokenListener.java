import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.ParserRuleContext;

public class TokenListener extends HahaSqlBaseListener {
    int position = 0;

    public TokenListener(int position) {
        super();
        this.position = position;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        System.out.println("TokenListener : " + node.getText() + ", start position : "
                + node.getSymbol().getStartIndex() + " , stop position : " + node.getSymbol().getStopIndex());
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitSelectStmt(HahaSqlParser.SelectStmtContext ctx) {
        // if (ctx.COLUMN.getStartIndex() < this.position && ctx.COLUMN.getStopIndex() +
        // 1 >= this.position) {
        // System.out.println("cursor in the COLUMN");
        // }

        if (ctx.TABLE != null && ctx.TABLE.getStartIndex() < this.position
                && ctx.TABLE.getStopIndex() + 1 >= this.position) {
            System.out.println("cursor in the TABLE");
        }
    }

    @Override
    public void exitColumnList(HahaSqlParser.ColumnListContext ctx) {
        for (int i = 0; i < ctx.columnRef().size(); i++) {
            System.out.println("ctx.columnRef().get(i).getText():" + ctx.columnRef().get(i).getText()
                    + " getStartIndex():" + ctx.columnRef().get(i).getStart().getStartIndex() + " getStopIndex():"
                    + ctx.columnRef().get(i).getStart().getStopIndex());
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
