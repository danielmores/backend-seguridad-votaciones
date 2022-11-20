package com.votacionesmisiontic.seguridad.Repositorios;

import com.votacionesmisiontic.seguridad.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PermisosRolesRepositorio extends MongoRepository<PermisosRoles,String> {

    @Query("{'rol.$id': ObjectId(?0),'permiso.$id': ObjectId(?1)}")
    PermisosRoles getPermisoRol(String id_rol,String id_permiso);

}
