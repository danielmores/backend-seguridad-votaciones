package com.votacionesmisiontic.seguridad.Repositorios;

import com.votacionesmisiontic.seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsuarioRepositorio extends MongoRepository<Usuario,String>{

    @Query("{'correo': ?0}")
    public Usuario getUserByEmail(String correo);
}
