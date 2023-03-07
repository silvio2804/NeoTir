package com.example.neoproject.service;
import com.example.neoproject.exception.NeonatoNotFoundException;
import com.example.neoproject.exception.PostolettoNotFoundException;
import com.example.neoproject.exception.RepartoNotFoundException;
import com.example.neoproject.map.DashboardMapper;
import com.example.neoproject.map.PostolettoMapper;
import com.example.neoproject.map.dtos.postoletto.PostilettoAll;
import com.example.neoproject.model.Neonato;
import com.example.neoproject.model.Postoletto;
import com.example.neoproject.model.Reparto;
import com.example.neoproject.repository.NeonatoRepository;
import com.example.neoproject.repository.PostolettoRepository;
import com.example.neoproject.repository.RepartoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostoLettoService {

    @Autowired
    private PostolettoRepository postolettoRepository;

    @Autowired
    private RepartoRepository repartoRepository;

    @Autowired
    private NeonatoRepository neonatoRepository;

    private PostolettoMapper mapper = Mappers.getMapper(PostolettoMapper.class);

    public Postoletto addPostoLetto(String nomeReparto){
        if(!repartoRepository.existsById(nomeReparto))
            throw new RepartoNotFoundException(nomeReparto);
        Postoletto p = new Postoletto();
        Reparto r = repartoRepository.findRepartoById(nomeReparto);
        p.setNomereparto(r);
        return postolettoRepository.save(p);
    }

    public List <Postoletto> findAllPostiLetto(){
        return postolettoRepository.findAll();
    }

    public Postoletto findPostoLettoByNeonato(Neonato n){
        if(! neonatoRepository.existsById(n.getId()))
            throw new NeonatoNotFoundException(n.getId());
        return postolettoRepository.findPostolettoByNeonatoes(n);
    }

    public Postoletto findPostolettoById(Integer id){
        if(!postolettoRepository.existsById(id))
            throw new PostolettoNotFoundException(id);
        return postolettoRepository.findPostolettoById(id);
    }

    public List<PostilettoAll> findAllPostiLettoByReparto(String nomeReparto){
        if(!repartoRepository.existsById(nomeReparto))
            throw new RepartoNotFoundException(nomeReparto);
        Reparto r = repartoRepository.findRepartoById(nomeReparto);
        return  mapper.postilettoAllToPostilettoAllDto((postolettoRepository.findAllByNomereparto(r)));
    }

    public void deletePostolettoById(Integer id){
        postolettoRepository.deleteById(id);
    }

   public void deleteAllPostiletto(){
        postolettoRepository.deleteAll();
   }
}
