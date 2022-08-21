package com.alperen.w_02.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_02.R;
import com.alperen.w_02.models.ProductModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Alperen on 17.08.2022.
 */
public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {
    private List<ProductModel> list;
    private List<ProductModel> cartItems;
    IRecyclerViewEvent event;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        // Added item count variable
        int frequency = 0;
        ImageView ivCartImage = itemView.findViewById(R.id.ivCartImage);
        TextView tvCartItemName = itemView.findViewById(R.id.tvCartItemName);
        ImageButton ibCartMinus = itemView.findViewById(R.id.ibCartMinus);
        ImageButton ibCartPlus = itemView.findViewById(R.id.ibCartPlus);
        TextView tvCartQuantity = itemView.findViewById(R.id.tvCartQuantity);
        TextView tvCartItemPrice = itemView.findViewById(R.id.tvCartItemPrice);
    }

    public CartRecyclerAdapter(List<ProductModel> list, IRecyclerViewEvent event) {
        this.list = list;
        // Get unique product list
        List<ProductModel> temp = list.stream()
                .filter(distinctByKey(ProductModel::getId))
                .collect(Collectors.toList());

        this.cartItems = new ArrayList<ProductModel>(temp);
        this.event = event;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.frequency = getFrequency(cartItems.get(position));

        holder.tvCartItemName.setText(cartItems.get(position).name);
        double price = cartItems.get(position).price * holder.frequency;
        holder.tvCartItemPrice.setText(String.format("$%.2f", price));
        holder.tvCartQuantity.setText(String.valueOf(holder.frequency));

        StorageReference reference = FirebaseStorage.getInstance().getReference(cartItems.get(position).image);
        GlideApp.with(holder.itemView.getContext())
                .load(reference)
                .into(holder.ivCartImage);

        holder.ibCartMinus.setOnClickListener(view -> {
            if (holder.frequency == 1) {
                event.removeItemFromCart(cartItems.get(position));
                cartItems.remove(position);
                notifyDataSetChanged();
            } else {
                holder.frequency--;
                event.removeItemFromCart(cartItems.get(position));
                updateViewHolder(holder, position);
            }
        });

        holder.ibCartPlus.setOnClickListener(view -> {
            holder.frequency++;
            event.addItemToCart(cartItems.get(position));
            updateViewHolder(holder, position);
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Get item count for each unique item
    // Example: 3 egg, 2 meat
    private int getFrequency(ProductModel item) {
        return Collections.frequency(list, item);
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    private void updateViewHolder(ViewHolder holder, int position) {
        double price = cartItems.get(position).price * holder.frequency;
        holder.tvCartItemPrice.setText(String.format("$%.2f", price));
        holder.tvCartQuantity.setText(String.valueOf(holder.frequency));
        notifyDataSetChanged();
    }

    public List<ProductModel> getCartItems() {
        List<ProductModel> result = new ArrayList<>(cartItems);

        for(ProductModel item : result) {
            int frequency = getFrequency(item);
            item.count = frequency;
            item.weight *= frequency;
            item.price *= frequency;
        }

        return result;
    }


}
