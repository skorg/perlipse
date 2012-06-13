package org.scriptkitty.perlipse.ast;

public class PerlForeachStatement extends PerlLoopStatement
{
    private static final String KEYWORD = "foreach";

    public PerlForeachStatement(int start)
    {
        super(start, 0);
    }

    @Override public int getKind()
    {
        return S_FOREACH;
    }

    @Override protected String getKeyword()
    {
        return KEYWORD;
    }
}
