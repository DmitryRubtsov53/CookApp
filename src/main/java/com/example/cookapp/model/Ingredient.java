package com.example.cookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
@Data
@AllArgsConstructor
public class Ingredient {

    private final String ingredientName;
    private final String unit;
    private final int count;

//    public Ingredient(String ingredientName,  int count, String unit) {
//        this.ingredientName = ingredientName;
//        this.unit = unit;
//        this.count = count;
//    }
//
//    public String getIngredientName() { return ingredientName;
//    }
//
//    public String getUnit() { return unit;
//    }
//
//    public int getCount() { return count;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Ingredient that = (Ingredient) o;
//        return count == that.count && Objects.equals(ingredientName, that.ingredientName)
//                && Objects.equals(unit, that.unit);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(ingredientName, unit, count);
//    }
//
//    @Override
//    public String toString() {
//        return "Ingredient{ " + ingredientName +
//                ", unit: " + unit + ", count: " + count + " }";
//    }
}
