package org.scriptkitty.perlipse.internal.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.templates.ITemplateAccess;
import org.eclipse.dltk.ui.viewsupport.ScriptUILabelProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.core.PerlLanguageToolkit;
import org.scriptkitty.perlipse.internal.ui.templates.PerlTemplateAccess;
import org.scriptkitty.perlipse.internal.ui.text.PerlSourceViewerConfiguration;
import org.scriptkitty.perlipse.ui.PerlUIConstants;


/**
 */
public class PerlUILanguageToolkit extends AbstractDLTKUILanguageToolkit
{
    private static String FOLDER_DELIM = String.valueOf(IScriptFolder.PACKAGE_DELIMITER);
    private static String PACKAGE_DELIM = "::";

    private static ScriptElementLabels scriptElementLabels =
        new ScriptElementLabels()
        {
            @Override protected void getScriptFolderLabel(IScriptFolder folder, StringBuffer buf)
            {
                String name = folder.getElementName();
                name = name.replaceAll(FOLDER_DELIM, PACKAGE_DELIM);
                buf.append(name);
            }
        };

    @Override public ScriptUILabelProvider createScriptUILabelProvider()
    {
        // TODO create label provider implementation
        return null;
    }

    @Override public ScriptSourceViewerConfiguration createSourceViewerConfiguration()
    {
        return new PerlSourceViewerConfiguration.SimplePerlSourceViewerConfiguration(
                getTextTools().getColorManager(), getPreferenceStore(), null, getPartitioningId(),
                false);
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getCoreToolkit()
     */
    public IDLTKLanguageToolkit getCoreToolkit()
    {
        return PerlLanguageToolkit.getInstance();
    }

    /*
     * @see org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit#getDebugPreferencePage()
     */
    @Override public String getDebugPreferencePage()
    {
        return PerlCoreConstants.DEBUG_PREFERENCE_PAGE_ID;
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getEditorId(java.lang.Object)
     */
    @Override public String getEditorId(Object inputElement)
    {
        // TODO: this should probably check the input element :)
        return PerlCoreConstants.EDITOR_ID;
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getEditorPreferencePages()
     */
    @Override public String[] getEditorPreferencePages()
    {
        return PerlUIConstants.EDITOR_PREFERNEC_PAGE_IDS;
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getInterpreterContainerId()
     */
    @Override public String getInterpreterContainerId()
    {
        return PerlCoreConstants.INTERPRETER_CONTAINER_ID;
    }

    @Override public String getInterpreterPreferencePage()
    {
        return PerlCoreConstants.INTERPRETER_PREF_PAGE_ID;
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getPartitioningId()
     */
    @Override public String getPartitioningId()
    {
        return PerlCoreConstants.PARTITIONING_ID;
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getPreferenceStore()
     */
    public IPreferenceStore getPreferenceStore()
    {
        return PerlUIPlugin.getPlugin().getPreferenceStore();
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getProvideMembers(org.eclipse.dltk.core.ISourceModule)
     */
    @Override public boolean getProvideMembers(ISourceModule element)
    {
        // TODO: what does getProvideMembers() do?
        return true;
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getScriptElementLabels()
     */
    @Override public ScriptElementLabels getScriptElementLabels()
    {
        return scriptElementLabels;
    }

    /*
     * @see org.eclipse.dltk.ui.IDLTKUILanguageToolkit#getTextTools()
     */
    @Override public ScriptTextTools getTextTools()
    {
        return PerlUIPlugin.getPlugin().getTextTools();
    }

    /*
     * @see org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit#getEditorTemplates()
     */
    @Override public ITemplateAccess getEditorTemplates()
    {
        return PerlTemplateAccess.getInstance();
    }
}
