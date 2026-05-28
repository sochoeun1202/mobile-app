package com.sochoeun.myapplication;

import java.io.Serializable;

public class Province implements Serializable {
    private int id;
    private String nameEn;
    private String nameKh;
    private String imageName;

    public Province(int id, String nameEn, String nameKh, String imageName) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameKh = nameKh;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameKh() {
        return nameKh;
    }

    public String getImageName() {
        return imageName;
    }

    public int getImageResourceId() {
        // This is a helper to get the resource ID from the image name
        // Strip .png extension
        String resourceName = imageName.replace(".png", "");
        return resourceName.isEmpty() ? 0 : 0; // Will be handled in adapter
    }
}
