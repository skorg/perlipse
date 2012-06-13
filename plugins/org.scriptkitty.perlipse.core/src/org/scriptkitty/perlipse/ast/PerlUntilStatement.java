package org.scriptkitty.perlipse.ast;

public class PerlUntilStatement extends PerlLoopStatement
{
    private static final String KEYWORD = "until";

    public PerlUntilStatement(int start)
    {
        super(start, 0);
    }

    @Override public int getKind()
    {
        return S_UNTIL;
    }

    @Override protected String getKeyword()
    {
        return KEYWORD;
    }
}
