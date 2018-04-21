function drawUserTagCloud(id, user) {

    var newList=[];

    console.log(user);

        for(var key in user){
            console.log(key);
            var userTag = new Object();
            userTag.value = 1;
            userTag.name = user[key];
            newList.push(userTag);
        }

    console.log(newList);

    //console.log(newList);
    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption(
        {
            title: {
             //   text: "用户画像",
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
