package com.example.corzello.Service;


import com.example.corzello.Entity.Prof;

import java.util.List;

public interface IprofService {

    public List<Prof> getAll();
    public Prof ajprof(Prof p );
    public List<Prof> retrieveAll();
    public void remove(int idprof);
    public Prof update(int id, Prof p);

    List<Prof> getProfClass(int idClass);
}
