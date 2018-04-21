function drawPlaceSalaryCountOrderByCount(jobPlaceCountSalaryOrderByCountList) {
    var types=new Array();
    var counts=new Array();
    var salary=new Array();
    for(var i = jobPlaceCountSalaryOrderByCountList.length - 1; i >= 0; i--){
        types.push(jobPlaceCountSalaryOrderByCountList[i].job_place);
        counts.push(jobPlaceCountSalaryOrderByCountList[i].count);
        salary.push(parseInt(jobPlaceCountSalaryOrderByCountList[i].avg_salary))
    }
    var myChart = echarts.init(document.getElementById("planA"));
    myChart.setOption({
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            //最上面的显示导航
            data: ['工作数量', '平均工资']
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
                name: '工作数量',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: counts
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
                data: salary
            }
        ]
    });
}

function drawPlaceSalaryCountOrderBySalary(jobPlaceCountSalaryOrderBySalaryList) {
    var types=new Array();
    var counts=new Array();
    var salary=new Array();
    for(var i = jobPlaceCountSalaryOrderBySalaryList.length - 1; i >= 0; i--){
        types.push(jobPlaceCountSalaryOrderBySalaryList[i].job_place);
        counts.push(jobPlaceCountSalaryOrderBySalaryList[i].count);
        salary.push(parseInt(jobPlaceCountSalaryOrderBySalaryList[i].avg_salary))
    }
    var myChart = echarts.init(document.getElementById("planB"));
    myChart.setOption({
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            //最上面的显示导航
            data: ['工作数量', '平均工资']
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
                name: '平均工资',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: salary
            },
            {
                name: '工作数量',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: counts
            }
        ]
    });
}