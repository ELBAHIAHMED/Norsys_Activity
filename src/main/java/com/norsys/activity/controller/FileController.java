package com.norsys.activity.controller;

import com.norsys.activity.cloudservice.EventCloudService;
import com.norsys.activity.serviceImp.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/file")
@AllArgsConstructor
public class FileController {
    private FileService fileService;
    private EventCloudService eventCloudService;
    @DeleteMapping("/{file_id}")
    public long deleteFileById(@PathVariable Long file_id) {
        this.eventCloudService.deleteFile(this.fileService.getFileByID(file_id).get().getPath());
        return this.fileService.deleteFile(file_id);
    }
}
