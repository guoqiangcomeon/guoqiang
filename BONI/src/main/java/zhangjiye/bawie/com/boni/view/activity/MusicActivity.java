package zhangjiye.bawie.com.boni.view.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.BoFangBean;
import zhangjiye.bawie.com.boni.model.bean.Song;
import zhangjiye.bawie.com.boni.model.bean.SongIdBean;
import zhangjiye.bawie.com.boni.model.bean.Timebean;
import zhangjiye.bawie.com.boni.model.bean.WuYinYueBean;
import zhangjiye.bawie.com.boni.okhttp.AbstractUiCallBack;
import zhangjiye.bawie.com.boni.okhttp.OkhttpUtils;
import zhangjiye.bawie.com.boni.view.fragment.AnimationFragment;
import zhangjiye.bawie.com.boni.view.fragment.LyricFragment;
import zhangjiye.bawie.com.boni.view.service.MyService;

public class MusicActivity extends AppCompatActivity {

    @BindView(R.id.xiajiantou)
    ImageView xiajiantou;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.stop)
    TextView stop;
    @BindView(R.id.qiehuan)
    ImageView qiehuan;
    @BindView(R.id.shangyishou)
    ImageView shangyishou;
    @BindView(R.id.zanting)
    ImageView zanting;
    @BindView(R.id.xiayishou)
    ImageView xiayishou;
    @BindView(R.id.activity_music)
    LinearLayout activityMusic;
    private boolean flag;

    public Intent service;
    public ServiceConnection connection;
    public MyService.MyBinder binder;
    private String showLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", 3);
         Log.i("-----",flag+"flag");
        if (flag == 1){
            String title = intent.getStringExtra("title");
            String name = intent.getStringExtra("name");
            String musicUrl = intent.getStringExtra("musicUrl");
//            int songid1 = intent.getIntExtra("songid",0);
            Bundle bundle = intent.getExtras();
            int songid1 = bundle.getInt("songid");

            EventBus.getDefault().postSticky(new WuYinYueBean(title,musicUrl,name));

            text1.setText(title);
            text2.setText(name);

            //http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=877578
            OkhttpUtils.getInstance().asy(null, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=" + songid1, new AbstractUiCallBack<BoFangBean>() {
                @Override
                public void success(BoFangBean bean) {
                    zanting.setImageResource(R.drawable.zanting);
                    BoFangBean.BitrateBean bitrate = bean.getBitrate();
                    showLink = bitrate.getShow_link();

                    service = new Intent(MusicActivity.this, MyService.class);
                    //连接服务的对象
                    connection = new ServiceConnection() {
                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            // activity与服务断开连接时调用的方法
                        }
                        //activity与服务连接上调用的方法
                        //IBinder service中间人对象....MyService中的onBind方法返回的对象
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            // 链接后返回的中间人对象
                            binder = (MyService.MyBinder) service;
                            Log.i("------showlink---------",showLink);
                            binder.playInBinder(showLink);
				/*//调用到了service里面的方法
                binder.jieQianBinder();*/
                        }
                    };
                    //在绑定服务之前,,,调用startService来启动一下服务,,,,使用的是这种混合启动服务的方式...因为只绑定在退出的时候同生共死,服务销毁,,,而starService方式只要不调用stopService方法,服务不销毁
                    startService(service);
                    MusicActivity.this.bindService(service, connection, BIND_AUTO_CREATE);

                }
                @Override
                public void failure(Exception e) {
                }
            });
        }else if (flag == 2){

            Song model = (Song) intent.getSerializableExtra("model");
            System.out.println(model.getPath()+"+++");
            final String path = model.getPath();
            text1.setText(model.getSong());
            text2.setText(model.getSinger());
            EventBus.getDefault().postSticky(new WuYinYueBean(model.getSong(),null,null));
            service = new Intent(MusicActivity.this, MyService.class);
            //连接服务的对象
            connection = new ServiceConnection() {

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    // activity与服务断开连接时调用的方法

                }

                //activity与服务连接上调用的方法
                //IBinder service中间人对象....MyService中的onBind方法返回的对象
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    // 链接后返回的中间人对象
                    binder = (MyService.MyBinder) service;
                    binder.playInBinder(path);

				/*//调用到了service里面的方法
				binder.jieQianBinder();*/
                }
            };

            //在绑定服务之前,,,调用startService来启动一下服务,,,,使用的是这种混合启动服务的方式...因为只绑定在退出的时候同生共死,服务销毁,,,而starService方式只要不调用stopService方法,服务不销毁
            startService(service);

            MusicActivity.this.bindService(service, connection, BIND_AUTO_CREATE);

        } else if (flag == 0){
            Intent intent1 = getIntent();
            text1.setText(intent1.getStringExtra("name1"));
            text2.setText(intent1.getStringExtra("musicName1"));
            service = new Intent(MusicActivity.this, MyService.class);
            //连接服务的对象
            connection = new ServiceConnection() {
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    // activity与服务断开连接时调用的方法
                }
                //activity与服务连接上调用的方法
                //IBinder service中间人对象....MyService中的onBind方法返回的对象
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    // 链接后返回的中间人对象
                    binder = (MyService.MyBinder) service;
				/*//调用到了service里面的方法
                binder.jieQianBinder();*/
                }
            };
            //在绑定服务之前,,,调用startService来启动一下服务,,,,使用的是这种混合启动服务的方式...因为只绑定在退出的时候同生共死,服务销毁,,,而starService方式只要不调用stopService方法,服务不销毁
            startService(service);
            MusicActivity.this.bindService(service, connection, BIND_AUTO_CREATE);
        }

        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new AnimationFragment());
        list.add(new LyricFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
            @Override
            public int getCount() {
                return list.size();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(seekBar.getProgress());
                Message message=new Message();
                message.obj=seekBar.getProgress();
                message.what=1;
                MyService.handler.sendMessage(message);
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

    @OnClick({R.id.xiajiantou, R.id.qiehuan, R.id.shangyishou, R.id.zanting, R.id.xiayishou})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiajiantou:
                finish();
                break;
            case R.id.qiehuan:
                break;
            case R.id.shangyishou:
                break;
            case R.id.zanting:
                if(flag){
                    zanting.setImageResource(R.drawable.zanting);
                    binder.playInBinder(showLink);
                }else{
                    zanting.setImageResource(R.drawable.bofang);
                    if (showLink != null){
                        binder.stopInBinder();
                    }
                }
                flag = !flag;
                break;
            case R.id.xiayishou:
                break;
            //http://img5.imgtn.bdimg.com/it/u=1344893835,2213038112&fm=27&gp=0.jpg
        }
    }
    @Subscribe(sticky = false)
    public void GetEvent(final Timebean timebean) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                start.setText(dateFormat.format(new Date(timebean.currentPosition)));
                stop.setText(dateFormat.format(new Date(timebean.duration)));
                seekBar.setProgress(timebean.currentPosition * 100 / timebean.duration);
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (connection != null) {
            unbindService(connection);
            connection = null;
        }
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
