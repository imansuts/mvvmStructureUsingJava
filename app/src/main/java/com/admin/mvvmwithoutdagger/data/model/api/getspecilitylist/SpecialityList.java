
package com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialityList {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("type")
    @SerializedName("type")
    @Expose
    private String type;
    @JsonProperty("id")
    @SerializedName("id")
    @Expose
    private String id;
    @JsonProperty("name")
    @SerializedName("name")
    @Expose
    private String name;
    @JsonProperty("image")
    @SerializedName("image")
    @Expose
    private String image;
    @JsonProperty("is_active")
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @JsonProperty("is_deleted")
    @SerializedName("is_deleted")
    @Expose
    private String isDeleted;
    @JsonProperty("created_at")
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @JsonProperty("modified_at")
    @SerializedName("modified_at")
    @Expose
    private String modifiedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

}
