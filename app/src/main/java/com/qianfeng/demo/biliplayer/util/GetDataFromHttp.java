package com.qianfeng.demo.biliplayer.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qianfeng.demo.biliplayer.Bean.WanJieDatas;
import com.qianfeng.demo.biliplayer.adapter.RecyclerViewAdapter_WanJie;

import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class GetDataFromHttp {

    //private List<WanJieDatas.ListEntity> list;

    /**
     * 网络获取数据
     */
    public  void getDataFromHttp(){
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, URLUtil.URL_WAN_JIE, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                  parserForGson(result);
                Log.i("result", result.toString());
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();

            }
        });
    }

    private void  parserForGson(String result){
        WanJieDatas datas = new Gson().fromJson(result, WanJieDatas.class);
        List<WanJieDatas.ListEntity> list = datas.getList();
        for (WanJieDatas.ListEntity data: list){
            data.getTitle();
            data.getPic();
        }

    }
}
