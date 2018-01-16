package zhangjiye.bawie.com.boni.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.MusicBean;
import zhangjiye.bawie.com.boni.model.bean.SimpleBean;
import zhangjiye.bawie.com.boni.model.bean.SongIdBean;
import zhangjiye.bawie.com.boni.okhttp.AbstractUiCallBack;
import zhangjiye.bawie.com.boni.okhttp.OkhttpUtils;
import zhangjiye.bawie.com.boni.view.adapter.RecyclerAdapter2;

public class LeiBiaoActivity extends AppCompatActivity {

    @BindView(R.id.tubiao)
    ImageView tubiao;
    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.beijing)
    ImageView beijing;
    private String baseUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?fromat=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=";
    private String url = "&size=10&offset=0";
    private int type;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.recycler_leibiao)
    RecyclerView recyclerLeibiao;
    @BindView(R.id.activity_lei_biao)
    LinearLayout activityLeiBiao;
    private RecyclerAdapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lei_biao);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        int position = intent.getIntExtra("type", 1);

        if (position == 1 || position == 2) {
            type = position;

        } else if (position == 4 || position == 5) {
            type = position + 7;


        } else if (position == 6) {
            type = position + 10;

        } else if (position == 7 || position == 8) {
            type = position + 14;

        } else if (position == 10 || position == 11 || position == 12) {
            type = position + 13;

        }
        OkhttpUtils.getInstance().asy(null, baseUrl + type + url, new AbstractUiCallBack<MusicBean>() {

            @Override
            public void success(final MusicBean bean) {
                final List<MusicBean.SongListBean> song_list = bean.getSong_list();
                String s = bean.getBillboard().getPic_s260();
                adapter2 = new RecyclerAdapter2(LeiBiaoActivity.this, song_list);
                recyclerLeibiao.setLayoutManager(new LinearLayoutManager(LeiBiaoActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerLeibiao.setAdapter(adapter2);
                ImageLoader.getInstance().displayImage(bean.getBillboard().getPic_s260(), tubiao);
                text1.setText(bean.getBillboard().getName());
                text2.setText("最近更新：" + bean.getBillboard().getUpdate_date());
                text3.setText(bean.getBillboard().getComment());
                ImageLoader.getInstance().displayImage(bean.getBillboard().getPic_s260(),beijing);
                adapter2.setOnItemClickListener(new RecyclerAdapter2.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent1 = new Intent(LeiBiaoActivity.this,MusicActivity.class);
                        intent1.putExtra("title",song_list.get(position).getTitle());
                        intent1.putExtra("name",song_list.get(position).getAuthor());
                        intent1.putExtra("songid",song_list.get(position).getSong_id());
                        intent1.putExtra("musicUrl",song_list.get(position).getPic_radio());
                        Bundle bundle = new Bundle();
                        bundle.putInt("songid", Integer.parseInt(song_list.get(position).getSong_id()));
                        EventBus.getDefault().postSticky(new SongIdBean(Integer.parseInt(song_list.get(position).getSong_id())));
                        intent1.putExtras(bundle);
                        intent1.putExtra("flag",1);
                        EventBus.getDefault().postSticky(new SimpleBean(song_list.get(position).getPic_radio()));
                        startActivity(intent1);
                    }
                });
            }

            @Override
            public void failure(Exception e) {

            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @OnClick(R.id.image)
    public void onClick() {
        finish();
    }
}
