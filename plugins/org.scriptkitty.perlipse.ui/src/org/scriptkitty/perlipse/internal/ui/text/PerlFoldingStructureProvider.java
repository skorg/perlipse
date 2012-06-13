package org.scriptkitty.perlipse.internal.ui.text;

import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


/**
 */
public class PerlFoldingStructureProvider extends AbstractASTFoldingStructureProvider
{
    @Override protected String getCommentPartition()
    {
        return PerlPartitionConstants.PERL_COMMENT;
    }

    @Override protected String getDocPartition()
    {
        return PerlPartitionConstants.PERL_PERLDOC;
    }

    @Override protected ILog getLog()
    {
        return PerlUIPlugin.getPlugin().getLog();
    }

    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    @Override protected String getPartition()
    {
        return PerlPartitionConstants.PERL_PARTITIONING;
    }

    @Override protected IPartitionTokenScanner getPartitionScanner()
    {
        return PerlUIPlugin.getPlugin().getTextTools().getPartitionScanner();
    }

    @Override protected String[] getPartitionTypes()
    {
        return PerlPartitionConstants.PERL_PARTITION_TYPES;
    }
}
