# parserXmlSaxDBLP


### USAGE:

Donwload **dblp-2016-10-01.dtd** and **dblp-2016-11-02.xml** from http://dblp.org/xml/release/

More info: http://dblp.uni-trier.de/faq/

Please, note the default parser from dblp uses Document Object Model (DOM), that causes some difficulty for it use, because the XML file is very huge and has a large number of entities to parsing.

This code (in Java) uses Simple API for XML (SAX). This API provides a mechanism for reading data from an XML document that is an alternative to the solution provided by the Document Object Model (DOM). While DOM operates on the document as a whole, i.e. building a full AST of an XML document, SAX parsers operate on each piece of the XML document sequentially, issuing parsing events while making single pass through the input stream.

Check out: https://en.wikipedia.org/wiki/Simple_API_for_XML

Creation year: 2016. The parser may no work as expected presently.
