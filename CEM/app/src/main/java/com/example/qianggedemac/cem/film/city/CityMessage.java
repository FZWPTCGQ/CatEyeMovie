package com.example.qianggedemac.cem.film.city;

/**
 * Created by qianggedemac on 17/1/7.
 */
public class CityMessage {
    private String cityId;
    private String cityName;

    public CityMessage(String cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public CityMessage setCityId(String cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public CityMessage setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }
}
