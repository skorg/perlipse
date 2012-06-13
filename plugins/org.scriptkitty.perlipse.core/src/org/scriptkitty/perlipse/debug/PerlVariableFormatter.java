package org.scriptkitty.perlipse.debug;

public class PerlVariableFormatter 
{

    public static String stripDashArrow(String name) 
    {
    	int start = name.indexOf('>');
    	if (start != -1)
    	{
    		name = name.substring(start + 1);
    	}
    	
    	return name;
    }
    
    public static String stripCurlies(String name) {
    	int start = name.indexOf('{');
    	if (start != -1)
    	{
    		int end = name.indexOf('}');
    		name = name.substring(start + 1, end);
    	}

    	return name;
    }
}
