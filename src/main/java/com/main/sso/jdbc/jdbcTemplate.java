package com.main.sso.jdbc;

import java.sql.*;

public abstract class jdbcTemplate {
    /**
     * 获取数据库连接
     * @param url
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection getJDBCconnect(String url,String username,String password) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    /**
     * 根据connection 获取 statement
     * @param conn
     * @return
     * @throws SQLException
     */
    private Statement getStatement(Connection conn) throws SQLException {
        Statement st;
        //获取statement
        st = conn.createStatement();
        return st;
    }

    /**
     * 关闭连接和会话
     * @param st
     * @param conn
     */
    private void close(Statement st, Connection conn){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 抽象方法，子类自行定制，处理ResultSet
     * @param set
     * @return
     */
    protected Object runSql(ResultSet set){
        return null;
    };

    /**
     * 主方法，执行sql
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return
     */
    public Object run(String url,String username,String password,String sql,String model) {
        Object re = null;
        try {
            Connection conn = getJDBCconnect(url, username, password);
            Statement st = getStatement(conn);
            //执行sql
            if(model.equals("select")) {
                ResultSet set = st.executeQuery(sql);
                re = runSql(set);
            }else if(model.equals("update")||(model.equals("insert"))||(model.equals("delete"))){
                st.executeUpdate(sql);
            }else {
                st.execute(sql);
            }
            close(st,conn);

        }catch (Exception e){
            e.printStackTrace();
        }
        return re;
    }
}
