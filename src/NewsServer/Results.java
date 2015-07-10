package NewsServer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

/**
 * @author �ƿ�13-2  ������
 */

public class Results
    extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html; charset=GBK";
  //Initialize global variables
  public void init() throws ServletException {
  }

  //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    String QC = request.getParameter("QueryContent");
    if (QC == null) {
      QC = "";
    }
    else {
      QC = input(QC);
    }
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    try {
      Search(QC, out);
    }
    catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void Search(String qc, PrintWriter out) throws Exception {
    // ������Ŀ¼��������
    IndexSearcher _searcher = new IndexSearcher("c:\\news\\index");
    // ������׼������
    Analyzer analyzer = new ChineseAnalyzer();
    // ��ѯ����
    String line = qc;
    // Query��һ��������
    Query query = QueryParser.parse(line, "title", analyzer);

    out.println("<html>");
    out.println("<head><title>�������</title></head>");
    out.println("<body bgcolor=#ffffff>");
    out.println("<center>" +
                "<form action='/NewsServer/results' method='get'>" +
                "<font face='��������' color='#3399FF'>������������</font>:" +
                "<input type='text' name='QueryContent' size='20'>" +
                "<input type='submit' name='submit' value='��ʼ����'>" +
                "</form></center>"
                );
    out.println("<p>�����ؼ��֣�<font color=red>" + query.toString("title") +
                "</font></p>");
    Hits hits = _searcher.search(query);
    out.println(" �ܹ��ҵ�<font color=red>" + hits.length() + "</font>������<br>");

    final int HITS_PER_PAGE = 10;
    for (int start = 0; start < hits.length(); start += HITS_PER_PAGE) {
      int end = Math.min(hits.length(), start + HITS_PER_PAGE);
      for (int i = start; i < end; i++) {
        Document doc = hits.doc(i);
        String url = doc.get("url");
        if (url != null) {
          out.println( (i + 1) + " <a href='" + url + "'>" +
                      replace(doc.get("title"), qc) +
                      "</a><br>");
        }
        else {
          System.out.println("û���ҵ���");
        }
      }
    }
    out.println("</body></html>");
    _searcher.close();
  };

  public String input(String str) {
    String temp = null;
    if (str != null) {
      try {
        temp = new String(str.getBytes("ISO8859_1"));
      }
      catch (Exception e) {
      }
    }
    return temp;
  }

  public String replace(String title, String keyword) {
    return title.replaceAll(keyword, "<font color='red'>" + keyword + "</font>");
  };

  //Clean up resources
  public void destroy() {
  }
}