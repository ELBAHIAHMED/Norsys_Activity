package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.FileDao;
import com.norsys.activity.dto.FileDto;
import com.norsys.activity.model.FileS;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {

    private FileDao fileDao;
    private static final ModelMapper modelMapper = new ModelMapper();

    public static FileS getFile(FileDto fileDto) {
        return modelMapper.map(fileDto, FileS.class);
    }

    public long deleteFile(long file_id) {
        return this.fileDao.deleteFileById(file_id);
    }
    public Optional<FileDto> getFileByID(Long file_id) {
        Optional<FileS> fileS = this.fileDao.getFileByID(file_id);
        System.out.println(fileS);
        FileDto fileDto = FileDto.builder().id(fileS.get().getId())
                .path(fileS.get().getPath())
                .sharedPath(fileS.get().getSharedPath())
                .survey_id(fileS.get().getSurvey_id())
                .build();
        return Optional.ofNullable(fileDto);
    }
}
