package com.stu.petc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.util.ResourceUtils;

public class Tools {
//	public static String DateFormat(Timestamp timestamp) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//		
//		return sdf.format(timestamp);
//	}
	public static String getImgDirectory() {
		File path = null;
	      try {
	         path = new File(ResourceUtils.getURL("classpath:").getPath());
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      }
	      String imgDirectory=path.getParentFile().getParentFile().getParent() + "/img/";
	      imgDirectory = imgDirectory.replace('\\', '/');
	      if(imgDirectory.startsWith("file:/")) {
	    	  imgDirectory = imgDirectory.replaceAll("file:/", "");
	      }
	      System.out.println(imgDirectory);
	      return imgDirectory;
	}
}
