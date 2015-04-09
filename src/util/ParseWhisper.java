package util;

import java.util.ArrayList;

public class ParseWhisper {
	static String wordsplit = "%&";
	static String messagesplit = "#@";
	
	public static String process(String word1, String word2, String word3, String message){
		if(word1 == null)
			word1 = "";
		if(word2 == null)
			word2 = "";
		if(word3 == null)
			word3 = "";
		if(message == null)
			message = "";
		
		String words = "";
		if(!word1.trim().equals(""))
			words = word1;
		if(!word2.trim().equals("")){
			if(!words.trim().equals(""))
				words += wordsplit;
			words += word2;
		}
		if(!word3.trim().equals("")){
			if(!words.trim().equals(""))
				words += wordsplit;
			words += word3;
		}
		
		String final_message = words + messagesplit + message;
		return final_message;
	}
	
	public static String processWeibo(String word1, String word2, String word3){
		ArrayList<String> words = new ArrayList<String>();
		
		if(word1 != null && !word1.trim().equals(""))
			words.add(word1.trim());
		if(word2 != null && !word2.trim().equals(""))
			words.add(word2.trim());
		if(word3 != null && !word3.trim().equals(""))
			words.add(word3.trim());
		
		if(words.size() == 0){
			return "";
		}
		else if(words.size() == 1){
			return "#"+words.get(0)+"#";
		}else if(words.size() == 2){
			return "#"+words.get(0)+"#" + " 并且 " + "#" +words.get(1)+"#";
		}else{
			return "#"+words.get(0)+"#" + " ， "+ "#" + words.get(1) +  "#" +" 并且 " +"#" + words.get(2) +"#";
		}
	}
	
	public static String[] parse(String final_message){
		if(final_message == null)
			final_message = "";
		
		
		String[] messages = final_message.split(messagesplit);
		String[] words = messages[0].split(wordsplit);
		
		String[] finalwords = new String[words.length + 1];
		for(int i = 0; i < finalwords.length; i++){
			finalwords[i] = "";
		}
		if(messages.length == 2)
			finalwords[finalwords.length-1] = messages[1];
		
		for(int i = 0; i < words.length; i++){
			finalwords[i] = words[i];
		}
		
		return finalwords;
	}
	
	public static void main(String[] argvs){
		String message = ParseWhisper.process("word1", "", "word3", "");
		System.out.println(message);
		
		String[] words = ParseWhisper.parse(message);
		System.out.println(words[1]);
	}

}
