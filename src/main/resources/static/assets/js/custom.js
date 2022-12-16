// Init Table
var $table = $('#table')

$table.bootstrapTable({
    columns: [{
      field: 'place',
      title: 'Place'
    }, {
      field: 'nickname',
      title: 'Nick Name'
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
    var requestGetUser = window.location.origin + "/player?name=" + rowByUniqueId.name;
    console.log(requestGetUser);
    $.getJSON(requestGetUser, function(data) {
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
    var username = $(".username span")[0].textContent
    var requestAddPoint = window.location.origin + "/player/addPoint";
    console.log(username)
    fetch(requestAddPoint, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'plain/text'
        },
        body: username
    })
    .then(response => response.json())
    .then(response => {
        console.log(JSON.stringify(response))
        $(".username span").text(response.name);
        $(".position span").text(response.score);
        setTimeout(reloadTable(), 1000);
    })
}

// Remove point
function removePoint() {
    var username = $(".username span")[0].textContent
    var requestRemovePoint = window.location.origin + "/player/removePoint";
    console.log(username)
    fetch(requestRemovePoint, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'plain/text'
        },
        body: username
    })
    .then(response => response.json())
    .then(response => {
        console.log(JSON.stringify(response))
        $(".username span").text(response.name);
        $(".position span").text(response.score);
        setTimeout(reloadTable(), 1000);
    })
}
