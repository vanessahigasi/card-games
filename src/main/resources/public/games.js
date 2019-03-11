console.log("Games js loaded!!!");

//const gamePromise = fetch("http://localhost:8080/api/games"); //get the url
const gamePromise = axios.get("http://localhost:8080/api/games");

gamePromise
    //.then(x => x.json()) //converts the response to Json
    .then(function (response) {

        const games = response.data;

        let gamesContainer = document.getElementById("container");

        for (let game of games) {
        console.log(`${game.id} -> ${game.state}`);

        //Display the games in HTML

        const p = document.createElement("div");
        p.textContent = `The game ${game.id} is ${game.state}`;
        gamesContainer.appendChild(p);
    }
});

var addGameBtn = document.querySelector("#add-game");

addGameBtn.addEventListener("click", function () {

    axios.post("/api/games")
        .then(function (response) {
                const gameId = response.data;
                console.log("Game added: " + gameId);
                location.reload();
        })
        .catch( function (error) {
            console.log("ERROR", error);
        });
});


