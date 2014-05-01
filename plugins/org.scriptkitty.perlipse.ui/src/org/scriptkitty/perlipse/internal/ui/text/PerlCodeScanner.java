package org.scriptkitty.perlipse.internal.ui.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.scriptkitty.perl.lang.Language;
import org.scriptkitty.perlipse.internal.ui.text.rules.PerlSyntaxRulesFactory;


/**
 */
public class PerlCodeScanner extends AbstractScriptScanner
{
    private static String[] colors =
    {
        PerlColorConstants.PERL_DEFAULT_TEXT_COLOR,
        PerlColorConstants.PERL_ARRAY_VARIABLE,
        PerlColorConstants.PERL_HASH_VARIABLE,
        PerlColorConstants.PERL_SCALAR_VARIABLE,
        PerlColorConstants.PERL_BAREWORD_KEYWORDS,
        PerlColorConstants.PERL_FILEHANDLE_KEYWORDS,
        PerlColorConstants.PERL_FUNCTION_KEYWORDS,
//        PerlColorConstants.PERL_ARRAY_KEYWORDS,
//        PerlColorConstants.PERL_HASH_KEYWORDS,
//        PerlColorConstants.PERL_SCALAR_KEYWORDS,
        PerlColorConstants.PERL_RETURN_KEYWORD,
        PerlColorConstants.PERL_STRING,
        PerlColorConstants.PERL_SUB_NAME,
        PerlColorConstants.PERL_PACKAGE_NAME,
    };

    public PerlCodeScanner(IColorManager manager, IPreferenceStore store)
    {
        super(manager, store);
        initialize();
    }

    @Override protected List<IRule> createRules()
    {
        List<IRule> rules = new ArrayList<IRule>();
        
        IToken token = getToken(PerlColorConstants.PERL_DEFAULT_TEXT_COLOR);
        setDefaultReturnToken(token);
        
        // order matters!
        rules.add(PerlSyntaxRulesFactory.getWhiteSpaceRule());

        addKeywordRules(rules);
        addVariableRules(rules);

        return rules;
    }

    @Override protected String[] getTokenProperties()
    {
        return colors;
    }

    private void addKeywordRules(List<IRule> rules)
    {
        rules.add(createScalarKeywordRule());
        rules.add(createArrayKeywordRule());
        rules.add(createHashKeywordRule());

        /*
         * barewords need to come before functions, otherwise functions that are
         * also method names don't highlight correctly
         */
        rules.add(createBarewordRule());
        rules.add(createReturnRule());

        rules.add(createFunctionRule());
        rules.add(createFileHandleRule());
    }

    private void addVariableRules(List<IRule> rules)
    {
        rules.add(createScalarVariableRule());
        rules.add(createArrayVariableRule());
        rules.add(createHashVariableRule());
    }

    private IRule createArrayKeywordRule()
    {
        // IToken token = getToken(PerlColorConstants.PERL_ARRAY_KEYWORDS);
        IToken token = getToken(PerlColorConstants.PERL_ARRAY_VARIABLE);
        return PerlSyntaxRulesFactory.createArrayKeywordSyntaxRule(token);
    }

    private IRule createArrayVariableRule()
    {
        IToken token = getToken(PerlColorConstants.PERL_ARRAY_VARIABLE);
        return PerlSyntaxRulesFactory.createArrayVariableSyntaxRule(token);
    }

    private IRule createBarewordRule()
    {
        String pkg = Language.getPackageKeyword().toString();
        IToken pToken = getToken(PerlColorConstants.PERL_PACKAGE_NAME);

        String sub = Language.getSubKeyword().toString();
        IToken sToken = getToken(PerlColorConstants.PERL_SUB_NAME);

        Map<String, IToken> map = new HashMap<String, IToken>(2);
        map.put(pkg, pToken);
        map.put(sub, sToken);

        IToken token = getToken(PerlColorConstants.PERL_BAREWORD_KEYWORDS);
        return PerlSyntaxRulesFactory.createBarewordSyntaxRule(token, map);
    }

    private IRule createFileHandleRule()
    {
        IToken token = getToken(PerlColorConstants.PERL_FILEHANDLE_KEYWORDS);
        return PerlSyntaxRulesFactory.createFileHandleSyntaxRule(token);
    }

    private IRule createFunctionRule()
    {
        IToken token = getToken(PerlColorConstants.PERL_FUNCTION_KEYWORDS);
        return PerlSyntaxRulesFactory.createFunctionSyntaxRule(token);
    }

    private IRule createHashKeywordRule()
    {
        // IToken token = getToken(PerlColorConstants.PERL_HASH_KEYWORDS);
        IToken token = getToken(PerlColorConstants.PERL_HASH_VARIABLE);
        return PerlSyntaxRulesFactory.createHashKeywordSyntaxRule(token);
    }

    private IRule createHashVariableRule()
    {
        IToken token = getToken(PerlColorConstants.PERL_HASH_VARIABLE);
        return PerlSyntaxRulesFactory.createHashVariableSyntaxRule(token);
    }

    private IRule createReturnRule()
    {
        IToken token = getToken(PerlColorConstants.PERL_RETURN_KEYWORD);
        return PerlSyntaxRulesFactory.createReturnSyntaxRule(token);
    }

    private IRule createScalarKeywordRule()
    {
        // IToken token = getToken(PerlColorConstants.PERL_SCALAR_KEYWORDS);
        IToken token = getToken(PerlColorConstants.PERL_SCALAR_VARIABLE);
        return PerlSyntaxRulesFactory.createScalarKeywordSyntaxRule(token);
    }

    private IRule createScalarVariableRule()
    {
        IToken token = getToken(PerlColorConstants.PERL_SCALAR_VARIABLE);
        return PerlSyntaxRulesFactory.createScalarVariableSyntaxRule(token);
    }
}
