package cn.zdxh.lcy.demo03.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MylListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized 初始化，web应用启动了...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed 初始化，web应用销毁了...");
    }
}
