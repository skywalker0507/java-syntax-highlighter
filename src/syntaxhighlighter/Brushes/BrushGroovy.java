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
import syntaxhighlighter.Brush.RegExpRule;

/**
 * Groovy brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushGroovy extends Brush {

    public BrushGroovy() {
        super();

        // Contributed by Andres Almiray
        // http://jroller.com/aalmiray/entry/nice_source_code_syntax_highlighter

        String keywords = "as assert break case catch class continue def default do else extends finally "
                + "if in implements import instanceof interface new package property return switch "
                + "throw throws try while public protected private static";
        String types = "void boolean byte char short int long float double";
        String constants = "null";
        String methods = "allProperties count get size "
                + "collect each eachProperty eachPropertyName eachWithIndex find findAll "
                + "findIndexOf grep inject max min reverseEach sort "
                + "asImmutable asSynchronized flatten intersect join pop reverse subMap toList "
                + "padRight padLeft contains eachMatch toCharacter toLong toUrl tokenize "
                + "eachFile eachFileRecurse eachB yte eachLine readBytes readLine getText "
                + "splitEachLine withReader append encodeBase64 decodeBase64 filterLine "
                + "transformChar transformLine withOutputStream withPrintWriter withStream "
                + "withStreams withWriter withWriterAppend write writeLine "
                + "dump inspect invokeMethod print println step times upto use waitForOrKill "
                + "getText";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.multiLineCComments, "comments")); // multiline comments
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule("\"\"\".*\"\"\"", "string")); // GStrings
        _regExpRuleList.add(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value")); // numbers
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // goovy keyword
        _regExpRuleList.add(new RegExpRule(getKeywords(types), Pattern.MULTILINE, "color1")); // goovy/java type
        _regExpRuleList.add(new RegExpRule(getKeywords(constants), Pattern.MULTILINE, "constants")); // constants
        _regExpRuleList.add(new RegExpRule(getKeywords(methods), Pattern.MULTILINE, "functions")); // methods
        setRegExpRuleList(_regExpRuleList);

        setHTMLScriptRegExp(HTMLScriptRegExp.aspScriptTags);

        setCommonFileExtensionList(Arrays.asList("groovy"));
    }
}
