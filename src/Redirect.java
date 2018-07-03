import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this class by created wuyongfei on 2018/6/5 13:50
 * 用户授权后的回调控制
 **/
@WebServlet("/showUserInfo")
public class Redirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取到code的值
        String code = request.getParameter("code");
        String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + EnterWechat.APPID +
                "&secret=" + EnterWechat.APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        // 异步发送请求（用过code获取access_token和openId）
        JSONObject configInfo = GetInfoFromUrl.getInfoFromUrl(access_token_url);
        String access_token = configInfo.getString("access_token");
        String openId = configInfo.getString("openid");
        // 获取用户信息
        String userInfo_url = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=" + access_token +
                "&openid=" + openId +
                "&lang=zh_CN";
        // 异步发送请求（通过access_token和openId获取用户的信息）
        JSONObject userInfo = GetInfoFromUrl.getInfoFromUrl(userInfo_url);
        PrintWriter out = response.getWriter();
        out.print(userInfo);
    }
}
