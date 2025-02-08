package com.example.prediccion.entity;

import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Iris implements Serializable {
    private float a1;
    private float a2;
    private float a3;
    private float a4;

    public Iris() {}

    public Iris(float a1, float a2, float a3, float a4) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
    }

    public float getA1() {
        return a1;
    }

    public void setA1(float a1) {
        this.a1 = a1;
    }

    public float getA2() {
        return a2;
    }

    public void setA2(float a2) {
        this.a2 = a2;
    }

    public float getA3() {
        return a3;
    }

    public void setA3(float a3) {
        this.a3 = a3;
    }

    public float getA4() {
        return a4;
    }

    public void setA4(float a4) {
        this.a4 = a4;
    }
}