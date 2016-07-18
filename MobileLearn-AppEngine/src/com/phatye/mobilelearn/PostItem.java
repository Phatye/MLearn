package com.phatye.mobilelearn;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class PostItem extends HttpServlet  
{
   private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
   {
      String title = req.getParameter("title");
      String type = req.getParameter("type");
      String category = req.getParameter("category");
      String pName = req.getParameter("fileExtension");
      String fSize = req.getParameter("fileSize");
      Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
      BlobKey blobKey = blobs.get("uploadedBlob").get(0);
      
      /*ImagesService imagesService = ImagesServiceFactory
              .getImagesService();
      ServingUrlOptions servingOptions = ServingUrlOptions.Builder
              .withBlobKey(blobKey);

      servingOptions.
      String servingUrl = imagesService.getServingUrl(servingOptions);*/
      
      PersistenceManager pm = PMF.get().getPersistenceManager();   
      
      Item item = new Item(title, category, pName, blobKey.toString(), type, fSize);
      
   //persist
      try{ pm.makePersistent(item); }
      finally{ pm.close(); }
      //resp.sendRedirect("viewItems.jsp");
   }
}