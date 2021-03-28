package soft.stark.finalproject.AsyncTask;

import android.os.AsyncTask;

import soft.stark.finalproject.Room.ProductDAO;

public class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {

    private ProductDAO productDAO;

    public DeleteAsyncTask(ProductDAO productDAO) {

        this.productDAO = productDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        productDAO.deleteAllProducts();
        return null;
    }
}
