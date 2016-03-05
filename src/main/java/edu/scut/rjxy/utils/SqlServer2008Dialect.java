package edu.scut.rjxy.utils;

/**
 * Created by Administrator on 2016/3/5.
 */

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

public class SqlServer2008Dialect extends SQLServerDialect {

    public SqlServer2008Dialect() {
        super();
        registerHibernateType(Types.CHAR, Hibernate.STRING.getName());
        registerHibernateType(Types.NVARCHAR, Hibernate.STRING.getName());
        registerHibernateType(Types.LONGNVARCHAR, Hibernate.STRING.getName());
        registerHibernateType(Types.DECIMAL, Hibernate.DOUBLE.getName());
    }
}