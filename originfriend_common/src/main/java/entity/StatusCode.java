package entity;

/**
 * @author lxy
 * @date 2019/9/5 - 15:48
 * 保存状态码常量的类
 */
public class StatusCode {
    public static final int OK=20000; //成功
    public static final int ERROR =20001; //失败
    public static final int LOGINERROR =20002; //用户名或密码错误
    public static final int ACCESSERROR =20003; //权限不足
    public static final int REMOTEERROR =20004; //远程调用失败
    public static final int REPERROR =20005; //重复操作
}
