package com.phatye.mobilelearn;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;


@SuppressWarnings("serial")
public class ServeItem extends HttpServlet  
{
	 private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	 
	 public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	 {
        BlobKey blobKey = new BlobKey(req.getParameter("id"));
        blobstoreService.serve(blobKey, resp);
	 }
	 
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	 {
		 doGet(req, resp);
	 }
}
