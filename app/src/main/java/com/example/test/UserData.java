package com.example.test;

public class UserData {

    DataClass data;
    SupportClass support;
    class DataClass{
        String first_name;
        String name;
        String[] starPower;
        String[] gadgets;


        public String getName() {
            return name;
        }
        public String getFirst_name(){
            return first_name;
        }

        public void setName(String name) {
            this.first_name = name;
        }

        public String[] getStarPower() {
            return starPower;
        }

        public void setStarPower(String[] starPower) {
            this.starPower = starPower;
        }

        public String[] getGadgets() {
            return gadgets;
        }

        public void setGadgets(String[] gadgets) {
            this.gadgets = gadgets;
        }
    }
    class SupportClass{
        String url, text;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
