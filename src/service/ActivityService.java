package service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import dao.*;
import model.*;
import model.Likezan;

public class ActivityService {
	public Integer newActivity(String title, Timestamp startTime, Integer lastTime,
			String province, String city, String shortAddr, String detailAddr,
			Integer publisherId, Integer totalPeople, Integer fee,
			String content) {
		
		ActivityDAO ad = new ActivityDAO();
		Activity a = new Activity();
		a.setTitle(title);
		a.setStartTime(startTime);
		a.setLastTime(lastTime);
		a.setProvince(province);
		a.setCity(city);
		a.setShortAddr(shortAddr);
		a.setDetailAddr(detailAddr);
		a.setPublisherId(publisherId);
		a.setTotalPeople(totalPeople);
		a.setFee(fee);
		a.setContent(content);
		a.setState(Activity.PASS_VERIFIED);
		a.setPublishTime(new Timestamp(System.currentTimeMillis()));
		ad.save(a);
		
		MessageService ms = new MessageService();
		ms.sendMessage(5, publisherId, "您成功发起了一个新的活动\""+title+"\"，请等待领金财经客服的审核！", "领金财经会在一个工作日之内通过系统消息和微信服务号通知您审核结果！");
		LogDAO ld = new LogDAO();
		Log log = new Log();
		log.setContent("您成功发起了一个新的活动\""+title+"\"");
		
		return a.getId();
	}
	
	public boolean modifyActivity(Integer activity_id, String title, Timestamp startTime, Integer lastTime,
			String province, String city, String shortAddr, String detailAddr,
			Integer publisherId, Integer totalPeople, Integer fee,
			String content, Integer state, Timestamp publishTime) {
		
		ActivityDAO ad = new ActivityDAO();
		Activity a = ad.findById(activity_id);
		if(a == null)
			return false;
		
		a.setTitle(title);
		a.setStartTime(startTime);
		a.setLastTime(lastTime);
		a.setProvince(province);
		a.setCity(city);
		a.setShortAddr(shortAddr);
		a.setDetailAddr(detailAddr);
		a.setPublisherId(publisherId);
		a.setTotalPeople(totalPeople);
		a.setFee(fee);
		a.setContent(content);
		a.setState(state);
		a.setPublishTime(publishTime);
		
		ad.modify(a);
		
		return true;
	}
	
	public boolean deleteActivity(Integer activity_id) {
		
		ActivityDAO ad = new ActivityDAO();
		Activity a = ad.findById(activity_id);
		if(a == null)
			return false;
		else{
			ad.delete(a);
			return true;
		}
	}
	
	public boolean verifyActivity(Integer activity_id){
		
		ActivityDAO ad = new ActivityDAO();
		Activity a = ad.findById(activity_id);
		if(a == null)
			return false;
		else{
			a.setState(Activity.PASS_VERIFIED);
			a.setPublishTime(new Timestamp(System.currentTimeMillis()));
			return true;
		}
	}
	
	public boolean closeActivity(Integer activity_id){
		
		ActivityDAO ad = new ActivityDAO();
		Activity a = ad.findById(activity_id);
		if(a == null)
			return false;
		else{
			a.setState(Activity.CLOSE_SIGNUP);
			return true;
		}
	}
	
	public Integer addSpeaker(Integer activity_id, String name, String introduction){
		SpeakerDAO sd = new SpeakerDAO();
		Speaker s = new Speaker();
		s.setActivityId(activity_id);
		s.setName(name);
		s.setIntroduction(introduction);
		s.setUserId(-1);
		sd.save(s);
		
		return s.getId();
	}
	
	public boolean modifySpeaker(Integer speaker_id, String name, String introduction){

		SpeakerDAO sd = new SpeakerDAO();
		Speaker s = sd.findById(speaker_id);
		if(s == null)
			return false;
		else{
			s.setName(name);
			s.setIntroduction(introduction);
			sd.modify(s);
			return true;
		}
	}
	
	public boolean deleteSpeaker(Integer speaker_id) {
		
		SpeakerDAO sd = new SpeakerDAO();
		Speaker s = sd.findById(speaker_id);
		if(s == null)
			return false;
		else{
			sd.delete(s);
			return true;
		}
	}
	
