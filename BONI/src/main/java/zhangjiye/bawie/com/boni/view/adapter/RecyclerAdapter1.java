package zhangjiye.bawie.com.boni.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.MusicBean;
import zhangjiye.bawie.com.boni.okhttp.AbstractUiCallBack;
import zhangjiye.bawie.com.boni.okhttp.OkhttpUtils;


public class RecyclerAdapter1 extends RecyclerView.Adapter{

    private static final int TYPE_PROFILE = 0;
    private static final int TYPE_MUSIC_LIST = 1;
    private String baseUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?fromat=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=";
    private String url = "&size=10&offset=0";
    private int i;
    Context context;
    private ViewHolder1 holder1;
    public RecyclerAdapter1(Context context) {
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0){
            View view = View.inflate(context, R.layout.item_text, null);
            return new ViewHolder1(view);
        }else{
            View view = View.inflate(context, R.layout.item_leixin, null);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder1){
            holder1 = (ViewHolder1) holder;
            if (position == 0){
                holder1.bangdan.setText("主打榜单");
            }else if(position == 3){
                holder1.bangdan.setText("分类榜单");
            }else if (position == 10){
                holder1.bangdan.setText("媒体榜单");
            }
        }else {
            if (holder instanceof ViewHolder2) {
                final ViewHolder2 holder2 = (ViewHolder2) holder;
                if (position == 1 || position == 2) {
                    i = position;
                    OkhttpUtils.getInstance().asy(null, baseUrl + position + url, new AbstractUiCallBack<MusicBean>() {
                        @Override
                        public void success(MusicBean bean) {
                            List<MusicBean.SongListBean> song_list = bean.getSong_list();
                            String s = bean.getBillboard().getPic_s260();
                            ImageLoader.getInstance().displayImage(s,holder2.image);
                            holder2.text1.setText("1."+song_list.get(0).getTitle()+" - "+song_list.get(0).getAuthor());
                            holder2.text2.setText("2."+song_list.get(1).getTitle()+" - "+song_list.get(1).getAuthor());
                            holder2.text3.setText("3."+song_list.get(2).getTitle()+" - "+song_list.get(2).getAuthor());
                        }

                        @Override
                        public void failure(Exception e) {

                        }
                    });
                }else if(position == 4 || position == 5){
                    i = position + 7;
                    OkhttpUtils.getInstance().asy(null, baseUrl + i + url, new AbstractUiCallBack<MusicBean>() {
                        @Override
                        public void success(MusicBean bean) {
                            List<MusicBean.SongListBean> song_list = bean.getSong_list();
                            String s = bean.getBillboard().getPic_s260();
                            ImageLoader.getInstance().displayImage(s,holder2.image);
                            holder2.text1.setText("1."+song_list.get(0).getTitle()+" - "+song_list.get(0).getAuthor());
                            holder2.text2.setText("2."+song_list.get(1).getTitle()+" - "+song_list.get(1).getAuthor());
                            holder2.text3.setText("3."+song_list.get(2).getTitle()+" - "+song_list.get(2).getAuthor());
                        }

                        @Override
                        public void failure(Exception e) {

                        }
                    });
                }else if(position == 6){
                    i = position + 10;
                    OkhttpUtils.getInstance().asy(null, baseUrl + i + url, new AbstractUiCallBack<MusicBean>() {
                        @Override
                        public void success(MusicBean bean) {
                            List<MusicBean.SongListBean> song_list = bean.getSong_list();
                            String s = bean.getBillboard().getPic_s260();
                            ImageLoader.getInstance().displayImage(s,holder2.image);
                            holder2.text1.setText("1."+song_list.get(0).getTitle()+" - "+song_list.get(0).getAuthor());
                            holder2.text2.setText("2."+song_list.get(1).getTitle()+" - "+song_list.get(1).getAuthor());
                            holder2.text3.setText("3."+song_list.get(2).getTitle()+" - "+song_list.get(2).getAuthor());
                        }

                        @Override
                        public void failure(Exception e) {

                        }
                    });
                }else if(position == 7 || position == 8){
                    i = position + 14;
                    OkhttpUtils.getInstance().asy(null, baseUrl + i + url, new AbstractUiCallBack<MusicBean>() {
                        @Override
                        public void success(MusicBean bean) {
                            List<MusicBean.SongListBean> song_list = bean.getSong_list();
                            String s = bean.getBillboard().getPic_s260();
                            ImageLoader.getInstance().displayImage(s,holder2.image);
                            holder2.text1.setText("1."+song_list.get(0).getTitle()+" - "+song_list.get(0).getAuthor());
                            holder2.text2.setText("2."+song_list.get(1).getTitle()+" - "+song_list.get(1).getAuthor());
                            holder2.text3.setText("3."+song_list.get(2).getTitle()+" - "+song_list.get(2).getAuthor());
                        }

                        @Override
                        public void failure(Exception e) {

                        }
                    });
                }else if (position == 10 || position == 11 || position == 12){
                    i = position + 13;
                    OkhttpUtils.getInstance().asy(null, baseUrl + i + url, new AbstractUiCallBack<MusicBean>() {
                        @Override
                        public void success(MusicBean bean) {
                            List<MusicBean.SongListBean> song_list = bean.getSong_list();
                            String s = bean.getBillboard().getPic_s260();
                            ImageLoader.getInstance().displayImage(s,holder2.image);
                            holder2.text1.setText("1."+song_list.get(0).getTitle()+" - "+song_list.get(0).getAuthor());
                            holder2.text2.setText("2."+song_list.get(1).getTitle()+" - "+song_list.get(1).getAuthor());
                            holder2.text3.setText("3."+song_list.get(2).getTitle()+" - "+song_list.get(2).getAuthor());
                        }

                        @Override
                        public void failure(Exception e) {

                        }
                    });
                }
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onClick(position,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 13;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 3 || position == 9){
            return 0;
        }else{
            return 1;
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{

        private final TextView bangdan;

        public ViewHolder1(View itemView) {
            super(itemView);
            bangdan = (TextView) itemView.findViewById(R.id.bangdan);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{

        private final ImageView image;
        private final TextView text1;
        private final TextView text2;
        private final TextView text3;

        public ViewHolder2(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text1 = (TextView) itemView.findViewById(R.id.text1);
            text2 = (TextView) itemView.findViewById(R.id.text2);
            text3 = (TextView) itemView.findViewById(R.id.text3);
        }
    }

    /**
     * 点击
     */
    public interface OnItemClickListener {
        void onClick(int position,int i);
    }
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
