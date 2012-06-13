package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


public class PerlGeneralPreferencePage extends AbstractConfigurationBlockPreferencePage
{
    /*
     * @see
     * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage#createConfigurationBlock(org.eclipse.dltk.ui.preferences.OverlayPreferenceStore)
     */
    @Override protected IPreferenceConfigurationBlock createConfigurationBlock(
        OverlayPreferenceStore overlayPreferenceStore)
    {
        return new AbstractConfigurationBlock(overlayPreferenceStore, this)
        {
            public Control createControl(Composite parent)
            {
                Composite composite = new Composite(parent, SWT.NONE);
                composite.setLayout(new GridLayout());

                return composite;
            }
        };
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage#getHelpId()
     */
    @Override protected String getHelpId()
    {
        // TODO: implement help
        return "";
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage#setDescription()
     */
    @Override protected void setDescription()
    {
        setDescription("Perlipse Global Preferences");
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage#setPreferenceStore()
     */
    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getPlugin().getPreferenceStore());
    }
}
