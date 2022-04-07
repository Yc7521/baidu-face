package cn.edu.njuit.web.server.service;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

//百度ai服务
public class BaiduAiService {
    //设置APPID/AK/SK
    private static final String APP_ID = "25865445";
    private static final String API_KEY = "onP606TpQxrwn0xHyiMeGbdv";
    private static final String SECRET_KEY = "1o7WviAiyziITBmvQxOkmnOlRjVDaCAk";

    public JSONObject checkFaces(String imageBase64) {
        //实例化百度ai的SDK
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        //传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<>();
        options.put(
          "face_field",
          "age,beauty,expression,face_shape,gender,glasses,quality,eye_status,emotion,face_type"
        );
        options.put("max_fave_num", "10");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "NONE");
        String imageType = "BASE64";
        //人脸检测
        return client.detect(imageBase64, imageType, options);
    }
}