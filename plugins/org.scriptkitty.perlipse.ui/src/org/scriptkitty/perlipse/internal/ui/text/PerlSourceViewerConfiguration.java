package org.scriptkitty.perlipse.internal.ui.text;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.SingleTokenScriptScanner;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.dltk.ui.text.heredoc.HereDocEnabledPresentationReconciler;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;
import org.scriptkitty.perlipse.internal.ui.text.completion.PerlCompletionProcessor;
import org.scriptkitty.perlipse.internal.ui.text.completion.PerlContentAssistPreference;


/**
 */
public class PerlSourceViewerConfiguration extends ScriptSourceViewerConfiguration
{
    //~ Instance fields

    private Map<String, AbstractScriptScanner> scanners;

    //~ Constructors

    public PerlSourceViewerConfiguration(IColorManager colorManager, IPreferenceStore preferenceStore,
        ITextEditor editor, String partitioning)
    {
        super(colorManager, preferenceStore, editor, partitioning);
    }

    //~ Methods

    @Override public boolean affectsTextPresentation(PropertyChangeEvent event)
    {
        for (AbstractScriptScanner scanner : scanners.values())
        {
            if (scanner.affectsBehavior(event))
            {
                return true;
            }
        }

        return false;
    }

    @Override public String[] getConfiguredContentTypes(ISourceViewer sourceViewer)
    {
        return PerlPartitionConstants.PERL_PARTITION_TYPES;
    }

    /*
     * @see org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration#getContentAssistPreference()
     */
    @Override public ContentAssistPreference getContentAssistPreference()
    {
        return PerlContentAssistPreference.getInstance();
    }

    @Override public IInformationPresenter getHierarchyPresenter(ScriptSourceViewer viewer, boolean b)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
    {
        PresentationReconciler reconciler = new HereDocEnabledPresentationReconciler();
        reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

        for (String partition : scanners.keySet())
        {
            AbstractScriptScanner scanner = scanners.get(partition);
            createRepairer(scanner, reconciler, partition);

        }

        return reconciler;
    }

    @Override public void handlePropertyChangeEvent(PropertyChangeEvent event)
    {
        for (AbstractScriptScanner scanner : scanners.values())
        {
            if (scanner.affectsBehavior(event))
            {
                scanner.adaptToPreferenceChange(event);
            }
        }
    }

    @Override protected void alterContentAssistant(ContentAssistant assistant)
    {
        PerlCompletionProcessor processor = new PerlCompletionProcessor(getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
    }

    @Override protected void initializeScanners()
    {
        scanners = new HashMap<String, AbstractScriptScanner>();

        IColorManager manager = getColorManager();

//        // code scanner
        PerlCodeScanner codeScanner = new PerlCodeScanner(manager, fPreferenceStore);
        scanners.put(PerlPartitionConstants.PERL_VARIABLE, codeScanner);
        scanners.put(IDocument.DEFAULT_CONTENT_TYPE, codeScanner);

        // quote like/regexp scanner
        scanners.put(PerlPartitionConstants.PERL_QUOTE_AND_REGEXP,
            new PerlQuoteAndRegExpScanner(manager, fPreferenceStore));

        // perldoc scanner
        scanners.put(PerlPartitionConstants.PERL_PERLDOC,
            new PerlPodScanner(manager, fPreferenceStore));

        // comment scanner
        scanners.put(PerlPartitionConstants.PERL_COMMENT,
            createCommentScanner(PerlColorConstants.PERL_LINE_COMMENT,
                PerlColorConstants.PERL_TODO_TAG));

        // string scanner
        scanners.put(PerlPartitionConstants.PERL_STRING,
            new SingleTokenScriptScanner(manager, fPreferenceStore,
                PerlColorConstants.PERL_STRING));

        // backtick scanner
        scanners.put(PerlPartitionConstants.PERL_BACKTICK,
            new SingleTokenScriptScanner(manager, fPreferenceStore,
                PerlColorConstants.PERL_BACKTICK));
        
        /*
         * heredoc scanner
         * 
         * TODO: separate colors for delimiters and content?
         * 
         * it should be possible to allow for this, but it's going to take a fair bit of work b/c the scanner
         * would need to re-detect what the delimiters are or figure out where to get them from
         */
        scanners.put(PerlPartitionConstants.PERL_HEREDOC, 
                new SingleTokenScriptScanner(manager, fPreferenceStore,
                    PerlColorConstants.PERL_HEREDOC));
    }

    private void createRepairer(AbstractScriptScanner scanner, PresentationReconciler reconciler, String contentType)
    {
        DefaultDamagerRepairer repairer = new DefaultDamagerRepairer(scanner);
        reconciler.setDamager(repairer, contentType);
        reconciler.setRepairer(repairer, contentType);
    }

    //~ Inner Classes

    /**
     */
    public static class SimplePerlSourceViewerConfiguration extends PerlSourceViewerConfiguration
    {
        // TODO: this can be pulled up if not already done
        private boolean configure;

        public SimplePerlSourceViewerConfiguration(IColorManager colorManager, IPreferenceStore preferenceStore,
            ITextEditor editor, String partitioning, boolean configure)
        {
            super(colorManager, preferenceStore, editor, partitioning);

            this.configure = configure;
        }

        @Override public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer)
        {
            return null;
        }

        @Override public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType)
        {
            return null;
        }

        @Override public int[] getConfiguredTextHoverStateMasks(ISourceViewer sourceViewer, String contentType)
        {
            return null;
        }

        @Override public IContentFormatter getContentFormatter(ISourceViewer sourceViewer)
        {
            if (configure)
            {
                return super.getContentFormatter(sourceViewer);
            }

            return null;
        }

        @Override public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer)
        {
            return null;
        }

        @Override public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer)
        {
            return null;
        }

        @Override public IInformationPresenter getInformationPresenter(ISourceViewer sourceViewer)
        {
            return null;
        }

        @Override public IAnnotationHover getOverviewRulerAnnotationHover(ISourceViewer sourceViewer)
        {
            return null;
        }

        @Override public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType)
        {
            return null;
        }

        @Override public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask)
        {
            return null;
        }
    }
}
