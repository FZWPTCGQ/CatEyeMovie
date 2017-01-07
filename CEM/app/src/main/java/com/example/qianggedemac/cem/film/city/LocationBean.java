package com.example.qianggedemac.cem.film.city;

/**
 * Created by qianggedemac on 17/1/7.
 */

public class LocationBean {
    /**
     * country : 中国
     * country_code : 0
     * province : 辽宁省
     * city : 大连市
     * district : 甘井子区
     * street : 弘远路
     * street_number :
     */

    private ResultBean result;
    /**
     * result : {"country":"中国","country_code":"0","province":"辽宁省","city":"大连市","district":"甘井子区","street":"弘远路","street_number":""}
     * error_code : 0
     * reason : Succes
     */

    private int error_code;
    private String reason;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class ResultBean {
        private String country;
        private String country_code;
        private String province;
        private String city;
        private String district;
        private String street;
        private String street_number;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreet_number() {
            return street_number;
        }

        public void setStreet_number(String street_number) {
            this.street_number = street_number;
        }
    }
}
