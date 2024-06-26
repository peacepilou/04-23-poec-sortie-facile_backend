package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.category.Category;
import com.poec.projet_backend.domain.category.CategoryRepository;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityRepository;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.department.DepartmentRepository;
import com.poec.projet_backend.domain.profile.Profile;
import com.poec.projet_backend.domain.profile.ProfileRepository;
import com.poec.projet_backend.domain.region.Region;
import com.poec.projet_backend.domain.region.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public List<Activity> getAll() {
        return repository.findAll();
    }

    public Activity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Activity add(Activity activity, Long regionId, Long departmentId, Long cityId, Long profileId, Long categoryId) {
        Region existingRegion = regionRepository.findById(regionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Région with id " + regionId + " not found")
                );
        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Département with id " +  departmentId + " not found")
                );
        City existingCity = cityRepository.findById(cityId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Ville with id " +  cityId + " not found")
                );
        Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Profile with id " +  profileId + " not found")
                );
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category with id " +  categoryId + " not found")
                );
        activity.setRegion(existingRegion);
        activity.setDepartment(existingDepartment);
        activity.setCity(existingCity);
        activity.setProfile(existingProfile);
        activity.setCategory(existingCategory);
        Activity savedActivity = repository.save(activity);

        existingProfile.getActivities().add(activity);
        profileRepository.save(existingProfile);
        return savedActivity;
    }

    public Activity update(Activity activity, Long id) {
        Activity newActivity = getById(id);
        newActivity.setName(activity.getName());
        newActivity.setDate(activity.getDate());
        newActivity.setAge(activity.getAge());
        newActivity.setImgUrl(activity.getImgUrl());
        newActivity.setLink(activity.getLink());
        newActivity.setDescription(activity.getDescription());
        newActivity.setNbGuest(activity.getNbGuest());
        newActivity.setVisible(activity.isVisible());

        return repository.save(newActivity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public int countBookingsByActivityId(Long activityId) {
        return repository.countBookingsByActivityId(activityId);
    }


}
