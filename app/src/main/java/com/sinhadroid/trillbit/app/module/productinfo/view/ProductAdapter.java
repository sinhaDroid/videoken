package com.sinhadroid.trillbit.app.module.productinfo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.Adapter;
import com.sinhadroid.trillbit.app.module.productinfo.ProductResponse;

import org.w3c.dom.Text;

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

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_name)
        TextView mProductName;

        @BindView(R.id.product_image)
        ImageView mProductImage;

        @BindView(R.id.discount_info)
        TextView mProductDiscount;

        @BindView(R.id.)
        TextView mProductDiscount;

        @BindView(R.id.discount_info)
        TextView mProductDiscount;

        @BindView(R.id.discount_info)
        TextView mProductDiscount;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
