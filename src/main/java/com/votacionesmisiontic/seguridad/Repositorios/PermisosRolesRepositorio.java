package com.votacionesmisiontic.seguridad.Repositorios;

import com.votacionesmisiontic.seguridad.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermisosRolesRepositorio extends MongoRepository<PermisosRoles,String> {
}