	public Integer addAttachFile(Integer activityId, Integer userId, String fileType,
			String fileName, String fileUrl, Integer fileSize) {
		
		AttachmentDAO atd = new AttachmentDAO();
		Attachment a = new Attachment(activityId, userId, fileType,
				fileName, fileUrl, fileSize, new Timestamp(System.currentTimeMillis()), Attachment.ATTACH_FILE);
		atd.save(a);
		return a.getId();
	}
	
	public Integer addAttachImage(Integer activityId, Integer userId, String fileType,
			String fileName, String fileUrl, Integer fileSize) {
		
		AttachmentDAO atd = new AttachmentDAO();
		Attachment a = new Attachment(activityId, userId, fileType,
				fileName, fileUrl, fileSize, new Timestamp(System.currentTimeMillis()), Attachment.ATTACH_IMAGE);
		atd.save(a);
		return a.getId();
	}
	
	public boolean modifyAttachment(Integer attachmentId, Integer activityId, Integer userId, String fileType,
			String fileName, String fileUrl, Integer fileSize, Timestamp ctime, Integer state){

		AttachmentDAO atd = new AttachmentDAO();
		Attachment at = atd.findById(attachmentId);
		if(at == null)
			return false;
		else{
			at.setActivityId(activityId);
			at.setUserId(userId);
			at.setFileType(fileType);
			at.setFileName(fileName);
			at.setFileUrl(fileUrl);
			at.setFileSize(fileSize);
			at.setCtime(ctime);
			at.setState(state);
			atd.modify(at);
			return true;
		}
	}
	
	public boolean deleteAttachment(Integer attachmentId) {
		AttachmentDAO atd = new AttachmentDAO();
		Attachment at = atd.findById(attachmentId);
		if(at == null)
			return false;
		else{
			atd.delete(at);
			return true;
		}
	}
	
	public boolean likezan(Integer activityId, Integer userId){
		LikezanDAO lzd = new LikezanDAO();
		UserDAO ud = new UserDAO();
		List<Likezan> lzs = lzd.findByAidandUid(activityId, userId);
		if(lzs.size() > 0)
			return false;
		else{
			User u = ud.findById(userId);
			if(u == null)
				return false;
			Activity a = findActivity(activityId);
			if(a == null)
				return false;
			
			
			Likezan lz = new Likezan(activityId, userId, Likezan.STATE_NORMAL, new Timestamp(System.currentTimeMillis()), u.getNickname(), u.getWeixinHeadimg());
			lzd.save(lz);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
			LogDAO ld = new LogDAO();
			Log l = new Log();
			
			String logContent = "我 "+df.format(new Timestamp(System.currentTimeMillis()))+" 为 \"<a href=\"activity.jsp?activity_id="+a.getId()+"\">"+
								a.getTitle()+"</a>\" 活动点赞。";
			
			l.setUserId(userId);
			l.setType(Log.TYPE_SIGNUP_ACTIVITY);
			l.setCtime(new Timestamp(System.currentTimeMillis()));
			l.setContent(logContent);
			
			ld.save(l);
			
			
			return true;
		}
	}
	
	public boolean cancellikezan(Integer activityId, Integer userId){
		LikezanDAO lzd = new LikezanDAO();
		List<Likezan> lzs = lzd.findByAidandUid(activityId, userId);
		if(lzs.size() == 0)
			return false;
		else{
			lzd.delete(lzs.get(0));
			return true;
		}
	}
	
	public Integer addComment(Integer activityId, Integer userId, String title,
			String content, Timestamp ctime, Integer state) {
		
		CommentDAO cd = new CommentDAO();
		UserDAO ud = new UserDAO();
		User u = ud.findById(userId);
		if(u == null)
			return -1;
		Activity a = findActivity(activityId);
		if(a == null)
			return -1;
		
		Comment c = new Comment(activityId, userId, title,
				content, ctime, state, u.getNickname(), u.getWeixinHeadimg());
		
		cd.save(c);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
		LogDAO ld = new LogDAO();
		Log l = new Log();
		
		String logContent = "我 "+df.format(new Timestamp(System.currentTimeMillis()))+" 评论了 \"<a href=\"activity.jsp?activity_id="+a.getId()+"\">"+
							a.getTitle()+"</a>\" 活动。";
		
		l.setUserId(userId);
		l.setType(Log.TYPE_COMMENT_ACTIVITY);
		l.setCtime(new Timestamp(System.currentTimeMillis()));
		l.setContent(logContent);
		
		ld.save(l);
		
		
		return c.getId();
	}
	
