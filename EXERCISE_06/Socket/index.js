const alpha = require('alphavantage')({key: '7P8SGZJ8R2E29OOH'});
const app = require('express')();
const server = require('http').Server(app);
const io = require('socket.io')(server);
const FCM = require('fcm-node')
const serverKey = require('./socketclient-56ffc-firebase-adminsdk-qz6jn-e7213ac882.json')        
const fcm = new FCM(serverKey)

const PORT = process.env.PORT || 3000;

server.listen(PORT, () => console.log(`Listening in port ${PORT}`));

app.get('/', function (req, res) {
    res.status(403).send('Forbidden');
});

io.on('connect', function (socket) {
  let stock = null;
  socket.on('sendStockName', (data) => {
    stock = data;
  });

  let postStockPrice = setInterval(() => {
	if(socket) {
		if(!stock){
			socket.emit('connect_error');
			return;
		  }
		alpha.data.batch([`${stockName}`]).then(data => {
			socket.emit('postStockPrice',{
				price: `${data['Stock Quotes'][0]['2. price']}`,
			});
		}).catch(err => {
			socket.emit('connect_error');
		}
	} else {
		if(!stock){
			return;
		  }
		  console.log(stock.toString())
		  alpha.data.intraday(stock.toString()).then((data) => {
		  let mostRecentStock = Object.keys(data['Time Series (1min)'])[0];
		  const stockPrice = data['Time Series (1min)'][mostRecentStock]["1. open"];
		  const stockData = {
			price: stockPrice
		  };
			var message = {
				to: 'registration_token', 
				
				notification: {
					title: stock.toString(), 
					body: 'Price ' + stockPrice.toString() 
				},
			}
			
			fcm.send(message, function(err, response){})	  
		});
	}

}, 15000);
});