package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.ReponseOptionDao;
import com.norsys.activity.dao.ReponseTextDao;
import com.norsys.activity.dto.ReponseDto;
import com.norsys.activity.model.ReponseOption;
import com.norsys.activity.model.ReponseText;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ReponseService {
    private ReponseOptionDao reponseOptionDao;
    private ReponseTextDao reponseTextDao;
    private final ModelMapper modelMapper = new ModelMapper();


    private ReponseOption getReponseOption(ReponseDto reponseDto) {

        return modelMapper.map(reponseDto, ReponseOption.class);
    }

    private ReponseText getReponseText(ReponseDto reponseDto) {

        return modelMapper.map(reponseDto, ReponseText.class);
    }

    public Optional<ReponseDto> addReponse(ReponseDto reponseDto) {
        long reponse_id;
        if(reponseDto.getType().equals("Texte")) {
            reponse_id = this.reponseTextDao.addReponse(this.getReponseText(reponseDto));
        }
        else {
            reponse_id = this.reponseOptionDao.addReponse(this.getReponseOption(reponseDto));
        }
        reponseDto.setId(reponse_id);
        if(reponseDto.getId() != null) {
            return Optional.of(reponseDto);
        }
        else {
            return null;
        }
    }

    public List<ReponseDto> getAllResponsesOfQuestionText(Long question_id) {
        List<ReponseDto> reponseDtoList = new ArrayList<>();
        List<ReponseText> reponseTextList = this.reponseTextDao.getAllResponsesOfQuestionText(question_id);
        if (!reponseTextList.isEmpty()) {
            for (ReponseText reponseText: reponseTextList) {
                reponseDtoList.add(ReponseDto.getReponseDto(reponseText));
            }
            log.info("response got");
            return reponseDtoList;
        }
        log.error("response didn't got");
        return null;
    }


    public Optional<Integer> countOption(Long option_id) {
        Optional<Integer> count = this.reponseOptionDao.countOption(option_id);
        return count;
    }
}
