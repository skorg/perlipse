package org.scriptkitty.perlipse.internal.debug;

import org.eclipse.dltk.debug.core.model.IScriptType;
import org.eclipse.dltk.debug.core.model.IScriptTypeFactory;

public class PerlTypeFactory implements IScriptTypeFactory
{
	/*
	 * @see org.eclipse.dltk.debug.core.model.IScriptTypeFactory#buildType(java.lang.String)
	 */
    public IScriptType buildType(String type)
    {
    	/*
    	 * NOTE: as of 4/4/2012, the lastest version of the dbgp implementation no longer works in the same manner
    	 * as when this was first implemented. the scalar type is missing and the array/hash types are now sent
    	 * across the wire in all upper case.
    	 */
        
    	if (ARRAY.equalsIgnoreCase(type))
    	{
    		return new PerlArrayType(type);
    	}
    	
    	if (HASH.equalsIgnoreCase(type))
    	{
    		return new PerlHashType(type);
    	}
    	
    	if (PerlScalarType.SCALAR.equals(type) || "".equals(type))
    	{
    		return new PerlScalarType();
    	}
    	
    	/*
    	 * this is a cheat - it's still a scalar, but this allows a bit more
    	 * flexibility 
    	 */
    	return new PerlObjectType(type);
    }

}
