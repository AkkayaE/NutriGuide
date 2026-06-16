package com.example.nutritionapp.model;

import java.io.Serializable;
import java.util.Locale;

public class FoodItem implements Serializable {
    private final String name;
    private final String brand;
    private final String servingSize;
    private final double energyKcal;
    private final double proteinGrams;
    private final double carbohydrateGrams;
    private final double fatGrams;
    private final double sugarGrams;

    public FoodItem(
            String name,
            String brand,
            String servingSize,
            double energyKcal,
            double proteinGrams,
            double carbohydrateGrams,
            double fatGrams,
            double sugarGrams
    ) {
        this.name = name;
        this.brand = brand;
        this.servingSize = servingSize;
        this.energyKcal = energyKcal;
        this.proteinGrams = proteinGrams;
        this.carbohydrateGrams = carbohydrateGrams;
        this.fatGrams = fatGrams;
        this.sugarGrams = sugarGrams;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getServingSize() {
        return servingSize;
    }

    public double getEnergyKcal() {
        return energyKcal;
    }

    public double getProteinGrams() {
        return proteinGrams;
    }

    public double getCarbohydrateGrams() {
        return carbohydrateGrams;
    }

    public double getFatGrams() {
        return fatGrams;
    }

    public double getSugarGrams() {
        return sugarGrams;
    }

    public String getSummary() {
        return String.format(
                Locale.US,
                "%.0f kcal | P %.1fg | C %.1fg | F %.1fg",
                energyKcal,
                proteinGrams,
                carbohydrateGrams,
                fatGrams
        );
    }
}
