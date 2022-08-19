package com.norsys.activity.serviceImp;

import com.norsys.activity.dao.OptionDao;
import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.model.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {
    @Autowired
    private OptionDao optionDao;

    private final ModelMapper modelMapper = new ModelMapper();

    public Long createNewOption(OptionDto optionDto) {
        return this.optionDao.createNewOption(this.getOption(optionDto));
    }

    private Option getOption(OptionDto optionDto) {
        return modelMapper.map(optionDto, Option.class);
    }
}
