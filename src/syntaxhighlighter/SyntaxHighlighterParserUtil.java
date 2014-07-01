package syntaxhighlighter;

import java.util.List;

import syntaxhighlight.ParseResult;
import syntaxhighlight.Parser;
import syntaxhighlighter.beans.CodeContainer;
import syntaxhighlighter.brush.Brush;

/**
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class SyntaxHighlighterParserUtil {



	public static CodeContainer brush(String aText, Brush aBrush) {

		Parser aParser = new SyntaxHighlighterParser(aBrush);
		List<ParseResult> aResultList = aParser.parse(null, aText);

		CodeContainer container = new CodeContainer();

		int currentIndex = 0;
		for (ParseResult parseResult : aResultList) {
			if (parseResult.getOffset() >= currentIndex) {

				String plainText = aText.substring(currentIndex, parseResult.getOffset());
				addCodeAndRowElements(container, plainText, "plain");

				String formattedText = aText.substring(parseResult.getOffset(), parseResult.getOffset() + parseResult.getLength());
				addCodeAndRowElements(container, formattedText, parseResult.getStyleKeysString());
				
				currentIndex = parseResult.getOffset() + parseResult.getLength();
			}
		}

		String plainTextRemaining = aText.substring(currentIndex);
		addCodeAndRowElements(container, plainTextRemaining, "plain");

		return container;

	}

	private static void addCodeAndRowElements(CodeContainer container, String plainText, String style) {

		while (!plainText.equals("")) {

			if (!plainText.contains("\n")) {
				container.addCode(style, plainText);
				plainText = "";
			} else if (plainText.startsWith("\n")) {
				container.newCodeRow();
				if (plainText.equals("\n")) {
					plainText = "";
				} else {
					plainText = plainText.substring(1);
				}
			} else {
				container.addCode(style, plainText.substring(0, plainText.indexOf("\n")));
				plainText = plainText.substring(plainText.indexOf("\n"));
			}
		}

	}

}
