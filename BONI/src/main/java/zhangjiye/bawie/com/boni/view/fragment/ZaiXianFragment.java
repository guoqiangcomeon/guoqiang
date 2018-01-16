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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhangjiye.bawie.com.boni.R;
import zhangjiye.bawie.com.boni.view.activity.LeiBiaoActivity;
import zhangjiye.bawie.com.boni.view.adapter.RecyclerAdapter1;

public class ZaiXianFragment extends Fragment{
    @BindView(R.id.recycler_zaixian)
    RecyclerView recyclerZaixian;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zaixian_fragment, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerAdapter1 adapter1 = new RecyclerAdapter1(getActivity());
        recyclerZaixian.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerZaixian.setAdapter(adapter1);


        adapter1.setOnItemClickListener(new RecyclerAdapter1.OnItemClickListener() {
            @Override
            public void onClick(int position,int i) {
                Intent intent = new Intent(getActivity(), LeiBiaoActivity.class);
                intent.putExtra("type",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
