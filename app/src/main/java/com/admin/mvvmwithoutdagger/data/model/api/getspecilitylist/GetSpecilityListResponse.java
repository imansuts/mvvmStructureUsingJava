
package com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSpecilityListResponse {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("code")
    @SerializedName("code")
    @Expose
    private String code;
    @JsonProperty("status")
    @SerializedName("status")
    @Expose
    private String status;
    @JsonProperty("result")
    @SerializedName("result")
    @Expose
    private Result result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
