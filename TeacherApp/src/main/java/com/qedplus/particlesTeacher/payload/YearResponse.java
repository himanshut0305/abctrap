package com.qedplus.particlesTeacher.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qedplus.particlesTeacher.db.SchoolClass;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YearResponse {
    @JsonProperty("name") private String name;

    public String getName() {
        return name;
    }

    public int getYear() {
        switch (name) {
            case "6" : return SchoolClass.SIX;     
            case "7" : return SchoolClass.SEVEN;    
            case "8" : return SchoolClass.EIGHT;    
            case "9" : return SchoolClass.NINE;     
            case "10" : return SchoolClass.TEN;     
            case "11" : return SchoolClass.ELEVEN;  
            case "12" : return SchoolClass.TWELVE;  
            default: return SchoolClass.TEN;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "YearResponse{" +
                "name='" + name + '\'' +
                '}';
    }
}
