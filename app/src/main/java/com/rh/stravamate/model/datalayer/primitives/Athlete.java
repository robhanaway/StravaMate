package com.rh.stravamate.model.datalayer.primitives;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Entity(tableName = "athlete")
public class Athlete {
    @ColumnInfo(name = "me")
    private boolean me;

    @SerializedName("sex")
    @ColumnInfo(name = "sex")
    @Expose
    private String sex;

    @SerializedName("resource_state")
    @ColumnInfo(name = "resource_state")
    @Expose
    private String resourceState;

    @SerializedName("premium")
    @ColumnInfo(name = "premium")
    @Expose
    private boolean premium;

    @SerializedName("state")
    @ColumnInfo(name = "state")
    @Expose
    private String state;

    @SerializedName("lastname")
    @ColumnInfo(name = "lastname")
    @Expose
    private String lastName;

    @SerializedName("firstname")
    @ColumnInfo(name = "firstname")
    @Expose
    private String firstName;

    @SerializedName("profile_medium")
    @ColumnInfo(name = "profile_medium")
    @Expose
    private String profileMedium;

    @SerializedName("city")
    @ColumnInfo(name = "city")
    @Expose
    private String city;

    @SerializedName("access_token")
    @ColumnInfo(name = "access_token")
    @Expose
    private String country;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    @Expose
    private String id;

    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("email")
    @ColumnInfo(name = "email")
    @Expose
    private String email;

    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    @Expose
    private Date createdAt;

    @SerializedName("profile")
    @ColumnInfo(name = "profile")
    @Expose
    private String profile;

    public String getSex() {
        return sex;
    }

    public String getResourceState() {
        return resourceState;
    }

    public boolean isPremium() {
        return premium;
    }

    public String getState() {
        return state;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getProfileMedium() {
        return profileMedium;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getProfile() {
        return profile;
    }

    public boolean isMe() {
        return me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setResourceState(String resourceState) {
        this.resourceState = resourceState;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setProfileMedium(String profileMedium) {
        this.profileMedium = profileMedium;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
