const topojson = require('topojson')
const topoJson = require('./us-states.json')
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

function statesMap({ nodes, radius = defaultRadius, fill = defaultFill, styles = defaultStyles } = {}) {
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
