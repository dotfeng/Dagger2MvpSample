package net.fengg.dagger2mvpsample.ui.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

import timber.log.Timber;

@Interceptor(priority = 8, name = "登录拦截器")
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if(false){
            callback.onInterrupt(null);
            ARouter.getInstance().build(Path.LOGIN_ACTIVITY).greenChannel().navigation();
        } else{
            Timber.d("login");
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
