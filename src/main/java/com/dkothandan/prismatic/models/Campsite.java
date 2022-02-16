package com.dkothandan.prismatic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Campsite {
    private String campsite_id, campsite_reserve_type, campsite_type, capacity_rating, loop, site, type_of_use;
    private int max_num_people, min_num_people;
    private Map<String, String> availabilities;

    public String getCampsite_id() {
        return campsite_id;
    }

    public void setCampsite_id(String campsite_id) {
        this.campsite_id = campsite_id;
    }

    public String getCampsite_reserve_type() {
        return campsite_reserve_type;
    }

    public void setCampsite_reserve_type(String campsite_reserve_type) {
        this.campsite_reserve_type = campsite_reserve_type;
    }

    public String getCampsite_type() {
        return campsite_type;
    }

    public void setCampsite_type(String campsite_type) {
        this.campsite_type = campsite_type;
    }

    public String getCapacity_rating() {
        return capacity_rating;
    }

    public void setCapacity_rating(String capacity_rating) {
        this.capacity_rating = capacity_rating;
    }

    public String getLoop() {
        return loop;
    }

    public void setLoop(String loop) {
        this.loop = loop;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType_of_use() {
        return type_of_use;
    }

    public void setType_of_use(String type_of_use) {
        this.type_of_use = type_of_use;
    }

    public int getMax_num_people() {
        return max_num_people;
    }

    public void setMax_num_people(int max_num_people) {
        this.max_num_people = max_num_people;
    }

    public int getMin_num_people() {
        return min_num_people;
    }

    public void setMin_num_people(int min_num_people) {
        this.min_num_people = min_num_people;
    }

    public Map<String, String> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Map<String, String> availabilities) {
        this.availabilities = availabilities;
    }
}
