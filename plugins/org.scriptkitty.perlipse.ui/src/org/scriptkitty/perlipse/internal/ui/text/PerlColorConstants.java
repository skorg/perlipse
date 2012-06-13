package org.scriptkitty.perlipse.internal.ui.text;

import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.DLTKColorConstants;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;

import org.eclipse.swt.graphics.RGB;


/**
 */
public abstract class PerlColorConstants
{
    //~ Static fields/initializers

    /** color key for string and character literals */
    public static final String PERL_STRING = DLTKColorConstants.DLTK_STRING;

    public static final String PERL_BACKTICK = DLTKColorConstants.PREFIX + "_backtick";
    
    public static final String PERL_HEREDOC = DLTKColorConstants.PREFIX + "_heredoc";
    public static final String PERL_HEREDOC_TAG = PERL_HEREDOC + "_tag";
    
    /** color key for comments */
    public static final String PERL_LINE_COMMENT =
        DLTKColorConstants.DLTK_SINGLE_LINE_COMMENT;

    public static final String PERL_ARRAY_KEYWORDS = DLTKColorConstants.DLTK_KEYWORD + "_arrays";

    public static final String PERL_ARRAY_KEYWORDS_BOLD = PERL_ARRAY_KEYWORDS + PreferenceConstants.EDITOR_BOLD_SUFFIX;
       
    public static final String PERL_BAREWORD_KEYWORDS =
        DLTKColorConstants.DLTK_KEYWORD + "_bareword";

    public static final String PERL_BAREWORD_KEYWORDS_BOLD = PERL_BAREWORD_KEYWORDS + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_FILEHANDLE_KEYWORDS =
        DLTKColorConstants.DLTK_KEYWORD + "_fileHandle";

    public static final String PERL_FILEHANDLE_KEYWORDS_BOLD = PERL_FILEHANDLE_KEYWORDS + PreferenceConstants.EDITOR_BOLD_SUFFIX;    
    
    public static final String PERL_FUNCTION_KEYWORDS =
        DLTKColorConstants.DLTK_KEYWORD + "_function";

    public static final String PERL_FUNCTION_KEYWORDS_BOLD = PERL_FUNCTION_KEYWORDS + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_HASH_KEYWORDS = DLTKColorConstants.DLTK_KEYWORD + "_hash";

    public static final String PERL_HASH_KEYWORDS_BOLD = PERL_HASH_KEYWORDS + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_SCALAR_KEYWORDS = DLTKColorConstants.DLTK_KEYWORD + "_scalar";

    public static final String PERL_SCALAR_KEYWORDS_BOLD = PERL_SCALAR_KEYWORDS + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_ARRAY_VARIABLE = DLTKColorConstants.DLTK_KEYWORD + "_arrayVar";
    public static final String PERL_ARRAY_VARIABLE_BOLD = PERL_ARRAY_VARIABLE + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_HASH_VARIABLE = DLTKColorConstants.DLTK_KEYWORD + "_hashVar";
    public static final String PERL_HASH_VARIABLE_BOLD = PERL_HASH_VARIABLE + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_SCALAR_VARIABLE = DLTKColorConstants.DLTK_KEYWORD + "_scalarVar";
    public static final String PERL_SCALAR_VARIABLE_BOLD = PERL_SCALAR_VARIABLE + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_PACKAGE_NAME = DLTKColorConstants.DLTK_KEYWORD + "_packageName";
    public static final String PERL_PACKAGE_NAME_BOLD = PERL_PACKAGE_NAME + PreferenceConstants.EDITOR_BOLD_SUFFIX;
    
    public static final String PERL_SUB_NAME = DLTKColorConstants.DLTK_KEYWORD + "_subName";
    public static final String PERL_SUB_NAME_BOLD = PERL_SUB_NAME + PreferenceConstants.EDITOR_BOLD_SUFFIX;

    public static final String PERL_QUOTE_REGEXP_OP = DLTKColorConstants.DLTK_KEYWORD + "_quoteLike";
    
    /** color key for keyword 'return'. */
    public static final String PERL_RETURN_KEYWORD = DLTKColorConstants.DLTK_KEYWORD_RETURN;

    /** color key for pod */
    public static final String PERL_PODDOC = DLTKColorConstants.DLTK_DOC;
    
    public static final String PERL_PODDOC_TAG = DLTKColorConstants.DLTK_DOC + "_tag";
    public static final String PERL_PODDOC_TAG_BOLD = PERL_PODDOC_TAG + PreferenceConstants.EDITOR_BOLD_SUFFIX; 

