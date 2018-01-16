package zhangjiye.bawie.com.boni.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.model.bean.Model;
import zhangjiye.bawie.com.boni.model.bean.Song;
import zhangjiye.bawie.com.boni.model.bean.Titlebean;
import zhangjiye.bawie.com.boni.utils.MusicUtils;
import zhangjiye.bawie.com.boni.view.activity.MusicActivity;
import zhangjiye.bawie.com.boni.view.adapter.RecyclerAdapterBenDi;



public class BenDiFragment extends Fragment {
    @BindView(R.id.recycler_bendi)
    RecyclerView recyclerBendi;
    Unbinder unbinder;

    RecyclerAdapterBenDi adapter;
    ArrayList<Song> list;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dengdi_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        //把扫描到的音乐赋值给list

        list = (ArrayList<Song>) MusicUtils.getMusicData(getActivity());
        adapter = new RecyclerAdapterBenDi(list, getActivity());
        recyclerBendi.setAdapter(adapter);
        ArrayList<Model> list1 = new ArrayList<>();

        adapter.setOnItemClickListener(new RecyclerAdapterBenDi.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                EventBus.getDefault().postSticky(new Titlebean(list.get(position).getSong(), list.get(position).getSinger(), list.get(position).getPath()));
                Intent intent = new Intent(getActivity(), MusicActivity.class);
                intent.putExtra("flag", 2);
                intent.putExtra("model", list.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerBendi.setLayoutManager(new LinearLayoutManager(getActivity()));//九宫格布局

    }
      @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
