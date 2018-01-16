package zhangjiye.bawie.com.boni.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.Song;



public class RecyclerAdapterBenDi extends RecyclerView.Adapter {

    ArrayList<Song> list;
    Context context;

    public RecyclerAdapterBenDi(ArrayList<Song> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycler_bendi, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.text1.setText(list.get(position).getSong());
        holder1.text2.setText(list.get(position).getSinger()+"-"+list.get(position).getSong());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.text2)
        TextView text2;
        @BindView(R.id.fenxiang)
        ImageView fenxiang;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 点击
     */
    public interface OnItemClickListener {
        void onClick(int position);
    }
    RecyclerAdapterBenDi.OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(RecyclerAdapterBenDi.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
