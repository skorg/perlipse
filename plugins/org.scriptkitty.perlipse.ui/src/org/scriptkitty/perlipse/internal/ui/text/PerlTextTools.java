package org.scriptkitty.perlipse.internal.ui.text;

import java.util.List;

import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.heredoc.HereDocEnabledPartitionScanner;
import org.eclipse.dltk.ui.text.heredoc.HereDocEnabledPartitioner;
import org.eclipse.dltk.ui.text.heredoc.HereDocPartitionRule;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.ui.texteditor.ITextEditor;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;
import org.scriptkitty.perlipse.internal.ui.text.rules.PerlPartitionRulesFactory;


/**
 */
public class PerlTextTools extends ScriptTextTools
{
    //~ Static fields/initializers

    private static final String[] LEGAL_CONTENT_TYPES = PerlPartitionConstants.PERL_PARTITION_TYPES;

    //~ Constructors

    public PerlTextTools(boolean autoDispose)
    {
        super(PerlPartitionConstants.PERL_PARTITIONING, LEGAL_CONTENT_TYPES, autoDispose);
    }

    //~ Methods

    @Override public IDocumentPartitioner createDocumentPartitioner()
    {
        return new HereDocEnabledPartitioner(createPartitionScanner(), LEGAL_CONTENT_TYPES)
        {
            @Override public void connect(IDocument document, boolean delayInitialise)
            {
                super.connect(document, delayInitialise);

                if (PerlUIPlugin.PRINT_CONNECT_PARTITIONS)
                {
                    printPartitions(document);
                }
            }

            @Override public IRegion documentChanged2(DocumentEvent e)
            {
               IRegion region = super.documentChanged2(e);

                if (PerlUIPlugin.PRINT_CHANGED_PARTITIONS)
                {
                    printPartitions(e.getDocument());
                }

                return region;
            }

            private void printPartitions(IDocument document)
            {
                StringBuffer buffer = new StringBuffer();

                ITypedRegion[] partitions = computePartitioning(0, document.getLength());
                for (int i = 0; i < partitions.length; i++)
                {
                    try
                    {
                        buffer.append("partition type: " + partitions[i].getType() + ", offset: ");
                        buffer.append(partitions[i].getOffset() + ", length: " + partitions[i].getLength());
                        buffer.append("\n---------------------------\n");

                        String contents = document.get(partitions[i].getOffset(), partitions[i].getLength());                        
                        // replace the newline w/ an escaped newline to see what partition it belongs to
                        buffer.append(contents.replaceAll("\n", "\\\\n\n"));

                        if (buffer.lastIndexOf("\n") < (buffer.length() - 1))
                        {
                            buffer.append("\n");
                        }

                        buffer.append("---------------------------\n");
                    }
                    catch (BadLocationException e)
                    {
                        e.printStackTrace();
                    }
                }

                System.out.print(buffer);
            }

        };
    }

    @Override public IPartitionTokenScanner createPartitionScanner()
    {
        HereDocPartitionRule heredoc = PerlPartitionRulesFactory.getHereDocRule();
        List<IPredicateRule> rules = PerlPartitionRulesFactory.getPartitionRules();
        
        return new HereDocEnabledPartitionScanner(rules, heredoc);
    }

    /*
     * @see
     * org.eclipse.dltk.ui.text.ScriptTextTools#createSourceViewerConfiguraton(org.eclipse.jface.preference.IPreferenceStore,
     * org.eclipse.ui.texteditor.ITextEditor, java.lang.String)
     */
    @Override public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(IPreferenceStore preferenceStore,
        ITextEditor editor, String partitioning)
    {
        return new PerlSourceViewerConfiguration(getColorManager(), preferenceStore, editor, partitioning);
    }

    @Override public IPartitionTokenScanner getPartitionScanner()
    {
        // TODO: remove this when folding is refactored
        return createPartitionScanner();
    }
}
