package com.poec.projet_backend.domain.city;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityService {

    private CityRepository repository;

    public List<City> getAll() {
        return repository.findAll();
    }

    public City getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                () -> new EntityNotFoundException(id + " not fouund")
        );
    }

    public City add(City city) {
        return repository.save(city);
    }

    public City update(City city, Long id) {
        City newCity = getById(id);
        newCity.setName(city.getName());

        return repository.save(newCity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
