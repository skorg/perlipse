package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPreferencePage;
import org.eclipse.dltk.ui.preferences.IPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.folding.DefaultFoldingPreferenceConfigurationBlock;
import org.eclipse.dltk.ui.text.folding.DocumentationFoldingPreferenceBlock;
import org.eclipse.dltk.ui.text.folding.IFoldingPreferenceBlock;
import org.eclipse.dltk.ui.text.folding.SourceCodeFoldingPreferenceBlock;
import org.eclipse.jface.preference.PreferencePage;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


/**
 * perl editor folding options preference page
 */
public class PerlEditorFoldingPreferencePage extends AbstractConfigurationBlockPreferencePage
{
    @Override protected IPreferenceConfigurationBlock createConfigurationBlock(
        OverlayPreferenceStore overlayPreferenceStore)
    {
        return new DefaultFoldingPreferenceConfigurationBlock(overlayPreferenceStore, this)
        {
            @Override protected IFoldingPreferenceBlock createSourceCodeBlock(
                OverlayPreferenceStore store, PreferencePage page)
            {
                return new PerlSourceFoldingConfigurationBlock(store, page);
            }

            @Override protected IFoldingPreferenceBlock createDocumentationBlock(
                OverlayPreferenceStore store, PreferencePage page)
            {
                return new PerlDocumentationFoldingBlock(store, page);
            }
        };
    }

    @Override protected String getHelpId()
    {
        return null;
    }

    @Override protected void setDescription()
    {
        // setDescription(PreferencesMessages.EditorPreferencePage_folding_title);
    }

    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getPlugin().getPreferenceStore());
    }

    /**
     * perl comments/documentation folding preference block
     */
    private class PerlDocumentationFoldingBlock extends DocumentationFoldingPreferenceBlock
    {
        public PerlDocumentationFoldingBlock(OverlayPreferenceStore store, PreferencePage page)
        {
            super(store, page);
        }
            
        @Override protected String getInitiallyFoldDocsText()
        {
            return PerlPreferenceMessages.initiallyFoldPod;
        }
        
        @Override protected boolean supportsDocFolding() 
        {
            return true;
        }
    }

    /**
     * perl source folding preference block
     */
    private class PerlSourceFoldingConfigurationBlock extends SourceCodeFoldingPreferenceBlock
    {
        public PerlSourceFoldingConfigurationBlock(OverlayPreferenceStore store,
            PreferencePage page)
        {
            super(store, page);
        }

        @Override
        protected String getInitiallyFoldClassesText()
        {
            return PerlPreferenceMessages.initiallyFoldPkgs;
        }

        @Override
        protected String getInitiallyFoldMethodsText()
        {
            return PerlPreferenceMessages.initiallyFoldSubs;
        }
    }
}
