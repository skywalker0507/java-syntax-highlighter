package syntaxhighlighter;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import syntaxhighlight.ParseResult;
import syntaxhighlight.Parser;
import syntaxhighlight.SyntaxHighlighter;
import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.BrushJava;
import syntaxhighlighter.theme.ThemeDefault;
import static org.junit.Assert.*;

/**
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SyntaxHighlighterTest {

  public SyntaxHighlighterTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void test() {
    // there is no testing for brushes in original JavaScript version of SyntaxHighlighter and so far there is no plan for me to create some myself.
    // if you want something more reliable, you can choose Java Prettify (http://java-prettify.googlecode.com), which has much more tests.
    assertTrue(true);
    
    String aJavaString = 
    		"/* Some mulitline comment\n" + 
    		" * which span over \n" +
    		" */\n" + 
    		"String someString = \"An example comment: /* example */\";\n" +
			"// The comment around this code has been commented out.\n" +
			"// /*\n" +
			"some_code(\"an example string\", true);\n" +
			"// */\";\n";
    
    System.out.println(aJavaString);
    
    Parser aParser = new SyntaxHighlighterParser(new BrushJava());
    List<ParseResult> aResultList = aParser.parse(null, aJavaString);
        
    Element container = new Element("div").setAttribute("class", "container");

    int lineNumber = 1;
    Element currentRow = new Element("div").setAttribute("class", "line number" + lineNumber);
    container.setText("\n   ").addContent(currentRow);
    
    int currentIndex = 0;
    for (ParseResult parseResult : aResultList) {
    	if ( parseResult.getOffset() >= currentIndex){
    		
    		String plainText = aJavaString.substring(currentIndex, parseResult.getOffset());
    		
    		while (!plainText.equals("")){
    			
    			if ( !plainText.contains("\n") ) {
    				currentRow.addContent(plainText);
    				plainText = "";
    			} else if (plainText.startsWith("\n")){
        			lineNumber++;
        			currentRow = new Element("div").setAttribute("class", "line number" + lineNumber);
        			container.addContent("\n   ").addContent(currentRow);
        		    if ( plainText.equals("\n") ){
        		    	plainText = "";
        		    } else {
						plainText = plainText.substring(1);
					}
    			} else {
    				currentRow.addContent(plainText.substring(0, plainText.indexOf("\n")));
    				plainText = plainText.substring(plainText.indexOf("\n"));
    			}
    		}
    		
    		
    		
//    		for (int i = 1; i < textLines.length; i++) {
//    			currentRow.addContent("\n");
//    			lineNumber++;
//    			currentRow = new Element("div").setAttribute("class", "line number" + lineNumber);
//    			currentRow.addContent(textLines[i]);
//    		    container.addContent(currentRow);
//    		}
    		
    		Element code = new Element("code").setAttribute("class", parseResult.getStyleKeysString());
    		code.addContent(aJavaString.substring(parseResult.getOffset(), parseResult.getOffset() + parseResult.getLength()));
    		currentRow.addContent(code);
    		
    		currentIndex = parseResult.getOffset() + parseResult.getLength();
    	}
	}
    container.addContent("\n");
    
    XMLOutputter serializer = new XMLOutputter();
    System.out.println(serializer.outputString(container));
    
//    for (ParseResult parseResult : aResultList) {
//    	System.out.println(parseResult.getStyleKeysString() + "  " + parseResult.getOffset() + ", " + aJavaString.substring(parseResult.getOffset(), parseResult.getLength()+ parseResult.getOffset()));
//	}
  }
}
