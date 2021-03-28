package soft.stark.finalproject.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import soft.stark.finalproject.ProductModel;

@Database(entities = {ProductModel.class}, version = 1,exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract  ProductDAO getProductDao();

}