	public boolean modifyComment(Integer commentId, Integer activityId, Integer userId, String title,
			String content, Timestamp ctime, Integer state){

		CommentDAO cd = new CommentDAO();
		Comment c = cd.findById(commentId);
		if(c == null)
			return false;
		else{
			c.setActivityId(activityId);
			c.setUserId(userId);
			c.setTitle(title);
			c.setContent(content);
			c.setCtime(ctime);
			c.setState(state);
			return true;
		}
	}
	
	public boolean deleteComment(Integer commentId) {
		CommentDAO cd = new CommentDAO();
		Comment c = cd.findById(commentId);
		if(c == null)
			return false;
		else{
			cd.delete(c);
			return true;
		}
	}
	
	public static int SIGNUP_SUCCESS = 1;
	public static int SIGNUP_FAILURE = 2;
	public static int SIGNUP_OVER_PEOPLE = 3;
	public static int SIGNUP_ALREADY = 4;
	public static int SIGNUP_NO_REALNAME_VERIFIED = 5;
	
	public static int CANCEL_SIGNUP_NOT_EXIST = 6;
	public static int CANCEL_SIGNUP_SUCCESS = 7;
	
	public int signup(Integer activityId, Integer userId){
		SignupDAO sd = new SignupDAO();
		UserDAO ud = new UserDAO();
		
		Activity a = findActivityWithExtraData(activityId);
		User u = ud.findById(userId);
		if(a == null || u == null)
			return SIGNUP_FAILURE;
		
		if(u.getVerified().equals(User.NO_VERIFIED))
			return SIGNUP_NO_REALNAME_VERIFIED;
		
		List<Signup> ss = sd.findByAidandUid(activityId, userId);
		if(ss.size() > 0)
			return SIGNUP_ALREADY;
		
		if(a.getSignup_users().size() >= a.getTotalPeople()){
			return SIGNUP_OVER_PEOPLE;
		}
		
		Signup s = new Signup(activityId, userId, Signup.NORMAL_STATE, new Timestamp(System.currentTimeMillis()), u.getNickname(), u.getWeixinHeadimg());
		sd.save(s);
		

		MessageService ms = new MessageService();

		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
		
		String content = "您成功报名了活动\"<a href=\"activity.jsp?activity_id="+a.getId()+"\">"+
							a.getTitle()+"</a>\"，请于 " + 
							df.format(a.getStartTime()) + " 准时到 "+
							a.getDetailAddr()+" 参加活动！"; 
		String title = "您成功报名了活动\""+a.getTitle()+"\"，请准时参加活动！";
		
		ms.sendMessage(5, userId, title, content);
		
		
		LogDAO ld = new LogDAO();
		Log l = new Log();
		
		String logContent = "我 "+df.format(new Timestamp(System.currentTimeMillis()))+" 报名了 \"<a href=\"activity.jsp?activity_id="+a.getId()+"\">"+
							a.getTitle()+"</a>\" 活动。";
		
		l.setUserId(userId);
		l.setType(Log.TYPE_SIGNUP_ACTIVITY);
		l.setCtime(new Timestamp(System.currentTimeMillis()));
		l.setContent(logContent);
		
		ld.save(l);
		
		return SIGNUP_SUCCESS;
	}
	
	public int cancelsignup(Integer activityId, Integer userId){
		SignupDAO sd = new SignupDAO();
		UserDAO ud = new UserDAO();
		
		Activity a = findActivityWithExtraData(activityId);
		User u = ud.findById(userId);
		if(a == null || u == null)
			return SIGNUP_FAILURE;
		
		List<Signup> ss = sd.findByAidandUid(activityId, userId);
		if(ss.size() == 0)
			return CANCEL_SIGNUP_NOT_EXIST;
		else{
			sd.delete(ss.get(0));
			MessageService ms = new MessageService();
			ms.sendMessage(5, userId, "您已取消参加活动\""+a.getTitle()+"\"！", "您已取消参加活动\""+a.getTitle()+"\"！");
			
			
			LogDAO ld = new LogDAO();
			Log l = new Log();
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
			
			String logContent = "我 "+df.format(new Timestamp(System.currentTimeMillis()))+" 取消报名了 \"<a href=\"activity.jsp?activity_id="+a.getId()+"\">"+
					a.getTitle()+"</a>\" 活动。";

			l.setUserId(userId);
			l.setType(Log.TYPE_CANCEL_SIGNUP_ACTIVITY);
			l.setCtime(new Timestamp(System.currentTimeMillis()));
			l.setContent(logContent);
			
			ld.save(l);
			
			return CANCEL_SIGNUP_SUCCESS;
		}
	}
	
