package org.scriptkitty.perlipse.internal.debug;

import org.eclipse.dltk.debug.core.model.AtomicScriptType;

public class PerlScalarType extends AtomicScriptType {

	static String SCALAR = "scalar";
	
	public PerlScalarType() {
		super(SCALAR);
	}
}
