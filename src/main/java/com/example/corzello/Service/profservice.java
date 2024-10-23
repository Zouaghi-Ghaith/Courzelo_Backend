package com.example.corzello.Service;


import com.example.corzello.Entity.Prof;
import com.example.corzello.Repository.Iprofrepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
@Service
public class profservice implements IprofService {
    public Iprofrepo iprofrepo;
    @Override
    public List<Prof> getAll() {
        return iprofrepo.findAll();
    }

    @Override
    public Prof ajprof(Prof p) {
        return iprofrepo.save(p);
    }

    @Override
    public List<Prof> retrieveAll() {
        return iprofrepo.findAll();
    }

    @Override
    public void remove(int idprof) {
        Prof p = iprofrepo.findById(idprof).orElse(null);
        iprofrepo.delete(p);
    }

    @Override
    public Prof update(int id, Prof p) {
        p.setIdprof(id);
        return iprofrepo.save(p);
    }

    @Override
    public List<Prof> getProfClass(int idClass) {
        List<Prof> list = iprofrepo.findAll();
        return list.stream().filter(p->p.getClassMa().getIdclass()==idClass).toList();

    }
}
