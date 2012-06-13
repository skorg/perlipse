package org.scriptkitty.perlipse.internal.debug.ui.preferences;

import org.eclipse.dltk.internal.debug.ui.interpreters.InternalScriptInterpreterPreferenceBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;



import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;
import org.scriptkitty.perlipse.ui.PerlPreferenceConstants;


public class InternalInterpreterPreferencePage extends AbstractConfigurationBlockPreferencePage
{
    @Override protected IPreferenceConfigurationBlock createConfigurationBlock(
        OverlayPreferenceStore overlayPreferenceStore)
    {
        return new InternalScriptInterpreterPreferenceBlock(overlayPreferenceStore, this)
        {
            @Override protected String getNatureId()
            {
                return PerlCoreConstants.NATURE_ID;
            }

            @Override protected String getPreferenceKey()
            {
                return PerlPreferenceConstants.INTERNAL_EDITOR_INTERPRETER;
            }

            @Override protected String getSelectorGroupLabel()
            {
                return "Group";
            }

            @Override protected String getSelectorNameLabel()
            {
                return "Name";
            }
        };
    }

    @Override protected String getHelpId()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage#setDescription()
     */
    @Override protected void setDescription()
    {
        setDescription("Description");
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage#setPreferenceStore()
     */
    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getPlugin().getPreferenceStore());
    }
}
