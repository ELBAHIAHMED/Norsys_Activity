package com.norsys.activity.cloudservice;

import com.norsys.activity.dao.FileDao;
import com.norsys.activity.dto.FileDto;
import com.norsys.activity.model.FileS;
import com.norsys.activity.util.CloudFileHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class EventCloudService {

	EventCloudDao eventCloudDao;

	FileDao fileDao;
	
	public List<String> getFolders() {
		return eventCloudDao.getEventFolders();
	}
	@Async
	public void createFolder(String path) {

		eventCloudDao.createFolder(path);
	}

	public FileDto uploadFile(MultipartFile multipartFile, String path, String generatedKey) {
		String[] name = multipartFile.getOriginalFilename().split("\\.");
		String fullFilePath = path.concat(name[0] + "_" + generatedKey + "." + name[1]);
		Optional<File> fileOptional = Optional.ofNullable(CloudFileHelper.getTempFileFromMultiPartFile(multipartFile));
		fileOptional.ifPresent(file -> {
			eventCloudDao.upLoadFile(file, fullFilePath);
			file.delete();
		});

		FileS fileS = FileS.builder()
				.survey_id(Long.valueOf(generatedKey))
				.path(fullFilePath)
				.sharedPath(this.doShared(fullFilePath))
				.build();
		boolean isEquals = false;
		System.out.println(this.fileDao.getAllFilesOfSurvey(Long.valueOf(generatedKey)).isEmpty());
		if(!this.fileDao.getAllFilesOfSurvey(Long.valueOf(generatedKey)).isEmpty()) {
			System.out.println(this.fileDao.getAllFilesOfSurvey(Long.valueOf(generatedKey)));
			for (FileS fileS1 : this.fileDao.getAllFilesOfSurvey(Long.valueOf(generatedKey))) {
				if (fileS1.getPath().equals(fileS.getPath())) {
					isEquals = true;
				}
			}
			if(!isEquals) {
				fileS.setId(this.fileDao.createNewFile(fileS));
			}
			else {
				log.error("file existed");
			}
		}
		else {
			fileS.setId(this.fileDao.createNewFile(fileS));
		}
		if (fileS.getId() != null) {
			return FileDto.getFileDto(fileS);
		}
		else
			return null;
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
		String fullFilePath = "/norsys_activity/image/".concat(name[0] + "_" + generatedKey + "." + name[1]);
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
