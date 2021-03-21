//package org.hua.dit.oopii_21950_219113;
//
//import oracle.ucp.jdbc.PoolDataSource;
//import oracle.ucp.jdbc.PoolDataSourceFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Configuration
//@Profile("oracle-ucp")
//public class OracleUCPConfiguration {
//
//    @Bean
//    public DataSource dataSource() throws SQLException {
//        PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();
//        dataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
//        dataSource.setURL("jdbc:oracle:thin:@oracle12c.hua.gr:1521:orcl");
//        dataSource.setUser("IT219113");
//        dataSource.setPassword("Th@naras123");
//        dataSource.setFastConnectionFailoverEnabled(true);
//        dataSource.setInitialPoolSize(5);
//        dataSource.setMinPoolSize(5);
//        dataSource.setMaxPoolSize(10);
//        return dataSource;
//    }
//
//}
