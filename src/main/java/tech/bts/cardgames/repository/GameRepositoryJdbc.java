package tech.bts.cardgames.repository;
import org.springframework.stereotype.Repository;
import tech.bts.cardgames.model.Game;

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

    private DataSource dataSource;

    public GameRepositoryJdbc() {
        this.dataSource = DataSourceUtil.getDataSourceInPath();

    }

    public void create(Game game) {

        try {

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into games (state, players) " + "values (" + game.getState() + " , null)");
            String sql = "insert into games (state, players) values (game.getState()" + " , null)";
            System.out.println(sql);


        } catch (Exception e) {
            //if it fails do this
            throw new RuntimeException("Error getting the games", e);
        }
    }


    public Game getById(long id) {

        try {

        Connection connection = dataSource.getConnection(); //get connection to the Database
        Statement statement = connection.createStatement(); //can create statements
        ResultSet rs = statement.executeQuery("select * from games where id = " + id);

        Game game = null;

        if (rs.next()) { //boolean it returns true if there is another row
             game = getGame(rs);
        }

        rs.close();
        statement.close();
        connection.close();

        return game;


    } catch (Exception e) {
        //if it fails do this
        throw new RuntimeException("Error getting the games", e);
    }
    }

    public Collection<Game> getAll()  {

        try {

            //try this code
            Connection connection = dataSource.getConnection(); //get connection to the Database
            Statement statement = connection.createStatement(); //can create statements
            ResultSet rs = statement.executeQuery("select * from games");

            List<Game> games = new ArrayList<>();

            while (rs.next()) { //boolean it returns true if there is another row

                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();
            statement.close();
            connection.close();

            return games;

        } catch (Exception e) {
            //if it fails do this
            throw new RuntimeException("Error getting the games", e);
        }
    }

    private Game getGame(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        //String state = rs.getString("state");
        String players = rs.getString("players");

        Game game = new Game(null); //create a game
        game.setId(id);

        if(players != null) {
            String[] names =  players.split(",");
            for (String name : names) {
                game.join(name);
            }
        }
        return game;
    }
}

