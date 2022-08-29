package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.OptionDao;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.model.Option;
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

    private static final ModelMapper modelMapper = new ModelMapper();

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
    public long updateOption(OptionDto optionDto) {
        return this.optionDao.updateOption(this.getOption(optionDto));
    }
    public static Option getOption(OptionDto optionDto) {
        return modelMapper.map(optionDto, Option.class);
    }

    public long deleteOption(long question_id) {
        return this.optionDao.deleteOption(question_id);
    }

    public long deleteQuestionById(Long option_id) {
        return this.optionDao.deleteOptionById(option_id);
    }
}
