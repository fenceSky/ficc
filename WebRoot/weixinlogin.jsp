<%@ page language="java" import="java.util.*,util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String state = request.getParameter("state");

	StringBuffer sbrWeiXinAuthUrl = new StringBuffer();
	boolean snsapi_base = true;

	//组装认证URL  
	final String WEIXIN_WEB_AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	final String CALL_BACK_URL = "http://www.ihaomen.com/weixincallback.jsp";
	
	sbrWeiXinAuthUrl.append(WEIXIN_WEB_AUTH_URL);
	sbrWeiXinAuthUrl.append("?");
	sbrWeiXinAuthUrl.append("appid=" + RefreshToken.APP_ID);
	sbrWeiXinAuthUrl.append("&");
	sbrWeiXinAuthUrl.append("redirect_uri="
			+ java.net.URLEncoder.encode(CALL_BACK_URL,"UTF-8"));
	sbrWeiXinAuthUrl.append("&");
	sbrWeiXinAuthUrl.append("response_type=code");
	sbrWeiXinAuthUrl.append("&");
	if (snsapi_base) {
		sbrWeiXinAuthUrl.append("scope=snsapi_base");
	} else {
		sbrWeiXinAuthUrl.append("scope=snsapi_userinfo");
	}
	sbrWeiXinAuthUrl.append("&");
	sbrWeiXinAuthUrl.append("state=" + state);
	sbrWeiXinAuthUrl.append("#wechat_redirect");

	response.sendRedirect(sbrWeiXinAuthUrl.toString());
%>


