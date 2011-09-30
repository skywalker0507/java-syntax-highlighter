/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.Brushes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;

/**
 * Java brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushJava extends Brush {

    public BrushJava() {
        super();

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments"));
        _regExpRuleList.add(new RegExpRule("\\/\\*([^\\*][\\s\\S]*?)?\\*\\/", Pattern.MULTILINE, "comments"));
        _regExpRuleList.add(new RegExpRule("\\/\\*(?!\\*\\/)\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE, "preprocessor"));
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string"));
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string"));
        _regExpRuleList.add(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value"));
        _regExpRuleList.add(new RegExpRule("(?!\\@interface\\b)\\@[\\$\\w]+\\b", "color1"));
        _regExpRuleList.add(new RegExpRule("\\@interface\\b", "color2"));
        _regExpRuleList.add(new RegExpRule(getKeywords("abstract assert boolean break byte case catch char class const "
                + "continue default do double else enum extends "
                + "false final finally float for goto if implements import "
                + "instanceof int interface long native new null "
                + "package private protected public return "
                + "short static strictfp super switch synchronized this throw throws true "
                + "transient try void volatile while"), Pattern.MULTILINE, "keyword"));
        setRegExpRuleList(_regExpRuleList);

        setHTMLScriptRegExp(new HTMLScriptRegExp("(?:&lt;|<)%[@!=]?", "%(?:&gt;|>)"));

        setCommonFileExtensionList(Arrays.asList("java"));
    }
}