    /** color key for remainder of code base */
    public static final String PERL_DEFAULT_TEXT_COLOR = DLTKColorConstants.DLTK_DEFAULT;

    /** color key for 'todo' tasks in comments */
    public static final String PERL_TODO_TAG = DLTKColorConstants.TASK_TAG;

    private static final String PERL_RETURN_KEYWORD_BOLD =
        PERL_RETURN_KEYWORD + PreferenceConstants.EDITOR_BOLD_SUFFIX;

    private static final String COMMENT_TASK_TAGS_BOLD =
        PERL_TODO_TAG + PreferenceConstants.EDITOR_BOLD_SUFFIX;

    //~ Methods

    public static void intializeDefaultValues(IPreferenceStore store)
    {
        // strings
        PreferenceConverter.setDefault(store, PERL_STRING, new RGB(42, 0, 255));
        PreferenceConverter.setDefault(store, PERL_BACKTICK, new RGB(42, 0, 255));
        
        // heredoc
        PreferenceConverter.setDefault(store, PERL_HEREDOC, new RGB(50, 123, 180));
        PreferenceConverter.setDefault(store, PERL_HEREDOC_TAG, new RGB(50, 123, 180));
        store.setDefault(PERL_HEREDOC_TAG, true);
        
        // comment
        PreferenceConverter.setDefault(store, PERL_LINE_COMMENT, new RGB(63, 127, 95));

        // todo tags
        PreferenceConverter.setDefault(store, PERL_TODO_TAG, new RGB(127, 159, 191));
        store.setDefault(COMMENT_TASK_TAGS_BOLD, true);

        // pod
        PreferenceConverter.setDefault(store, PERL_PODDOC, new RGB(63, 95, 191));
        PreferenceConverter.setDefault(store, PERL_PODDOC_TAG, new RGB(63, 95, 191));
        store.setDefault(PERL_PODDOC_TAG_BOLD, true);

        addKeywordPreferences(store);
    }
    
    private static void addKeywordPreferences(IPreferenceStore store) 
    {
        // array variables
        PreferenceConverter.setDefault(store, PERL_ARRAY_VARIABLE, new RGB(0, 104, 139));
        store.setDefault(PERL_ARRAY_VARIABLE_BOLD, false);

        // hash variables
        PreferenceConverter.setDefault(store, PERL_HASH_VARIABLE, new RGB(0, 104, 139));
        store.setDefault(PERL_HASH_VARIABLE_BOLD, false);

        // scalar variables
        PreferenceConverter.setDefault(store, PERL_SCALAR_VARIABLE, new RGB(0, 104, 139));
        store.setDefault(PERL_SCALAR_VARIABLE_BOLD, false);
        
        // array keywords
        PreferenceConverter.setDefault(store, PERL_ARRAY_KEYWORDS, new RGB(0, 104, 139));
        store.setDefault(PERL_ARRAY_KEYWORDS_BOLD, false);

        // bareword keywords
        PreferenceConverter.setDefault(store, PERL_BAREWORD_KEYWORDS, new RGB(127, 0, 85));
        store.setDefault(PERL_BAREWORD_KEYWORDS_BOLD, true);

        // filehandle keywords
        PreferenceConverter.setDefault(store, PERL_FILEHANDLE_KEYWORDS, new RGB(67, 82, 118));
        store.setDefault(PERL_FILEHANDLE_KEYWORDS_BOLD, false);

        // function keywords
        PreferenceConverter.setDefault(store, PERL_FUNCTION_KEYWORDS, new RGB(127, 0, 85));
        store.setDefault(PERL_FUNCTION_KEYWORDS_BOLD, true);

        // hash keywords
        PreferenceConverter.setDefault(store, PERL_HASH_KEYWORDS, new RGB(0, 104, 139));
        store.setDefault(PERL_HASH_KEYWORDS_BOLD, false);

        // scalar keywords
        PreferenceConverter.setDefault(store, PERL_SCALAR_KEYWORDS, new RGB(0, 104, 139));
        store.setDefault(PERL_SCALAR_KEYWORDS_BOLD, false);

        // return keyword
        PreferenceConverter.setDefault(store, PERL_RETURN_KEYWORD, new RGB(127, 0, 85));
        store.setDefault(PERL_RETURN_KEYWORD_BOLD, true);
    }
}
