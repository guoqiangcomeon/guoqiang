package zhangjiye.bawie.com.boni.view.callBack;

import zhangjiye.bawie.com.boni.model.bean.SouSuoBean;

/**
 * Created by 张继业 on 2017/12/28.
 */

public interface MainViewCallBack {
    public void onSuccess(SouSuoBean bean);
    public void onFail(Exception e);
}
