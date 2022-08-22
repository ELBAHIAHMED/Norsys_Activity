package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.OptionDao;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.model.Option;
import com.norsys.activity.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {
    @Autowired
    private OptionDao optionDao;

    private final ModelMapper modelMapper = new ModelMapper();

    public Long createNewOption(OptionDto optionDto) {
        return this.optionDao.createNewOption(this.getOption(optionDto));
    }

    public List<OptionDto> getAllOptionsOfQuestion(Long question_id) {
        Optional<List<Option>> optionOptional = Optional.ofNullable(this.optionDao.getAllOptionsOfQuestion(question_id));
        List<OptionDto> optionDtoList = new ArrayList<>();
        for (Option option:optionOptional.get()) {
            optionDtoList.add(OptionDto.getOptionDto(option));
        }
        return optionDtoList;
    }

    private Option getOption(OptionDto optionDto) {
        return modelMapper.map(optionDto, Option.class);
    }
}
