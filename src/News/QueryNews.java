package News;

/**
 * @author 计科13-2  刘翔宇
 */

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

public class QueryNews {

  public static void main(String[] args) throws Exception{
    // 从索引目录创建索引
      IndexSearcher _searcher = new IndexSearcher("c:\\news\\index");
      // 创建标准分析器
      Analyzer analyzer = new ChineseAnalyzer();
      // 查询条件
      String line = "程序员";
      // Query是一个抽象类
      Query query = QueryParser.parse(line, "title", analyzer);

      System.out.println("Searching for: " + query.toString("title"));

      Hits hits = _searcher.search(query);
      System.out.println(hits.length() + " total matching News");

      final int HITS_PER_PAGE = 10;
      for (int start = 0; start < hits.length(); start += HITS_PER_PAGE) {
        int end = Math.min(hits.length(), start + HITS_PER_PAGE);
        for (int i = start; i < end; i++) {
          Document doc = hits.doc(i);
          String path = doc.get("path");
          if (path != null) {
            System.out.println(i + ". " + path);
          }
          else {
            String url = doc.get("url");
            if (url != null) {
              System.out.println(i + ". " + url);
              System.out.println("   - " + doc.get("title"));
            }
            else {
              System.out.println(i + ". " + "No path nor URL for this document");
            }
          }
        }
      }
      _searcher.close();

  }

}