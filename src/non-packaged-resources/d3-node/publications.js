const output = require('d3node-output')
const d3nMap = require('./map-us-network')
const d3 = d3nMap.d3;

var nodes = null;
var radius = null;
var fill = null;

var edges = null;
var scale = null;

//
// because of the async nature of the output function, it's challenging to iterate said output
// so we just need to run this with a really big heap:
//
// node --max_old_space_size=102400 publications.js
//

async function init() {
	nodes = await d3.json('https://n3c.cd2h.org/dashboard/feeds/sitePublications.jsp');
	nodes = nodes.sites;
	
	edges = await d3.json('https://n3c.cd2h.org/dashboard/feeds/sitePublicationEdges.jsp');
	edges = edges.edges;

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
	const options = {width: 960, height: 500};
	// not providing target here results in all weighted edges being rendered
	output(`./publications`, d3nMap({ nodes, radius, fill, edges, scale }), options)
	nodes.forEach(function(val, index, array) {
		var target = val.id;
		// this output call generates x.png, x.svg and x.html
		output(`./sites/`+val.site.replace(/ /g, '_'), d3nMap({ nodes, radius, fill, edges, scale, target }), options)
	});
});
