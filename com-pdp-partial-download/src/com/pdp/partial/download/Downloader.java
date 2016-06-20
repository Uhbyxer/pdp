package com.pdp.partial.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
	
	private static String FILE_TO_DOWNLOAD = "http://fs208.www.ex.ua/get/251915699/zaneta01001.jpg";
	
	public static void main(String[] args) {
		 try {
			  	URL url = new URL(FILE_TO_DOWNLOAD);
		        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		        urlConnection.setRequestProperty("Range", "bytes=0-24");
		        
		        urlConnection.connect();

		        System.out.println("Responce Code: " + urlConnection.getResponseCode());
		        System.out.println("Content-Length: " + urlConnection.getContentLength());

		        InputStream inputStream = urlConnection.getInputStream();
		        long size = 0;

		        while(inputStream.read() != -1 )
		            size++;

		        System.out.println("Downloaded Size: " + size);
		        
		        boolean support = urlConnection.getHeaderField("Accept-Ranges").equals("bytes");
		        System.out.println("Partial content retrieval support = " + (support ? "Yes" : "No"));

		    }catch(MalformedURLException mue) {
		        mue.printStackTrace();
		    }catch(IOException ioe) {
		        ioe.printStackTrace();
		    }

	}

}
