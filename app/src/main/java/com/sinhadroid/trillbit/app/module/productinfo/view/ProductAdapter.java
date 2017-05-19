package com.sinhadroid.trillbit.app.module.productinfo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.Adapter;
import com.sinhadroid.trillbit.app.module.productinfo.ProductResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends Adapter<ProductResponse, ProductAdapter.MyViewHolder> {
    public ProductAdapter(Context context) {
        super(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(getLayoutInflater().inflate(R.layout.inflater_product_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_name)
        TextView mProductName;

        @BindView(R.id.product_image)
        ImageView mProductImage;

        @BindView(R.id.discount_info)
        TextView mProductDiscount;

        @BindView(R.id.used_info)
        TextView mProductUsed;

        @BindView(R.id.coupon_code)
        TextView mProductCoupon;

        @BindView(R.id.product_time)
        TextView mProductTime;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        void bind(ProductResponse item) {
            mProductName.setText(item.getProductName());
            Glide.with(getLayoutInflater().getContext())
                    .load(item.getProductImage())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade()
                    .into(mProductImage);
            mProductDiscount.setText(item.getDiscountInfo());
            mProductUsed.setText(item.getUsedToday() + " Used");
            mProductCoupon.setText("Coupon: " + item.getCoupon());
            mProductTime.setText(item.getAdded());
        }
    }
}
