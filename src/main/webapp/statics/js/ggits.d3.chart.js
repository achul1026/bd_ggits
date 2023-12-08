 $(function(){


    //세로 그래프

    //  var salesData = [
    //     {Region:'12시', Qty:5565},
    //     {Region:'13시', Qty:5565},
    //     {Region:'14시', Qty:5565},
    //     {Region:'15시', Qty:5565},
    //     {Region:'16시', Qty:5565},
    //     {Region:'17시', Qty:5565},
    // ];
    // var svg = d3.select("#big_data_traffic");
    // var padding = {top:20, right:30, bottom:30, left:50};
    // var colors = ["#fff","#999","#887461","red","blue","green","pink","yellow","#123123","#887461",];
    // var chartArea = {
    //     "width":parseInt(svg.style("width"))-padding.left-padding.right,
    //     "height":parseInt(svg.style("height"))-padding.top-padding.bottom
    // };

    // var yScale = d3.scaleLinear()
    // .domain([0,d3.max(salesData,function(d,i){return d.Qty})])
    // .range([chartArea.height,0]).nice();

    // var xScale = d3.scaleBand()
    // .domain(salesData.map(function(d){return d.Region}))
    // .range([0,chartArea.width])
    // .padding(.2);

    // var xAxis = svg.append("g")
    //     .classed("xAxis", true)
    //     .attr('transform', 'translate('+padding.left+','+(chartArea.height+padding.top)+')')
    //     .call(d3.axisBottom(xScale));
        

    // var yAxisFn=d3.axisLeft(yScale);
    // var yAxis = svg.append("g")
    //     .classed("yAxis", true)
    //     .attr('transform', 'translate('+padding.left+','+padding.top+')')
    // yAxisFn(yAxis);

    // //bar
    // var rectGrp = svg.append("g").attr(
    //     'transform', 'translate('+padding.left+','+padding.top+')'
    // );

    // rectGrp.selectAll("rect").data(salesData).enter()
    // .append("rect")
    // .attr("width",xScale.bandwidth())
    // .attr("height",function (d,i) {
    //     return chartArea.height-yScale(d.Qty);
    // })
    // .attr("x", function (d,i){
    //     return xScale(d.Region);
    // })
    // .attr("y", function (d,i){
    //     return yScale(d.Qty);
    // })
    // .attr("fill", function (d,i){
    //     return colors[i];
    // })

    

    
    //빅데이터 교통량 분석 결과 그래프 버전

    const margin = {top:10, right:40, bottom:30, left:50}
    const width = 400 - margin.left - margin.right
    const height = 200 - margin.top - margin.bottom

     var data = [
        {name:'파주시', total:500},
        {name:'평택시', total:460},
        {name:'안성시', total:150},
        {name:'수원시', total:320},
        {name:'시흥시', total:480},
        {name:'안양시', total:60}
    ]

    const svg2 = d3.select("#big_data_traffic")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate("+ margin.left + ", "+ margin.top + ")");

    // d3.json("chart.json").then(data => {
    //     data.forEach(d => {
    //         d.total = +d.total;
    //     });

        const x = d3.scaleLinear()
        .range([0, width])
        .domain([0, d3.max(data, function (d) {return d.total;})]);

        const y = d3.scaleBand()
        .range([height, 0])
        .padding(0.6)
        .domain(data.map(function(d){return d.name;}));
    
        const xAxis = d3.axisBottom(x)
            .ticks(5)
            .tickSize(0)
            .tickPadding(10);
        const yAxis = d3.axisLeft(y)
            .tickSize(0)
            .tickPadding(10);
 
        const color = ["#FF2828","#ff9900","#ff2828","#FED501","#ACDC87","#3CBC00"]

        svg2.selectAll(".bar")
        .data(data)
        .enter().append("rect")
        .attr("class", "bar")
        .attr("y", function(d) {return y(d.name);})
        .attr("height", y.bandwidth())
        .attr("x", 0)
        .attr("width", function(d) {return x(d.total);})
        .attr("fill", (d, i) => {
            return color[i];
        })


        svg2.append("g")
        .attr("class", "x axis")
        .style("font-size", "10px")
        .style("font-family", "pretendard")
        .attr("transform", "translate(0, "+ height +")")
        .call(xAxis)
        // .call(g => g.select(".domain").remove());

        svg2.append("g")
        .call(yAxis)
        .style("font-family", "pretendard")
        .style("font-size", "10px");

//  });


    //effectFusion

     var effectFusionData = [
        {nameA:'도로구간A', totalA:500},
        {nameA:'도로구간B', totalA:460},
        {nameA:'도로구간C', totalA:150},
        {nameA:'도로구간D', totalA:320},
        {nameA:'도로구간E', totalA:480},
        {nameA:'도로구간F', totalA:60}
    ]

    const svg3 = d3.select("#big_data_traffic2")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate("+ margin.left + ", "+ margin.top + ")");

    // d3.json("chart.json").then(data => {
    //     data.forEach(d => {
    //         d.total = +d.total;
    //     });

        const xFusion = d3.scaleLinear()
        .range([0, width])
        .domain([0, d3.max(effectFusionData, function (d) {return d.totalA;})]);

        const yFusion = d3.scaleBand()
        .range([height, 0])
        .padding(0.6)
        .domain(effectFusionData.map(function(d){return d.nameA;}));
    
        const xAxisFusion = d3.axisBottom(xFusion)
        .ticks(5)
        .tickSize(0)
        .tickPadding(10);
        const yAxisFusion = d3.axisLeft(yFusion)
        .tickSize(0)
        .tickPadding(10);

        svg3.selectAll(".bar")
        .data(effectFusionData)
        .enter().append("rect")
        .attr("class", "bar")
        .attr("y", function(d) {return yFusion(d.nameA);})
        .attr("height", y.bandwidth())
        .attr("x", 0)
        .attr("width", function(d) {return xFusion(d.totalA);})
        .attr("fill", (d, i) => {
            return color[i];
        })


        svg3.append("g")
        .attr("class", "x axis")
        .style("font-size", "10px")
        .style("font-family", "pretendard")
        .attr("transform", "translate(0, "+ height +")")
        .call(xAxisFusion)
        // .call(g => g.select(".domain").remove());

        svg3.append("g")
        .call(yAxisFusion)
        .style("font-family", "pretendard")
        .style("font-size", "10px");

//  });

})

