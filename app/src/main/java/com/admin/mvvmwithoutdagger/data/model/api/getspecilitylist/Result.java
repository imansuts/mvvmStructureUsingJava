
package com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @JsonProperty("status")
    @SerializedName("status")
    @Expose
    private Boolean status;
    @JsonProperty("code")
    @SerializedName("code")
    @Expose
    private Integer code;
    @JsonProperty("message")
    @SerializedName("message")
    @Expose
    private String message;
    @JsonProperty("speciality_list")
    @SerializedName("speciality_list")
    @Expose
    private ArrayList<SpecialityList> specialityList = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<SpecialityList> getSpecialityList() {
        return specialityList;
    }

    public void setSpecialityList(ArrayList<SpecialityList> specialityList) {
        this.specialityList = specialityList;
    }

}
