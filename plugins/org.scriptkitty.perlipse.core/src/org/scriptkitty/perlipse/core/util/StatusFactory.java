package org.scriptkitty.perlipse.core.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Convience class to create {@link IStatus IStatus} objects used for logging
 *
 * <p>Any method defined in this class that does not specify a <code>status</code> parameter
 * uses <code>IStatus.OK</code></p>
 */
public class StatusFactory
{

    public static IStatus createOk(String pluginId)
    {
        return createOk(pluginId, IStatus.OK);
    }

    public static IStatus createOk(String pluginId, int status)
    {
        return createStatus(IStatus.OK, pluginId, status, "", null);
    }

    public static IStatus createInfo(String pluginId, String message)
    {
        return createInfo(pluginId, IStatus.OK, message);
    }

    public static IStatus createInfo(String pluginId, int status, String message)
    {
        return createStatus(IStatus.INFO, pluginId, status, message, null);
    }

    public static IStatus createError(String pluginId, String message)
    {
        // XXX: refactor once more error status are created
        return createError(pluginId, message, null);
    }

    public static IStatus createError(String pluginId, String message, Throwable throwable)
    {
    	return createStatus(IStatus.ERROR, pluginId, 0, message, throwable);
    }
    
    private static IStatus createStatus(int severity, String pluginId, int code, String message, Throwable throwable)
    {
        /*
         * TODO: support for i8n
         *
         * this should be very easy to add here w/o any changes to the interface and - lookup
         * the message in the resource bundle based on the input message param - if it's not found,
         * default to using the message param.  -jae
         */
        return new Status(severity, pluginId, code, message, throwable);
    }

}
