package zhangjiye.bawie.com.boni.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.SouSuoBean;
import zhangjiye.bawie.com.boni.presenter.MainPresenter;
import zhangjiye.bawie.com.boni.view.adapter.SouSuoRecyclerAdapter;
import zhangjiye.bawie.com.boni.view.callBack.MainViewCallBack;
import zhangjiye.bawie.com.boni.view.cusView.CustomView;

public class SouSuoActivity extends AppCompatActivity implements MainViewCallBack {


    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sousuo)
    ImageView sousuo;
    @BindView(R.id.recycler_sousuo)
    RecyclerView recyclerSousuo;
    @BindView(R.id.activity_sou_suo)
    LinearLayout activitySouSuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        ButterKnife.bind(this);

        final MainPresenter presenter = new MainPresenter(this);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getData(name.getText().toString());
            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

    @Override
    public void onSuccess(SouSuoBean bean) {

        recyclerSousuo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<SouSuoBean.SongBean> song = bean.getSong();
        System.out.print("11111111111");
        if(song != null){
            SouSuoRecyclerAdapter adapter = new SouSuoRecyclerAdapter(this,song);
            recyclerSousuo.addItemDecoration(new CustomView());
            recyclerSousuo.setAdapter(adapter);
        }
    }

    @Override
    public void onFail(Exception e) {

    }
}
