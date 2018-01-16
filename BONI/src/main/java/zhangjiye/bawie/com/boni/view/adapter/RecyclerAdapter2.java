package zhangjiye.bawie.com.boni.view.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.BoFangBean;
import zhangjiye.bawie.com.boni.model.bean.MusicBean;
import zhangjiye.bawie.com.boni.okhttp.AbstractUiCallBack;
import zhangjiye.bawie.com.boni.okhttp.OkhttpUtils;
import zhangjiye.bawie.com.boni.view.activity.Main2Activity;
import zhangjiye.bawie.com.boni.view.activity.Main3Activity;


public class RecyclerAdapter2 extends RecyclerView.Adapter{

    Context context;
    List<MusicBean.SongListBean> song_list;
    private PopupWindow popupWindow;

    public RecyclerAdapter2(Context context, List<MusicBean.SongListBean> song_list) {
        this.context = context;
        this.song_list = song_list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recycler_leibiao, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        ImageLoader.getInstance().displayImage(song_list.get(position).getPic_radio(),holder1.image);
        holder1.text1.setText(song_list.get(position).getTitle());
        holder1.text2.setText(song_list.get(position).getAuthor()+" - "+song_list.get(position).getTitle());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });
           //歌曲详情的

       /* holder1.fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("列表弹窗")
                        .setItems(R.array.list, new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:

                                        *//*Intent intent = new Intent(context, Main2Activity.class);
                                        context.startActivity(intent);*//*
                                        OkhttpUtils.getInstance().asy(null, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=" + song_list.get(position).getSong_id(), new AbstractUiCallBack<BoFangBean>() {
                                            @Override
                                            public void success(BoFangBean bean) {
                                                String file_link = bean.getBitrate().getFile_link();
                                                Intent share_intent = new Intent();
                                                share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                                                share_intent.setType("text/plain");//设置分享内容的类型
                                                share_intent.putExtra(Intent.EXTRA_SUBJECT, file_link);//添加分享内容标题
                                                share_intent.putExtra(Intent.EXTRA_TEXT, file_link);//添加分享内容
                                                //创建分享的Dialog
                                                share_intent = Intent.createChooser(share_intent, "分享");
                                                context.startActivity(share_intent);

                                            }
                                            @Override
                                            public void failure(Exception e) {
                                            }
                                        });

                                      Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
                                         break;
                                    case 1:
                                       *//* Intent intent1 = new Intent(context,Main3Activity.class);
                                        context.startActivity(intent1);*//*

                                        OkhttpUtils.getInstance().asy(null, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=" + song_list.get(position).getSong_id(), new AbstractUiCallBack<BoFangBean>() {
                                            @Override
                                            public void success(BoFangBean bean) {
                                                String file_link = bean.getBitrate().getFile_link();
                                                String title = bean.getSonginfo().getTitle();
                                                //创建下载任务,downloadUrl就是下载链接
                                                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(file_link));
                                                //指定下载路径和下载文件名
                                                request.setDestinationInExternalPublicDir("/storage/emulated/0/PonyMusic/Music",title);
                                                //获取下载管理器
                                                DownloadManager downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                                               //将下载任务加入下载队列，否则不会进行下载
                                                downloadManager.enqueue(request);
                                            }
                                            @Override
                                            public void failure(Exception e) {
                                            }
                                        });
                                        Toast.makeText(context, "下载", Toast.LENGTH_SHORT).show();

                                        break;
                                    case 2:



                                        break;
                                }
                            }
                        })
                        .setCancelable(true)//不允许被某些方式取消,比如按对话框之外的区域或者是返回键
                        .show();



            }
        });*/


        holder1.fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //设置contentView
                View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
                popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setContentView(contentView);
                //设置各个控件的点击响应
                TextView tv1 = (TextView) contentView.findViewById(R.id.text1);
                TextView tv2 = (TextView) contentView.findViewById(R.id.text2);
                TextView tv3 = (TextView) contentView.findViewById(R.id.text3);
                TextView tv4 = (TextView) contentView.findViewById(R.id.text4);
                tv1.setText(song_list.get(position).getTitle());
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, song_list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        OkhttpUtils.getInstance().asy(null, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=" + song_list.get(position).getSong_id(), new AbstractUiCallBack<BoFangBean>() {
                            @Override
                            public void success(BoFangBean bean) {
                                String file_link = bean.getBitrate().getFile_link();
                                Intent share_intent = new Intent();
                                share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
                                share_intent.setType("text/plain");//设置分享内容的类型
                                share_intent.putExtra(Intent.EXTRA_SUBJECT, file_link);//添加分享内容标题
                                share_intent.putExtra(Intent.EXTRA_TEXT, file_link);//添加分享内容
                                //创建分享的Dialog
                                share_intent = Intent.createChooser(share_intent, "分享");
                                context.startActivity(share_intent);
                            }
                            @Override
                            public void failure(Exception e) {
                            }
                        });
                        Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                tv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "查看歌手信息", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                tv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        OkhttpUtils.getInstance().asy(null, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=" + song_list.get(position).getSong_id(), new AbstractUiCallBack<BoFangBean>() {
                            @Override
                            public void success(BoFangBean bean) {
                                String file_link = bean.getBitrate().getFile_link();
                                String title = bean.getSonginfo().getTitle();
                                //创建下载任务,downloadUrl就是下载链接
                                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(file_link));
//指定下载路径和下载文件名
                                request.setDestinationInExternalPublicDir("/storage/emulated/0/PonyMusic/Music",title);
//获取下载管理器
                                  DownloadManager downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//将下载任务加入下载队列，否则不会进行下载
                                downloadManager.enqueue(request);
                            }
                            @Override
                            public void failure(Exception e) {
                            }
                        });
                        Toast.makeText(context, "下载", Toast.LENGTH_SHORT).show();

                        popupWindow.dismiss();

                    }
                });
                //显示PopupWindow
                View rootview = LayoutInflater.from(context).inflate(R.layout.activity_lei_biao, null);
                popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
            }
        });


    }

    @Override
    public int getItemCount() {
        return song_list==null?0:song_list.size();
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
    RecyclerAdapter2.OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(RecyclerAdapter2.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
