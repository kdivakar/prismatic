package com.dkothandan.prismatic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CampsiteAvailability {
    private Long count;
    private Map<String, Campsite> campsites;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Map<String, Campsite> getCampsites() {
        return campsites;
    }

    public void setCampsites(Map<String, Campsite> campsites) {
        this.campsites = campsites;
    }
}