document.addEventListener('DOMContentLoaded', function() {
    const goalsData = {
        total: 100,
        completed: 70,
        byColor: [
            {color: "Red", value: 10, hex: "#E81416"},
            {color: "Orange", value: 15, hex: "#FFA500"},
            {color: "Yellow", value: 20, hex: "#FAEB36"},
            {color: "Green", value: 5, hex: "#79C314"},
            {color: "Blue", value: 10, hex: "#487DE7"},
            {color: "Violet", value: 10, hex: "#70369D"}
        ]
    };

    const barcodeWidth = 400, barcodeHeight = 20;

    const barcodeSvg = d3.select('#goals-barcode').append('svg')
        .attr('width', barcodeWidth)
        .attr('height', barcodeHeight);

    // Total Goals
    barcodeSvg.append('rect')
        .attr('width', barcodeWidth)
        .attr('height', barcodeHeight)
        .attr('fill', '#ccc');

    // Completed Goals
    barcodeSvg.append('rect')
        .attr('width', barcodeWidth * (goalsData.completed / goalsData.total))
        .attr('height', barcodeHeight)
        .attr('fill', 'green');

    const pieRadius = 100;
    const pieSvg = d3.select("#goals-pie").append("svg")
        .attr("width", pieRadius * 2)
        .attr("height", pieRadius * 2)
        .append("g")
        .attr("transform", `translate(${pieRadius},${pieRadius})`);

    const pie = d3.pie().value(d => d.value);
    const arcs = d3.arc().innerRadius(0).outerRadius(pieRadius);

    const color = d3.scaleOrdinal(goalsData.byColor.map(d => d.hex));

    pieSvg.selectAll("path")
        .data(pie(goalsData.byColor))
        .enter().append("path")
        .attr("fill", d => color(d.data.hex))
        .attr("d", arcs)
        .append("title")
        .text(d => `${d.data.color}: ${d.data.value}`);
});
