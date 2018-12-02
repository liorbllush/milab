const http = require('http');
let server = http.createServer(
							function(request, response) {
								response.writeHead(200);
								console.log(request.headers.host+request.url);
								response.end(request.headers.host+request.url);
							});									
server.listen(8080);

