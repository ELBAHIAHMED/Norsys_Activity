package com.norsys.activity.controller;

import com.norsys.activity.dto.OptionDto;
import com.norsys.activity.serviceImp.OptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/option")
@AllArgsConstructor
public class OptionController {
    private OptionService optionService;

    @PostMapping
    public long createNewOption(OptionDto optionDto) {
        return this.optionService.createNewOption(optionDto);
    }
}
