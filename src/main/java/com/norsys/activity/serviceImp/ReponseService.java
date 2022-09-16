package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.ReponseDao;
import com.norsys.activity.dto.ReponseDto;
import com.norsys.activity.model.Reponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReponseService {
    private ReponseDao reponseDao;
    private final ModelMapper modelMapper = new ModelMapper();


    private Reponse getReponse(ReponseDto reponseDto) {

        return modelMapper.map(reponseDto, Reponse.class);
    }

    public Optional<ReponseDto> addReponse(ReponseDto reponseDto) {
        long reponse_id = this.reponseDao.addReponse(this.getReponse(reponseDto));
        reponseDto.setId(reponse_id);
        if(reponseDto.getId() != null) {
            return Optional.of(reponseDto);
        }
        else {
            return null;
        }
    }
    public Optional<Integer> countOption(Long option_id) {
        Optional<Integer> count = this.reponseDao.countOption(option_id);
        return count;
    }
}
