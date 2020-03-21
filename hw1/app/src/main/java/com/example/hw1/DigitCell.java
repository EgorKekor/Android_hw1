package com.example.hw1;

import android.graphics.Color;

public class DigitCell {
    private int color;
    private Integer value;

    public DigitCell(Integer num) {
        this.value = num;
        this.color = ((num % 2) == 1) ? Color.BLUE : Color.RED;
    }

    public Integer getColor() {
        return color;
    }

    public Integer getValue() {
        return value;
    }
}
