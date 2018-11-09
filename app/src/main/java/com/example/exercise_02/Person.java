package com.example.exercise_02;

class Person {
    private int picture;
    private String name;
    private  int house;

    public Person (int picture, String name, int house){
        this.picture = picture;
        this.name = name;
        this.house = house;
    }

    public int getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public int getHouse() {
        return house;
    }
}
