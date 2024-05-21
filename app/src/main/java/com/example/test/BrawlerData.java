package com.example.test;

import com.google.gson.annotations.SerializedName;

public class BrawlerData {
    int id, avatarID;
    String name, imageUrl, imageUrl2, imageUrl3, description;

    g[] gadgets;
    sP[] starPowers;
    raR rarity;

    @SerializedName("class")
    Class brawlerClass;







    public int getId() {
        return id;
    }
    public int getAvatarID(){
        return avatarID;
    }
    public String getName(){
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public String getDescription(){
        return description;
    }


    class g{
        String name, description, imageUrl;

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    class sP{
        String name, description, imageUrl;

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    class raR{
        String name, color;

        public String getName(){
            return name;
        }

        public String getColor(){
            return color;
        }
    }

    class Class{
        String name;
        public String getName(){
            return name;
        }
    }




}

