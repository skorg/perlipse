package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;

import org.eclipse.jface.text.IDocument;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;
import org.scriptkitty.perlipse.internal.ui.templates.PerlTemplateAccess;
import org.scriptkitty.perlipse.internal.ui.text.PerlTextTools;
import org.scriptkitty.perlipse.internal.ui.text.PerlSourceViewerConfiguration.SimplePerlSourceViewerConfiguration;


public class PerlCodeTemplatesPreferencePage extends ScriptTemplatePreferencePage
{
    /*
     * @see org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage#createSourceViewerConfiguration()
     */
    @Override protected ScriptSourceViewerConfiguration createSourceViewerConfiguration()
    {
        return new SimplePerlSourceViewerConfiguration(getTextTools().getColorManager(),
            getPreferenceStore(), null, PerlCoreConstants.PARTITIONING_ID, false);
    }

    @Override protected ScriptTemplateAccess getTemplateAccess()
    {
        return PerlTemplateAccess.getInstance();
    }

    @Override protected void setDocumentPartitioner(IDocument document)
    {
        getTextTools().setupDocumentPartitioner(document, PerlCoreConstants.PARTITIONING_ID);
    }

    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getPlugin().getPreferenceStore());
    }

    private PerlTextTools getTextTools()
    {
        return PerlUIPlugin.getPlugin().getTextTools();
    }
}
