package com.votacionesmisiontic.seguridad.Repositorios;


import com.votacionesmisiontic.seguridad.Modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermisoRepositorio  extends MongoRepository<Permiso,String>{
}
