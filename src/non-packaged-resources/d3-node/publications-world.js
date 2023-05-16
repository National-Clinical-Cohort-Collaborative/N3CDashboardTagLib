const output = require('d3node-output')
const worldMap = require('./map-world-network')
const fs = require('fs')
const data = fs.readFileSync('data/world-pop.tsv').toString()
const d3 = require('d3')
const populationsByCountry = d3.tsvParse(data)

var nodes = null;
var radius = null;
var fill = null;

var edges = null;
var scale = null;

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
	// create output files
	const optionsListFull = [
		{ projectionKey: 'Albers' },
		{ projectionKey: 'Equirectangular' },
		{ projectionKey: 'Aitoff' },
		{ projectionKey: 'August' },
		{ projectionKey: 'Baker' },
		{ projectionKey: 'Boggs' },
		{ projectionKey: 'Bonne' },
		{ projectionKey: 'Bromley' },
		{ projectionKey: 'Collignon' },
		{ projectionKey: 'Craster' },
		{ projectionKey: 'Eckert I' },
		{ projectionKey: 'Eckert II' },
		{ projectionKey: 'Eckert III' },
		{ projectionKey: 'Eckert IV' },
		{ projectionKey: 'Eckert V' },
		{ projectionKey: 'Eckert VI' },
		{ projectionKey: 'Eisenlohr' },
		{ projectionKey: 'Hammer' },
		{ projectionKey: 'Hill' },
		{ projectionKey: 'Goode Homolosine' },
		{ projectionKey: 'Kavrayskiy VII' },
		{ projectionKey: 'Lambert cylindrical equal-area' },
		{ projectionKey: 'Lagrange' },
		{ projectionKey: 'Larrivée' },
		{ projectionKey: 'Laskowski' },
		{ projectionKey: 'Loximuthal' },
		{ projectionKey: 'Mercator' },
		{ projectionKey: 'Miller' },
		{ projectionKey: 'McBryde–Thomas Flat-Polar Parabolic' },
		{ projectionKey: 'McBryde–Thomas Flat-Polar Quartic' },
		{ projectionKey: 'McBryde–Thomas Flat-Polar Sinusoidal' },
		{ projectionKey: 'Mollweide' },
		{ projectionKey: 'Natural Earth' },
		{ projectionKey: 'Nell–Hammer' },
		{ projectionKey: 'Polyconic' },
		{ projectionKey: 'Robinson' },
		{ projectionKey: 'Sinusoidal' },
		{ projectionKey: 'Sinu-Mollweide' },
		{ projectionKey: 'van der Grinten' },
		{ projectionKey: 'van der Grinten IV' },
		{ projectionKey: 'Wagner IV' },
		{ projectionKey: 'Wagner VI' },
		{ projectionKey: 'Wagner VII' },
		{ projectionKey: 'Winkel Tripel' }
	];
	const optionsList = [
		{ projectionKey: 'Miller' }
	];
	// use defaults
	optionsList.forEach((options) => {
		output((`./world/output-${options.projectionKey}`).replace(/ /g, '_'), worldMap(populationsByCountry, options, nodes, radius, fill, edges, scale))
	nodes.forEach(function(val, index, array) {
		var target = val.id;
		// this output call generates x.png, x.svg and x.html
		output(`./world/`+val.site.replace(/ /g, '_'), worldMap(populationsByCountry, options, nodes, radius, fill, edges, scale, target))
	});
	})
});
