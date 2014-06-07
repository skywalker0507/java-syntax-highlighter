# Java Syntax Highlighter #

## Overview

This library is a java port of [SyntaxHighlighter](http://alexgorbatchev.com/SyntaxHighlighter), the current version ported is 3.0.83. The copyright holder of the SyntaxHighlighter is Alex Gorbatchev. It is dual licensed under the MIT and LGPL licenses. This port is written by Chan Wai Shing (cws1989@gmail.com) distributed under the MIT and LGPL licenses. 

This repository is an import from https://code.google.com/p/java-syntax-highlighter/


## Configurations

-    Allows you to change the first (starting) line number.
-    Allows you to turn gutter with line numbers on and off.
-    Allows you to highlight one or more lines to focus user's attention.
-    Allows you to highlight a mixture of HTML/XML code and a script which is very common in web development. 



## Use the Parser Alone

Besides using this library with GUI (see below), you can use only the parser without GUI. The class syntaxhighlighter.SyntaxHighlighterParser has implemented the Parser interface. You can initiate the class with brushes(programming language) specified and then invoke the Parser#parse(String, String) to parse the content. See also the ParseResult to know how to use the parsed results.


```
#!java
    Parser aParser = new SyntaxHighlighterParser(new BrushJava());
    List<ParseResult> aResultList = aParser.parse(null, aJavaString);
    
    for (ParseResult parseResult : aResultList) {
       ...
    }
```


For more details, you can have a look to syntaxhighlighter.parser.SyntaxHighlighter#parse(...). 


## Use as a Swing component


```
#!java
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import syntaxhighlight.SyntaxHighlighter;
import syntaxhighlighter.brush.*;
import syntaxhighlighter.SyntaxHighlighterParser;
import syntaxhighlighter.theme.ThemeRDark;

public class Example {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        // the SyntaxHighlighter parser
        SyntaxHighlighterParser parser = new SyntaxHighlighterParser(new BrushXml());
        // turn HTML script on
        parser.setHtmlScript(true);
        // set HTML Script brushes
        parser.setHTMLScriptBrushes(Arrays.asList(new BrushCss(), new BrushJScript(), new BrushPhp()));

        // initialize the highlighter and use RDark theme
        SyntaxHighlighter highlighter = new SyntaxHighlighter(parser, new ThemeRDark());
        // set the line number count from 10 instead of 1
        highlighter.setFirstLine(10);
        // set to highlight line 13, 27, 28, 38, 42, 43 and 53
        highlighter.setHighlightedLineList(Arrays.asList(13, 27, 28, 38, 42, 43, 53));
        try {
          highlighter.setContent(new File("test.html"));
        } catch (IOException ex) {
          Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(highlighter);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
      }
    });
  }
}

```