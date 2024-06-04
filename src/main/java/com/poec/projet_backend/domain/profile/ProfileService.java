package com.poec.projet_backend.domain.profile;

import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public List<Profile> getAll() {
        return repository.findAll();
    }

    public Profile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Profile add(Profile profile) {
        return repository.save(profile);
    }

    public Profile update(Profile profile, Long id) {
        Profile newProfile = getById(id);
        newProfile.setFirstname(profile.getFirstname());
        newProfile.setLastname(profile.getLastname());
        newProfile.setStreetNumber(profile.getStreetNumber());
        newProfile.setStreet(profile.getStreet());
        newProfile.setPostalCode(profile.getPostalCode());
        newProfile.setDescription(profile.getDescription());
        newProfile.setAvatar(profile.getAvatar());
        newProfile.setPhone(profile.getPhone());
        newProfile.setDateOfBirth(profile.getDateOfBirth());
        newProfile.setCategories(profile.getCategories());
        newProfile.setBookings(profile.getBookings());
        newProfile.setUser(profile.getUser());
        newProfile.setCity(profile.getCity());
        newProfile.setDepartment(profile.getDepartment());
        newProfile.setRegion(profile.getRegion());

        return repository.save(newProfile);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
