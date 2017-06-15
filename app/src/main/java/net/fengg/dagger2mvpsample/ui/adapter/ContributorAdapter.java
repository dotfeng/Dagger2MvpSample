package net.fengg.dagger2mvpsample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.ui.view.activity.SecondActivity;

import java.util.List;

/**
 * Created by feng on 2017/6/9.
 */

public class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    SecondActivity context;
    List<Contributor> datas;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Contributor contributor);
        void onItemLongClick(View view, Contributor contributor);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public ContributorAdapter(SecondActivity context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

        return holder;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Contributor) v.getTag());
        }
    }
    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener!= null) {
            mOnItemClickListener.onItemLongClick(v, (Contributor) v.getTag());
        }
        return false;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(null != datas) {
            holder.tv_text.setText(datas.get(position).toString());
            Glide.with(context).load(datas.get(position).getAvatar_url()).into(holder.imageView);
            holder.itemView.setTag(datas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return null == datas? 0 : datas.size();
    }

    public void setDatas(List<Contributor> contributors) {
        this.datas = contributors;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv_text;
        ImageView imageView;

        public MyViewHolder(View view)
        {
            super(view);
            tv_text = (TextView) view.findViewById(R.id.tv_text);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
