package org.scriptkitty.perlipse.internal.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import org.osgi.framework.BundleContext;



import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.text.PerlTextTools;
import org.scriptkitty.perlipse.ui.PerlPreferenceConstants;


public class PerlUIPlugin extends AbstractUIPlugin
{
    //~ Static fields/initializers

    public static final String PLUGIN_ID = "org.scriptkitty.perlipse.ui";

    public static final boolean PRINT_CHANGED_PARTITIONS = Boolean.valueOf(Platform.getDebugOption(
        "org.scriptkitty.perlipse.ui/printChangedPartitions")).booleanValue();

    public static final boolean PRINT_CONNECT_PARTITIONS = Boolean.valueOf(Platform.getDebugOption(
        "org.scriptkitty.perlipse.ui/printConnectPartitions")).booleanValue();

    private static PerlUIPlugin plugin;

    //~ Instance fields

    private PerlTextTools textTools;

    //~ Constructors

    public PerlUIPlugin()
    {
        plugin = this;
    }

    //~ Methods

    public static IPreferenceStore getCorePreferences()
    {
        return new ScopedPreferenceStore(DefaultScope.INSTANCE, PerlCoreConstants.PLUGIN_ID);
    }

    public static ImageDescriptor getImageDescriptor(String path)
    {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    public static IInterpreterInstall getInterpreter()
    {
        IPreferenceStore store = PerlUIPlugin.getPlugin().getPreferenceStore();
        String id = store.getString(PerlPreferenceConstants.INTERNAL_EDITOR_INTERPRETER);

        return ScriptRuntime.getInterpreterInstall(id, PerlCoreConstants.NATURE_ID);
    }

    public static PerlUIPlugin getPlugin()
    {
        return plugin;
    }

    public synchronized PerlTextTools getTextTools()
    {
        if (textTools == null)
        {
            textTools = new PerlTextTools(true);
        }

        return textTools;
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
