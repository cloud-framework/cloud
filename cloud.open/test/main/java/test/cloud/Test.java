package test.cloud;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {
	public static void main(String[] args) {
		Document detailDoc = null;
		String url = "https://www.google.com/cse?cx=006369457434482665413:mnzddbuz2ke&ie=UTF-8&q=&ref=#gsc.q=%E9%A9%AF%E9%BE%99%E9%AB%98%E6%89%8B2";
		String url2 = "http://torrentproject.com/?t=%E9%A9%AF%E9%BE%99%E9%AB%98%E6%89%8B2";
		try {
			detailDoc = Jsoup.connect(url2)
					.timeout(30000)
			// .header("User-Agent",
			// UserAgentAry[Utils.getRandom(UserAgentAry.length)])
			// .header("Accept",
			// "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
			// .header("Accept-Encoding", "gzip,deflate,sdch")
			// .header("Accept-Language",
			// "en-US,en;q=0.8,ja;q=0.6,zh-CN;q=0.4,zh;q=0.2")
			// .header("Connection", "keep-alive")
			// .header("Cache-Control", "max-age=0")
			// .header("Cookie", "bid=\""+SpiderUtils.getRandomStr(11)+"\"; ")
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(detailDoc.toString());
	}
}
