package com.poec.projet_backend.domain.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping(BASE_URL + ACTIVITY)
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private ActivityService service;

    @GetMapping(ALL)
    public ResponseEntity<List<ActivityDTO>> getAll() {
        List<Activity> activities = service.getAll();
        List<ActivityDTO> activityDTOS = activities.stream().map(ActivityDTO::mapFromEntity).toList();
        return new ResponseEntity<>(activityDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ActivityDTO> getById(@PathVariable Long id) {
        Activity newActivity = service.getById(id);
        ActivityDTO activityDTO = ActivityDTO.mapFromEntity(newActivity);
        return new ResponseEntity<>(activityDTO, HttpStatus.OK);
    }

    @PostMapping(ADD + REGION + "/{regionId}" + DEPARTMENT + "/{departmentId}" + CITY + "/{cityId}" + PROFILE + "/{profileId}" + CATEGORY + "/{categoryId}")
    public ResponseEntity<ActivityDTO> add(@RequestBody ActivityDTO activityDTO,
                                           @PathVariable Long regionId,
                                           @PathVariable Long departmentId,
                                           @PathVariable Long cityId,
                                           @PathVariable Long profileId,
                                           @PathVariable Long categoryId
                                           ) {
        Activity newActivity = service.add(ActivityDTO.mapToEntity(activityDTO), regionId, departmentId, cityId, profileId, categoryId);
        ActivityDTO newActivityDTO = ActivityDTO.mapFromEntity(newActivity);
        return new ResponseEntity<>(newActivityDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ActivityDTO> update(@RequestBody Activity activity, @PathVariable Long id) {
        Activity newActivity = service.update(activity, id);
        ActivityDTO activityDTO = ActivityDTO.mapFromEntity(newActivity);
        System.out.println(activityDTO.isVisible());
        return new ResponseEntity<>(activityDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @GetMapping("/{activityId}/countBookings")
    public ResponseEntity<Integer> countBookingsForActivity(@PathVariable Long activityId) {
        int count = service.countBookingsByActivityId(activityId);
        return ResponseEntity.ok(count);
    }

}
