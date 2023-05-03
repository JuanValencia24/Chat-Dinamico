var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            var key = JSON.parse(greeting.body).key;
            if(key==0){
                window.location.href = "login";
            }
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    window.location.href="sala";
}

function sendName() {
    
    stompClient.send("/app/hello",{}, JSON.stringify({'name': $("#name").val(),'token':localStorage.token,'id':localStorage.getItem("id"), 'salaId':localStorage.getItem("salaId")}));
    $('#name').val('');
}

function showGreeting(message) {
    $("#greetings").append("<ul class='list-group list-group-horizontal'><li class='list-group-item'>"+message.nombre+" "+message.apellido+"</li><li class='list-group-item'>"+message.content+"</li></ul>");
}

function cerrarSesion(){
    localStorage.clear();
    window.location.href = "login"
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});