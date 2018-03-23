function drawChart(id, list, type){

    var types=new Array();

    var min_salaries=new Array();
    var avg_salaries=new Array();
    var max_salaries=new Array();
    //for(var i = 0; i < list.length; i++){
    for(var i = list.length - 1; i >= 0; i--){
        switch (type){
            case "place":
                types.push(list[i].job_place);
                break;
            case "xueli":
                types.push(list[i].xueli);
                break;
            case "experience":
                types.push(list[i].experience);
                break;
            case "company":
                types.push(list[i].company_prop);
                break;
            default:
                break;
        }
        console.log(types);
        //places.push(list[i].job_place);

        min_salaries.push(parseInt(list[i].min_salary));
        avg_salaries.push(parseInt(list[i].avg_salary));
        max_salaries.push(parseInt(list[i].top_salary));
        // min_salaries.push(22);
        //avg_salaries.push(3600);
        //max_salaries.push(9000);
    }

    console.log(types);

    var myChart = echarts.init(document.getElementById(id));
    myChart.setOption({
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            //最上面的显示导航
            data: ['最低工资', '平均工资','最高工资']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            data:  types
        },
        series: [
            {
                name: '最低工资',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: min_salaries
            },
            {
                name: '平均工资',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: avg_salaries
            },
            {
                name: '最高工资',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: max_salaries
            }
        ]
    });
    return true;
}
