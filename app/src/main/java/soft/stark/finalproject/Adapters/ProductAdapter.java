package soft.stark.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import soft.stark.finalproject.ProductModel;
import soft.stark.finalproject.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductsViewHolder> {

    private List<ProductModel> productsList;
    private Context context;
    private OnProductClickListener onProductClickListener;

    private onAddProductsClickListener onAddProductsClickListener;

    public interface OnProductClickListener{
        void onProductClick(View view,int position);
    }

    public interface onAddProductsClickListener{
        void onAddProductClick(View view,int position);
    }

    public ProductAdapter(List<ProductModel> productsList, Context context, OnProductClickListener onProductClickListener, ProductAdapter.onAddProductsClickListener onAddProductsClickListener) {
        this.productsList = productsList;
        this.context = context;
        this.onProductClickListener = onProductClickListener;
        this.onAddProductsClickListener = onAddProductsClickListener;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_rv_items,parent,false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        ProductModel productModel= productsList.get(position);
        holder.productTitle.setText(productModel.getTitle());
        holder.productDetails.setText(productModel.getDetails());
        holder.productPrice.setText(productModel.getPrice());

        Glide.with(context).load(productModel.getImage()).into(holder.productIv);


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onProductClickListener.onProductClick(v, holder.getAdapterPosition());
            }
        });
        holder.addToCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onAddProductsClickListener.onAddProductClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder{

        ImageView productIv;
        TextView productTitle;
        TextView productDetails;
        TextView productPrice;
        ImageButton addToCard;



        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv =itemView.findViewById(R.id.product_iv);
            productTitle =itemView.findViewById(R.id.product_title_tv);
            productDetails =itemView.findViewById(R.id.product_details_tv);
            productPrice =itemView.findViewById(R.id.product_price_tv);
            addToCard =itemView.findViewById(R.id.add_product_ib);


        }
    }


}
