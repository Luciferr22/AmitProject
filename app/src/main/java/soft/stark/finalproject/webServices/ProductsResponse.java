package soft.stark.finalproject.webServices;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import soft.stark.finalproject.ProductModel;

public class ProductsResponse {

    @SerializedName("products")
    private List<ProductModel> productList;

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}
