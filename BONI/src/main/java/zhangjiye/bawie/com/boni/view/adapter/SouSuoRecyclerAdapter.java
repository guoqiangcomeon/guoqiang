package zhangjiye.bawie.com.boni.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.SouSuoBean;


public class SouSuoRecyclerAdapter extends RecyclerView.Adapter {
    Context context;
    List<SouSuoBean.SongBean> song;

    public SouSuoRecyclerAdapter(Context context, List<SouSuoBean.SongBean> song) {
        this.context = context;
        this.song = song;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycler_sousuo, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder holder1 = (ViewHolder) holder;
        holder1.text1.setText(song.get(position).getSongname());
        holder1.text2.setText(song.get(position).getArtistname());

    }

    @Override
    public int getItemCount() {
        return song.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
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
}
