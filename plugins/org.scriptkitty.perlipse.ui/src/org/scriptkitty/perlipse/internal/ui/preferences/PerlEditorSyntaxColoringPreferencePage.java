package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.preferences.PreferencesMessages;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.texteditor.ITextEditor;

import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;
import org.scriptkitty.perlipse.internal.ui.text.PerlColorConstants;
import org.scriptkitty.perlipse.internal.ui.text.PerlPartitionConstants;
import org.scriptkitty.perlipse.internal.ui.text.PerlSourceViewerConfiguration.SimplePerlSourceViewerConfiguration;

import java.io.InputStream;


/**
 */
public class PerlEditorSyntaxColoringPreferencePage extends AbstractConfigurationBlockPreferencePage
{
    @Override protected IPreferenceConfigurationBlock createConfigurationBlock(
        OverlayPreferenceStore overlayPreferenceStore)
    {
        return new PerlEditorColoringConfigurationBlock(overlayPreferenceStore);
    }

    @Override protected String getHelpId()
    {
        return null;
    }

    @Override protected void setDescription()
    {
        // leave blank
    }

    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getPlugin().getPreferenceStore());
    }

    /**
     */
    private static class PerlEditorColoringConfigurationBlock
        extends AbstractScriptEditorColoringConfigurationBlock
    {
        private static String PREVIEW = "previewFile.txt";

        private static final String[][] syntaxColorListModel =
            new String[][]
            {
                {
                    PreferencesMessages.DLTKEditorPreferencePage_returnKeyword,
                    PerlColorConstants.PERL_RETURN_KEYWORD,
                    sCoreCategory
                },
                {
                    PreferencesMessages.DLTKEditorPreferencePage_strings,
                    PerlColorConstants.PERL_STRING, 
                    sCoreCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorBackticks,
                    PerlColorConstants.PERL_BACKTICK,
                    sCoreCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorPod,
                    PerlColorConstants.PERL_PODDOC,
                    sDocumentationCategory,
                },
                {
                    PerlPreferenceMessages.syntaxColorPodTag,
                    PerlColorConstants.PERL_PODDOC_TAG,
                    sDocumentationCategory,
                },
                {
                    PreferencesMessages.DLTKEditorPreferencePage_singleLineComment,
                    PerlColorConstants.PERL_LINE_COMMENT,
                    sCommentsCategory
                },
                {
                    PreferencesMessages.DLTKEditorPreferencePage_CommentTaskTags,
                    PerlColorConstants.PERL_TODO_TAG,
                    sCommentsCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorBarewordKeywords,
                    PerlColorConstants.PERL_BAREWORD_KEYWORDS,
                    sCoreCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorFileHandleKeywords,
                    PerlColorConstants.PERL_FILEHANDLE_KEYWORDS,
                    sCoreCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorFunctionKeywords,
                    PerlColorConstants.PERL_FUNCTION_KEYWORDS,
                    sCoreCategory
                },
                //                 {
                //                 PerlPreferenceMessages.syntaxColorArrayKeywords,
                //                 PerlColorConstants.PERL_ARRAY_KEYWORDS,
                //                 sCoreCategory
                //                 },
                //                 {
                //                 PerlPreferenceMessages.syntaxColorHashKeywords,
                //                 PerlColorConstants.PERL_HASH_KEYWORDS,
                //                 sCoreCategory
                //                 },
                //                 {
                //                 PerlPreferenceMessages.syntaxColorScalarKeywords,
                //                 PerlColorConstants.PERL_SCALAR_KEYWORDS,
                //                 sCoreCategory
                //                 },
                {
                    PerlPreferenceMessages.syntaxColorArrayVariable,
                    PerlColorConstants.PERL_ARRAY_VARIABLE,
                    sCoreCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorHashVariable,
                    PerlColorConstants.PERL_HASH_VARIABLE, sCoreCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorScalarVariable,
                    PerlColorConstants.PERL_SCALAR_VARIABLE,
                    sCoreCategory
                },
                {
                    PerlPreferenceMessages.syntaxColorPackageName,
                    PerlColorConstants.PERL_PACKAGE_NAME,
                    sCoreCategory,
                },
                {
                    PerlPreferenceMessages.syntaxColorSubroutineName,
                    PerlColorConstants.PERL_SUB_NAME,
                    sCoreCategory,
                },
                {
                    PerlPreferenceMessages.syntaxColorQuoteAndRegExp,
                    PerlColorConstants.PERL_QUOTE_REGEXP_OP,
                    sCoreCategory,
                },
                {  
                    PerlPreferenceMessages.syntaxColorHereDoc,
                    PerlColorConstants.PERL_HEREDOC,
                    sCoreCategory,
                },
                
            };

        public PerlEditorColoringConfigurationBlock(OverlayPreferenceStore store)
        {
            super(store);
        }

        @Override protected ProjectionViewer createPreviewViewer(Composite parent,
            IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IPreferenceStore store)
        {
            return new ScriptSourceViewer(parent, verticalRuler, overviewRuler,
                    showAnnotationsOverview, styles, store);
        }

        @Override protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(
            IColorManager colorManager, IPreferenceStore preferenceStore, ITextEditor editor,
            boolean configureFormatter)
        {
            return new SimplePerlSourceViewerConfiguration(colorManager, preferenceStore, editor,
                    PerlPartitionConstants.PERL_PARTITIONING, configureFormatter);
        }

        @Override protected InputStream getPreviewContentReader()
        {
            return getClass().getResourceAsStream(PREVIEW);
        }

        @Override protected String[][] getSyntaxColorListModel()
        {
            return syntaxColorListModel;
        }

        @Override protected ScriptTextTools getTextTools()
        {
            return PerlUIPlugin.getPlugin().getTextTools();
        }

        @Override protected void setDocumentPartitioning(IDocument document)
        {
            final String partitioning = PerlPartitionConstants.PERL_PARTITIONING;
            getTextTools().setupDocumentPartitioner(document, partitioning);
        }
    }

}
