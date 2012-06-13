package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.core.resources.IProject;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


public class PerlCompilerPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage
{
    //~ Methods

    @Override protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
        IProject project, IWorkbenchPreferenceContainer container)
    {
        return new AbstractOptionsBlock(newStatusChangedListener, project, getKeys(), container)
        {
            @Override protected Control createOptionsBlock(Composite parent)
            {
                return parent;
            }
        };
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
     */
    @Override protected String getHelpId()
    {
        return null;
    }

    /*
     * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getNatureId()
     */
    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    /*
     * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPreferencePageId()
     */
    @Override protected String getPreferencePageId()
    {
        return "org.scriptkitty.perlipse.ui.preferences.PerlipseCompilerPreferencePage";
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getProjectHelpId()
     */
    @Override protected String getProjectHelpId()
    {
        return null;
    }

    /*
     * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPropertyPageId()
     */
    @Override protected String getPropertyPageId()
    {
        return "org.scriptkitty.perlipse.ui.propertyPage.PerlCompilerPreferencePage";
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setDescription()
     */
    @Override protected void setDescription()
    {
        // no description
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setPreferenceStore()
     */
    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getCorePreferences());
    }

    private static PreferenceKey[] getKeys()
    {
        return new PreferenceKey[] {};
    }
}
