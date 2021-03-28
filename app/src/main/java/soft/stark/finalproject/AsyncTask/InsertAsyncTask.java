package soft.stark.finalproject.AsyncTask;

import android.os.AsyncTask;

import soft.stark.finalproject.ProductModel;
import soft.stark.finalproject.Room.ProductDAO;

public class InsertAsyncTask extends AsyncTask<ProductModel,Void ,Void> {


    private ProductDAO productDAO;

    public InsertAsyncTask(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    protected Void doInBackground(ProductModel... productModels) {
        productDAO.insertProduct(productModels[0]);
        return null;
    }
}
