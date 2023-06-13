package com.angelopicc.saute.utility.comparator;

import java.util.Comparator;

import com.angelopicc.saute.entity.RecipeBook;

public class RecipeBookComparator implements Comparator<RecipeBook> {

    @Override
    public int compare(RecipeBook o1, RecipeBook o2) {
        String o1Name = o1.getRecipeBookName();
        String o2Name = o2.getRecipeBookName();
        
        if (o1Name.compareTo(o2Name) > 0) {
            return 1;
        } else if (o1Name.compareTo(o2Name) < 0) {
            return -1;
        }
        return 0;
    }
    
}
