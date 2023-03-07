package com.example.neoproject.service;

import com.example.neoproject.exception.NeonatoNotFoundException;
import com.example.neoproject.exception.PostolettoNotFoundException;
import com.example.neoproject.exception.RepartoNotFoundException;
import com.example.neoproject.map.NeonatoMapper;
import com.example.neoproject.map.dtos.neonato.NeonatoGetDto;
import com.example.neoproject.map.dtos.neonato.NeonatoPostDto;
import com.example.neoproject.model.Neonato;
import com.example.neoproject.model.Postoletto;
import com.example.neoproject.model.Reparto;
import com.example.neoproject.repository.NeonatoRepository;
import com.example.neoproject.repository.PostolettoRepository;
import com.example.neoproject.repository.RepartoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeonatoService {
    @Autowired
    private NeonatoRepository neonatoRepository;

    @Autowired
    private RepartoRepository repartoRepository;

    @Autowired
    private PostolettoRepository postolettoRepository;

    private NeonatoMapper mapper = Mappers.getMapper(NeonatoMapper.class);

    private Neonato neonato = new Neonato();

    public NeonatoGetDto findNeonatoById(Integer id){
        if(!neonatoRepository.existsById(id))
            throw new NeonatoNotFoundException(id);
        NeonatoGetDto n = mapper.neonatoGetDtoToNeonato(neonatoRepository.findNeonatoById(id));
        return n;
    }

    public Neonato addNeonato(NeonatoPostDto neonatoPostDto){
        Neonato n = mapper.neonatoPostDtoToNeonato(neonatoPostDto);
        Postoletto p = new Postoletto();
        if(!repartoRepository.existsById(neonatoPostDto.getNomeReparto()))
            throw new RepartoNotFoundException(neonatoPostDto.getNomeReparto());
        Reparto r = repartoRepository.findRepartoById(neonatoPostDto.getNomeReparto());
        p.setNomereparto(r);
        postolettoRepository.save(p);
        n.setIdpostoletto(p);
        return neonatoRepository.save(n);
    }

    public void deleteNeonato(Integer idNeonato){
        if(!postolettoRepository.existsById(idNeonato))
            throw new PostolettoNotFoundException(idNeonato);
        neonatoRepository.deleteById(idNeonato);
    }

    public void deleteAll(){
        neonatoRepository.deleteAll();
    }
}
