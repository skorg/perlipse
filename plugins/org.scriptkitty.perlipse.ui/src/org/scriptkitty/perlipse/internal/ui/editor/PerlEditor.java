package org.scriptkitty.perlipse.internal.ui.editor;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.actions.FoldingActionGroup;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.folding.IFoldingStructureProvider;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;

import org.eclipse.ui.IEditorInput;



import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.core.PerlLanguageToolkit;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;
import org.scriptkitty.perlipse.internal.ui.text.PerlFoldingStructureProvider;
import org.scriptkitty.perlipse.internal.ui.text.PerlPartitionConstants;
import org.scriptkitty.perlipse.ui.PerlUIConstants;


/**
 */
public class PerlEditor extends ScriptEditor
{
    //~ Methods

    @Override public String getCallHierarchyID()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#getEditorId()
     */
    @Override public String getEditorId()
    {
        return PerlUIConstants.EDITOR_ID;
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#getLanguageToolkit()
     */
    @Override public IDLTKLanguageToolkit getLanguageToolkit()
    {
        return PerlLanguageToolkit.getInstance();
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#getScriptPreferenceStore()
     */
    @Override public IPreferenceStore getScriptPreferenceStore()
    {
        return PerlUIPlugin.getPlugin().getPreferenceStore();
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#getTextTools()
     */
    @Override public ScriptTextTools getTextTools()
    {
        return PerlUIPlugin.getPlugin().getTextTools();
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#connectPartitioningToElement(org.eclipse.ui.IEditorInput,
     * org.eclipse.jface.text.IDocument)
     */
    @Override protected void connectPartitioningToElement(IEditorInput input, IDocument document)
    {
        final String partitioning = PerlPartitionConstants.PERL_PARTITIONING;
        if (document instanceof IDocumentExtension3)
        {
            IDocumentExtension3 extension = (IDocumentExtension3) document;
            if (extension.getDocumentPartitioner(partitioning) == null)
            {
                getTextTools().setupDocumentPartitioner(document, partitioning);
            }
        }
    }

//  protected void createActions()
//  {
//      super.createActions();
//      ActionGroup generateActions = new GenerateActionGroup(this, ITextEditorActionConstants.GROUP_EDIT);
//      fActionGroups.addGroup(generateActions);
//      fContextMenuGroup.addGroup(generateActions);
//  }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#createFoldingActionGroup()
     */
    @Override protected FoldingActionGroup createFoldingActionGroup()
    {
        return new FoldingActionGroup(this, getViewer(), getScriptPreferenceStore());
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#createFoldingStructureProvider()
     */
    @Override protected IFoldingStructureProvider createFoldingStructureProvider()
    {
        return new PerlFoldingStructureProvider();
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#doCreateOutlinePage()
     */
    @Override protected ScriptOutlinePage doCreateOutlinePage()
    {
        return new PerlOutlinePage(this, getScriptPreferenceStore());
    }

    /*
     * @see org.eclipse.dltk.internal.ui.editor.ScriptEditor#initializeEditor()
     */
    @Override protected void initializeEditor()
    {
        super.initializeEditor();

        setEditorContextMenuId(PerlCoreConstants.EDITOR_CONTEXT);
        setRulerContextMenuId(PerlCoreConstants.RULER_CONTEXT);
    }

    /*
     * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
     */
    @Override protected void initializeKeyBindingScopes()
    {
        setKeyBindingScopes(new String[] { PerlCoreConstants.EDITOR_SCOPE_ID });
    }
}
