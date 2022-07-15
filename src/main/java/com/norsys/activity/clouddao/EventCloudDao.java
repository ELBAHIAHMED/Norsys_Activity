package com.norsys.activity.clouddao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.aarboard.nextcloud.api.NextcloudConnector;
import org.aarboard.nextcloud.api.filesharing.SharePermissions;
import org.aarboard.nextcloud.api.filesharing.SharePermissions.SingleRight;
import org.aarboard.nextcloud.api.filesharing.ShareType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class EventCloudDao {

    @Autowired
    NextcloudConnector connector;

    @Value("${cloud.server.name}")
    String serverName;

    @Value("${cloud.server.download-url}")
    String downloadUrl;


    public List<String> getEventFolders() {
        // TODO: why this /category is static
        return connector.listFolderContent("/");
    }


    public void createFolder(String path) {
        connector.createFolder(path);
    }

    public InputStream getFile(String path) throws IOException {
        return connector.downloadFile(path);
    }

    public void upLoadFile(File file, String path) {
        connector.uploadFile(file, path);
    }

    public Boolean isFolderExist(String path) {
        return connector.folderExists(path);
    }

    public void deleteFolder(String pathFolder) {
        connector.deleteFolder(pathFolder);
    }

    @Async
    public void deleteFile(String pathFile) {
        connector.removeFile(pathFile);
    }

    public String doShared(String path) {
//		SharePermissions permissions = new SharePermissions(SingleRight.READ);
//		Share share = connector.doShare(path, ShareType.PUBLIC_LINK, null, false, null, permissions);
//		return share.getUrl();
        return connector.doShare(path, ShareType.PUBLIC_LINK, null, false, null, new SharePermissions(SingleRight.READ)).getUrl()
                .replace(serverName, downloadUrl);
    }

    public void renameFile(String oldPath, String newPath) {
        connector.renameFile(oldPath, newPath, false);
    }


}
