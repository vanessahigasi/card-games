

class GamesList extends React.Component {

    constructor(props) {
        super(props);

        this.state ={
            games:[] //properties  [] creates an empty list
        };
    }

    componentDidMount() {
        // call some API to get data
        this.fetchAndDisplayGames();
    }

    fetchAndDisplayGames() {
        axios.get("/api/games")
            .then((response) => {

                const games = response.data;

                //this.state.games = games;
                this.setState({
                    games: games
                })
            });
    }

    addGame() {

        axios.post("/api/games").then(response => { //get the post create game
            //do something after game is created
           this.fetchAndDisplayGames();

        })
    }

    render() {
        return (
            <div>
                <Title text={this.props.title}/>
                <p><button onClick={() => this.addGame()}>Add game</button></p>
                <ul>
                    {this.state.games.map(game => ( //loop
                        <li key={game.id}><a href={"/games/" + game.id}>Game {game.id}</a> is {game.state}</li>
                    ))}
                </ul>
            </div>


        );
    }
}