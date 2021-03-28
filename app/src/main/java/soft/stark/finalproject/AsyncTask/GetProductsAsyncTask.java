package soft.stark.finalproject.AsyncTask;

import android.os.AsyncTask;

import java.util.List;

import soft.stark.finalproject.ProductModel;
import soft.stark.finalproject.Room.ProductDAO;

public class GetProductsAsyncTask extends AsyncTask<Void,Void, List<ProductModel>> {

    private ProductDAO productDAO;

    public GetProductsAsyncTask(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    protected List<ProductModel> doInBackground(Void... voids) {
        return productDAO.getAllProducts();
    }
}
