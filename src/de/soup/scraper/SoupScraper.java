package de.soup.scraper;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.CompactXmlSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class SoupScraper {

	private Writer output;
	private final String URL = "http://suppenladen.com/index.php/aktuelle_wochenkarte.html";
	public Node currentDocument;

	public void scrape() {
		System.setProperty("javax.xml.transform.TransformerFactory",
				"net.sf.saxon.TransformerFactoryImpl");

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;

		try {
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			fac.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd",
					false);
			DocumentBuilder db = fac.newDocumentBuilder();

			Document sourceDoc = db.parse(new InputSource(new StringReader(
					output.toString())));
			Document xslDoc = db.parse(new File("files/transform.xsl"));
			Source xmlSource = new DOMSource(sourceDoc);
			Source xslSource = new DOMSource(xslDoc);
			transformer = tFactory.newTransformer(xslSource);

			StringWriter sw = new StringWriter();

			transformer.transform(xmlSource, new StreamResult(sw));
			SoupDAO.getInstance().setThisWeek(sw.toString());

		} catch (Exception e) {
			// okay, this is very lazy...
			e.printStackTrace();
		}

	}

	public void cleanHTML() {
		final HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		props.setPruneTags("script, style");
		props.setOmitComments(true);
		props.setCharset("UTF-8");
		props.setOmitDoctypeDeclaration(true);
		props.setOmitHtmlEnvelope(true);
		props.setRecognizeUnicodeChars(true);
		try {
			TagNode node = cleaner.clean(new java.net.URL(URL));
			TagNode getMe = node.findElementByAttValue("id", "main",
					true, true);

			CompactXmlSerializer serializer = new CompactXmlSerializer(props);
			output = new StringWriter();
			serializer.write(getMe, output, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
