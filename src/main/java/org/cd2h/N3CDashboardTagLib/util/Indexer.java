package org.cd2h.N3CDashboardTagLib.util;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Indexer {
	static Vector<String> queue = new Vector<String>();
	static Hashtable<String, String> crawled = new Hashtable<String, String>();
	
	public static void main(String[] args) throws IOException {
		String root = "https://covid.cd2h.org/";
		queue.add(root);
		
		IndexWriterConfig config = new IndexWriterConfig(org.apache.lucene.util.Version.LUCENE_43, new StandardAnalyzer(org.apache.lucene.util.Version.LUCENE_43));
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		config.setRAMBufferSizeMB(500);
		IndexWriter theWriter = new IndexWriter(FSDirectory.open(new File("/Users/eichmann/Documents/Components/n3c_index")), config);

		while(!queue.isEmpty()) {
			String candidate = queue.remove(0);
			System.out.println(candidate);
			crawled.put(candidate, candidate);
			try {
				Document doc = Jsoup.connect(candidate).timeout(0).get();
				
				String title = doc.title();
				System.out.println("\t" + title);
				
				String text = doc.text();
				//System.out.println(text);

				org.apache.lucene.document.Document theDocument = new org.apache.lucene.document.Document();
				theDocument.add(new StringField("url", candidate, Field.Store.YES));
				theDocument.add(new StringField("title", title, Field.Store.YES));
				theDocument.add(new TextField("content", text, Field.Store.NO));
				theWriter.addDocument(theDocument);

				for (Element link : doc.getElementsByTag("a")) {
					String href = link.attr("href");

					// canonicalize the link
					if (href.indexOf('#') > 0)
						href = href.substring(0, href.indexOf('#'));
					if (href.startsWith("/")) {
						href = root + href.substring(1);
					}
					
					if (href.startsWith(root) && !crawled.containsKey(href) && !queue.contains(href))
						queue.add(href);
				}
			} catch (IOException e) {
				System.out.println("\t skipping " + candidate);
			}
		}
		theWriter.close();
	}
}
