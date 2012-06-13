package org.scriptkitty.perlipse.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.progress.IProgressConstants;

import org.scriptkitty.perlipse.internal.ui.wizards.PerlPackageWizard;
import org.scriptkitty.perlipse.internal.ui.wizards.PerlProjectWizard;


/**
 */
public class PerlPerspective implements IPerspectiveFactory
{
    //~ Static fields/initializers

    private static final String SCRIPT_EXPLORER = "org.eclipse.dltk.ui.ScriptExplorer";

    private static final String NEW_FOLDER_WIZARD = "org.eclipse.ui.wizards.new.folder";

    private static final String NEW_FILE_WIZARD = "org.eclipse.ui.wizards.new.file";

    private static final String NEW_UNTITLED_TEXT_FILE_WIZARD = "org.eclipse.ui.editors.wizards.UntitledTextFileWizard";

    private static final String NEW_SRC_FOLDER_WIZARD = "org.scriptkitty.perlipse.ui.wizards.NewSourceFolderCreationWizard";

    //~ Methods

    public void createInitialLayout(IPageLayout layout)
    {
        createFolders(layout);
        addActionSets(layout);
        addShowViewShortcuts(layout);
        addNewWizardShortcuts(layout);
        addPerspectiveShotcuts(layout);
    }

    protected void addActionSets(IPageLayout layout)
    {
        layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
        layout.addActionSet(PerlUIConstants.ACTION_SET_ID);
    }

    protected void addNewWizardShortcuts(IPageLayout layout)
    {
        layout.addNewWizardShortcut(PerlProjectWizard.WIZARD_ID);
        layout.addNewWizardShortcut(PerlPackageWizard.WIZARD_ID);

        // source folder always after project specific items
        layout.addNewWizardShortcut(NEW_SRC_FOLDER_WIZARD);

        // general
        layout.addNewWizardShortcut(NEW_FOLDER_WIZARD);
        layout.addNewWizardShortcut(NEW_FILE_WIZARD);
        layout.addNewWizardShortcut(NEW_UNTITLED_TEXT_FILE_WIZARD);
    }

    protected void addPerspectiveShotcuts(IPageLayout layout)
    {
        layout.addPerspectiveShortcut("org.eclipse.debug.ui.DebugPerspective");
    }

    protected void addShowViewShortcuts(IPageLayout layout)
    {
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        // layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);

        layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
        layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);

        layout.addShowViewShortcut(SCRIPT_EXPLORER);
    }

    protected void createFolders(IPageLayout layout)
    {
        final String editorArea = layout.getEditorArea();

        // Folder
        IFolderLayout folder = layout.createFolder("left", IPageLayout.LEFT, (float) 0.2, editorArea); // $NON-NLS-1$

        folder.addView(SCRIPT_EXPLORER);
        folder.addPlaceholder(IPageLayout.ID_BOOKMARKS);

        // Output folder
        IFolderLayout outputFolder = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.75, editorArea); // $NON-NLS-1$

        outputFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
        outputFolder.addView(IPageLayout.ID_TASK_LIST);
        // outputFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);

        // outputFolder.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
        outputFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
        outputFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
    }

}
