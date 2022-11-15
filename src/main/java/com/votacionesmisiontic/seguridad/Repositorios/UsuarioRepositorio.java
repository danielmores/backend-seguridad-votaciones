package com.votacionesmisiontic.seguridad.Repositorios;

import com.votacionesmisiontic.seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepositorio extends MongoRepository<Usuario,String>{
}
