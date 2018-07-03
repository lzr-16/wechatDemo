import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * this class by created wuyongfei on 2018/6/5 13:50
 * 引导用户进入授权页面
 **/
@WebServlet("/enter")
public class EnterWechat extends HttpServlet {
    public static String APPID = "wxf86a11b4021f9c9f"; // 账号
    public static String APPSECRET = "2f16c09f872e30d523b53728f498e0e6"; // 密码
    private static String redirectUrl = "http://5309a571.nat123.cc/showUserInfo"; // 回调地址

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID +
                "&redirect_uri=" + redirectUrl +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        resp.sendRedirect(url);
    }
}
