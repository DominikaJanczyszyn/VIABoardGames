$.get("xml/upcomingBoardGamesWebsite.xml", function (xml) {
    var text = "<div class='row'><div class='col-2 col-lg-1 headercol'><b>Number</b></div><div class='col-6 col-lg-3 headercol'><b>Name</b></div><div class='col-4 col-lg-2 headercol'><b>Players</b></div><div class='col-5 headercol d-none d-lg-block'><b>Description</b></div><div class='col-1 headercol d-none d-lg-block'><b>Votes</b></div></div>";
    var i = 1;
    var j = 1;
    $(xml).find("upcomingBoardGames").each(function () {
        let name = $(this).find("name").text();
        let numberOfPlayers = $(this).find("numberOfPlayers").text();
        let description = $(this).find("description").text();
        let numberOfVotes = $(this).find("numberOfVotes").text();
        text += "<div class='row'><div class='col-2 col-lg-1 headercol'>" + i + "</div><div class='col-6 col-lg-3 oddrow'>" + name + "</div><div class='col-4 col-lg-2 oddrow'>" + numberOfPlayers + "</div><div class='col-5 oddrow d-none d-lg-block'>" + description + "</div><div class='col-1 oddrow d-none d-lg-block'>" + numberOfVotes + "</div></div>";
        i++;
    });
    
    text += "<div class='row d-lg-none'><div class='col-2 headercol'><b>Number</b></div><div class='col-8 headercol'><b>Description</b></div><div class='col-2 headercol'><b>Rating</b></div></div>";
    
    $(xml).find("upcomingBoardGames").each(function () {
        let numberOfPlayers = $(this).find("numberOfPlayers").text();
        let description = $(this).find("description").text();
        let numberOfVotes = $(this).find("numberOfVotes").text();
        text += "<div class='row d-lg-none'><div class='col-2 headercol'>" + j + "</div><div class='col-8 oddrow'>" + description +"</div><div class='col-2 oddrow'>" + numberOfVotes + "</div></div>";
        j++;
    });
    $("#upcomingBoardGames").html(text);
});

$.get("xml/boardGamesWebsite.xml", function (xml) {
    var text = "<div class='row'><div class='col-2 col-lg-1 headercol'><b>Number</b></div><div class='col-6 col-lg-3 headercol'><b>Name</b></div><div class='col-4 col-lg-2 headercol'><b>Game onwer</b></div><div class='col-2 col-lg-1 headercol d-none d-lg-block'><b>Players</b></div><div class='col-4 headercol d-none d-lg-block'><b>Description</b></div><div class='col-1 headercol d-none d-lg-block'><b>Rating</b></div></div>";
    var i = 1;
    var j = 1;
    $(xml).find("boardGames").each(function () {
        let name = $(this).find("name").text();
        let numberOfPlayers = $(this).find("numberOfPlayers").text();
        let description = $(this).find("description").text();
        let averageRating = $(this).find("averageRating").text();
        let owner = $(this).find("owner").find("firstName").text() + " " + $(this).find("owner").find("lastName").text();
        if (owner == " ")
        {
            owner = "-";
        }
        text += "<div class='row'><div class='col-2 col-lg-1 headercol'>" + i + "</div><div class='col-6 col-lg-3 oddrow'>" + name + "</div><div class='col-4 col-lg-2 oddrow'>" + owner + "</div><div class='col-2 col-lg-1 oddrow d-none d-lg-block'>" + numberOfPlayers + "</div><div class='col-4 oddrow d-none d-lg-block'>" + description + "</div><div class='col-1 oddrow d-none d-lg-block'>" + averageRating + "</div></div>";
        i++;
    });
    
    text += "<div class='row d-lg-none'><div class='col-2 headercol'><b>Number</b></div><div class='col-2 headercol'><b>Player</b></div><div class='col-6 headercol'><b>Description</b></div><div class='col-2 headercol'><b>Rating</b></div></div>";
    
    $(xml).find("boardGames").each(function () {
        let numberOfPlayers = $(this).find("numberOfPlayers").text();
        let description = $(this).find("description").text();
        let averageRating = $(this).find("averageRating").text();
        text += "<div class='row d-lg-none'><div class='col-2 headercol'>" + j + "</div><div class='col-2 oddrow'>" + numberOfPlayers + "</div><div class='col-6 oddrow'>" + description +"</div><div class='col-2 oddrow'>" + averageRating + "</div></div>";
        j++;
    });

    $("#boardGames").html(text);
});

$.get("xml/eventsWebsite.xml", function (xml) {
    var text1 = "";
    $(xml).find("events").each(function () {
        let day = $(this).find("day").text();
        let month = $(this).find("month").text();
        let year = $(this).find("year").text();
        let name = $(this).find("name").text();
        let description = $(this).find("description").text();
        text1 += "<div class=\"row content\"><h4><b>" + name + "</b></h4><h5><b>Date: " + day + "/" + month + "/" + year + "</b></h5><p class=\"text\">" + description +"</p></div>";
    });
    $("#events").html(text1);
});
