package com.example.prediccion.entity;

import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Iris implements Serializable {
    private float a1;
    private float a2;
    private float a3;
    private float a4;

}