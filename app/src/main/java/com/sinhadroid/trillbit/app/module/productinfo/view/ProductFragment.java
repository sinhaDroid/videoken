package com.sinhadroid.trillbit.app.module.productinfo.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitFragment;
import com.sinhadroid.trillbit.app.module.offline.RecordDataHandler;
import com.sinhadroid.trillbit.app.module.productinfo.presenter.ProductPresenter;
import com.sinhadroid.trillbit.app.module.productinfo.presenter.ProductPresenterImpl;

import butterknife.BindView;

public class ProductFragment extends TrillbitFragment implements ProductView {

    @BindView(R.id.list_rcv)
    RecyclerView mRecyclerView;

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private ProductPresenter mProductPresenter;

    private boolean listview;

    public static ProductFragment newInstance(int position) {
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

        this.mProductPresenter = ProductPresenterImpl.newInstance(this);
        mProductPresenter.onCreate();
    }

    @Override
    public void setAdapter(ProductAdapter productAdapter) {
        mRecyclerView.setAdapter(productAdapter);
    }

    private void switchView() {
        if (listview) {
            listview = false;
            mStaggeredGridLayoutManager.setSpanCount(2);
        } else {
            listview = true;
            mStaggeredGridLayoutManager.setSpanCount(1);
        }
    }
}
