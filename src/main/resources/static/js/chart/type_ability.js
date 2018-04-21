function drawAbilityChart(id, list) {
    //var newList=new Array();
    var newList=[];
    // for(var i = 0; i < list.length; i++){
    //     var jobImg = new Object();
    //     jobImg.name = list[i].label;
    //     jobImg.value = i+201 + 1000;
    //     console.log(jobImg);
    //     newList.push(jobImg);
    // }

    for(var i = 0; i < list.length; i++){
    //    console.log(list[i]);
        for(var key in list[i]){
            var jobImg = new Object();
            jobImg.name = key;
            jobImg.value = list[i][key];
            newList.push(jobImg);
        }
    }

    //console.log(newList);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(
        {
            title: {
                text: "岗位能力需求",
                link: '#',
                //subtext: '牛逼不',
                sublink: 'http://hqweay.cn',
            },
            tooltip: {},
            series: [{
                type: 'wordCloud',
                gridSize: 20,
                sizeRange: [12, 50],
                rotationRange: [0, 0],
                shape: 'circle',
                textStyle: {
                    normal: {
                        color: function () {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        }
                    },
                    emphasis: {
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },
                data: newList
            }]
        }
    );

    return true;
}
