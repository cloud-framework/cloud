package test.cloud.spider;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.cloud.spider.util.SpiderUtils;

import cn.egame.common.util.Utils;

public class Test {
	public static void main(String[] args) throws IOException {
		
		String douban250Url = "http://movie.douban.com/top250";

		int startNo = 0;
		
		
//		Document doc = Jsoup.connect(douban250Url + "?start=" + startNo +"&timestamp="+System.currentTimeMillis())
//				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36")
//				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//				.header("Accept-Encoding", "gzip,deflate,sdch")
//				.header("Accept-Language", "en-US,en;q=0.8,ja;q=0.6,zh-CN;q=0.4,zh;q=0.2")
//				.header("Connection", "keep-alive")
//				.header("Cache-Control", "max-age=0")
//				.header("Cookie", "ll=\"118159\"; __ozlvd1511=1385995416; viewed=\"2348372_3295340_2243615_24541955\"; f2c=1407278847965; f2t=1407278852939; f2p=1407278867348; f2b=1407278906269; f2d=1407546090176; dbcl2=\"49013483:o7eP1pJ30Kk\"; bid=\"1AaC0vQ+1W4\"; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1408377390%2C%22http%3A%2F%2Fwww.baidu.com%2F%22%5D; ck=\"NEse\"; _dc=1; push_noty_num=0; push_doumail_num=18; _pk_id.100001.4cf6=0613615712cf97b6.1402414502.16.1408379903.1408316702.; _pk_ses.100001.4cf6=*; __utma=30149280.72395377.1382019321.1408316144.1408377390.28; __utmb=30149280.8.10.1408377390; __utmc=30149280; __utmz=30149280.1408283761.26.22.utmcsr=user.qzone.qq.com|utmccn=(referral)|utmcmd=referral|utmcct=/670522855/infocenter; __utmv=30149280.4901; __utma=223695111.72395377.1382019321.1408316636.1408377390.45; __utmb=223695111.6.10.1408377390; __utmc=223695111; __utmz=223695111.1408316636.44.38.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _ga=GA1.2.72395377.1382019321")
//				.get();

		Response response = null;
		try {
			while(true){
				Thread.sleep(30);
//				String cookieVal = "ll=\"118159\"; __ozlvd1511=1385995416; viewed=\"2348372_3295340_2243615_24541955\"; f2c=1407278847965; f2t=1407278852939; f2p=1407278867348; f2b=1407278906269; f2d=1407546090176; dbcl2=\"49013483:o7eP1pJ30Kk\"; bid=\"1AaC0vQ+1W4\"; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1408377390%2C%22http%3A%2F%2Fwww.baidu.com%2F%22%5D; ck=\"NEse\"; _dc=1; push_noty_num=0; push_doumail_num=18; _pk_id.100001.4cf6=0613615712cf97b6.1402414502.16.1408379903.1408316702.; _pk_ses.100001.4cf6=*; __utma=30149280.72395377.1382019321.1408316144.1408377390.28; __utmb=30149280.8.10.1408377390; __utmc=30149280; __utmz=30149280.1408283761.26.22.utmcsr=user.qzone.qq.com|utmccn=(referral)|utmcmd=referral|utmcct=/670522855/infocenter; __utmv=30149280.4901; __utma=223695111.72395377.1382019321.1408316636.1408377390.45; __utmb=223695111.6.10.1408377390; __utmc=223695111; __utmz=223695111.1408316636.44.38.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _ga=GA1.2.72395377.1382019321";
//				String cookieVal = "";
				String cookieVal = "bid=\""+SpiderUtils.getRandomStr(11)+"\"; ";
//						"ll=\"118159\"; helperVersion=2014.02.13; __utma=223695111.1229132589.1387172933.1394014864.1397541939.17; __utmz=223695111.1394014864.16.15.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/ulink; __utmv=223695111.|1=Addon=CR%20Y2014.02.13%2040425=1; viewed=\"25826578_25852441_3420144_1437973_6082808_25815348_25746084_1052241_1792387_1102259\"; dbcl2=\"49013483:o7eP1pJ30Kk\"; ct=y; f2t=1408337202346; f2d=1408337220557; f2b=1408337220561; f2c=1408337220562; f2p=1408337235357; ";
//						"bid=\"Lo8KwahPOEA\"; ";
//						"bid=\"ssadsfasdfA\"; ";
//						"bid=\"11adasdsd11\"; ";
//						"f2b=1407278906269; ";
				
				
						
//				"ck=\"NEse\"; _dc=1; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1408509164%2C%22http%3A%2F%2Fwww.baidu.com%2Fs%3Fwd%3D%25E8%25B1%2586%25E7%2593%25A3%25E7%2594%25B5%25E5%25BD%25B1250%26rsv_spt%3D1%26issp%3D1%26rsv_bp%3D0%26ie%3Dutf-8%26tn%3Dbaiduhome_pg%26inputT%3D3292%22%5D; push_noty_num=0; push_doumail_num=18; _pk_id.100001.4cf6=04318808201810d7.1402016320.25.1408509248.1408502778.; _pk_ses.100001.4cf6=*; __utma=30149280.1629396045.1387172933.1397103835.1397530575.5; __utmb=30149280.6.10.1408509205; __utmc=30149280; __utmz=30149280.1397530575.5.4.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%E5%B7%A5%E4%BD%9C%E5%90%8E%E6%80%8E%E4%B9%88%E6%8F%90%E5%8D%87%E8%87%AA%E5%B7%B1; __utma=223695111.1229132589.1387172933.1394014864.1397541939.17; __utmb=223695111.5.10.1408509164; __utmc=223695111; __utmz=223695111.1394014864.16.15.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/ulink; __utmv=223695111.|1=Addon=CR%20Y2014.02.13%2040425=1; _ga=GA1.2.1629396045.1387172933";

				Connection conn = Jsoup.connect(douban250Url + "?start=" + startNo +"&timestamp="+System.currentTimeMillis())
//						.header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36")
//						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//						.header("Accept-Encoding", "gzip,deflate,sdch")
//						.header("Accept-Language", "en-US,en;q=0.8,ja;q=0.6,zh-CN;q=0.4,zh;q=0.2")
//						.header("Connection", "keep-alive")
//						.header("Cache-Control", "max-age=0")
						.header("Cookie", cookieVal);
				
				conn.method(Method.GET);  
				conn.followRedirects(false);  
				response = conn.execute();  
				System.out.println("xxx-cookies-xxxbegin:"+response.cookies()+":xxx-cookies-xxx-end");
				System.out.println(new String(response.bodyAsBytes()));
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("------------------");
			if(response!=null){
				System.out.println(new String(response.bodyAsBytes()));
			}
		}
				
	}
}
