var url = "http://rhymebrain.com/talk?function=getRhymes&maxResults=10&word="
var http = require("http")
var MAX = 10;
var counter = 0;
var words = process.argv[2].split(" ");

whisper();

function whisper() {

	console.log(words.join(" "))

	var word = getRand(words)
	getRhymes(word, function(rhymes) {

		words[words.indexOf(word)] = getRand(rhymes).word;

		if(counter++ < MAX){
			whisper();
		}
	});
}

function getRhymes(word, reply){
	//console.log("gettings rhymes for " + word)
	http.get(url+word, function(res){
		res.setEncoding('utf8');
		var data = '';
		if(res.statusCode != 200) { console.log("error getting rhyme!"); return; }
		res.on('data', function(chunk){
			
			data += chunk
			reply(eval(data))

		});
		//res.on('end', reply(eval(data)));
		
	}).on('error', function(e) {
	   console.log('couldn\'t get rhyming word: ' + e.message);
	});
}

function getRand(items){

	return items[Math.floor(Math.random()*items.length )];
}