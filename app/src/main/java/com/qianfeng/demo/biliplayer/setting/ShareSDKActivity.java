//package com.qianfeng.demo.biliplayer.setting;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//
//import com.qianfeng.demo.biliplayer.R;
//import com.umeng.socialize.controller.UMServiceFactory;
//import com.umeng.socialize.controller.UMSocialService;
//
//public class ShareSDKActivity extends AppCompatActivity {
//    UMSocialService mController ;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_share_sdk2);
//        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
//        // 设置分享内容
//        mController
//                .setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
//        // 设置分享图片, 参数2为图片的url地址
////		mController.setShareMedia(new UMImage(getActivity(),
////				"http://www.umeng.com/images/pic/banner_module_social.png"));
//        // 设置分享图片，参数2为本地图片的资源引用
//        // mController.setShareMedia(new UMImage(getActivity(),
//        // R.drawable.icon));
//        // 设置分享图片，参数2为本地图片的路径(绝对路径)
//        // mController.setShareMedia(new UMImage(getActivity(),
//        // BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));
//
//        // 设置分享音乐
//        // UMusic uMusic = new
//        // UMusic("http://sns.whalecloud.com/test_music.mp3");
//        // uMusic.setAuthor("GuGu");
//        // uMusic.setTitle("天籁之音");
//        // 设置音乐缩略图
//        // uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
//        // mController.setShareMedia(uMusic);
//
//        // 设置分享视频
//        // UMVideo umVideo = new UMVideo(
//        // "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
//        // 设置视频缩略图
//        // umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
//        // umVideo.setTitle("友盟社会化分享!");
//        // mController.setShareMedia(umVideo);
//    }
//
//    public void clickall(View v) {
//        mController.openShare(ShareSDKActivity.this, false);
//
//    }
//}
