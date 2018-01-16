package zhangjiye.bawie.com.boni.presenter;

import zhangjiye.bawie.com.boni.model.bean.SouSuoBean;
import zhangjiye.bawie.com.boni.model.okhttp1.OkHttpUtils;
import zhangjiye.bawie.com.boni.view.callBack.MainViewCallBack;

/**
 * Created by 张继业 on 2017/12/28.
 */

public class MainPresenter extends BasePresenter{

    MainViewCallBack viewCallBack;
    OkHttpUtils okhttpUtil;
    public MainPresenter(MainViewCallBack viewCallBack) {
        this.viewCallBack = viewCallBack;
        this.okhttpUtil = new OkHttpUtils();
    }
    public void getData(String query){
        okhttpUtil.getData(new OkHttpUtils.MainModelCallBack<SouSuoBean>() {
            @Override
            public void onSuccess(SouSuoBean bean) {
                viewCallBack.onSuccess(bean);
            }

            @Override
            public void onFail(Exception e) {

            }
        },SouSuoBean.class,query);
    }
}
