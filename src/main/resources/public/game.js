console.log("Game js loaded!!!");

const urlParams = new URLSearchParams(window.location.search);
const gameId = urlParams.get('gameId');
console.log("gameId = " + gameId);

let url = "http://localhost:8080/api/games/" + gameId;
console.log(url);

const gamesPromise = fetch(url);

gamesPromise
    .then(x => x.json()) //converts the response to Json
    .then(function (game) {
        //This function will be called when the data comes
        console.log(game);

            //Display the games in HTML

            const p = document.createElement("p");
            p.textContent = `The game ${game.id} is ${game.state}`;
            const container = document.getElementById("container");
            container.appendChild(p);
    });