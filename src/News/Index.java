package News;

/**
 * @author º∆ø∆13-2  ¡ıœË”Ó
 */

import java.io.IOException;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class Index {

  IndexWriter _writer = null;

  Index() throws Exception {
    _writer = new IndexWriter("c:\\News\\index",
                              new ChineseAnalyzer(), true);
  }

  /*
    void AddNews(String url,String title)throws Exception {
      Document _doc  = new Document();
      _doc.add(Field.Text("title",title));
      _doc.add(Field.UnIndexed("url",url));
      _writer.addDocument(_doc);
    }
    void close()throws Exception{
      _writer.optimize();
      System.out.println(_writer.docCount());
      _writer.close();
    }*/
}
