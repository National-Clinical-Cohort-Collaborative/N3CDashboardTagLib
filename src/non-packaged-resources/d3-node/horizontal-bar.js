const D3Node = require('d3-node')
const d3 = D3Node.d3

// This assumes various components installed from https://github.com/d3-node

function collaborators(data) {
	const d3n = new D3Node()
	var margin = {
		top: 15,
		right: 35,
		bottom: 15,
		left: 80
	};

	var width = 500 - margin.left - margin.right,
		height = 500 - margin.top - margin.bottom;

	var svg = d3n.createSVG(width, height)
		.attr("width", width + margin.left + margin.right)
		.attr("height", height + margin.top + margin.bottom)
		.append("g")
		.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	var x = d3.scaleLinear()
		.range([0, width])
		.domain([0, d3.max(data, function(d) {
			return d.value;
		})]);

		var y = d3.scaleBand()			
			.range([0, height-margin.bottom])	
			.paddingInner(0.25)
			.align(0.1)
			.domain(data.map(function(d) { return d.name; }));

	// Color Scale For Legend and Map 
	fill = d3.scaleOrdinal()
		.domain(["N3C", "CTSA", "GOV", "CTR", "COM", "UNAFFILIATED", "REGIONAL", "X1", "X2", "X3"])
		.range(["#007bff", "#8406D1", "#09405A", "#AD1181", "#ffa600", "#ff7155", "#a6a6a6", "8B8B8B", "black", "yellow"]);

	//make y axis to show bar names
		svg.append("g")
			.attr("class", "y axis")
			.attr("transform", "translate(0,0)") 						
			.call(d3.axisLeft(y));	

	var bars = svg.selectAll(".bar")
		.data(data)
		.enter()
		.append("g")

	//append rects
	bars.append("rect")
		.attr("class", "bar")
		.attr("y", function(d) {
			return y(d.name);
		})
			.attr('fill', function(d) {return fill(d.name)})
		.attr("height", y.bandwidth())
		.attr("x", 0)
		.attr("width", function(d) {console.log(d,x(d.value),y.bandwidth())
			return x(d.value);
		});

	//add a value label to the right of each bar
	bars.append("text")
		.attr("class", "label")
		//y position of the label is halfway down the bar
		.attr("y", function(d) {
			return y(d.name) + y.bandwidth() / 2 + 4;
		})
		//x position is 3 pixels to the right of the bar
		.attr("x", function(d) {
			return x(d.value) + 3;
		})
		.text(function(d) {
			return d.value;
		});

	return d3n
}

module.exports = collaborators
module.exports.d3 = d3
module.exports.dsvFormat = d3.dsvFormat
module.exports.csvParse = d3.csvParse
module.exports.scaleThreshold = d3.scaleThreshold
