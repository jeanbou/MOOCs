var express = require('express');
var session = require('cookie-session'); // Charge le middleware de sessions
var bodyParser = require('body-parser'); // Charge le middleware de gestion des paramètres
var urlencodedParser = bodyParser.urlencoded({ extended: false });

var app = express();
server = require('http').createServer(app),
io = require('socket.io').listen(server),
ent = require('ent'),
fs = require('fs');


/* On utilise les sessions */
app.use(session({secret: 'todotopsecret'}))

.use(function(req, res, next){
    if (typeof(req.session.todolist) == 'undefined') {
        req.session.todolist = [];
    }
    next();
})

.get('/todo', function(req, res) {
    res.render('todo.ejs', {todolist: req.session.todolist});
})

.post('/todo/ajouter/', urlencodedParser, function(req, res) {
    if (req.body.newtodo != '') {
        req.session.todolist.push(req.body.newtodo);
    }
    res.redirect('/todo');
})

.get('/todo/supprimer/:id', function(req, res) {
    if (req.params.id != '') {
        req.session.todolist.splice(req.params.id, 1);
    }
    res.redirect('/todo');
})

.use(function(req, res, next){
    res.redirect('/todo');
})


io.sockets.on('connection', function (socket, pseudo) {

    socket.on('nouveau_client', function(pseudo) {       
       socket.pseudo = pseudo;
       socket.broadcast.emit('nouveau_client', pseudo);
    });

    socket.on('message', function (message) {
       message = ent.encode(message);
       socket.broadcast.emit('message', {pseudo: socket.pseudo, message: message});
    }); 

});


server.listen(8080)
