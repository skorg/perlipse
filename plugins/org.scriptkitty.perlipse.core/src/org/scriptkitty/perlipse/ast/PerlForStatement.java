package org.scriptkitty.perlipse.ast;

public class PerlForStatement extends PerlLoopStatement
{
    private static final String KEYWORD = "for";

    public PerlForStatement(int start)
    {
        super(start, 0);
    }

    @Override public int getKind()
    {
        return S_FOR;
    }

    @Override protected String getKeyword()
    {
        return KEYWORD;
    }
}
