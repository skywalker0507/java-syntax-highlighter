package syntaxhighlighter;

import java.util.List;

import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format.TextMode;

import syntaxhighlight.ParseResult;
import syntaxhighlight.Parser;
import syntaxhighlighter.brush.Brush;

/**
 * @author Holger Schimanski <holger@schimanski-web.de>
 */
public class SyntaxHighlighterHtmlUtil {

	public static String outputString(Element codePanel) {

		XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat().setTextMode(TextMode.TRIM_FULL_WHITE));
		return serializer.outputString(codePanel);

	}

	public static Element addGutterAndTitle(Element container, String aTitle, int startLine, boolean hideLinenum) {

		Element codePanel = new Element("div").setAttribute("class", "codePanel");

		if (aTitle != null) {
			Element codeHeader = new Element("div").setAttribute("class", "codeHeader");
			codeHeader.addContent(aTitle);
			codePanel.addContent(codeHeader);
		}

		Element tr = new Element("tr");
		Element table = new Element("table").addContent(tr);
		Element codeContent = new Element("div").setAttribute("class", "codeContent").addContent(table);
		codePanel.addContent(codeContent);

		if (!hideLinenum) {
			int numberOfRows = container.getChildren().size();
			Element glutter = new Element("div").setAttribute("class", "glut");
			for (int i = 0; i < numberOfRows; i++) {
				Element linenumber = new Element("div").setText(String.valueOf(startLine + i)); 
				glutter.addContent(linenumber);
			}
			tr.addContent(glutter);
		}
		tr.addContent(container);

		return codePanel;
	}

	public static Element brush(String aText, Brush aBrush) {

		Parser aParser = new SyntaxHighlighterParser(aBrush);
		List<ParseResult> aResultList = aParser.parse(null, aText);

		Element container = new Element("div").setAttribute("class", "container");

		Element currentRow = new Element("div").setAttribute("class", "line");
		container.setText("\n    ").addContent(currentRow);

		int currentIndex = 0;
		for (ParseResult parseResult : aResultList) {
			if (parseResult.getOffset() >= currentIndex) {

				String plainText = aText.substring(currentIndex, parseResult.getOffset());
				currentRow = addCodeAndRowElements(container, currentRow, plainText, "plain");

				String formattedText = aText.substring(parseResult.getOffset(), parseResult.getOffset() + parseResult.getLength());
				currentRow = addCodeAndRowElements(container, currentRow, formattedText, parseResult.getStyleKeysString());

				currentIndex = parseResult.getOffset() + parseResult.getLength();
			}
		}

		String plainTextRemaining = aText.substring(currentIndex);
		currentRow = addCodeAndRowElements(container, currentRow, plainTextRemaining, "plain");

		return container;

	}

	private static Element addCodeAndRowElements(Element container, Element currentRow, String plainText, String style) {

		while (!plainText.equals("")) {

			if (!plainText.contains("\n")) {
				Element plain = new Element("code").setAttribute("class", style);
				plain.addContent(plainText);
				currentRow.addContent(plain);
				plainText = "";
			} else if (plainText.startsWith("\n")) {
				currentRow = new Element("div").setAttribute("class", "line");
				container.addContent(currentRow);
				if (plainText.equals("\n")) {
					plainText = "";
				} else {
					plainText = plainText.substring(1);
				}
			} else {
				Element plain = new Element("code").setAttribute("class", style);
				plain.addContent(plainText.substring(0, plainText.indexOf("\n")));
				currentRow.addContent(plain);
				plainText = plainText.substring(plainText.indexOf("\n"));
			}
		}
		return currentRow;
	}

}
