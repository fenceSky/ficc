package util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetIpadress {
	
	public static String getAddress(String ip){
		try {
			URL url = new URL("http://whois.pconline.com.cn/ip.jsp?ip=" + ip);
			HttpURLConnection connect = (HttpURLConnection) url
					.openConnection();
			InputStream is = connect.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buff = new byte[256];
			int rc = 0;
			while ((rc = is.read(buff, 0, 256)) > 0) {
				outStream.write(buff, 0, rc);
			}
			byte[] b = outStream.toByteArray();
			
			// 关闭
			outStream.close();
			is.close();
			connect.disconnect();
			String ipaddress = new String(b,"gb2312");
			return ipaddress;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String[] processAddress(String address){
		String[] addresses = new String[3];
		addresses[0] = "";
		addresses[1] = "";
		addresses[2] = "";
		
		String[] firstaddr = address.split("\\s");
		if(firstaddr.length == 2)
		{
			String[] secondaddr = firstaddr[0].split("省");
			if(secondaddr.length == 2){
				addresses[0] = secondaddr[0];
				addresses[1] = secondaddr[1];
			}else{
				secondaddr = firstaddr[0].split("市");
				if(secondaddr.length == 2){
					addresses[0] = secondaddr[0];
					addresses[1] = secondaddr[1];
				}else{
					addresses[0] = secondaddr[0];
					addresses[1] = firstaddr[0];
				}
			}
			addresses[2] = firstaddr[1];
		}
		
		return addresses;
	}
	
	public static void main(String[] argvs) {
		
	}
}
