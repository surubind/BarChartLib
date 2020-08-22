package suru.bind.custombarchart.model;

import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatImageView;


public class ChartContent {
    AppCompatImageView image;
    int color;
    Drawable icon;
    String title;
    double value;
    double total;

    public double getValue() {
        return value;
    }

    public void setValues(double value) {
        this.value = value;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ChartContent(Drawable icon, String title, int color, double value, double total) {
        this.icon = icon;
        this.title = title;
        this.color = color;
        this.value = value;
        this.total = total;
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
