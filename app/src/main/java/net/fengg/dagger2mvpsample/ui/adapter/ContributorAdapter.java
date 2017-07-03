package net.fengg.dagger2mvpsample.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;

import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.model.Contributor;
import net.fengg.dagger2mvpsample.ui.view.activity.SecondActivity;

/**
 * Created by feng on 2017/6/23.
 */

public class ContributorAdapter extends BaseQuickAdapter<Contributor, BaseViewHolder> {

    public ContributorAdapter() {
        super(R.layout.item_second, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Contributor item) {
        helper.setText(R.id.tv_text, item.toString());
//                .addOnClickListener(R.id.tv_share)
//                .addOnClickListener(R.id.tv_delete)
//                .addOnClickListener(R.id.tv_collect)
//                .addOnClickListener(R.id.tv_text);
        helper.getView(R.id.tv_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SecondActivity)mContext).showToast("收藏");
                resetItemStatus(helper);
            }
        });
        helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(helper.getAdapterPosition());
            }
        });
        helper.getView(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetItemStatus(helper);
                ((SecondActivity)mContext).openThird(item);
            }
        });

        Glide.with(mContext).load(item.getAvatar_url()).into((ImageView) helper.getView(R.id.iv_item));
    }

    public void resetItemStatus(BaseViewHolder helper) {
        EasySwipeMenuLayout easySwipeMenuLayout = helper.getView(R.id.ctm_es_second);
        easySwipeMenuLayout.resetStatus();
    }

}
