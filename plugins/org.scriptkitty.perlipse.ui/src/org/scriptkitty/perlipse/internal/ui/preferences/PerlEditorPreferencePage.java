package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.EditorConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


/**
 * perl editor preference page
 */
public class PerlEditorPreferencePage extends AbstractConfigurationBlockPreferencePage
{
    @Override protected IPreferenceConfigurationBlock createConfigurationBlock(
        OverlayPreferenceStore overlayPreferenceStore)
    {
        return new EditorConfigurationBlock(this, overlayPreferenceStore);
    }

    @Override protected String getHelpId()
    {
        return null;
    }

    @Override protected void setDescription()
    {
        setDescription(PerlPreferenceMessages.preferencePage_editor);
    }

    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getPlugin().getPreferenceStore());
    }

}
