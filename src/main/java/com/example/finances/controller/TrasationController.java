package com.example.finances.controller;

import com.example.finances.dto.TrasationDto;
import com.example.finances.models.Trasation;
import com.example.finances.service.TrasationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TrasationController {
    private TrasationService trasationService;
    private ModelMapper modelMapper;
    public TrasationController(TrasationService trasationService, ModelMapper modelMapper) {
        this.trasationService = trasationService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/trasations/{id}")
    public Trasation getTrasation(@PathVariable Long id){
        Trasation trasation = trasationService.getTrasationById(id);
        return trasation;
    }
    @PostMapping("/trasations")
    public Trasation createTrasation(@RequestBody TrasationDto trasationDto){
        Trasation trasation = convertToEntity(trasationDto);
        return trasationService.create(trasation);
    }
    @PutMapping("/trasations/{id}")
    public Trasation updateTrasation(@PathVariable Long id, @RequestBody TrasationDto trasationDto){
        Trasation trasation = convertToEntity(trasationDto);
        return trasationService.update(id, trasation);
    }
    @DeleteMapping("/trasations/{id}")
    public void deleteTrasation(@PathVariable Long id){
        trasationService.delete(id);
    }


    private TrasationDto convertToDTO(Trasation u) {
        return modelMapper.map(u, TrasationDto.class);
    }
    private Trasation convertToEntity(TrasationDto trasationDto) {
        return modelMapper.map(trasationDto, Trasation.class);
    }

}
