package com.phatye.mobilelearn;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
//import com.google.gson.JsonObject;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is a utility servlet that responds to a GET request with a URL that can be used for
 * uploading a file to Blobstore.
 *
 * @author dacton@google.com (Daniel Acton)
 */
public class BlobUrlServlet extends HttpServlet {
  private static final long serialVersionUID = 893271931645250254L;

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    String url = blobstoreService.createUploadUrl("/postItem");
    JSONObject jsonResponse = new JSONObject();
    try {
		jsonResponse.put("uploadUrl", url);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    resp.setContentType("application/json");
    resp.getWriter().write(jsonResponse.toString());
  }
}