package com.example.cookapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor            //$$$
public class Ingredient {

    private String ingredientName;  //$$$ убрал модификатор final
    private String unit;            //$$$ убрал модификатор final
    private int count;              //$$$ убрал модификатор final

}
