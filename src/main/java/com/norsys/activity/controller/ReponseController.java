package com.norsys.activity.controller;

import com.norsys.activity.dto.ReponseDto;
import com.norsys.activity.serviceImp.ReponseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/reponse")
@AllArgsConstructor
public class ReponseController {
    private ReponseService reponseService;
    @PostMapping
    public ResponseEntity<ReponseDto> addReponse(@RequestBody ReponseDto reponseDto){
        Optional<ReponseDto> reponseDto1 = this.reponseService.addReponse(reponseDto);
        if(reponseDto1 != null){
            return ResponseEntity.ok().body(reponseDto);
        }else{
            return new ResponseEntity<>(reponseDto, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/{option_id}")
    public ResponseEntity<Optional<Integer>> countOption(@PathVariable  Long option_id) {
        Optional<Integer> count = this.reponseService.countOption(option_id);
        System.out.println(count);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
