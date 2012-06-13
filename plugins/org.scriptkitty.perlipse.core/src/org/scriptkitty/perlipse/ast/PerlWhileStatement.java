package org.scriptkitty.perlipse.ast;

public class PerlWhileStatement extends PerlLoopStatement
{
    private static final String KEYWORD = "while";

    public PerlWhileStatement(int start)
    {
        super(start, 0);
    }

    @Override public int getKind()
    {
        return S_WHILE;
    }

    @Override protected String getKeyword()
    {
        return KEYWORD;
    }
}
