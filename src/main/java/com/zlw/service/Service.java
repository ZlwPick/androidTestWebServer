package com.zlw.service;

import com.zlw.db.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zlw
 * @create 2020-09-01 8:38
 */
public class Service {

    public Boolean login(String username, String password) {
        // 获取Sql查询语句
        String logSql = "select * from user where uname ='" + username
                + "' and password ='" + password + "'";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(logSql);
            if (rs.next()) {
                sql.closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }

    public Boolean register(String username, String password) {
        // 获取Sql查询语句
        String regSql = "insert into user values(null,'" + username + "','" + password + "') ";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        return false;
    }

    public Boolean queryOne(String username) {
        String queryOnesql = "select * from user where uname ='" + username
                + "'";
        // 获取DB对象
        DBManager sql = DBManager.createInstance();
        sql.connectDB();
        // 操作DB对象
        try {
            ResultSet rs = sql.executeQuery(queryOnesql);
            if (rs.next()) {
                sql.closeDB();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.closeDB();
        return false;
    }


}
