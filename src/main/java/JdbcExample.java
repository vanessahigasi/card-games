import tech.bts.cardgames.model.Game;
import tech.bts.cardgames.repository.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcExample {

    public static void main(String[] args) throws SQLException { //Sql language to access database

        DataSource dataSource = DataSourceUtil.getDataSourceInPath(); //create a database

        Connection connection = dataSource.getConnection(); //get connection to the Database
        Statement statement = connection.createStatement(); //can create statements
        ResultSet rs = statement.executeQuery("select * from games");

        List<Game> games = new ArrayList<>();

        while (rs.next()) { //boolean it returns true if there is another row

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
            games.add(game);
        }

        rs.close();
        statement.close();
        connection.close();

        System.out.println("Games:");
        for (Game game: games) {
            System.out.println(game.getId() + " " + game.getState() + " " + game.getPlayerNames());
        }
    }
}
