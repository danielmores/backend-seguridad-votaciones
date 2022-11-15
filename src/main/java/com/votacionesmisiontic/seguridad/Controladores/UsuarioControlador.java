package com.votacionesmisiontic.seguridad.Controladores;

import com.votacionesmisiontic.seguridad.Modelos.Rol;
import com.votacionesmisiontic.seguridad.Modelos.Usuario;
import com.votacionesmisiontic.seguridad.Repositorios.RolRepositorio;
import com.votacionesmisiontic.seguridad.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping("")
    public List<Usuario> index(){
        return this.usuarioRepositorio.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody  Usuario infoUsuario){
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.usuarioRepositorio.save(infoUsuario);
    }
    @GetMapping("{id}")
    public Usuario show(@PathVariable String id){
        Usuario usuarioActual=this.usuarioRepositorio
                .findById(id)
                .orElse(null);
        return usuarioActual;
    }
    @PutMapping("{id}")
    public Usuario update(@PathVariable String id,@RequestBody  Usuario infoUsuario){
        Usuario usuarioActual=this.usuarioRepositorio
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return this.usuarioRepositorio.save(usuarioActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Usuario usuarioActual=this.usuarioRepositorio
                .findById(id)
                .orElse(null);
        if (usuarioActual!=null){
            this.usuarioRepositorio.delete(usuarioActual);
        }
    }
    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    /**
     * Relación (1 a n) entre rol y usuario
     * @param id
     * @param id_rol
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}")
    public Usuario asignarRolAUsuario(@PathVariable String id,@PathVariable String id_rol){
        Usuario usuarioActual=this.usuarioRepositorio
                .findById(id)
                .orElse(null);
        Rol rolActual=this.rolRepositorio
                .findById(id_rol)
                .orElse(null);
        if (usuarioActual!=null && rolActual!=null){
            usuarioActual.setRol(rolActual);
            return this.usuarioRepositorio.save(usuarioActual);
        }else{
            return null;
        }

    }



}
