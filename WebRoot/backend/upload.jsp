<%@ page language="java"
	import="java.util.*, service.*, model.*, config.*, 
com.oreilly.servlet.MultipartRequest,util.*,java.awt.image.BufferedImage,java.io.PrintWriter"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String type = request.getParameter("type");
	if(type == null)
		type = "image";

	try {

		if (type.trim().equals("image")) {
			String Path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "upload/image";

			MultipartRequest multi;
			String fileName = "default.png";

			multi = new MultipartRequest(request, Path, 1 * 1024 * 1024,
					"gbk", new RandomFileRenamePolicy());
			Enumeration filesName = multi.getFileNames();
			while (filesName.hasMoreElements()) {
				fileName = (String) filesName.nextElement();
			}

			String filename = multi.getFilesystemName(fileName);
			BufferedImage image = ZoomImage.readImage(Path + "/"
					+ filename);
			int width = image.getWidth();
			int height = image.getHeight();

			double size = 1;
			if (width >= height && width > 500)
				size = ((double) 500) / (double) width;
			if (height >= width && height > 500)
				size = ((double) 500) / (double) height;

			if (size < 1) {
				ZoomImage.reduceImg(Path + "/" + filename, Path + "/"
						+ "0" + multi.getFilesystemName(fileName),
						(int) ((double) width * size),
						(int) ((double) height * size));
				filename = "0" + filename;
			}

			PrintWriter resultout;
			resultout = response.getWriter();
			resultout.write(filename);
			resultout.flush();
			resultout.close();
			
		}else if(type.equals("file")){
			String Path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "upload/file";


			MultipartRequest multi;
			
			String fileName = "";
			multi = new MultipartRequest(request, Path, 50 * 1024 * 1024,
					"gbk", new RandomFileRenamePolicy());
			Enumeration filesName = multi.getFileNames();
			while (filesName.hasMoreElements()) {
				fileName = (String) filesName.nextElement();
			}

			String filename = multi.getFilesystemName(fileName);

			PrintWriter resultout;
			resultout = response.getWriter();
			resultout.write(filename);
			resultout.flush();
			resultout.close();
		} else if(type.equals("attachment")){
			String Path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "upload/attachimage";

			Path = "/data/home/zl2011/htdocs/upload/attachimage";
			if (type.trim().equals("thphoto")) {
				Path = "/data/home/zl2011/htdocs/upload/thphoto";
			}

			MultipartRequest multi;
			String fileName = "default.png";

			multi = new MultipartRequest(request, Path, 50 * 1024 * 1024,
					"gbk", new RandomFileRenamePolicy());
			Enumeration filesName = multi.getFileNames();
			while (filesName.hasMoreElements()) {
				fileName = (String) filesName.nextElement();
			}

			String filename = multi.getFilesystemName(fileName);

			BufferedImage image = ZoomImage.readImage(Path + "/"
					+ filename);
			int width = image.getWidth();
			int height = image.getHeight();

			double size = 1;
			if (width >= height && width > 500)
				size = ((double) 500) / (double) width;
			if (height >= width && height > 500)
				size = ((double) 500) / (double) height;

			if (size < 1) {
				ZoomImage.reduceImg(Path + "/" + filename, Path + "/"
						+ "0" + multi.getFilesystemName(fileName),
						(int) ((double) width * size),
						(int) ((double) height * size));
				filename = "0" + filename;
			}

			PrintWriter resultout;
			resultout = response.getWriter();
			resultout.write(filename);
			resultout.flush();
			resultout.close();
		}
	} catch (Exception e) {
		System.err.println("ERROR: upload photo error!\t" + e);
		request.setAttribute("error", "发生错误，请重新登陆。");
		PrintWriter resultout;
		try {
			resultout = response.getWriter();
			resultout.write("error");
			resultout.flush();
			resultout.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
%>

