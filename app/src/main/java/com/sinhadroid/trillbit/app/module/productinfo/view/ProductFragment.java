package com.sinhadroid.trillbit.app.module.productinfo.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitFragment;
import com.sinhadroid.trillbit.app.module.productinfo.presenter.ProductPresenter;
import com.sinhadroid.trillbit.app.module.productinfo.presenter.ProductPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductFragment extends TrillbitFragment implements ProductView {

    @BindView(R.id.list_rcv)
    RecyclerView mRecyclerView;

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private boolean gridview;

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    @Override
    protected int getInflater() {
        return R.layout.fragment_product;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL
        );
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        ProductPresenter mProductPresenter = ProductPresenterImpl.newInstance(this);
        mProductPresenter.onCreate();
    }

    @Override
    public void setAdapter(ProductAdapter productAdapter) {
        mRecyclerView.setAdapter(productAdapter);
    }

    @OnClick(R.id.btnswitch)
    public void onClickSwitch(View view) {
        if (gridview) {
            gridview = false;
            mStaggeredGridLayoutManager.setSpanCount(1);
        } else {
            gridview = true;
            mStaggeredGridLayoutManager.setSpanCount(2);
        }
    }
}
