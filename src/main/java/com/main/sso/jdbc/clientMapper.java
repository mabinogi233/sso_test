
package com.main.sso.jdbc;

import com.main.sso.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;


public class clientMapper {
    //连接数据库的信息
    String url="jdbc:mysql://localhost:3306/ssousers?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    String SQLusername="root";
    String SQLpassword="liuwenze0501";

    /**
     * 插入user
     * @param client
     */
    public void insert(Client client){
        String sql = "insert into client(client_id,client_name) values ('" +
                client.getClientID()+"','"+
                client.getClientName()+"')";
        jdbcTemplate jdbc=new jdbcTemplate(){};
        jdbc.run(url,SQLusername,SQLpassword,sql,"insert");
    }


    /**
     * 查找
     * @param clientID
     * @return
     */
    public Client select(String clientID){
        String sql = "select * from client where client_id='"+clientID+"'";
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                Client client = new Client();
                boolean isNull = true;
                try {
                    while (set.next()) {
                        isNull = false;
                        client.setClientID(set.getString("client_id"));
                        client.setClientName(set.getString("client_name"));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return client;
                }
            }
        };
        Object client = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(client==null){
            return null;
        }else {
            return (Client) client;
        }
    }

    /**
     * 根据主键删除
     * @param clientID
     */
    public void delete(String clientID){
        String sql = "delete from client where client_id='"+clientID+"'";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"delete");
    }

    /**
     * 更新
     * @param client
     */
    public void update(Client client){
        String sql = "update client set " +
                "client_id='"+client.getClientID()+"',"+
                "client_name='"+client.getClientName()+"' "+
                "where client_id='"+client.getClientID()+"'";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"update");
    }

}