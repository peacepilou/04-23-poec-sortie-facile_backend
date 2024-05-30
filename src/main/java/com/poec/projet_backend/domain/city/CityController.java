package com.poec.projet_backend.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("city")
@RequiredArgsConstructor
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAll() {
        List<City> cities = service.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<City> add(@RequestBody City city) {
        return new ResponseEntity<>(service.add(city), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<City> update(@RequestBody City city, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(city, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
