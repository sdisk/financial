package com.hq.financial.config.datasource;


import com.baomidou.mybatisplus.toolkit.PluginUtils;
import com.google.common.base.Joiner;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program: financial
 * @description: 数据范围的拦截器
 * @author: Mr.Huang
 * @create: 2018-10-15 16:28
 **/
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class})})
public class DataScopeInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())){
            return invocation.proceed();
        }

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String oridinalSql = boundSql.getSql();
        Object paramterObject = boundSql.getParameterObject();

        //查找参数中包含DataScope类型的参数
        DataScope dataScope = findDataScopeObject(paramterObject);
        if (null == dataScope){
            return invocation.proceed();
        } else {
            String scopeName = dataScope.getScopeName();
            List<Integer> limitIds =  dataScope.getLimitIds();
            String join = Joiner.on(",").join(limitIds);
            oridinalSql = " select * from (" + oridinalSql +" ) temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
            metaObject.setValue("delegate.boundSql.sql",oridinalSql);
            return invocation.proceed();
        }
    }
    public DataScope findDataScopeObject(Object obj){
        if (obj instanceof  DataScope){
            return (DataScope) obj;
        } else if(obj instanceof Map) {
            for(Object val: ((Map)obj).values()){
                if(val  instanceof  DataScope){
                    return (DataScope) val;
                }
            }
        }
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
