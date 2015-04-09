package service;


import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.*;
import model.*;

public class WpService {

	public void newpost(String title, String content, ArrayList<String> tags, String classname, 
			Timestamp time, HashMap<String, String> metas){
		
		WpPosts ip = new WpPosts();
		ip.setCommentCount(new Long(0));
		ip.setCommentStatus("open");
		ip.setGuid("");
		ip.setMenuOrder(0);
		ip.setPinged("");
		ip.setPingStatus("publish");
		ip.setPostAuthor(new Long(1));
		ip.setPostContent(content);
		ip.setPostContentFiltered("");
		ip.setPostDate(time);
		ip.setPostDateGmt(time);
		ip.setPostExcerpt("");
		ip.setPostMimeType("");
		ip.setPostModified(time);
		ip.setPostModifiedGmt(time);
		String postname = "";
		try {
			postname = java.net.URLEncoder.encode(title.trim(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(postname.length() > 200)
			postname = postname.substring(0, 199);
		ip.setPostName(postname);
		ip.setPostParent(new Long(0));
		ip.setPostPassword("");
		ip.setPostStatus("publish");
		ip.setPostTitle(title.trim());
		ip.setPostType("post");
		ip.setToPing("");
		
		WpPostsDAO ipd = new WpPostsDAO();
		ipd.save(ip);
		ip.setGuid("http://www.ihaomen.com/"+ip.getId());
		ipd.update(ip);
		
		WpPostmetaDAO ipmd = new WpPostmetaDAO();
		if(metas != null){
			for(String key : metas.keySet()){
				
				
				String value = metas.get(key);
				WpPostmeta ihaomenpostmeta = new WpPostmeta();
				ihaomenpostmeta.setPostId(ip.getId());
				ihaomenpostmeta.setMetaKey(key);
				ihaomenpostmeta.setMetaValue(value);
				ipmd.save(ihaomenpostmeta);
			}
		}
		
		
		WpTermsDAO tagsdao = new WpTermsDAO();
		WpTermRelationshipsDAO tagreladao = new WpTermRelationshipsDAO();
		WpTermTaxonomyDAO itertaxdao = new WpTermTaxonomyDAO();
		
		if(classname != null && !classname.trim().equals("")){
			List<WpTerms> classinfos = tagsdao.findByName(classname);
			
			WpTerms newterm = null;
			
			if(classinfos.size() == 0){
				newterm = new WpTerms();
				newterm.setName(classname.trim());
				String tagslug = "";
				try {
					tagslug = java.net.URLEncoder.encode(classname.trim(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				newterm.setSlug(tagslug);
				newterm.setTermGroup((long)0);
				tagsdao.save(newterm);
			}else{
				newterm = classinfos.get(0);
			}
			
			List<WpTermTaxonomy> termtaxs = itertaxdao.findByTermId(newterm.getTermId());
			WpTermTaxonomy newtermtaxs = null;
			
			for(WpTermTaxonomy curtt : termtaxs){
				if(curtt.getTaxonomy().equals("category")){
					newtermtaxs = curtt;
					break;
				}
			}
			
			if(newtermtaxs == null){
				newtermtaxs = new WpTermTaxonomy();
				newtermtaxs.setCount((long)0);
				newtermtaxs.setDescription("");
				newtermtaxs.setParent((long)0);
				newtermtaxs.setTermId(newterm.getTermId());
				newtermtaxs.setTaxonomy("category");
				itertaxdao.save(newtermtaxs);
			}
				
			newtermtaxs.setCount(newtermtaxs.getCount()+1);
			WpTermRelationships tl = new WpTermRelationships();
			WpTermRelationshipsId reid = new WpTermRelationshipsId();
			reid.setObjectId(ip.getId());
			reid.setTermTaxonomyId(newtermtaxs.getTermTaxonomyId());
			tl.setTermOrder(0);
			tl.setId(reid);
			tagreladao.save(tl);
			itertaxdao.update(newtermtaxs);
			
		}
		
		if(tags != null){
			for(String tag : tags){
				tag = tag.trim();
				if(tag.trim().equals(""))
					continue;
				
				List<WpTerms> taginfos = tagsdao.findByName(tag);
				
				WpTerms newterm = null;
				
				if(taginfos.size() == 0){
					newterm = new WpTerms();
					newterm.setName(tag.trim());
					String tagslug = "";
					try {
						tagslug = java.net.URLEncoder.encode(tag.trim(),"UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					newterm.setSlug(tagslug);
					newterm.setTermGroup((long)0);
					tagsdao.save(newterm);
				}else{
					newterm = taginfos.get(0);
				}
				
				List<WpTermTaxonomy> termtaxs = itertaxdao.findByTermId(newterm.getTermId());
				WpTermTaxonomy newtermtaxs = null;
				
				for(WpTermTaxonomy curtt : termtaxs){
					if(curtt.getTaxonomy().equals("post_tag")){
						newtermtaxs = curtt;
						break;
					}
				}
				
				if(newtermtaxs == null){
					newtermtaxs = new WpTermTaxonomy();
					newtermtaxs.setCount((long)0);
					newtermtaxs.setDescription("");
					newtermtaxs.setParent((long)0);
					newtermtaxs.setTermId(newterm.getTermId());
					newtermtaxs.setTaxonomy("post_tag");
					itertaxdao.save(newtermtaxs);
				}
					
				newtermtaxs.setCount(newtermtaxs.getCount()+1);
				WpTermRelationships tl = new WpTermRelationships();
				WpTermRelationshipsId reid = new WpTermRelationshipsId();
				reid.setObjectId(ip.getId());
				reid.setTermTaxonomyId(newtermtaxs.getTermTaxonomyId());
				tl.setTermOrder(0);
				tl.setId(reid);
				
				if(tagreladao.findById(reid) == null);
					tagreladao.save(tl);
				
				itertaxdao.update(newtermtaxs);
				
			}
		}
	}
	
	
	public static void main(String[] argvs){
		
		WpService wps = new WpService();
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("窦文涛");
		tags.add("孟广美");
		tags.add("专访标签");
		
		
		Timestamp time = new Timestamp(System.currentTimeMillis()); 
		HashMap<String, String> metas = new HashMap<String, String>();
		
		wps.newpost("专访123", "我喜欢看锵锵三人行123", tags, "专访", time, metas);
	}
}
