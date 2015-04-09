package service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import util.RefreshToken;

public class WeixinService {  
    HttpServletRequest final_request;   
    HttpServletResponse final_response;  
    
    public WeixinService(HttpServletRequest request, HttpServletResponse response){
    	this.final_request = request;
    	this.final_response = response;
    }
    
    private static class TrustAnyTrustManager implements X509TrustManager {
    	  
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
  
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
  
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
  
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    
    public static byte[] post(String url, String content, String charset){
        SSLContext sc;
		try {
			sc = SSLContext.getInstance("SSL");
		
	        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
	                new java.security.SecureRandom());
	  
	        URL console = new URL(url);
	        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
	        conn.setSSLSocketFactory(sc.getSocketFactory());
	        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
	        conn.setDoOutput(true);
	        conn.connect();
	        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	        out.write(content.getBytes(charset));
	        // 刷新、关闭
	        out.flush();
	        out.close();
	        InputStream is = conn.getInputStream();
	        
	        if (is != null) {
	            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = is.read(buffer)) != -1) {
	                outStream.write(buffer, 0, len);
	            }
	            is.close();
	            return outStream.toByteArray();
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return null;
    }
	
    public void valid(){  
        String echostr=final_request.getParameter("echostr");  
        if(null==echostr||echostr.isEmpty()){  
            responseMsg();  
        }else{
        	this.print(echostr);  
        	/*
            if(this.checkSignature()){  
                this.print(echostr);  
            }else{  
                this.print("error");                                                                                                                                                                                                                                                                                                                                           
            } */ 
        }  
    }  
    //自动回复内容  
    public void responseMsg(){  
        String postStr=null;  
        try{  
            postStr=this.readStreamParameter(final_request.getInputStream());  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        //System.out.println(postStr);  
        if (null!=postStr&&!postStr.isEmpty()){  
            Document document=null;  
            try{  
                document = DocumentHelper.parseText(postStr);  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
            if(null==document){  
                this.print("");  
                return;  
            }  
            Element root=document.getRootElement();
            
            String MsgType = root.elementText("MsgType");
            
            if(MsgType.equals("text")){
            	transferCustomerService(root);
            }else if(MsgType.equals("event")){
            	responseEventMsg(root);
            }else{
            	transferCustomerService(root);
            }
            
        }else {  
            this.print("");  
        }  
    }  
    
    public void transferCustomerService(Element root){  
    	String fromUsername = root.elementText("FromUserName");  
        String toUsername = root.elementText("ToUserName");  

        String textTpl = "<xml>"+  
                "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
                "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
                "<CreateTime>%3$s</CreateTime>"+  
                "<MsgType><![CDATA[transfer_customer_service]]></MsgType>"+  
                "</xml>";               
      
        String time = new Date().getTime()+"";  
        String resultStr = textTpl.format(textTpl, fromUsername, toUsername, time);  
        this.print(resultStr);  
    }
    
    public void responseTextMsg(Element root){  
    	String fromUsername = root.elementText("FromUserName");  
        String toUsername = root.elementText("ToUserName");  
        String contentStr = "欢迎关注凌云财经！您可以在这里得到最及时的财经资讯，也可以参与到丰富多彩的凌云财经活动中！";  
        sendTextMsg(fromUsername, toUsername, contentStr);
    }
    
    public void responseEventMsg(Element root){  
    	String eventtype = root.elementText("Event");
    	String fromUsername = root.elementText("FromUserName");  
        String toUsername = root.elementText("ToUserName");  
    	
    	if(eventtype.equals("CLICK")){
    		String eventkey = root.elementText("EventKey");
    		if(eventkey.equals("START_NEW_ACTIVITY")){
    			String contentStr = "欢迎关注凌云财经！您可以在这里得到最及时的财经资讯，也可以参与到丰富多彩的凌云财经活动中！";  
    	        sendTextMsg(fromUsername, toUsername, contentStr);
    		}else if(eventkey.equals("ACCOUNT_VERIFY")){
    			String contentStr = "感谢您支持凌云财经活动，我们的客服将马上联系您发起活动的事宜！";  
    	        sendTextMsg(fromUsername, toUsername, contentStr);
    		}
    	}else if(eventtype.equals("subscribe")){
    		String contentStr = "欢迎关注领金社区！";  
	        sendTextMsg(fromUsername, toUsername, contentStr);
    	}
    }
    
    void sendTextMsg(String fromUsername, String toUsername, String contentStr){
    	 String textTpl = "<xml>"+  
                 "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
                 "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
                 "<CreateTime>%3$s</CreateTime>"+  
                 "<MsgType><![CDATA[%4$s]]></MsgType>"+  
                 "<Content><![CDATA[%5$s]]></Content>"+  
                 "<FuncFlag>0</FuncFlag>"+  
                 "</xml>";               
       
         String msgType = "text";  
         String time = new Date().getTime()+"";  
         String resultStr = textTpl.format(textTpl, fromUsername, toUsername, time, msgType, contentStr);  
         this.print(resultStr);  
         
         //System.out.println(fromUsername);
         //System.out.println(RefreshToken.current_token);
         
         
         UserService us = new UserService();
         User user = us.getUserByOpenid(fromUsername);
         if(user == null){
        	 us.registerWeixinUser(fromUsername);
         }
    }
    
    //微信接口验证  
    public boolean checkSignature(){  
        String signature = final_request.getParameter("signature");  
        String timestamp = final_request.getParameter("timestamp");  
        String nonce = final_request.getParameter("nonce");  
        String token= RefreshToken.current_token;  
        String[] tmpArr={token,timestamp,nonce};  
        Arrays.sort(tmpArr);  
        String tmpStr=this.ArrayToString(tmpArr);  
        tmpStr=this.SHA1Encode(tmpStr);  
        if(tmpStr.equalsIgnoreCase(signature)){  
            return true;  
        }else{  
            return false;  
        }  
    }  
    //向请求端发送返回数据  
    public void print(String content){  
        try{  
            final_response.getWriter().print(content);  
            final_response.getWriter().flush();  
            final_response.getWriter().close();  
        }catch(Exception e){  
              
        }  
    }  
    //数组转字符串  
    public String ArrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
    //sha1加密  
    public String SHA1Encode(String sourceString) {  
        String resultString = null;  
        try {  
           resultString = new String(sourceString);  
           MessageDigest md = MessageDigest.getInstance("SHA-1");  
           resultString = byte2hexString(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }  
    public final String byte2hexString(byte[] bytes) {  
        StringBuffer buf = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            if (((int) bytes[i] & 0xff) < 0x10) {  
                buf.append("0");  
            }  
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
        }  
        return buf.toString().toUpperCase();  
    }  
    //从输入流读取post参数  
    public String readStreamParameter(ServletInputStream in){  
        StringBuilder buffer = new StringBuilder();  
        BufferedReader reader=null;  
        try{  
            reader = new BufferedReader(new InputStreamReader(in));  
            String line=null;  
            while((line = reader.readLine())!=null){  
                buffer.append(line);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(null!=reader){  
                try {  
                    reader.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return buffer.toString();  
    }  
}
