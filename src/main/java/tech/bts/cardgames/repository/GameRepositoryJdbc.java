package tech.bts.cardgames.repository;
import com.github.jknack.handlebars.internal.lang3.ObjectUtils;
import com.github.jknack.handlebars.internal.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.util.Util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * stores games in a memory*/
@Repository
public class GameRepositoryJdbc {

    //private DataSource dataSource; //to get connection to the database
    private JdbcTemplate jdbcTemplate;

    public GameRepositoryJdbc() {
        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Game game) {

        jdbcTemplate.update("insert into games (state, players)" +
                " values ('" + game.getState() + "', NULL)");

        /**doWithStatement((statement) -> {

            String sql = "insert into games (state, players)" +
                    " values ('" + game.getState() + "', NULL)";
            return statement.executeUpdate(sql);
        });*/
    }

    public void update(Game game) {

        String names = null;

        if (game.getPlayerNames() != null && !game.getPlayerNames().isEmpty()) {
            names = "'" + StringUtils.join(game.getPlayerNames(),",") + "'";
        }

        jdbcTemplate.update("update games set state  '" + game.getState() +
                "', players = '" + names  + "' where id = " + game.getId() + "");

        //jdbcTemplate.update(sql);
    }

    public void createOrUpdate(Game game) {

        if(game.getId() != null ) {
            update(game);
        } else {
            create(game);
        }
    }

    public Game getById(long id) {

        return jdbcTemplate.queryForObject("select * from games where id = " + id,
                (rs1, rowNum) -> getGame(rs1));

        /**
        return doWithStatement((statement -> {

            ResultSet rs = statement.executeQuery("select * from games where id = " + id);

            Game game = null;

            if (rs.next()) {
                game = getGame(rs);
            }"

            rs.close();

            return game;

        }));
         */
    }

    public Collection<Game> getAll() {

        return jdbcTemplate.query(
                "select * from games",
                (rs1, rowNum) -> getGame(rs1));

        /**
        return doWithStatement((statement -> {

            ResultSet rs = statement.executeQuery("select * from games");

            List<Game> games = new ArrayList<>();

            while (rs.next()) {

                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();

            return games;
        }));
         */
    }

    private Game getGame(ResultSet rs) throws SQLException {

        long id = rs.getLong("id");
        String players = rs.getString("players");

        Game game = new Game(null);
        game.setId(id);

        if (players != null) {
            String[] names = players.split(",");
            for (String name : names) {
                game.join(name);
            }
        }

        // String state = rs.getString("state");
        // The join() method already updates the game state so we don't need to update it

        return game;
    }

    // Function --> function that takes an argument and returns a value
    // Supplier --> function that doesn't take any argument and returns a value
    // Consumer --> function that takes an argument and doesn't return a value

    /** This is similar to Consumer, but allows exceptions
    private interface MyFunction<P, R> {
        R apply(P t) throws Exception;
    }

    private <T> T doWithStatement(MyFunction<Statement, T> useStatement) {

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            T result = useStatement.apply(statement); // part that you will specify

            statement.close();
            connection.close();

            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     */
}