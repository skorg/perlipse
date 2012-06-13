package org.scriptkitty.perlipse.internal.ui.text.rules;

import org.eclipse.dltk.ui.text.rules.IScriptWordDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.scriptkitty.perlipse.core.util.syntax.PerlVarSyntaxUtils;


/**
 */
public class PerlSyntaxDetectorFactory
{
    public static IWordDetector getArrayVariableDetector()
    {
        return new IWordDetector()
        {
            public boolean isWordPart(char c)
            {
                return PerlVarSyntaxUtils.isArrayVariableCharacter(c);
            }

            public boolean isWordStart(char c)
            {
                return PerlVarSyntaxUtils.isArrayVariableStartCharacter(c);
            }
        };
    }

    public static IWordDetector getArrayVariableKeyworDetector()
    {
        return new IWordDetector()
        {
            private char lastSeen;

            public boolean isWordPart(char c)
            {
                boolean isPart;
                if (PerlVarSyntaxUtils.isArrayKeywordVariableStartCharacter(lastSeen)
                    && PerlVarSyntaxUtils.isArrayKeywordVariableCharacter(c))
                {
                    isPart = true;
                }
                else
                {
                    isPart = PerlVarSyntaxUtils.isArrayVariableCharacter(c);
                }

                lastSeen = c;
                return isPart;
            }

            public boolean isWordStart(char c)
            {
                lastSeen = c;
                return PerlVarSyntaxUtils.isArrayKeywordVariableStartCharacter(c);
            }
        };
    }

    public static IWordDetector getBarewordDetector()
    {
        return new IScriptWordDetector()
        {
            public boolean isPriorCharValid(char c)
            {
                return PerlVarSyntaxUtils.isPrevBarewordChar(c);
            }

            public boolean isWordPart(char c)
            {
                return PerlVarSyntaxUtils.isBarewordChar(c);
            }

            public boolean isWordStart(char c)
            {
                return PerlVarSyntaxUtils.isBarewordChar(c);
            }
        };
    }

    /**
     * returns an <code>IWordDetector</code> capable of detecting perl file handles.
     */
    public static IWordDetector getFileHandleDetector()
    {
        return new IWordDetector()
        {
            public boolean isWordPart(char c)
            {
                return PerlVarSyntaxUtils.isFileHandleCharacter(c);
            }

            public boolean isWordStart(char c)
            {
                return PerlVarSyntaxUtils.isFileHandleCharacter(c);
            }
        };
    }

    public static IWordDetector getFunctionDetector()
    {
        return new IScriptWordDetector()
        {
            public boolean isPriorCharValid(char c)
            {
                return PerlVarSyntaxUtils.isPrevFunctionChar(c);
            }

            public boolean isWordPart(char c)
            {
                return PerlVarSyntaxUtils.isFunctionCharacter(c);
            }

            public boolean isWordStart(char c)
            {
                return PerlVarSyntaxUtils.isFunctionCharacter(c);
            }

        };
    }

    public static IWordDetector getHashVariableDetector()
    {
        return new IWordDetector()
        {
            public boolean isWordPart(char c)
            {
                return PerlVarSyntaxUtils.isHashVariableCharacter(c);
            }

            public boolean isWordStart(char c)
            {
                return PerlVarSyntaxUtils.isHashVariableStartCharacter(c);
            }
        };
    }

    public static IWordDetector getScalarVariableDetector()
    {
        return new IWordDetector()
        {
            private char lastSeen;

            public boolean isWordPart(char c)
            {
                boolean isPart;
                if (PerlVarSyntaxUtils.isScalarVariableStartCharacter(lastSeen)
                    && PerlVarSyntaxUtils.isScalarKeywordVariableCharacter(c))
                {
                    isPart = true;
                }
                else
                {
                    isPart = PerlVarSyntaxUtils.isScalarVariableCharacter(c);
                }

                lastSeen = (lastSeen != c) ? c : ' ';
                return isPart;
            }

            public boolean isWordStart(char c)
            {
                lastSeen = c;
                return PerlVarSyntaxUtils.isScalarVariableStartCharacter(c);
            }
        };
    }
}
