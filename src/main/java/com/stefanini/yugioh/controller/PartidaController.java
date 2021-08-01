package com.stefanini.yugioh.controller;

import com.stefanini.yugioh.dto.PartidaDto;
import com.stefanini.yugioh.mapper.JogadaMapper;
import com.stefanini.yugioh.mapper.PartidaMapper;
import com.stefanini.yugioh.model.Partida;
import com.stefanini.yugioh.service.JogadaService;
import com.stefanini.yugioh.service.PartidaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/games")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PartidaController {

    private final JogadaService jogadaService;
    private final PartidaService partidaService;
    private final JogadaMapper jogadaMapper = JogadaMapper.getInstance();
    private final PartidaMapper partidaMapper = PartidaMapper.getInstance();

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid PartidaDto partidaDto){

        log.info("dto: "+partidaDto);
        log.info("model: "+partidaMapper.toModel(partidaDto));

       Partida novaPartida = partidaService.save(partidaMapper.toModel(partidaDto));


       for (var jogadaDto: partidaDto.getJogadas()){
           jogadaDto.setPartidaId(novaPartida.getId());
           jogadaService.save(jogadaMapper.toModel(jogadaDto));
       }

       return ResponseEntity.status(HttpStatus.CREATED)
               .body(partidaMapper.toDTO(novaPartida));

    }


}