package com.example.corzello.Repository;

import com.example.corzello.Entity.classManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Iclassrepo extends JpaRepository<classManagement,Integer> {
    classManagement findByNomClass(String nomClass);

}
