package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.WeixinArticle;

public class SpiderService {

	public String captureHtml(String url_str){
		
		String buf = "";
		
		for(int i = 0; i < 3; i++){
			try{
				
				URL url = new URL(url_str);
				HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
				
				InputStreamReader input = new InputStreamReader(
						httpConn.getInputStream(), "utf-8");
				
				BufferedReader bufReader = new BufferedReader(input);
				String line = "";
				StringBuilder contentBuf = new StringBuilder();
				
				while ((line = bufReader.readLine()) != null) {
					contentBuf.append(line);
				}
				
				bufReader.close();
				input.close();
				httpConn.disconnect();
				
				buf = contentBuf.toString();
				break;
			}catch(Exception e){
				continue;
			}
		}
		
		return buf;
	}
	
	public WeixinArticle parseWeixinArticle(String content){
		WeixinArticle wa = new WeixinArticle();
		
		Matcher title_matcher = Pattern.compile("<h2 class=\"rich_media_title\" id=\"activity-name\">(.*?)</h2>").matcher(content);
		while(title_matcher.find()){
			System.out.println("title:\t" + title_matcher.group(1).trim());
			break;
		}
		
		Matcher date_matcher = Pattern.compile("<em id=\"post-date\" class=\"rich_media_meta text\">(.*?)</em>").matcher(content);
		while(date_matcher.find()){
			System.out.println("date:\t" + date_matcher.group(1).trim());
			break;
		}
		
		Matcher publisher_matcher = Pattern.compile("id=\"post-user\">(.*?)</a>").matcher(content);
		while(publisher_matcher.find()){
			System.out.println("publisher:\t" + publisher_matcher.group(1).trim());
			break;
		}
		
		/*
	
		Matcher content_matcher = Pattern.compile("<h2 class=\"rich_media_title\" id=\"activity-name\">(.*?)</h2>").matcher(content);
		while(content_matcher.find()){
			System.out.println("content:\t" + content_matcher.group(1).trim());
		}*/
		
		return wa;
	}
	
	
	
	public static void main(String[] argvs){
		SpiderService ss = new SpiderService();
		String result = "";
		result = ss.captureHtml("http://mp.weixin.qq.com/s?__biz=MjM5MDg3NDQ3MQ==&mid=200455122&idx=4&sn=673e587a43e97d14f408ad0047a8bb9f&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
		
		ss.parseWeixinArticle(result);
	}
}
