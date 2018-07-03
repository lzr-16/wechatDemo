import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * this class by created wuyongfei on 2018/6/5 13:50
 * 发送信息到url中并返回数据
 **/
public class GetInfoFromUrl {

    /**
     * 向指定的url提交请求，并获取返回的信息
     *
     * @param url
     * @return
     */
    public static JSONObject getInfoFromUrl(String url) {
        JSONObject jsonObject = null;
        // 发送url的对象
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        // get提交对象
        HttpGet httpGet = new HttpGet(url);
        try {
            // 发送请求
            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
            // 获取响应的信息
            HttpEntity httpEntity = httpResponse.getEntity();
            // 转换为json格式
            String json  = EntityUtils.toString(httpEntity);
            // 将string类型的json字符串进行转码
            String jsonStr = new String (json.getBytes("ISO-8859-1"),"utf-8");
            // 将json字符串转换为json对象
            jsonObject = JSON.parseObject(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
