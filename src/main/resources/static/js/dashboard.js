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

//    Added space for adding label
 const svgHeight = barcodeHeight + 60;

    const barcodeSvg = d3.select('#goals-barcode').append('svg')
        .attr('width', barcodeWidth)
        .attr('height', svgHeight);

    // Total Goals bar
    barcodeSvg.append('rect')
        .attr('width', barcodeWidth)
        .attr('height', barcodeHeight)
        .attr('fill', '#ccc');


    // Completed Goals bar
    barcodeSvg.append('rect')
        .attr('width', barcodeWidth * (goalsData.completed / goalsData.total))
        .attr('height', barcodeHeight)
        .attr('fill', 'green');

           // Label for Total Goals ("Goals Assigned")
            barcodeSvg.append("text")
                .attr("x", barcodeWidth / 2) // Center of the SVG
                .attr("y", barcodeHeight + 20) // Positioned below the total goals bar
                .text(`Goals Assigned: ${goalsData.total}`) // Text for Label Name
                .attr("text-anchor", "middle")  // positioning of the label
                .style("fill", "black")  // color and type of look
                .style("font-size", "14px");  // size for the label type

    // Label for Completed Goals ("Goals Accomplished")
    barcodeSvg.append("text")
        .attr("x", (barcodeWidth * (goalsData.completed / goalsData.total)) / 2) // Center of the completed part
        .attr("y", barcodeHeight + 40) // Below the first label
        .text(`Goals Accomplished: ${goalsData.completed}`)// Text for Label Name
        .attr("text-anchor", "middle")// positioning of the label
        .style("fill", "green")// color and type of look
        .style("font-size", "14px"); // size for the label type

// the pie chart code begins here -


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


  // Add Text to Pie Chart centered
    pieSvg.selectAll("text")
         .data(pie(goalsData.byColor))
         .enter().append("text")
         .attr("transform", d => `translate(${arcs.centroid(d)})`)
         .attr("text-anchor", "middle")
         .text(d => `${d.data.color}: ${d.data.value}`)
         .style("fill", "white")
         .style("font-size", "12px");
});
