package org.scriptkitty.perlipse.internal.debug.ui.preferences;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.IInterpreterComboBlockContext;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.ScriptInterpreterPreferencePage;

import org.scriptkitty.perlipse.internal.debug.ui.interpreters.PerlInterpretersBlock;


public class PerlInterpreterPreferencePage extends ScriptInterpreterPreferencePage
{
    static final String PAGE_ID = "org.scriptkitty.perlipse.debug.ui.preferences.interpreters";

    @Override public InterpretersBlock createInterpretersBlock()
    {
        return new PerlInterpretersBlock();
    }

    public static class PerlInterpreterComboBlock extends AbstractInterpreterComboBlock
    {
        public PerlInterpreterComboBlock(IInterpreterComboBlockContext context)
        {
            super(context);
        }

        /*
         * @see
         * org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock#showInterpreterPreferencePage()
         */
        @Override protected void showInterpreterPreferencePage()
        {
            showPrefPage(PAGE_ID);
        }
    }
}
