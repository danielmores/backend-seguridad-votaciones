package com.votacionesmisiontic.seguridad.Controladores;

import com.votacionesmisiontic.seguridad.Modelos.Permiso;
import com.votacionesmisiontic.seguridad.Repositorios.PermisoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class PermisoControlador {

    @Autowired
    private PermisoRepositorio permisoRepositorio;

    @GetMapping("")
    public List<Permiso> index(){
        return this.permisoRepositorio.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permiso create(@RequestBody  Permiso infoPermiso){
        return this.permisoRepositorio.save(infoPermiso);
    }
    @GetMapping("{id}")
    public Permiso show(@PathVariable String id){
        Permiso permisoActual=this.permisoRepositorio
                .findById(id)
                .orElse(null);
        return permisoActual;
    }
    @PutMapping("{id}")
    public Permiso update(@PathVariable String id,@RequestBody  Permiso infoPermiso){
        Permiso permisoActual=this.permisoRepositorio
                .findById(id)
                .orElse(null);
        if(permisoActual!=null){
            permisoActual.setMetodo(infoPermiso.getMetodo());
            permisoActual.setUrl(infoPermiso.getUrl());
            return this.permisoRepositorio.save(permisoActual);
        }else{
            return null;
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permiso permisoActual=this.permisoRepositorio
                .findById(id)
                .orElse(null);
        if (permisoActual!=null){
            this.permisoRepositorio.delete(permisoActual);
        }

    }

}
