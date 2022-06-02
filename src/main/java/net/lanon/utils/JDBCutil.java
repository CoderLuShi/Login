package net.lanon.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 殷若淮
 * @version 1.0.0
 * @ClassName JDBCutil.java
 * @Description TODO
 * @createTime 2021年09月26日 20:31:00
 */
public class JDBCutil {
    private static DataSource ds;
    //加载配置文件
    static{
        //使用classLoader加载配置文件
        try {
            Properties pro=new Properties();
            pro.load(JDBCutil.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取DataScorce
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @title getConnection
     * @description 获取连接
     * @author 殷若淮
     * @return: java.sql.Connection
     * @updateTime 2021/09/26 20:42
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    /**
     * @title getClose
     * @description 释放资源
     * @author 殷若淮
     * @param: stmt
     * @param: conn
     * @updateTime 2021/09/26 20:45
     */
    public static void getClose(Statement stmt, Connection conn) throws SQLException {
        if (stmt==null){
            stmt.close();//释放资源
        }
        if (conn==null){
            conn.close();//归还连接
        }
    }
    public static void getClose(ResultSet res,Connection conn) throws SQLException {
        if (res==null){
            res.close();//释放资源
        }
        if (conn==null){
            conn.close();//归还连接
        }
    }
    /**
     * @title getDataSource
     * @description 获取连接池
     * @author 殷若淮
     * @return: javax.sql.DataSource
     * @updateTime 2021/09/26 20:49
     */
    public static DataSource getDataSource(){
        return ds;
    }

}
