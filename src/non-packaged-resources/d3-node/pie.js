const D3Node = require('d3-node')
const d3 = D3Node.d3

// This assumes various components installed from https://github.com/d3-node

function collaborators(data) {
	const d3n = new D3Node()
	var text = "";

	var width = 500;
	var height = 500;
	var thickness = 40;
	var duration = 750;
	var padding = 10;
	var opacity = .8;
	var opacityHover = 1;
	var otherOpacityOnHover = .8;
	var tooltipMargin = 13;

	var radius = Math.min(width - padding * 2, height - padding * 2) / 2;
	var color = d3.scaleOrdinal()
		.domain(["N3C", "CTSA", "GOV", "CTR", "COM", "UNAFFILIATED", "REGIONAL", "X1", "X2", "X3"])
		.range(["#007bff", "#8406D1", "#09405A", "#AD1181", "#ffa600", "#ff7155", "#a6a6a6", "8B8B8B", "black", "yellow"]);

	var svg = d3n.createSVG(width, height)
		.attr('class', 'pie')
		.attr('width', width)
		.attr('height', height);

	var g = svg.append('g')
		.attr('transform', 'translate(' + (width / 2) + ',' + (height / 2) + ')');

	var pie = d3.pie()
		.value(function(d) { return d.value })

	var slices = pie(data);

	var arc = d3.arc()
		.innerRadius(radius * 0.25)
		.outerRadius(radius);

	var arcGraph = g.selectAll('path.slice')
		.data(slices)
		.enter();
	arcGraph.append('path')
		.attr('class', 'slice')
		.attr('d', arc)
		.attr('fill', function(d) {
			return color(d.data.name);
		});

	arcGraph.append("text")
		.attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })

		.attr("dy", "0.35em")
		.style("font-size", "24px")
		.style("fill", "white")
		.text(function(d) { return d.data.value });

	// do legends
	svg.append('g')
		.attr('class', 'legend')
		.selectAll('text')
		.data(slices)
		.enter()
		.append('text')
		.text(function(d) { return '• ' + d.data.name; })
		.attr('fill', function(d) { return color(d.data.name); })
		.attr('y', function(d, i) { return 20 * (i + 1); })

	return d3n
}

module.exports = collaborators
module.exports.d3 = d3
module.exports.dsvFormat = d3.dsvFormat
module.exports.csvParse = d3.csvParse
module.exports.scaleThreshold = d3.scaleThreshold
