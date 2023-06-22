const output = require('d3node-output')
const d3nMap = require('./pie')
const PropertiesReader = require('properties-reader');
const d3 = d3nMap.d3;

var data = null;

var targetPath = null;

var properties = PropertiesReader('/Users/eichmann/Documents/Components/d3-node.properties');
//
// because of the async nature of the output function, it's challenging to iterate said output
// so we just need to run this with a really big heap:
//
// node --max_old_space_size=102400 publications.js
//

async function init() { console.log(process.argv);
	data = await d3.json(properties.get("collaborators"));
	targetPath = properties.get("collaborators-path");
	console.log(data);
}

init().then(() => {
	const options = { width: 500, height: 500 };
	//output(targetPath+`_sites/03wmf1y16`, d3nMap(data), options)
	data.forEach(function(val, index, array) {
		var target = val.ror_id;
		if (val.orgs != null) {
			console.log(target,target.replace(/.*\//, ''), val.orgs)
			// this output call generates x.png, x.svg and x.html
			output(targetPath+`_sites/` + target.replace(/.*\//, ''), d3nMap(val.orgs), options)
		}
	});
});
