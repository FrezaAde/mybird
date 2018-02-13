package com.mybird;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCatModel {

    @SerializedName("id_sub_cat")
    @Expose
    private String idSubCat;

    @SerializedName("id_kategori")
    @Expose
    private String idKategori;

    @SerializedName("nama_sub")
    @Expose
    private String namaSub;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("status")
    @Expose
    private String status;

    public String getIdSubCat() {
        return idSubCat;
    }

    public void setIdSubCat(String idSubCat) {
        this.idSubCat = idSubCat;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaSub() {
        return namaSub;
    }

    public void setNamaSub(String namaSub) {
        this.namaSub = namaSub;
    }

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}