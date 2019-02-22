package tech.bts.cardgames;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import tech.bts.cardgames.repository.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertGamesSampleData {

    public static void main(String[] args) throws SQLException {

        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        Connection connection = dataSource.getConnection();
        ScriptUtils.executeSqlScript(connection,
                new ClassPathResource("sql-scripts/sample-data.sql"));
    }
}