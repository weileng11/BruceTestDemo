package com.bruce.core.net.operation;

/**
 * Author:lt
 * time:2017/8/5.
 * contact：weileng143@163.com
 *
 * @description
 */

public class RespnoseResultBean {


    /**
     * data : {"Stater":1,"Msg":"添加成功！"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * Stater : 1
         * Msg : 添加成功！
         */

        public int Stater;
        public String Msg;
    }
}
