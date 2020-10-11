package com.main.sso.jdbc;


import com.main.sso.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class userMapper {
    //连接数据库的信息
    String url="jdbc:mysql://localhost:3306/ssousers?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    String SQLusername="root";
    String SQLpassword="liuwenze0501";

    /**
     * 插入user
     * @param user
     */
    public void insert(User user){
        String sql = "insert into ssoUsers(username,password,code) values ('" +
                user.getUsername()+"','"+
                user.getPassword()+"','"+
                user.getCode() + "')";
        jdbcTemplate jdbc=new jdbcTemplate(){};
        jdbc.run(url,SQLusername,SQLpassword,sql,"insert");
    }


    /**
     * 查找
     * @param username
     * @return
     */
    public User select(String username){
        String sql = "select * from ssoUsers where username='"+username+"'";
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                User user = new User();
                boolean isNull = true;
                try {
                    while (set.next()) {
                        isNull = false;
                        user.setCode(set.getString("code"));
                        user.setPassword(set.getString("password"));
                        user.setUsername(set.getString("username"));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return user;
                }
            }
        };
        Object user = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(user==null){
            return null;
        }else {
            return (User) user;
        }
    }

    /**
     * 根据主键删除
     * @param username
     */
    public void delete(String username){
        String sql = "delete from ssoUsers where username='"+username+"'";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"delete");
    }

    /**
     * 更新
     * @param user
     */
    public void update(User user){
        String sql = "update ssoUsers set " +
                "username='"+user.getUsername()+"',"+
                "password='"+user.getPassword()+"',"+
                "code='"+user.getCode()+"' "+
                "where username='"+user.getUsername()+"'";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"update");
    }

    /**
     * 查找，根据code
     * @param code
     * @return
     */
    public User selectByCode(String code){
        String sql = "select * from ssoUsers where code='"+code+"'";
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                User user = new User();
                boolean isNull = true;
                try {
                    while (set.next()) {
                        isNull = false;
                        user.setCode(set.getString("code"));
                        user.setPassword(set.getString("password"));
                        user.setUsername(set.getString("username"));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return user;
                }
            }
        };
        Object user = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(user==null){
            return null;
        }else {
            return (User) user;
        }
    }
}
