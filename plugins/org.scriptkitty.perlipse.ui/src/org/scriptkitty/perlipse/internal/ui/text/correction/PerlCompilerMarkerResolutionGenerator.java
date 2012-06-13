package org.scriptkitty.perlipse.internal.ui.text.correction;

import org.eclipse.core.resources.IMarker;
import org.eclipse.dltk.ui.text.ScriptMarkerResoltionUtils;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.IMarkerResolutionGenerator2;

public class PerlCompilerMarkerResolutionGenerator implements
		IMarkerResolutionGenerator, IMarkerResolutionGenerator2 {

	public IMarkerResolution[] getResolutions(IMarker marker) {
		return ScriptMarkerResoltionUtils.NO_RESOLUTIONS;
	}

	public boolean hasResolutions(IMarker marker) {
		return false;
	}

}
