package News;

/**
 * @author �ƿ�13-2  ������
 */

import java.util.Iterator;
import java.util.Vector;

import com.heaton.bot.HTMLPage;
import com.heaton.bot.HTTP;
import com.heaton.bot.Link;

public class HTMLParse {
  HTTP _http = null;
  public HTMLParse(HTTP http) {
    _http = http;
  }

  /**
   * ��Webҳ����н�����������
   */
  public void start() {
    try {
      HTMLPage _page = new HTMLPage(_http);
      _page.open(_http.getURL(), null);
      Vector _links = _page.getLinks();
      Index _index = new Index();
      Iterator _it = _links.iterator();
      int n = 0;
      while (_it.hasNext()) {
        Link _link = (Link) _it.next();
        String _herf = input(_link.getHREF().trim());
        String _title = input(_link.getPrompt().trim());
        _index.AddNews(_herf, _title);
        n++;
      }
      System.out.println("��ɨ�赽" + n + "������");
      _index.close();
    }
    catch (Exception ex) {
      System.out.println(ex);
    }
  }
  /**
   * ���java�е���������
   * @param str ���������
   * @return �������������
   */
  public static String input(String str) {
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

}
