package org.scriptkitty.perlipse.internal.activestate;

import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.osgi.framework.BundleContext;


/**
 */
public class PerlActiveStateDebuggerPlugin extends AbstractUIPlugin
{
    //~ Static fields/initializers

    public static final String PLUGIN_ID = "org.scriptkitty.perlipse.debug.activestate";

    private static PerlActiveStateDebuggerPlugin plugin;

    //~ Constructors

    public PerlActiveStateDebuggerPlugin()
    {
        // empty
    }

    //~ Methods

    public static PerlActiveStateDebuggerPlugin getDefault()
    {
        return plugin;
    }

    /*
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override public void start(BundleContext context) throws Exception
    {
        super.start(context);
        plugin = this;
    }

    /*
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override public void stop(BundleContext context) throws Exception
    {
        plugin = null;
        super.stop(context);
    }
}
