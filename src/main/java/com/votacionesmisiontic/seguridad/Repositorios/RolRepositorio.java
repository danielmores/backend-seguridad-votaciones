package com.votacionesmisiontic.seguridad.Repositorios;

import com.votacionesmisiontic.seguridad.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolRepositorio extends MongoRepository<Rol,String>{
}
