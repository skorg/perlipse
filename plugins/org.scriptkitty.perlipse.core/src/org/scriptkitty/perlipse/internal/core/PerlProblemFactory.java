package org.scriptkitty.perlipse.internal.core;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.dltk.compiler.problem.DefaultProblemFactory;
import org.eclipse.dltk.compiler.problem.DefaultProblemSeverityTranslator;
import org.eclipse.dltk.compiler.problem.IProblem;
import org.eclipse.dltk.compiler.problem.IProblemSeverityTranslator;
import org.eclipse.dltk.core.IScriptProject;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlProblemFactory extends DefaultProblemFactory
{
    //~ Static fields/initializers

    private static final String PROBLEM = PerlCoreConstants.PLUGIN_ID + ".perlBuild";

    private static final String TASK = PerlCoreConstants.PLUGIN_ID + ".perlTask";

    //~ Methods

    /*
     * @see org.eclipse.dltk.compiler.problem.DefaultProblemFactory#createMarker(org.eclipse .core.resources.IResource,
     * org.eclipse.dltk.compiler.problem.IProblem)
     */
    @Override public IMarker createMarker(IResource resource, IProblem problem) throws CoreException
    {
        /*
         * TODO: set marker attributes
         * 
         * check if PerlBuildProblem and pull out compiler error docs
         */
        return super.createMarker(resource, problem);
    }

    /*
     * @see org.eclipse.dltk.compiler.problem.DefaultProblemFactory#createSeverityTranslator
     * (org.eclipse.dltk.core.IScriptProject)
     */
    @Override public IProblemSeverityTranslator createSeverityTranslator(IScriptProject project)
    {
        return new DefaultProblemSeverityTranslator(project);
    }

    /*
     * @see org.eclipse.dltk.compiler.problem.DefaultProblemFactory#getProblemMarkerType()
     */
    @Override protected String getProblemMarkerType()
    {
        return PROBLEM;
    }

    /*
     * @see org.eclipse.dltk.compiler.problem.DefaultProblemFactory#getTaskMarkerType()
     */
    @Override protected String getTaskMarkerType()
    {
        return TASK;
    }

}
