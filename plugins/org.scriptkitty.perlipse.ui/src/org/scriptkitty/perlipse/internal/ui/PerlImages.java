package org.scriptkitty.perlipse.internal.ui;

import org.eclipse.core.runtime.Path;

import org.eclipse.dltk.ui.PluginImagesHelper;

import org.eclipse.jface.resource.ImageDescriptor;


public class PerlImages
{
    private static final PluginImagesHelper helper = new PluginImagesHelper(
        PerlUIPlugin.getPlugin().getBundle(), new Path("/icons"));

    public static final ImageDescriptor PROJECT_DECORATOR = helper.createUnManaged(PluginImagesHelper.T_OVR,
        "perl_ovr.png");

    public static final ImageDescriptor DESC_WIZBAN_PROJECT_CREATION = helper.createUnManaged(
        PluginImagesHelper.T_WIZBAN, "project_wiz.png");
}
