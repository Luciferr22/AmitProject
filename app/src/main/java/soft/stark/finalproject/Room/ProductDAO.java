package soft.stark.finalproject.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import soft.stark.finalproject.ProductModel;

@Dao
public interface ProductDAO {


    @Insert
    void insertProduct(ProductModel productModel);

    @Query("SELECT * FROM products")
    List<ProductModel> getAllProducts();

    @Query("DELETE FROM PRODUCTS")
    void deleteAllProducts();


    @Update
    void updateProducts(ProductModel productModel);






}
