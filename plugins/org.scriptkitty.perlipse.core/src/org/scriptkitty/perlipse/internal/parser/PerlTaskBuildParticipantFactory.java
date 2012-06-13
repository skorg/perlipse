package org.scriptkitty.perlipse.internal.parser;

import org.eclipse.dltk.compiler.task.ITodoTaskPreferences;
import org.eclipse.dltk.compiler.task.TodoTaskPreferencesOnPreferenceLookupDelegate;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.AbstractTodoTaskBuildParticipantType;

import org.scriptkitty.perlipse.internal.core.PerlCorePlugin;


public class PerlTaskBuildParticipantFactory extends AbstractTodoTaskBuildParticipantType
{
    //~ Methods

    @Override protected ITodoTaskPreferences getPreferences(IScriptProject project)
    {
        return new TodoTaskPreferencesOnPreferenceLookupDelegate(PerlCorePlugin.PLUGIN_ID, project);
    }
}
