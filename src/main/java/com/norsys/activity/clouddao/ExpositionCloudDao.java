package com.norsys.activity.clouddao;

import org.aarboard.nextcloud.api.NextcloudConnector;
import org.aarboard.nextcloud.api.filesharing.Share;
import org.aarboard.nextcloud.api.filesharing.SharePermissions;
import org.aarboard.nextcloud.api.filesharing.ShareType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class ExpositionCloudDao {

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

    public String upLoadFile(File file, String path) {
        connector.uploadFile(file, path);
        return getExpositionImageUrl(path);
    }

    @Async
    public void deleteFile(String pathFile) {
        connector.removeFile(pathFile);
    }

    public void deleteTrainingPathPicture(String fullFilePath) {
        connector.deleteFolder(fullFilePath);
    }

    public String getExpositionImageUrl(String path) {
        SharePermissions permissions = new SharePermissions(SharePermissions.SingleRight.READ);
        Share share = connector.doShare(path, ShareType.PUBLIC_LINK, null, false, null, permissions);
        return share.getUrl().replace(serverName, downloadUrl) + "/preview";
    }

    public Boolean isFolderExist(String path) {
        return connector.folderExists(path);
    }

    public void deleteFolder(String pathFolder) {
        connector.deleteFolder(pathFolder);
    }

    public void renameFile(String oldPath, String newPath) {
        connector.renameFile(oldPath, newPath, false);
    }


}
