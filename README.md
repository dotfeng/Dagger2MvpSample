
dagger2 mvp retrofit2 rxjava2 butterknife arouter parceler icepick

    //findviewbyid
    compile 'com.jakewharton:butterknife:8.6.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'
    //依赖注入
    compile 'com.google.dagger:dagger:2.7'
    annotationProcessor 'com.google.dagger:dagger-compiler:2.7'
    //webservice
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
    compile 'com.squareup.okhttp3:okhttp:3.8.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.8.0'
    //rxjava2
    // Because RxAndroid releases are few and far between, it is recommended you also
    // explicitly depend on RxJava's latest version for bug fixes and new features.
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'io.reactivex.rxjava2:rxjava:2.1.0'
    //log
    compile 'com.jakewharton.timber:timber:4.5.1'
    //parcelable
    compile 'org.parceler:parceler-api:1.1.8'
    annotationProcessor 'org.parceler:parceler:1.1.8'
    //路由，页面跳转
    compile 'com.alibaba:arouter-api:1.2.1.1'
    annotationProcessor 'com.alibaba:arouter-compiler:1.1.2.1'
    //greendao,sqlcipher, wcdb


    //显示图片
    compile 'com.github.bumptech.glide:glide:4.0.0-RC0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.0.0-RC0'

    compile 'frankiesardo:icepick:3.2.0'
    annotationProcessor 'frankiesardo:icepick-processor:3.2.0'

