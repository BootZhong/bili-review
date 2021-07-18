package com.zfg.learn.until;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * bili until 目前仅支持单例
 * 内部用season 外部用media
 * @author bootzhong
 */
public class BiliUntil {
    private String SESSDATA;
    private String bili_jct;
    private String buvid3;
    private boolean isInit;

    private static BiliUntil biliUntil;
    CatchApi catchApi = new CatchApi();

    private BiliUntil(){}

    public static BiliUntil getUntil(){
        synchronized (BiliUntil.class){
            if (biliUntil == null){
                biliUntil = new BiliUntil();
            }
        }

        return biliUntil;
    }

    //初始化
    public void init(String SESSDATA, String bili_jct, String buvid3) throws IOException {
        if (isInit){
            return;
        }

        this.SESSDATA = SESSDATA;
        this.bili_jct = bili_jct;
        this.buvid3 = buvid3;
        followUp(35274219l);
        this.isInit = true;
    }

    public boolean isInit(){
        return isInit;
    }

    //更新
    public void updateAuth(String SESSDATA, String bili_jct, String buvid3){
        this.SESSDATA = SESSDATA;
        this.bili_jct = bili_jct;
        this.buvid3 = buvid3;
    }

    /**
     * 订阅博主
     */
    public void followUp(Long mid) throws IOException {
        relationModify(mid, 1);
    }

    /**
     * 取订阅博主
     */
    public void unfollowUp(Long mid) throws IOException {
        relationModify(mid, 2);
    }

    private void relationModify(Long fid, Integer act) throws IOException {
        String url = Api.RELATION_MODIFY;

        String data = "csrf="+bili_jct;
        data += "&fid=" + fid + "&act="+act;
        data += "&re_src=11&jsonp=jsonp";

        HashMap map = new HashMap();
        map.put("cookie", getAuthCookie());

        catchApi.request(url, map, data, "POST");
    }


    /**
     * 订阅/取订 番剧
     */
    public void followMd(Long season_id) throws IOException {
        modifyMdFollow(season_id, 1);
    }

    /**
     * 取订阅番剧
     */
    public void unFollowMd(Long season_id) throws IOException {
       modifyMdFollow(season_id, 0);
    }

    /**
     * 获取自己的动态列表
     */
    public String getDynamicList() throws IOException {
        HashMap map = new HashMap();
        map.put("cookie", getAuthCookie());
        return catchApi.request(Api.DYNAMIC_URL, map, "GET");
    }

    private void modifyMdFollow(Long season_id, Integer type) throws IOException {
        String url = "";
        if (type == 1){
            url = Api.FOLLOW_MEDIA;
        } else if (type == 0){
            url = Api.UNFOLLOW_MEDIA;
        } else {
            return;
        }

        HashMap header = new HashMap();
        header.put("cookie", getAuthCookie());
        header.put("referer", "https://www.bilibili.com/");
        String data = "csrf="+bili_jct+"&season_id="+season_id;

        catchApi.request(url, header, data, "POST");
    }

    private String getAuthCookie(){
        String cookie = "SESSDATA="+SESSDATA+";";
        cookie += "bili_jct="+bili_jct+";";
        cookie += "buvid3"+buvid3+";";
        return cookie;
    }

    /**
     * API
     */
    public interface Api{
        String RELATION_MODIFY = "https://api.bilibili.com/x/relation/modify";
        String FOLLOW_MEDIA = "https://api.bilibili.com/pgc/web/follow/add";
        String UNFOLLOW_MEDIA = "https://api.bilibili.com/pgc/web/follow/del";
        String DYNAMIC_URL = "https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/dynamic_new?uid=20736117&type_list=268435455&from=weball&platform=web";

    }
}
