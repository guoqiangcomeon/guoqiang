package zhangjiye.bawie.com.boni.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by muhanxi on 17/11/22.
 */

/**
 * 添加请求头
 */
public class UserAgentIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request =  chain.request().newBuilder()
                .addHeader("key1","value")
                .build();

        return chain.proceed(request);
    }
}
