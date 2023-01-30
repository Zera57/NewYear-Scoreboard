// Init Table
var $table = $('#table')

$table.bootstrapTable({
    columns: [{
      field: 'place',
      title: 'Place'
    }, {
      field: 'nickname',
      title: 'Nick name'
    }, {
      field: 'name',
      title: 'Name'
    }, {
        field: 'score',
        title: 'Score'
    }],
    data: [{
      place: 1,
      name: 'Item 1',
      score: '$1'
    }, {
      place: 2,
      name: 'Item 2',
      score: '$2'
    }]
  })

// Open Modal
$table.on('click-row.bs.table', function (row, $element, field) {
    var rowByUniqueId = $table.bootstrapTable('getRowByUniqueId', field[0].getAttribute("data-uniqueid"));
    console.log(JSON.stringify(rowByUniqueId))
    var requestGetUser = window.location.origin + "/player?name=" + rowByUniqueId.name + "&nickname=" + rowByUniqueId.nickname;
    console.log(requestGetUser);
    $.getJSON(requestGetUser, function(data) {
        $(".nickname span").text(data.nickname);
        $(".username span").text(data.name);
        $(".position span").text(data.score);
    });
    $(".modal-body div span").text("Add points");
    $("#myModal").modal("show");
});

// Refresh Table
function reloadTable() {
    var requestGetUser = window.location.origin + "/player/all";
    $.getJSON(requestGetUser, function(data) {
        // console.log(data);
        for (var i=0;i<data.length;i++) {
            data[i].place = i + 1;
         }
        $table.bootstrapTable('load', data)
    });
}

function getAllPlayers() {
    reloadTable()
    setTimeout(getAllPlayers, 5000);
}

getAllPlayers();

// Add point
function addPoint() {
    var nickname = $(".nickname span")[0].textContent
    var username = $(".username span")[0].textContent
    var requestAddPoint = window.location.origin + "/player/addPoint";
    var data = JSON.stringify({"name": username, "nickname": nickname, "points": "1"});
    console.log(data)
    fetch(requestAddPoint, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: data
    })
    .then(response => response.json())
    .then(response => {
        console.log(JSON.stringify(response))
        $(".nickname span").text(response.nickname);
        $(".username span").text(response.name);
        $(".position span").text(response.score);
        setTimeout(reloadTable(), 1000);
    })
}

function addFivePoints() {
    var nickname = $(".nickname span")[0].textContent
    var username = $(".username span")[0].textContent
    var requestAddPoint = window.location.origin + "/player/addPoint";
    var data = JSON.stringify({"name": username, "nickname": nickname, "points": "5"});
    console.log(data)
    fetch(requestAddPoint, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: data
    })
    .then(response => response.json())
    .then(response => {
        console.log(JSON.stringify(response))
        $(".nickname span").text(response.nickname);
        $(".username span").text(response.name);
        $(".position span").text(response.score);
        setTimeout(reloadTable(), 1000);
    })
}

// Remove point
function removePoint() {
    var nickname = $(".nickname span")[0].textContent
    var username = $(".username span")[0].textContent
    var requestRemovePoint = window.location.origin + "/player/removePoint";
    var data = JSON.stringify({"name": username, "nickname": nickname, "points": "1"});
    console.log(data)
    fetch(requestRemovePoint, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: data
    })
    .then(response => response.json())
    .then(response => {
        console.log(JSON.stringify(response))
        $(".nickname span").text(response.nickname);
        $(".username span").text(response.name);
        $(".position span").text(response.score);
        setTimeout(reloadTable(), 1000);
    })
}

document.getElementById("randomPlayerButton")
    .addEventListener("click", function(e){
        e.preventDefault();
        console.log("Party!")
        party.confetti(e, {
            gravity: 1,
            spread: 60
        });
    });

function getRandomPlayer() {
    var requestGetRandomUser = window.location.origin + "/player/random";
    console.log(requestGetRandomUser);
    $.getJSON(requestGetRandomUser, function(data) {
        $(".nickname span").text(data.nickname);
        $(".username span").text(data.name);
        $(".position span").text(data.score);
    });
    $("#randomPlayerModal").modal("show");
}
