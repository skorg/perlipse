package org.scriptkitty.perlipse.internal.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import org.osgi.framework.BundleContext;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlCorePlugin extends Plugin
{
    public static final String PLUGIN_ID = PerlCoreConstants.PLUGIN_ID;

    public static final boolean ALLOW_HALF_PARSED = Boolean
            .valueOf(Platform.getDebugOption(PLUGIN_ID + "/allowHalfParsed")).booleanValue();
    
    private static PerlCorePlugin plugin;

    public PerlCorePlugin()
    {
        plugin = this;
    }

    public static void error(String message)
    {
        error(message, null);
    }

    public static void error(String message, Throwable t)
    {
        if (plugin == null)
        {
            System.err.println(message);
            t.printStackTrace();
        }
        else
        {
            plugin.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, t));
        }
    }

    public static PerlCorePlugin getPlugin()
    {
        return plugin;
    }

    public static IEclipsePreferences getPreferences()
    {
        return DefaultScope.INSTANCE.getNode(PerlCoreConstants.PLUGIN_ID);
    }

    public static void warn(String message)
    {
        warn(message, null);
    }

    public static void warn(String message, Throwable t)
    {
        plugin.getLog().log(new Status(IStatus.WARNING, PLUGIN_ID, IStatus.OK, message, t));
    }

    @Override public void start(BundleContext context) throws Exception
    {
        super.start(context);
    }

    @Override public void stop(BundleContext context) throws Exception
    {
        plugin = null;
        getPreferences().flush();
        super.stop(context);
    }
}
