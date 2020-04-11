package top.lzp.ssm.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

@Configuration
//mapper的扫描路径
@MapperScan("top.lzp.ssm.dao")
public class SSMConfiguration {
    @Autowired
    private DataSource dataSource;

    //dataSource
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=utf-8&" +
                "zeroDateTimeBehavior=convertToNull&serverTimezone=UTC");

        dataSource.setUser("root");
        dataSource.setPassword("root");
        //在关闭数据库连接的时候不自动Commit
        dataSource.setAutoCommitOnClose(false);

        return dataSource;
    }
    //sessionFactory
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //主配置文件加载 mybatis-config.xml
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        //mapper扫描路径
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = "classpath*:/mapper/**.xml";
        bean.setMapperLocations(resolver.getResources(packageSearchPath));
        //配置实体的包
        bean.setTypeAliasesPackage("top.lzp.ssm.entity");
        //dataSource
        bean.setDataSource(dataSource);
        return bean;
    }
}
