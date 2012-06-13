package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.core.resources.IProject;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractTodoTaskOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;

import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


/**
 * preference/property page for 'todo' task tags
 */
public class PerlTodoTaskPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage
{
    //~ Static fields/initializers

    private static final PreferenceKey CASE_SENSITIVE = AbstractTodoTaskOptionsBlock.createCaseSensitiveKey(
        PerlCoreConstants.PLUGIN_ID);

    private static final PreferenceKey ENABLED = AbstractTodoTaskOptionsBlock.createEnabledKey(PerlCoreConstants.PLUGIN_ID);

    private static final PreferenceKey TAGS = AbstractTodoTaskOptionsBlock.createTagKey(PerlCoreConstants.PLUGIN_ID);

    //~ Methods

    @Override protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
        IProject project, IWorkbenchPreferenceContainer container)
    {
        return new AbstractTodoTaskOptionsBlock(newStatusChangedListener, project,
            getPreferenceKeys(), container)
        {
            @Override protected PreferenceKey getCaseSensitiveKey()
            {
                return CASE_SENSITIVE;
            }

            @Override protected PreferenceKey getEnabledKey()
            {
                return ENABLED;
            }

            @Override protected PreferenceKey getTags()
            {
                return TAGS;
            }
        };
    }

    @Override protected String getHelpId()
    {
        return null;
    }

    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    protected PreferenceKey[] getPreferenceKeys()
    {
        return new PreferenceKey[] { TAGS, ENABLED, CASE_SENSITIVE };
    }

    @Override protected String getPreferencePageId()
    {
        return "org.scriptkitty.perlipse.ui.preferences.PerlTodoTaskPreferencePage";
    }

    @Override protected String getProjectHelpId()
    {
        return null;
    }

    @Override protected String getPropertyPageId()
    {
        return "org.scriptkitty.perlipse.ui.propertyPage.PerlTodoTaskPreferencePage";
    }

    @Override protected void setDescription()
    {
        setDescription(PerlPreferenceMessages.todoTaskPage_description);
    }

    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getCorePreferences());
    }
}
