package syntaxhighlighter;

import static org.junit.Assert.assertTrue;

import org.jdom2.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import syntaxhighlighter.brush.BrushJava;

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
    		"/* Some multiline comment\n" + 
    		" * which span over \n" +
    		" */\n" + 
    		"   Some multi line code without any\n" +
    		"   matching word or text\n" +
    		"String someString = \"An example comment: /* example */\";\n" +
			"// The comment around this code has been commented out.\n" +
			"// /*\n" +
			"some_code(\"an example string\", true);\n" +
			"// */\";\n" +
			"some text at \n" +
			"the end";
    
    System.out.println(aJavaString);
    
    Element container = SyntaxHighlighterHtmlUtil.brush(aJavaString, new BrushJava());
    Element output = SyntaxHighlighterHtmlUtil.addGutterAndTitle(container, "Hello.java", 5, false);
    
    System.out.println(SyntaxHighlighterHtmlUtil.outputString(output));

    
  }
}
