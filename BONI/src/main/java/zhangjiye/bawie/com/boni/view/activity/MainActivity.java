package zhangjiye.bawie.com.boni.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.WuYinYueBean;
import zhangjiye.bawie.com.boni.view.adapter.RecyclerImageAdapter;
import zhangjiye.bawie.com.boni.view.adapter.RecyclerTextAdapter;
import zhangjiye.bawie.com.boni.view.fragment.BenDiFragment;
import zhangjiye.bawie.com.boni.view.fragment.ZaiXianFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.imagechouti)
    ImageView imagechouti;
    @BindView(R.id.radioButton01)
    RadioButton radioButton01;
    @BindView(R.id.radioButton02)
    RadioButton radioButton02;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.sousuo)
    ImageView sousuo;
    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.frame_layout)
    LinearLayout frameLayout;
    @BindView(R.id.image)
    TextView image;
    @BindView(R.id.list_view1)
    RecyclerView listView1;
    @BindView(R.id.list_view2)
    RecyclerView listView2;
    @BindView(R.id.relative_layout)
    LinearLayout relativeLayout;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;
    @BindView(R.id.gPimage)
    ImageView gPimage;
    @BindView(R.id.musicName)
    TextView musicName;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.imageZhan)
    ImageView imageZhan;
    @BindView(R.id.musicNext)
    ImageView musicNext;
    @BindView(R.id.music_shouye)
    LinearLayout musicShouye;
    private boolean flag;
    private String name1;
    private String musicName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        ArrayList<Integer> listImage = new ArrayList<>();
        listImage.add(R.drawable.shezhi);
        listImage.add(R.drawable.riye);
        listImage.add(R.drawable.dinshi);
        listImage.add(R.drawable.tuichu);
        listImage.add(R.drawable.guanyu);

        listView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerImageAdapter imageAdapter = new RecyclerImageAdapter(this, listImage);
        listView1.setAdapter(imageAdapter);

        ArrayList<String> list = new ArrayList<>();
        list.add("功能设置");
        list.add("夜间模式");
        list.add("定时停止播放");
        list.add("退出应用");
        list.add("关于波尼音乐");

        listView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final RecyclerTextAdapter textAdapter = new RecyclerTextAdapter(this, list);

        musicShouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MusicActivity.class);
                intent.putExtra("name",name1);
                intent.putExtra("flag",0);
                intent.putExtra("musicName1",musicName1);
                startActivity(intent);
            }
        });

        textAdapter.setOnItemClickListener(new RecyclerTextAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (position == 1) {
                    int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    if (mode == Configuration.UI_MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        // blah blah
                    }
                    recreate();
                }
                drawerMain.closeDrawer(relativeLayout);

                 //item等于3的时候 退出
                if (position==3)
                {
                    System.exit(0);

                }

            }
        });
        listView2.setAdapter(textAdapter);
        imagechouti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerMain.openDrawer(relativeLayout);
            }
        });

        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SouSuoActivity.class);
                startActivity(intent);
            }
        });

        initView();
    }

    @Subscribe(sticky = false)
    public void GetEvent(final WuYinYueBean bean) {
        musicName.setText(bean.getMusicName());
        name1 = bean.getName();
        musicName1 = bean.getMusicName();
        ImageLoader.getInstance().displayImage(bean.getMusicUrl(),gPimage);
    }

    private void initView() {

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BenDiFragment());
        fragments.add(new ZaiXianFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        // 添加页面切换事件的监听器
        viewPager.setOnPageChangeListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton01:
                        viewPager.setCurrentItem(0, true);
                        break;
                    case R.id.radioButton02:
                        viewPager.setCurrentItem(1, true);
                        break;
                    default:
                        break;
                }
            }
        });

        imageZhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    imageZhan.setImageResource(R.drawable.ic_play_bar_btn_play);
                } else {
                    imageZhan.setImageResource(R.drawable.ic_play_bar_btn_pause);
                }
                flag = !flag;
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.radioButton01);
                break;
            case 1:
                radioGroup.check(R.id.radioButton02);
                break;
            default:

                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
}
