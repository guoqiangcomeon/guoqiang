package zhangjiye.bawie.com.boni.view.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.GeChiBean;
import zhangjiye.bawie.com.boni.model.bean.SongIdBean;
import zhangjiye.bawie.com.boni.okhttp.AbstractUiCallBack;
import zhangjiye.bawie.com.boni.okhttp.OkhttpUtils;
import zhangjiye.bawie.com.boni.view.cusView.LrcView;
import zhangjiye.bawie.com.boni.view.service.MyService;



public class LyricFragment extends Fragment {

    @Nullable
    @BindView(R.id.lrcView)
    LrcView lrcView;
    Unbinder unbinder;
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                long time = (long) msg.obj;
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lyric_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Subscribe(sticky = true)
    public void GetEvent(final SongIdBean bean) {
        if (bean != null) {
            int songid = bean.getSongid();
            OkhttpUtils.getInstance().asy(null, "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.lry&songid=" + songid, new AbstractUiCallBack<GeChiBean>() {
                @Override
                public void success(GeChiBean bean) {
                    String lrcContent = bean.getLrcContent();
                    lrcView.setLrc(lrcContent);
                    lrcView.setPlayer(MyService.mediaPlayer);
                    lrcView.init();

                }

                @Override
                public void failure(Exception e) {

                }
            });
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
