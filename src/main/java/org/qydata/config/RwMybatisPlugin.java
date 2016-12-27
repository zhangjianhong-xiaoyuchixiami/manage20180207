//package org.qydata.config;
//
//import org.apache.ibatis.executor.statement.RoutingStatementHandler;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.logging.Log;
//import org.apache.ibatis.logging.LogFactory;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.*;
//
//import java.sql.Connection;
//import java.util.Properties;
//
//@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
//public class RwMybatisPlugin implements Interceptor {
//    private static final Log log = LogFactory.getLog(RwMybatisPlugin.class);
//
//    public Object intercept(Invocation invocation) throws Throwable {
//        Connection conn = (Connection) invocation.getArgs()[0];
//        // 如果是采用了我们代理,则路由数据源
//        if (conn instanceof ConnectionProxy) {
//            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//            MappedStatement mappedStatement = null;
//            if (statementHandler instanceof RoutingStatementHandler) {
//                StatementHandler delegate = (StatementHandler) ReflectionUtils.getFieldValue(statementHandler,
//                        "delegate");
//                mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(delegate, "mappedStatement");
//            } else {
//                mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(statementHandler, "mappedStatement");
//            }
//
//            String sql = statementHandler.getBoundSql().getSql();
//            String mapperId = mappedStatement.getId();
//
//            if (log.isDebugEnabled()) {
//                log.debug("Original Sql [" + mapperId + "]:" + sql);
//            }
//            // ------------------------------------------------------------------
//            // //
//            // 以下代码处理读写分离，根据SQL类型选择读写数据源
//            // ------------------------------------------------------------------
//            // //
//            String key = AbstractRWRoutingDataSourceProxy.WRITE;
//
//            if (mappedStatement.getSqlCommandType() == SqlCommandType.SELECT) {
//                if (log.isDebugEnabled()) {
//                    log.debug("准备调用[READ]实例");
//                }
//                key = AbstractRWRoutingDataSourceProxy.READ;
//            } else {
//                if (log.isDebugEnabled()) {
//                    log.debug("准备调用[WRITE]实例");
//                }
//                key = AbstractRWRoutingDataSourceProxy.WRITE;
//            }
//            // 选择读写数据源
//            ConnectionProxy conToUse = (ConnectionProxy) conn;
//            conToUse.getTargetConnection(key);
//        }
//
//        return invocation.proceed();
//    }
//
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    public void setProperties(Properties properties) {
//
//    }
//}
