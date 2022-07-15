package com.norsys.activity.cloudservice;

import com.norsys.activity.util.CloudFileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.norsys.activity.clouddao.EventCloudDao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class EventCloudService {

	@Autowired
	EventCloudDao eventCloudDao;
	
	public List<String> getFolders() {
		return eventCloudDao.getEventFolders();
	}
	@Async
	public void createFolder(String path) {

		eventCloudDao.createFolder(path);
	}

	public String uploadFile(MultipartFile multipartFile, String path, String generatedKey) {
		String[] name = multipartFile.getOriginalFilename().split("\\.");
		String fullFilePath = path.concat(name[0] + "_" + generatedKey + "." + name[1]);
		Optional<File> fileOptional = Optional.ofNullable(CloudFileHelper.getTempFileFromMultiPartFile(multipartFile));
		fileOptional.ifPresent(file -> {
			eventCloudDao.upLoadFile(file, fullFilePath);
			file.delete();
		});
		return fullFilePath;
	}

	public InputStream getFile(String fileName) throws IOException {
		return eventCloudDao.getFile(fileName);
	}

	public Boolean isFolderExist(String path) {
		return eventCloudDao.isFolderExist(path);
	}
	@Async
	public void deleteFolder(String pathFolder) {
		eventCloudDao.deleteFolder(pathFolder);
	}
	@Async
	public void deleteFile(String pathFile) {
		eventCloudDao.deleteFile(pathFile);
	}

	public String doShared(String path) {
		return eventCloudDao.doShared(path);
	}

	public String updateFiles(MultipartFile multipartFile, String path,String generatedKey) {
		String[] name = multipartFile.getOriginalFilename().split("\\.");
		String fullFilePath = "/Mediatheque/image/".concat(name[0] + "_" + generatedKey + "." + name[1]);
		Optional<File> fileOptional = Optional.ofNullable(CloudFileHelper.getTempFileFromMultiPartFile(multipartFile));
		eventCloudDao.deleteFile(path);
		fileOptional.ifPresent(file -> {
			eventCloudDao.upLoadFile(file, fullFilePath);
			file.delete();
		});
		return fullFilePath;
	}
	@Async
	public void renameFile(String oldPath, String newPath) {
		eventCloudDao.renameFile(oldPath,newPath);
	}
}
