package com.sinhadroid.trillbit.app.module.productinfo.presenter;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sinhadroid.trillbit.app.module.productinfo.ProductResponse;
import com.sinhadroid.trillbit.app.module.productinfo.SampleJson;
import com.sinhadroid.trillbit.app.module.productinfo.view.ProductAdapter;
import com.sinhadroid.trillbit.app.module.productinfo.view.ProductView;
import com.sinhadroid.trillbit.app.webservice.MyWebService;

import java.util.List;

public class ProductPresenterImpl implements ProductPresenter {

    private ProductView mProductView;

    private ProductPresenterImpl(ProductView productView) {
        this.mProductView = productView;
    }

    public static ProductPresenterImpl newInstance(ProductView productView) {
        return new ProductPresenterImpl(productView);
    }

    @Override
    public void onCreate() {
        Context context = mProductView.getContext();

        if (null != context) {
            ProductAdapter productAdapter;
            mProductView.setAdapter(productAdapter = new ProductAdapter(context));

            productAdapter.clear();
            productAdapter.addAll(MyWebService.getInstance()
                    .getObjectFromJson(SampleJson.SAMPLE_RESPONSE,
                            new TypeReference<List<ProductResponse>>() {
                            }));
            productAdapter.notifyDataSetChanged();
        }
    }
}
