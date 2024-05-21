package com.example.spring.HtmlToPDF;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;





/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        File htmlFile = new File("C:\\Users\\Affan\\Documents\\Table.html");
        Document document = Jsoup.parse(htmlFile,"UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try {
        	OutputStream os = new FileOutputStream("C:\\Users\\Affan\\Documents\\Table1.pdf");
			ITextRenderer renderer = new ITextRenderer();
			SharedContext context = renderer.getSharedContext();
			context.setPrint(true);
			context.setInteractive(false);
			String baseUrl = FileSystems.getDefault().getPath("C:\\Users\\Affan\\Documents")
								.toUri().toURL().toString();
			renderer.setDocumentFromString(document.html(),baseUrl);
			renderer.layout();
			renderer.createPDF(os);
			System.out.println("Done");
		} catch (Exception e) {
			System.out.println(e);
		}
    }
}
