package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class ProductPageData {

    private ProductPageData(){}


    @DataProvider(name = "productQuantityData")
    public static Object[][]getQuantityData(){
        return new Object[][]{
                {"1","valid quantity","You added"},
                {"0","invalid quantity","Please enter a quantity greater than 0."},
                {"-1","invalid quantity","Please enter a quantity greater than 0."},
        };
    }

    @DataProvider(name = "productSizeData" )
    public static Object[][]getData(){
        return new Object[][]{
                {"XS","You added"},
                {"S","You added"},
                {"M","You added"},
                {"L","You added"},
                {"XL","You added"}
        };
    }

    @DataProvider(name = "productColorData")
    public static Object[][]getProductColorData(){
        return new Object[][]{
                {"Black","You added"},
                {"Blue","You added"},
                {"Orange","You added"}
        };
    }
}
