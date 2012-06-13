package org.scriptkitty.perlipse.internal.ui.text.correction;

import org.eclipse.core.resources.IMarker;
import org.eclipse.dltk.ui.editor.IScriptAnnotation;
import org.eclipse.dltk.ui.text.IScriptCorrectionContext;
import org.eclipse.dltk.ui.text.IScriptCorrectionProcessor;

public class PerlCompilerCorrectionProcessor implements
		IScriptCorrectionProcessor {

	public boolean canFix(IScriptAnnotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canFix(IMarker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	public void computeQuickAssistProposals(IScriptAnnotation annotation,
			IScriptCorrectionContext context) {
		// TODO Auto-generated method stub
		System.out.println("foo");
	}

	public void computeQuickAssistProposals(IMarker marker,
			IScriptCorrectionContext context) {
		// TODO Auto-generated method stub
		System.out.println("foo");
	}

}
