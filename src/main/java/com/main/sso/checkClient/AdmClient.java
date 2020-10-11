package com.main.sso.checkClient;


import com.main.sso.entity.Client;
import com.main.sso.jdbc.clientMapper;

//管理服务端id
public class AdmClient {
    /**
     * 验证服务端id
     * @param client_id
     * @return
     */
    public static boolean checkClient(String client_id){
        boolean flag;
        clientMapper mapper = new clientMapper();
        Client client = mapper.select(client_id);
        flag = client != null;
        return flag;
    }
}
