package com.votacionesmisiontic.seguridad.Controladores;

import com.votacionesmisiontic.seguridad.Modelos.Permiso;
import com.votacionesmisiontic.seguridad.Modelos.PermisosRoles;
import com.votacionesmisiontic.seguridad.Modelos.Rol;
import com.votacionesmisiontic.seguridad.Repositorios.PermisoRepositorio;
import com.votacionesmisiontic.seguridad.Repositorios.PermisosRolesRepositorio;
import com.votacionesmisiontic.seguridad.Repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")
public class PermisosRolesControlador {

    @Autowired
    private PermisosRolesRepositorio permisosRolesRepositorio;

    @Autowired
    private PermisoRepositorio permisoRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;


    @GetMapping("")
    public List<PermisosRoles> index(){
        return this.permisosRolesRepositorio.findAll();
    }

    /**
     * Asignación rol y permiso
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles create(@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles nuevo=new PermisosRoles();
        Rol elRol=this.rolRepositorio.findById(id_rol).get();
        Permiso elPermiso=this.permisoRepositorio.findById(id_permiso).get();
        if (elRol!=null && elPermiso!=null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return this.permisosRolesRepositorio.save(nuevo);
        }else{
            return null;
        }
    }
    @GetMapping("{id}")
    public PermisosRoles show(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.permisosRolesRepositorio
                .findById(id)
                .orElse(null);
        return permisosRolesActual;
    }

    /**
     * Modificación Rol y Permiso
     * @param id
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles update(@PathVariable String id,@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles permisosRolesActual=this.permisosRolesRepositorio
                .findById(id)
                .orElse(null);
        Rol elRol=this.rolRepositorio.findById(id_rol).get();
        Permiso elPermiso=this.permisoRepositorio.findById(id_permiso).get();
        if(permisosRolesActual!=null && elPermiso!=null && elRol!=null){
            permisosRolesActual.setPermiso(elPermiso);
            permisosRolesActual.setRol(elRol);
            return this.permisosRolesRepositorio.save(permisosRolesActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.permisosRolesRepositorio
                .findById(id)
                .orElse(null);
        if (permisosRolesActual!=null){
            this.permisosRolesRepositorio.delete(permisosRolesActual);
        }
    }

}
