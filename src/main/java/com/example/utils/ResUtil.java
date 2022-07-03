package com.example.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.util.Objects;

public class ResUtil {


    public static FlatSVGIcon getSVGIcon(String name) {
        return new FlatSVGIcon(Objects.requireNonNull(
                ResUtil.class.getResource("/icons/" + name)));
    }
}
