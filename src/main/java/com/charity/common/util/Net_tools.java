package com.charity.common.util;


import com.charity.common.fiter.IPFilter;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ZhangXu on 2017/7/31.
 */
public class Net_tools {
	public static String get(String url) throws IOException{
		URL myURL = new URL(url);
		URLConnection httpsConn = myURL.openConnection();
		httpsConn.setRequestProperty("X-Forwarded-For", IPFilter.getIp());
		BufferedInputStream read = new BufferedInputStream(httpsConn.getInputStream());
		StringBuilder sb = IO_tools.fromInToOut(read,new StringBuilder());
		read.close();
		return sb.toString();
	}
	public static String post(String url,String json) throws IOException{
		URL myURL = new URL(url);
		HttpURLConnection httpsConn = (HttpURLConnection) myURL
				.openConnection();
		httpsConn.setDoOutput(true); 
		httpsConn.setDoInput(true);
		httpsConn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
		OutputStream out = httpsConn.getOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(json.getBytes());
		IO_tools.fromInToOut(in, out);
		in.close();out.close();
		BufferedInputStream read = new BufferedInputStream(httpsConn.getInputStream());
		StringBuilder sb = IO_tools.fromInToOut(read,new StringBuilder());
		read.close();
		return sb.toString();
	}
	public static String upload(String url,File file) throws IOException{
		URL myURL = new URL(url);
		HttpsURLConnection httpsConn = (HttpsURLConnection) myURL
				.openConnection();
		httpsConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundarywOUce20fskE00IRz");
		httpsConn.setDoOutput(true); 
		httpsConn.setDoInput(true);
		OutputStream out = httpsConn.getOutputStream();
		out.write("------WebKitFormBoundarywOUce20fskE00IRz\r\n".getBytes());
		out.write(("Content-Disposition: form-data; name=\"buffer\"; filename=\""+file.getName()+"\"\r\n").getBytes());
		out.write("Content-Type: image/jpeg\r\n".getBytes());
		out.write("\r\n".getBytes());
		
		FileInputStream in = new FileInputStream(file);
		IO_tools.fromInToOut(in, out);
		out.write("\r\n------WebKitFormBoundarywOUce20fskE00IRz--\r\n".getBytes());
		in.close();out.close();
		BufferedInputStream read = new BufferedInputStream(httpsConn.getInputStream());
		StringBuilder sb = IO_tools.fromInToOut(read,new StringBuilder());
		read.close();
		return sb.toString();
	}
	
}
