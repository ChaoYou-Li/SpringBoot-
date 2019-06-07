package cn.zdxh.lcy.demo03.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Field->not found [user] param!");//调用父类的构造方法
    }
}
