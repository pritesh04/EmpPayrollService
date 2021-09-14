package com.employeepayroll.test;

import java.io.File;

public class FilleUtils {
	public static boolean deleteFiles(File contentToDelete) {
		File[] allContents = contentToDelete.listFiles();
		if(allContents != null) {
			for(File file : allContents) {
				deleteFiles(file);
			}
		}
		return contentToDelete.delete();
	}
}

