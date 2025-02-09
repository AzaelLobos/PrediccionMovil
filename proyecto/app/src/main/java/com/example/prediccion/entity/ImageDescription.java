package com.example.prediccion.entity;

    public class ImageDescription {
        private final int imageResId;
        private final String description;

        public ImageDescription(int imageResId, String description) {
            this.imageResId = imageResId;
            this.description = description;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getDescription() {
            return description;
        }
    }