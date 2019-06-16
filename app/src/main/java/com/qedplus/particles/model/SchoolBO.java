package com.qedplus.particles.model;

public class SchoolBO {
    String schoolCode;
    String schoolName;
    String schoolLogoFileName;
    String theme;

    public String getTheme() {
        return theme;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLogoFileName() {
        return schoolLogoFileName;
    }

    public void setSchoolLogoFileName(String schoolLogoFileName) {
        this.schoolLogoFileName = schoolLogoFileName;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
