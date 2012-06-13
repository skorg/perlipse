package org.scriptkitty.perlipse.internal.ui.text;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

import org.scriptkitty.perlipse.internal.ui.text.rules.PerlSyntaxRulesFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * perl code scanner responsible for quote and regexp operator highlighting
 */
public class PerlQuoteAndRegExpScanner extends AbstractScriptScanner
{
    private String[] colors = { PerlColorConstants.PERL_QUOTE_REGEXP_OP };

    public PerlQuoteAndRegExpScanner(IColorManager manager, IPreferenceStore store)
    {
        super(manager, store);

        initialize();
    }

    @Override protected List<IRule> createRules()
    {
        List<IRule> rules = new ArrayList<IRule>();

        rules.add(PerlSyntaxRulesFactory.getWhiteSpaceRule());

        IToken token = getToken(PerlColorConstants.PERL_QUOTE_REGEXP_OP);
        setDefaultReturnToken(token);

        return rules;
    }

    @Override protected String[] getTokenProperties()
    {
        return colors;
    }
}
