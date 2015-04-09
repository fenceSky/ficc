package util;

import java.io.File;
import java.util.Date;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class RandomFileRenamePolicy implements FileRenamePolicy {

	public File rename(File file) {
		// TODO Auto-generated method stub
		String body = "";
		String ext = "";
		int pot = file.getName().lastIndexOf(".");
		if (pot != -1) {
			body = (new Date()).getTime() + "";
			ext = file.getName().substring(pot);
		} else {
			body = (new Date()).getTime() + "";
			ext = "";
		}
		String newName = body + ext;
		file = new File(file.getParent(), newName);
		return file;
	}

}