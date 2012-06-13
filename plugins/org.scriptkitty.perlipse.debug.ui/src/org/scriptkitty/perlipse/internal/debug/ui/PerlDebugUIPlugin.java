package org.scriptkitty.perlipse.internal.debug.ui;

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;
import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlDebugUIPlugin extends AbstractUIPlugin
{
    public static final String PLUGIN_ID = "org.scriptkitty.perlipse.debug.ui";

    private static PerlDebugUIPlugin plugin;

    public PerlDebugUIPlugin()
    {
        plugin = this;
    }

    public static IPreferenceStore getCorePreferences()
    {
        return new ScopedPreferenceStore(DefaultScope.INSTANCE, PerlCoreConstants.PLUGIN_ID);
    }

    public static PerlDebugUIPlugin getPlugin()
    {
        return plugin;
    }

    @Override public void start(BundleContext context) throws Exception
    {
        super.start(context);
    }

    @Override public void stop(BundleContext context) throws Exception
    {
        plugin = null;
        super.stop(context);
    }

}
