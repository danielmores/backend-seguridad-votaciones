package com.votacionesmisiontic.seguridad.Controladores;

import com.votacionesmisiontic.seguridad.Modelos.Rol;
import com.votacionesmisiontic.seguridad.Repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RolControlador {

    @Autowired
    private RolRepositorio rolRepositorio;


    @GetMapping("")
    public List<Rol> index(){
        return this.rolRepositorio.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol create(@RequestBody  Rol infoRol){
        return this.rolRepositorio.save(infoRol);
    }
    @GetMapping("{id}")
    public Rol show(@PathVariable String id){
        Rol rolActual=this.rolRepositorio
                .findById(id)
                .orElse(null);
        return rolActual;
    }
    @PutMapping("{id}")
    public Rol update(@PathVariable String id,@RequestBody  Rol infoRol){
        Rol rolActual=this.rolRepositorio
                .findById(id)
                .orElse(null);
        if (rolActual!=null){
            rolActual.setNombre(infoRol.getNombre());
            return this.rolRepositorio.save(rolActual);
        }else{
            return  null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Rol rolActual=this.rolRepositorio
                .findById(id)
                .orElse(null);
        if (rolActual!=null){
            this.rolRepositorio.delete(rolActual);
        }
    }
    
    
}
