/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlighter.brush;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * Delphi brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushDelphi extends Brush {

    public BrushDelphi() {
        super();

        String keywords = "abs addr and ansichar ansistring array as asm begin boolean byte cardinal "
                + "case char class comp const constructor currency destructor div do double "
                + "downto else end except exports extended false file finalization finally "
                + "for function goto if implementation in inherited int64 initialization "
                + "integer interface is label library longint longword mod nil not object "
                + "of on or packed pansichar pansistring pchar pcurrency pdatetime pextended "
                + "pint64 pointer private procedure program property pshortstring pstring "
                + "pvariant pwidechar pwidestring protected public published raise real real48 "
                + "record repeat set shl shortint shortstring shr single smallint string then "
                + "threadvar to true try type unit until uses val var varirnt while widechar "
                + "widestring with word write writeln xor";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule("\\(\\*[\\s\\S]*?\\*\\)", Pattern.MULTILINE, "comments")); // multiline comments (* *)
        _regExpRuleList.add(new RegExpRule("\\{(?!\\$)[\\s\\S]*?\\}", Pattern.MULTILINE, "comments")); // multiline comments { }
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleLineCComments, "comments")); // one line
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string")); // strings
        _regExpRuleList.add(new RegExpRule("\\{\\$[a-zA-Z]+ .+\\}", "color1")); // compiler Directives and Region tags
        _regExpRuleList.add(new RegExpRule("\\b[\\d\\.]+\\b", "value")); // numbers 12345
        _regExpRuleList.add(new RegExpRule("\\$[a-zA-Z0-9]+\\b", "value")); // numbers $F5D3
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "keyword")); // keyword
        setRegExpRuleList(_regExpRuleList);

        setCommonFileExtensionList(Arrays.asList("pas"));
    }
}
