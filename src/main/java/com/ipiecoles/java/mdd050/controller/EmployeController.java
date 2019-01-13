package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/employes")
public class EmployeController {
    @Autowired
    private EmployeRepository employeRepository;

    @RequestMapping("/count")
    public Long countEmploye() {
        return employeRepository.count();
    }
    @RequestMapping("/{id}")
    public Employe getById(@PathVariable("id") Long id) {
        return employeRepository.findOne(id);
    }
    @RequestMapping(value = "",params = "matricule" )
    public Employe getByMatricule(@RequestParam("matricule") String matricule) {
        return employeRepository.findByMatricule(matricule);
    }

   //@RequestMapping(value = "",params = "page,size,sortProperty,sortDirection" )
    @RequestMapping("")
    public Page<Employe> getAllByPage(@RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size,
                                      @RequestParam("sortProperty") String paramSort,
                                      @RequestParam("sortDirection") Sort.Direction direction) {
        Pageable pageRequest = new PageRequest(page, size, direction, paramSort);
        return employeRepository.findAll(pageRequest);
    }

    @RequestMapping(
            value="",
            method = RequestMethod.POST, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            consumes = "application/json" //Type MIME des données passées avec la requête : JSON, XML, Texte...
            )
    public Employe createEmploye(@RequestBody Employe e) {
        return employeRepository.save(e);
    }

    @RequestMapping(
            value="/{id}",
            method = RequestMethod.PUT, //Méthode HTTP : GET/POST/PATCH/PUT/DELETE
            consumes = "application/json", //Type MIME des données passées avec la requête : JSON, XML, Texte...
            produces = "application/json"
    )
    public Employe modifyEmploye(@PathVariable("id") Long id, @RequestBody Employe e) {
        return employeRepository.save(e);
    }

    @RequestMapping(
            value="/{id}",
            method = RequestMethod.DELETE) //Méthode HTTP : GET/POST/PATCH/PUT/DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void  deleteEmploye(@PathVariable("id") Long id) {
            employeRepository.delete(id);
            return;
    }

}
