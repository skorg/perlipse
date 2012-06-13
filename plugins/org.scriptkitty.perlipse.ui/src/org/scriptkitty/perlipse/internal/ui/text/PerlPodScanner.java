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
 * perl pod syntax highlighting scanner
 */
public class PerlPodScanner extends AbstractScriptScanner
{
    private static String[] colors =
    {
        PerlColorConstants.PERL_PODDOC,
        PerlColorConstants.PERL_PODDOC_TAG,
    };

    public PerlPodScanner(IColorManager manager, IPreferenceStore store)
    {
        super(manager, store);
        initialize();
    }

    @Override protected List<IRule> createRules()
    {
        List<IRule> rules = new ArrayList<IRule>();

        IToken podTag = getToken(PerlColorConstants.PERL_PODDOC_TAG);
        rules.add(PerlSyntaxRulesFactory.createPodTagSyntaxRule(podTag));

        IToken token = getToken(PerlColorConstants.PERL_PODDOC);
        setDefaultReturnToken(token);

        return rules;
    }

    @Override protected String[] getTokenProperties()
    {
        return colors;
    }
}
