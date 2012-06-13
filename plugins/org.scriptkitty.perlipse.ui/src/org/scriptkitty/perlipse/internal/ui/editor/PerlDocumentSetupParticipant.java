package org.scriptkitty.perlipse.internal.ui.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;
import org.scriptkitty.perlipse.internal.ui.text.PerlPartitionConstants;

public class PerlDocumentSetupParticipant implements IDocumentSetupParticipant
{
    public void setup(IDocument document)
    {
        PerlUIPlugin.getPlugin().getTextTools().setupDocumentPartitioner(document,
                PerlPartitionConstants.PERL_PARTITIONING);
    }
}
