package News;

/**
 * @author �ƿ�13-2  ������
 */

import com.heaton.bot.HTTP;
import com.heaton.bot.HTTPSocket;
import com.heaton.bot.ISpiderReportable;
import com.heaton.bot.IWorkloadStorable;
import com.heaton.bot.Spider;
import com.heaton.bot.SpiderInternalWorkload;

public class Searcher
    implements ISpiderReportable {
  public static void main(String[] args) throws Exception {

   //�������ڴ���
   IWorkloadStorable wl = new SpiderInternalWorkload();

    Searcher _searcher = new Searcher();

    //����Spider����
    Spider _spider
        = new Spider(_searcher, "http://www.jamessc.com/news.htm",
                     new HTTPSocket(), 100, wl);
    _spider.setMaxBody(100);
    _spider.start();
  }

  public boolean foundInternalLink(String url) {
    return false;
  }

  public boolean foundExternalLink(String url) {
    return false;
  }

  public boolean foundOtherLink(String url) {
    return false;
  }

  public void processPage(HTTP http) {
    System.out.println(http.getURL());
    //new HTMLParse(http).start();
  }

  public void completePage(HTTP http, boolean error) {
  }

  public boolean getRemoveQuery() {
    return true;
  }

  public void spiderComplete() {
  }
}
