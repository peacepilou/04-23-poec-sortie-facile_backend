package com.poec.projet_backend.domain.region;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.profile.Profile;

import java.util.List;

public record RegionDTO(
        Long id,
        String name,
        List<Long> activityIds,
        List<Long> profileIds
) {
    public static RegionDTO mapFromEntity(Region region) {
        return new RegionDTO(
                region.getId(),
                region.getName(),
                region.getActivities().stream().map(Activity::getId).toList(),
                region.getProfiles().stream().map(Profile::getId).toList()
        );
    }

}
