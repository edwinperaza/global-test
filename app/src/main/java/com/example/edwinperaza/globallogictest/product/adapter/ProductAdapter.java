package com.example.edwinperaza.globallogictest.product.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edwinperaza.globallogictest.R;
import com.example.edwinperaza.globallogictest.product.modelObject.ProductModelObject;
import com.example.edwinperaza.globallogictest.utils.listeners.RecyclerViewListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModelObject> products;
    private RecyclerViewListener<ProductModelObject> listener;

    public ProductAdapter(List<ProductModelObject> products) {
        this.products = products;
    }

    public void setProducts(List<ProductModelObject> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void setListener(RecyclerViewListener<ProductModelObject> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(productView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModelObject product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.description.setText(product.getDescription());
        Picasso.get().load(product.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView container;
        ImageView image;
        TextView title;
        TextView description;

        public ViewHolder(View itemView, final RecyclerViewListener<ProductModelObject> listener) {
            super(itemView);

            container = itemView.findViewById(R.id.cv_product_container);
            image = itemView.findViewById(R.id.iv_product_image);
            title = itemView.findViewById(R.id.tv_product_title);
            description = itemView.findViewById(R.id.tv_product_description);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(products.get(getLayoutPosition()));
                }
            });
        }
    }
}
