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
import java.util.List;
import java.util.regex.Pattern;
import syntaxhighlighter.Brush;
import syntaxhighlighter.Brush.RegExpRule;

/**
 * Erlang brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushErlang extends Brush {

    public BrushErlang() {
        super();

        // Contributed by Jean-Lou Dupont
        // http://jldupont.blogspot.com/2009/06/erlang-syntax-highlighter.html

        // According to: http://erlang.org/doc/reference_manual/introduction.html#1.5
        String keywords = "after and andalso band begin bnot bor bsl bsr bxor "
                + "case catch cond div end fun if let not of or orelse "
                + "query receive rem try when xor"
                + // additional
                " module export import define";

        List<RegExpRule> _regExpRuleList = new ArrayList<RegExpRule>();
        _regExpRuleList.add(new RegExpRule("[A-Z][A-Za-z0-9_]+", "constants"));
        _regExpRuleList.add(new RegExpRule("\\%.+", Pattern.MULTILINE, "comments"));
        _regExpRuleList.add(new RegExpRule("\\?[A-Za-z0-9_]+", "preprocessor"));
        _regExpRuleList.add(new RegExpRule("[a-z0-9_]+:[a-z0-9_]+", "functions"));
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.doubleQuotedString, "string"));
        _regExpRuleList.add(new RegExpRule(Brush.RegExpRule.singleQuotedString, "string"));
        _regExpRuleList.add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword"));
        setRegExpRuleList(_regExpRuleList);
    }
}
