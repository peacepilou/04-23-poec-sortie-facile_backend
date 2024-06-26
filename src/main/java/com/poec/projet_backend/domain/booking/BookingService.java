package com.poec.projet_backend.domain.booking;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.activity.ActivityRepository;
import com.poec.projet_backend.domain.profile.Profile;
import com.poec.projet_backend.domain.profile.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public List<Booking> getAll() {
        return repository.findAll();
    }

    public Booking getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Booking add(Long activityId, Long profileId) {
        Activity newActivity = activityRepository.findById(activityId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Activity " + activityId + " not found")
                );
        Profile newProfile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Profile " + profileId + " not found")
                );
        Booking booking = new Booking();
        booking.setCreatedAt(new Date().toString());
        booking.setActivity(newActivity);
        booking.setProfile(newProfile);
        return repository.save(booking);
    }

    public Booking update(Booking booking, Long id) {
        Booking newBooking = getById(id);
        newBooking.setCreatedAt(booking.getCreatedAt());
        newBooking.setActivity(booking.getActivity());
        newBooking.setProfile(booking.getProfile());

        return repository.save(newBooking);
    }

    public ResponseEntity<Void> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
