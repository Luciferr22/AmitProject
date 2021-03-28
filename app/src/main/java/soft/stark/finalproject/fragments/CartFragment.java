package soft.stark.finalproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import soft.stark.finalproject.Adapters.CartAdapter;
import soft.stark.finalproject.AsyncTask.DeleteAsyncTask;
import soft.stark.finalproject.AsyncTask.GetProductsAsyncTask;
import soft.stark.finalproject.AsyncTask.UpdateAsyncTask;
import soft.stark.finalproject.ProductModel;
import soft.stark.finalproject.R;
import soft.stark.finalproject.Room.RoomFactory;


public class CartFragment extends Fragment {

    private RecyclerView cartRV;
    private CartAdapter cartAdapter;

    ArrayList<ProductModel>productsList =new ArrayList<>();
    private Button clearBtn;
    private Button checkBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRV = view.findViewById(R.id.cart_rv);
        clearBtn = view.findViewById(R.id.clear_btn);
        checkBtn = view.findViewById(R.id.checkout_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupClickListeners();
        getAllProductsFromDB();
    }

    private void getAllProductsFromDB() {
        try {
            productsList.addAll(new GetProductsAsyncTask((RoomFactory.getRoomDatabase(requireContext()).getProductDao())).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cartAdapter.notifyDataSetChanged();
    }

    private void setupClickListeners() {

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute();
                productsList.clear();
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecyclerView() {

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(requireContext());
        cartRV.setLayoutManager(layoutManager);
        cartRV.addItemDecoration(new DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL));

        cartAdapter= new CartAdapter(requireContext(), productsList, new CartAdapter.OnIncClickListener() {
            @Override
            public void onIncClick(View view, int position) {
                productsList.get(position).setQuantity(productsList.get(position).getQuantity()+1);
                new UpdateAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute(productsList.get(position));
                cartAdapter.notifyDataSetChanged();
            }
        }, new CartAdapter.OnDecClickListener() {
            @Override
            public void onDecClick(View view, int position) {
                productsList.get(position).setQuantity(productsList.get(position).getQuantity()-1);
                new UpdateAsyncTask(RoomFactory.getRoomDatabase(requireContext()).getProductDao()).execute(productsList.get(position));
                cartAdapter.notifyDataSetChanged();
            }
        });
        cartRV.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }
}