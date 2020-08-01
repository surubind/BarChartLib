package suru.bind.barchartlib.model;

import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatImageView;


public class ChartContent {
    double value;
    AppCompatImageView image;
    int color;
    double height;
    Drawable icon;
    String title;

    public ChartContent(Drawable icon, String title, double value, int color, double height) {
        this.icon = icon;
        this.title = title;
        this.value = value;
        this.color = color;
        this.height = height;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public AppCompatImageView getImage() {
        return image;
    }

    public void setImage(AppCompatImageView image) {
        this.image = image;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        if (title == null) {
            title = "";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
