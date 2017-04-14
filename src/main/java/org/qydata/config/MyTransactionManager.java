package org.qydata.config;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * Created by jonhn on 2017/4/14.
 */


public class MyTransactionManager  extends DataSourceTransactionManager {

    @Override

    protected void doBegin(Object transaction, TransactionDefinition definition) {

        //选择master数据库
        super.doBegin(transaction, definition);

    }

}