	public boolean checksignup(Integer activityId, Integer userId){
		SignupDAO sd = new SignupDAO();
		UserDAO ud = new UserDAO();
		
		List<Signup> ss = sd.findByAidandUid(activityId, userId);
		if(ss.size() > 0)
			return true;
		else
			return false;
	}
	
	public Activity findActivity(Integer activityId){
		return new ActivityDAO().findById(activityId);
	}
	
	public Activity findActivityWithExtraData(Integer activityId){
		Activity a = findActivity(activityId);
		if(a == null)
			return null;
		
		a.setLikezan_count(new LikezanDAO().findByActivityId(activityId).size());
		a.setPublisher(new UserDAO().findById(a.getPublisherId()));
		a.setComments((ArrayList<Comment>)(new CommentDAO().findByActivityId(activityId)));
		a.setAttachments((ArrayList<Attachment>)(new AttachmentDAO().findByActivityId(activityId)));
		a.setSpeakers((ArrayList<Speaker>)(new SpeakerDAO().findByActivityId(activityId)));
		a.setSignup_users((ArrayList<Signup>)(new SignupDAO().findByActivityId(activityId)));
		
		return a;
	}
	
	public ArrayList<Activity> findActivityByUserid(Integer user_id, String type){
		
		ArrayList<Activity> activities = new ArrayList<Activity>();
		ActivityDAO ad = new ActivityDAO();
		
		if(type.equals("baoming")){
			ArrayList<Signup> signups = (ArrayList<Signup>)(new SignupDAO().findByUserId(user_id));
			for(int i = 0 ; i < signups.size(); i++){
				activities.add(findActivityWithExtraData(signups.get(i).getActivityId()));
			}
		}else if(type.equals("canjia")){
			ArrayList<Signup> signups = (ArrayList<Signup>)(new SignupDAO().findByUserId(user_id));
			for(int i = 0 ; i < signups.size(); i++){
				if(signups.get(i).getState() == Signup.CANJIA_STATE)
					activities.add(findActivityWithExtraData(signups.get(i).getActivityId()));
			}
		}else if(type.equals("faqi")){
			activities = (ArrayList<Activity>)ad.findByPublisherId(user_id);
		
			for(int i = 0; i < activities.size(); i++){
				activities.set(i, findActivityWithExtraData(activities.get(i).getId()));
			}
		}
		
		return activities;
	}
	
	public ArrayList<Activity> findRecentOpenActivities(int count){
		ActivityDAO ad = new ActivityDAO();
		ArrayList<Activity> rawactivities = (ArrayList<Activity>)ad.findOrderbyStartTime(count,new Timestamp(System.currentTimeMillis()));
		
		return rawactivities;
	}
	
	public ArrayList<Activity> findRecentOpenActivitiesWithExtraData(int count){
		ArrayList<Activity> activities = new ArrayList<Activity>();
		ActivityDAO ad = new ActivityDAO();
		ArrayList<Activity> rawactivities = (ArrayList<Activity>)ad.findOrderbyStartTime(count,new Timestamp(System.currentTimeMillis()));
	
		for(int i = 0 ; i < rawactivities.size(); i++){
			activities.add(findActivityWithExtraData(rawactivities.get(i).getId()));
		}
		
		return activities;
	}
	
	public ArrayList<Activity> search(String search_word){
		ArrayList<Activity> search_result = new ArrayList<Activity>();
		ActivityDAO ad = new ActivityDAO();
		search_result = (ArrayList<Activity>)ad.search(search_word);
		
		return search_result;
	}
}
