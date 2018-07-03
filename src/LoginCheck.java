import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登陆前的验证
 */
@WebServlet("/loginCheck")
public class LoginCheck extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response){
        String signature=request.getParameter("signature");//签名
        String timestamp=request.getParameter("timestamp");//时间戳
        String nonce=request.getParameter("nonce");//随机数
        String echostr=request.getParameter("echostr");//随机字符串
        //进行签名校验
        boolean flag= SignUtil.checkSignature(signature,timestamp,nonce);
        try{
            PrintWriter out=response.getWriter();
            if(flag){
                out.print(echostr);
                System.out.println("配置成功");
            }else{
                System.out.println("配置失败");
            }
        }catch(Exception e){

        }

    }
}
