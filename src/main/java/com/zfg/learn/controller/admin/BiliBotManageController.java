package com.zfg.learn.controller.admin;

import com.zfg.learn.common.ServerResponse;
import com.zfg.learn.until.BiliUntil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequestMapping("/admin/bot")
@RestController
public class BiliBotManageController {
    BiliUntil biliUntil = BiliUntil.getUntil();


    @PostMapping("/init")
    public ServerResponse init(@RequestParam(value = "SESSDATA", required = true) String SESSDATA,
                               @RequestParam(value = "bili_jct", required = true) String bili_jct,
                               @RequestParam(value = "buvid3", required = true) String buvid3){
        try {
            biliUntil.init(SESSDATA, bili_jct, buvid3);
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("初始化失败");
        }

        return ServerResponse.createBySuccess();
    }

    @GetMapping("/update")
    public ServerResponse update(@RequestParam("SESSDATA") String SESSDATA,
                               @RequestParam("bili_jct") String bili_jct,
                               @RequestParam("buvid3") String buvid3){
        biliUntil.updateAuth(SESSDATA, bili_jct, buvid3);
        return ServerResponse.createBySuccess();
    }
}
