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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<ProductModel> productModelList;

    private OnIncClickListener onIncClickListener;
    private OnDecClickListener onDecClickListener;

    public interface OnIncClickListener{
        void onIncClick(View view, int position);
    }
    public interface OnDecClickListener{
        void onDecClick(View view, int position);
    }


    public CartAdapter(Context context, List<ProductModel> productModelList, OnIncClickListener onIncClickListener, OnDecClickListener onDecClickListener) {
        this.context = context;
        this.productModelList = productModelList;
        this.onIncClickListener = onIncClickListener;
        this.onDecClickListener = onDecClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_rv_item,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        ProductModel productModel = productModelList.get(position);
        Glide.with(context).load(productModel.getImage()).into(holder.productIv);
        holder.titleTv.setText(productModel.getTitle());
        holder.detailsTv.setText(productModel.getDetails());
        holder.priceTv.setText(productModel.getPrice());
        holder.quantityTv.setText(productModel.getQuantity()+"");

        holder.incIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIncClickListener.onIncClick(v, holder.getAdapterPosition());
            }
        });
        holder.decIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDecClickListener.onDecClick(v, holder.getAdapterPosition());
            }
        });




    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productIv;
        TextView titleTv;
        TextView detailsTv;
        TextView priceTv;
        TextView quantityTv;
        ImageButton incIb;
        ImageButton decIb;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv = itemView.findViewById(R.id.product_card_iv);
            titleTv = itemView.findViewById(R.id.product_card_title_tv);
            detailsTv = itemView.findViewById(R.id.product_card_details_tv);
            priceTv = itemView.findViewById(R.id.product_card_price_tv);
            quantityTv = itemView.findViewById(R.id.quantity_tv);
            incIb = itemView.findViewById(R.id.inc_ib);
            decIb = itemView.findViewById(R.id.dec_ib);



        }
    }
}
