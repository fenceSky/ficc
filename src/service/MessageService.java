package service;

import java.sql.Timestamp;
import java.util.ArrayList;

import model.Message;
import model.User;
import dao.*;

public class MessageService {
	public ArrayList<Message> findMessagesByToUser(int to_user_id){
		return (ArrayList<Message>) new MessageDAO().findByToUserId(to_user_id);
	}
	
	public void changeMessageState(int message_id, int state){
		
		MessageDAO md = new MessageDAO();
		Message message = md.findById(message_id);
		if(message == null)
			return;
		message.setState(state);
		md.save(message);
		
	}
	
	public int getUnreadCount(int to_id){
		
		MessageDAO md = new MessageDAO();
		return md.findUnreadCount(to_id);
	}
	
	public boolean sendMessage(int from_id, int to_id, String title, String content){
		MessageDAO md = new MessageDAO();
		UserService us = new UserService();
		User from_user = us.getUserById(from_id);
		User to_user = us.getUserById(to_id);
		if(from_user == null || to_user == null)
			return false;
		
		
		Message m = new Message();
		m.setContent(content);
		m.setCtime(new Timestamp(System.currentTimeMillis()));
		m.setFromUserheadimg(from_user.getWeixinHeadimg());
		m.setFromUserId(from_user.getId());
		m.setFromUsername(from_user.getNickname());
		m.setState(Message.STATE_NO_READ);
		m.setTitle(title);
		m.setToUserId(to_id);
		
		md.save(m);
		
		return true;
	}
	
	public static void main(String[] argvs){
		MessageService ms = new MessageService();
		
		//ms.sendMessage(4, 1, "欢迎你加入领金社区！", "欢迎你加入领金社区！你可以在这里结交新的金融圈朋友，参加最新的金融圈活动！");
		ms.sendMessage(4, 1, "实名认证后就可以参加活动啦！", "您上传名片就可以完成实名认证，实名认证后就可以参加领金社区举办的一系列金融圈活动！");
		
		
	}
}
