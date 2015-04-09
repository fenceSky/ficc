package util;

import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class RefreshToken extends TimerTask {
	private static boolean isRunning = false;

	public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access
	public static final String APP_ID = "wxaa08dedd6d97a5ae";
	public static final String SECRET = "cc0025541090af9348dbdd85d66595b9";
	public static String current_token = "";
	
	public static String getToken(String apiurl, String appid, String secret){
		String turl = String.format(
				"%s?grant_type=client_credential&appid=%s&secret=%s", apiurl, appid, secret);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(turl);
		
		JSONParser jsonparer = new JSONParser();// 初始化解析json格式的对象
		String result = null;
		
		try{
			HttpResponse res = client.execute(get);
			String responseContent = null; // 响应内容
			
			HttpEntity entity = res.getEntity();
			
			responseContent = EntityUtils.toString(entity, "UTF-8");
			
			JSONObject json = (JSONObject) jsonparer.parse(responseContent);
			
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				if (json.get("errcode") != null){
					// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
				}
				else{// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
					result = json.get("access_token").toString();
					System.out.println(result);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			// 关闭连接 ,释放资源
			client.getConnectionManager().shutdown();
			return result;
		}
	}
	
	
	public void run() {
		if (!isRunning) {
			isRunning = true;
			
			String token = getToken(GET_TOKEN_URL, APP_ID, SECRET);
			
			if(token != null)
				current_token = token;

			isRunning = false;
		} else {
			//log.debug("上一次任务执行还未结束..."); // 上一次任务执行还未结束
		}
	}
}
