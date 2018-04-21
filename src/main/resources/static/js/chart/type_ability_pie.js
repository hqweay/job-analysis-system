function drawAbilityChartPie(id, list) {
    //var newList=new Array();
    var newList=[];

    for(var i = 0; i < list.length; i++){
        console.log(list[i]);
        for(var key in list[i]){
            var jobImg = new Object();
            jobImg.name = key;
            jobImg.value = list[i][key];
            newList.push(jobImg);
        }
    }



    var myChart = echarts.init(document.getElementById(id));
     myChart.setOption(
         {
    //
    //             backgroundColor: '#2c343c',
    //
    //             title: {
    //                 text: 'Customized Pie',
    //                 left: 'center',
    //                 top: 20,
    //                 textStyle: {
    //                     color: '#ccc'
    //                 }
    //             },
    //
    //             tooltip : {
    //                 trigger: 'item',
    //                 formatter: "{a} <br/>{b} : {c} ({d}%)"
    //             },
    //
    //             visualMap: {
    //                 show: false,
    //                 min: 80,
    //                 max: 600,
    //                 inRange: {
    //                     colorLightness: [0, 1]
    //                 }
    //             },
    //             series : [
    //                 {
    //                     name:'访问来源',
    //                     type:'pie',
    //                     radius : '55%',
    //                     center: ['50%', '50%'],
    //                     dataWaJue:newList.sort(function (a, b) { return a.value - b.value; }),
    //                     roseType: 'radius',
    //                     label: {
    //                         normal: {
    //                             textStyle: {
    //                                 color: 'rgba(255, 255, 255, 0.3)'
    //                             }
    //                         }
    //                     },
    //                     labelLine: {
    //                         normal: {
    //                             lineStyle: {
    //                                 color: 'rgba(255, 255, 255, 0.3)'
    //                             },
    //                             smooth: 0.2,
    //                             length: 10,
    //                             length2: 20
    //                         }
    //                     },
    //                     itemStyle: {
    //                         normal: {
    //                             color: '#c23531',
    //                             shadowBlur: 200,
    //                             shadowColor: 'rgba(0, 0, 0, 0.5)'
    //                         }
    //                     },
    //
    //                     animationType: 'scale',
    //                     animationEasing: 'elasticOut',
    //                     animationDelay: function (idx) {
    //                         return Math.random() * 200;
    //                     }
    //                 }
    //             ]
             tooltip: {
                 trigger: 'item',
                 formatter: "{a} <br/>{b}: {c} ({d}%)"
             },
             legend: {
                 orient: 'vertical',
                 x: 'left',
                 data:newList
             },
             series: [
                 {
                     name:'占比',
                     type:'pie',
                     radius: ['50%', '70%'],
                     avoidLabelOverlap: false,
                     label: {
                         normal: {
                             show: false,
                             position: 'center'
                         },
                         emphasis: {
                             show: true,
                             textStyle: {
                                 fontSize: '30',
                                 fontWeight: 'bold'
                             }
                         }
                     },
                     labelLine: {
                         normal: {
                             show: false
                         }
                     },
                     data:newList
                 }
             ]
             }
     );

    return true;
}
