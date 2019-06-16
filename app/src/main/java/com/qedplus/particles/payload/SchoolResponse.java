package com.qedplus.particles.payload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qedplus.particles.db.School;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolResponse {
    @JsonProperty("name") private String name;
    @JsonProperty("aka") private String aka;
    @JsonProperty("schoolCode") private String schoolCode;
    @JsonProperty("schoolTheme") private String schoolTheme;
    @JsonProperty("address") private String address;
    @JsonProperty("logoURL") private String logoURL;
    @JsonProperty("board") private BoardResponse board;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolTheme() {
        return schoolTheme;
    }

    public void setSchoolTheme(String schoolTheme) {
        this.schoolTheme = schoolTheme;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public BoardResponse getBoard() {
        return board;
    }

    public void setBoard(BoardResponse board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "SchoolResponse{" +
                "name='" + name + '\'' +
                ", aka='" + aka + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", schoolTheme='" + schoolTheme + '\'' +
                ", address='" + address + '\'' +
                ", logoURL='" + logoURL + '\'' +
                ", board=" + board +
                '}';
    }
}
