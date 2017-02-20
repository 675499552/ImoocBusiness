package com.android.jyc.imageloader;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Admin on 2017/2/20.
 */

public class ImageLoaderTest {

    private void testApi(){

        /**
         *  为ImageLoader配置参数
         */
//        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
//                .Builder(context).build();

        /**
         * 先获取到ImaageLoader的一个实例
         */
        ImageLoader imageLoader = ImageLoader.getInstance();

        /**
         * 使用displayImage去加载图片
         */
//        imageLoader.displayImage("url",imageView);


//        ImageLoaderManager.getInstance(context).displayImage();
    }
}
