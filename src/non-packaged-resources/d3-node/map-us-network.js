const topojson = require('topojson')
const topoJson = require('./data/us-states.json')
const D3Node = require('d3-node')
const d3 = D3Node.d3

// This assumes various components installed from https://github.com/d3-node

const defaultStyles = `
    .states { pointer-events: none; fill: #f8f9fa; stroke: #d4d4d4; stroke-width: 1px; stroke-linejoin: round;}
`

const defaultRadius = function(d) {
	return d.POP_2010 / 150000
}

const defaultFill = function(d) {
	const colorScale = d3.scaleThreshold()
		.domain([1, 4, 8, 15, 20, 30, 40, 50, 60])
		.range([
			'rgb(255,245,240)', 'rgb(254,224,210)', 'rgb(252,187,161)',
			'rgb(252,146,114)', 'rgb(251,106,74)', 'rgb(239,59,44)',
			'rgb(203,24,29)', 'rgb(165,15,21)', 'rgb(103,0,13)'
		])

	return colorScale(d.POP_2010 / 150000)
}

var defaultScale = d3.scaleLinear()
	.domain([0, 1])
	.range([0, 1]);

function statesMap({ nodes, radius = defaultRadius, fill = defaultFill, edges, scale = defaultScale, target, styles = defaultStyles } = {}) {
	const width = 960;
	const height = 500;
	const d3n = new D3Node({ styles })
	const projection = d3.geoAlbersUsa()
		.translate([width / 2, (height / 2)]) // translate to center of screen
		.scale([width]); // scale things down so see entire US
	const path = d3.geoPath().projection(projection)

	const rows = nodes.filter(function(d) { return projection([d.longitude, d.latitude]) })

	const svg = d3n.createSVG(width, height)

	svg.selectAll('.states')
		.data(topojson.feature(topoJson, topoJson.objects.states).features)
		.enter()
		.append('path')
		.attr('class', 'states')
		.attr('d', path)

	var node_map = d3.map(nodes, d => d.id);

	var links = [];
	edges.filter(function(link) {
		//console.log(link);
		var source = node_map.get(link.source);
		var target = node_map.get(link.target);
		//console.log(link, source, target);
		if (source == undefined || target == undefined)
			return false;
		var count = link.count;
		var source_loc = projection([source.longitude, source.latitude]);
		var target_loc = projection([target.longitude, target.latitude]);
		//console.log(source_loc, target_loc);
		if (source_loc != undefined && target_loc != undefined)
			links.push({ source, source_loc, target, target_loc, count })
	});

	const link = svg.selectAll("line")
		.data(links)
		.enter()
		.append("line")
		.attr("class", "links")
		.style("stroke", "#007bff")
		.style("stroke-width", function(d) {
			if (d.source.id == target || d.target.id == target)
				return "1.0px";
			else
				return "0.5px";
			})
		.style("stroke-opacity", function(d) {
			if (d.source.id == target || d.target.id == target)
				return 1;
			else if (target == null)
				return scale(d.count);
			else
				return 0; //scale(d.count);
			})
		.attr("x1", function(d) { return d.source_loc[0]; })
		.attr("x2", function(d) { return d.target_loc[0]; })
		.attr("y1", function(d) { return d.source_loc[1]; })
		.attr("y2", function(d) { return d.target_loc[1]; });

	const circleGroup = svg.selectAll('circle')
		.data(rows)
		.enter()
		.append('g')
		.attr('transform', function(d) {
			const coords = projection([d.longitude, d.latitude])
			return `translate(${coords})`
		})
		.attr('class', 'map-point')

	circleGroup
		.append('circle')
		.style("fill", function(d) { return fill(d.type); })
		.style('fill-opacity', 0.8)
		.attr("r", function(d) { return radius(d.count); })

	return d3n
}

module.exports = statesMap
module.exports.d3 = d3
module.exports.dsvFormat = d3.dsvFormat
module.exports.csvParse = d3.csvParse
module.exports.scaleThreshold = d3.scaleThreshold
