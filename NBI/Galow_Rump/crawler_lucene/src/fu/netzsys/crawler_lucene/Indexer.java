package fu.netzsys.crawler_lucene;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	String indexPath;
	Directory indexDir;
	Analyzer analyzer;

	public Indexer() {
		indexPath = System.getProperty("user.dir") + "\\lucene";
		try {
			File test = new File(indexPath);
			System.out.println(test.getAbsoluteFile());
			indexDir = FSDirectory.open(new File(indexPath));
			analyzer = new StandardAnalyzer(Version.LUCENE_45);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAllToIndex(URLInformation toIndexContent) throws IOException {
		
		addToIndex(toIndexContent);
		
		for(int i=0;i<toIndexContent.getImages().size();i++){
			this.addImgToIndex(toIndexContent.getURL(), toIndexContent.getImages().get(i));
		}
	}

	public void addToIndex(URLInformation toIndexContent) throws IOException {
		IndexWriter writer = null;
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_45,
				analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);

		// schau mal vorher nach ob du es schon im index hast hast
		try {
			writer = new IndexWriter(indexDir, iwc);
			Document doc = new Document();
			doc.add(new StringField("type", "url",
					Field.Store.YES));
			doc.add(new StringField("url", toIndexContent.getURL(),
					Field.Store.YES));
			doc.add(new StringField("title", toIndexContent.getTitle(),
					Field.Store.YES));
			doc.add(new TextField("content", toIndexContent.getContent(),
					Field.Store.YES));
			doc.add(new StringField("meta", toIndexContent.getMeta(),
					Field.Store.YES));

			writer.addDocument(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public void addImgToIndex(String parentUrl, ImgInfo img) throws IOException {
		IndexWriter writer = null;
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_45,
				analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);

		// schau mal vorher nach ob du es schon im index hast hast
		try {
			writer = new IndexWriter(indexDir, iwc);
			Document doc = new Document();
			doc.add(new StringField("type", "img",
					Field.Store.YES));
			doc.add(new StringField("url", parentUrl,
					Field.Store.YES));
			doc.add(new StringField("src", img.getSrc(),
					Field.Store.YES));			
			doc.add(new StringField("alt", img.getAlt(),
					Field.Store.YES));
			
			writer.addDocument(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
