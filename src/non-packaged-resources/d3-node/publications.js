const output = require('d3node-output')
const d3nMap = require('./map-us-network')
const PropertiesReader = require('properties-reader');
const d3 = d3nMap.d3;

var nodes = null;
var radius = null;
var fill = null;

var edges = null;
var scale = null;

var targetPath = null;

var properties = PropertiesReader('/Users/eichmann/Documents/Components/d3-node.properties');
//
// because of the async nature of the output function, it's challenging to iterate said output
// so we just need to run this with a really big heap:
//
// node --max_old_space_size=102400 publications.js
//

async function init() { console.log(process.argv);
	if (process.argv.length === 2) {
		console.error('Expected at least one argument!');
		process.exit(1);
	}

	if (process.argv[2] && process.argv[2] === 'publications') {
		nodes = await d3.json(properties.get("publication-nodes"));
		nodes = nodes.sites;

		edges = await d3.json(properties.get("publication-edges"));
		edges = edges.edges;
		
		targetPath = properties.get("publication-path");
	} else if (process.argv[2] && process.argv[2] === 'projects') {
		nodes = await d3.json(properties.get("project-nodes"));
		nodes = nodes.sites;

		edges = await d3.json(properties.get("project-edges"));
		edges = edges.edges;
		
		targetPath = properties.get("project-path");
	} else {
		console.log('Flag is not present.');
		process.exit(1);
	}

	// Color Scale For Legend and Map 
	fill = d3.scaleOrdinal()
		.domain(["N3C", "CTSA", "GOV", "CTR", "COM", "UNAFFILIATED", "REGIONAL", "X1", "X2", "X3"])
		.range(["#007bff", "#8406D1", "#09405A", "#AD1181", "#ffa600", "#ff7155", "#a6a6a6", "8B8B8B", "black", "yellow"]);


	radius = d3.scaleLinear()
		.domain([0, d3.max(nodes, function(d) { return d.count; })])
		.range([3, 15]);

	scale = d3.scaleLinear()
		.domain([0, d3.max(edges, function(d) { return d.count; })])
		.range([0, 1]);
}

init().then(() => {
	const options = { width: 960, height: 500 };
	// not providing target here results in all weighted edges being rendered
	output(targetPath, d3nMap({ nodes, radius, fill, edges, scale }), options)
	nodes.forEach(function(val, index, array) {
		var target = val.id;
		//console.error(target,target.replace(/.*\//, ''))
		// this output call generates x.png, x.svg and x.html
		output(targetPath+`_sites/` + target.replace(/.*\//, ''), d3nMap({ nodes, radius, fill, edges, scale, target }), options)
	});
});
