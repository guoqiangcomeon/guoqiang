package zhangjiye.bawie.com.boni.model.okhttp1;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 张继业 on 2017/12/28.
 */

public class OkHttpUtils<M> {

    private String baseUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query=";
    private MainModelCallBack callBack;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            M M = (M) msg.obj;
            callBack.onSuccess(M);
        }
    };
    public void getData(final MainModelCallBack callBack, final Class<M> mClass,String query){
        this.callBack = callBack;
        final Request request = new Request.Builder()
                .url(baseUrl+query)
                .build();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpInterceptor())
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                String string = response.body().string();
                Gson gson = new Gson();
                M m = gson.fromJson(string, mClass);
                Message message = handler.obtainMessage();
                message.obj = m;
                handler.sendMessage(message);

            }
        });

    }


    public interface MainModelCallBack<M>{
        public void onSuccess(M bean);
        public void onFail(Exception e);
    }
}
