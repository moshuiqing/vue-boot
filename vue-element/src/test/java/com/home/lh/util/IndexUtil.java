package com.home.lh.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class IndexUtil {

	public static void set(String name, String path) {

		File file = new File(path);
		try {
			String info = FileUtils.readFileToString(file, "utf-8");
			if(info.indexOf(name)==-1) {
				int dw = info.indexOf("Vue.use(Vuex)");
				StringBuilder sb = new StringBuilder(info);
				String yr = "import "+name+" from './modules/" + name + "'";
				sb.insert(dw - 1, yr + "\n");
				/////////////////////////////////
				int m = sb.indexOf("settings,");
				sb.insert(m+9, "\n    " +name +",");				
				FileUtils.write(file, sb.toString(),"utf-8");
				System.out.println(sb.toString());
			}
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
