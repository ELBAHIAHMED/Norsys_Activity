package com.norsys.activity.clouddao;

import java.io.File;

import org.aarboard.nextcloud.api.NextcloudConnector;
import org.aarboard.nextcloud.api.filesharing.SharePermissions;
import org.aarboard.nextcloud.api.filesharing.ShareType;
import org.aarboard.nextcloud.api.filesharing.SharePermissions.SingleRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class ThematicFolderCloudDao {
	 @Autowired
	    NextcloudConnector connector;

	    @Value("${cloud.server.name}")
	    String serverName;

	    @Value("${cloud.server.download-url}")
	    String downloadUrl;
	    
	    public String doShared(String path) {
	        return connector.doShare(path, ShareType.PUBLIC_LINK, null, false, null, new SharePermissions(SingleRight.READ)).getUrl()
	                .replace(serverName, downloadUrl);
	    }
	    
	    public void upLoadFile(File file, String path) {
	        connector.uploadFile(file, path);
	    }
	    
	    public void deleteFolder(String pathFolder) {
	        connector.deleteFolder(pathFolder);
	    }
	    
	    @Async
	    public void deleteFile(String pathFile) {
	        connector.removeFile(pathFile);
	    }

}
