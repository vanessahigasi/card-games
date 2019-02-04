console.log("Games js loaded!!!");

const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('gameId');
console.log("gameId = " + gameId);

//const gamesPromise = fetch("http://localhost:8080/api/games"); //get the url
const gamesPromise = axios.get("http://localhost:8080/api/games" + gameId);

gamesPromise
    //.then(x => x.json()) //converts the response to Json
    .then(function (response) {

        const games = response.data;

        let gamesContainer = document.getElementById("container");

        for (let game of games) {
        console.log(`${game.id} -> ${game.state}`);

        //Display the games in HTML

        const p = document.createElement("p");
        p.textContent = `The game ${game.id} is ${game.state}`;
        gamesContainer.appendChild(p);
    }
});

