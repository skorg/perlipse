package org.scriptkitty.perlipse.core.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.ScriptRuntime;

public abstract class PerlEnvironmentUtils {

	private static char SEPARATOR = Platform.getOS().equals(Platform.OS_WIN32) ? ';' : ':';
	
    public static String createBuildPath(IScriptProject project) throws CoreException
    {
        IPath[] paths = getBuildPath(project);
        return createBuildPath(paths, project.getProject());
    }
	
	public static String createBuildPath(IPath[] paths, IProject project) 
	{
		String projectPath = "";
		if (project != null) 
		{
			projectPath = project.getFullPath().toOSString();
		}
		
		boolean addedFlag = false;
		
		StringBuffer buffer = new StringBuffer();
		
        if (paths.length > 0) 
        {
        	int i = 0;        	
        	do
        	{
        		if (paths[i].toOSString().endsWith(projectPath))
        		{
        			continue;
        		}
        			
        		if (!addedFlag)
        		{
        			buffer.append("-I");
        			addedFlag = true;
        		}

        		buffer.append(paths[i].makeAbsolute().toOSString());
        		buffer.append(SEPARATOR);
        	} while (++i < paths.length);
        
        	if (buffer.length() > 0)
        	{
        		buffer.deleteCharAt(buffer.length() - 1);
        	}
        }

        return buffer.toString();
	}
	
    private static IPath[] getBuildPath(IScriptProject project) throws CoreException {
        List<IPath> paths = new ArrayList<IPath>();       

        IRuntimeBuildpathEntry[] buildPaths = ScriptRuntime.computeUnresolvedRuntimeBuildpath(project);
        
        for (int i = 0; i < buildPaths.length; i++)
        {
            IRuntimeBuildpathEntry[] resolved = ScriptRuntime.resolveRuntimeBuildpathEntry(buildPaths[i], project);
            for (int j = 0; j < resolved.length; j++)
            {
                paths.add(new Path(resolved[j].getLocation()));
            }
        }

        return paths.toArray(new IPath[paths.size()]);
    }
}
