package org.scriptkitty.perlipse.internal.parser;

import org.eclipse.dltk.core.DLTKIdContributionSelector;
import org.eclipse.dltk.core.PreferencesLookupDelegate;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.core.PerlCorePlugin;


/**
 * perl source parser selector
 */
public class PerlSourceParserSelector extends DLTKIdContributionSelector
{
    /*
     * @see
     * org.eclipse.dltk.core.DLTKIdContributionSelector#getSavedContributionId(org.eclipse.dltk.core.PreferencesLookupDelegate)
     */
    @Override protected String getSavedContributionId(PreferencesLookupDelegate delegate)
    {
        return delegate.getString(PerlCorePlugin.PLUGIN_ID, PerlCoreConstants.SOURCE_PARSER);
    }
}
