const express = require('express');
const app = express();
const path = require('path');

app.get('/files/:filename', (req, res) => {
    res.setHeader('content-type', 'text/plain');
    var options = {
        root: __dirname + '/files/',
        dotfiles: 'deny'
    };
    res.sendFile(req.params.filename, options, function(err) {
        if(err) {
            res.send("error not found file")
        }
    });

});

app.listen(5000);

