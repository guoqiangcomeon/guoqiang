package zhangjiye.bawie.com.boni.view.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.SimpleBean;
import zhangjiye.bawie.com.boni.view.cusView.MyAnimatorUpdateListener;



public class AnimationFragment extends Fragment {

    @BindView(R.id.simple)
    SimpleDraweeView simple;
    Unbinder unbinder;
    MyAnimatorUpdateListener listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.animation_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @SuppressLint("WrongConstant")
    @Subscribe(sticky = true)
    public void onEventMainThread(SimpleBean event) {

        String image = event.getImage();
        simple.setImageURI(Uri.parse(image));
        ObjectAnimator anim = ObjectAnimator.ofFloat(simple, "rotation", 0f, 360f);
        // 动画的持续时间，执行多久？
        anim.setDuration(30000);
        anim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        anim.start();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
