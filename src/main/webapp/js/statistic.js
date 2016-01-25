/**
 * Created by bryan on 2016/1/25.
 */
function doMonthStatictis() {
    $("#main1").remove();
    $("#main2").remove();
    $("#main3").remove();
    require(
        [
            'echarts',
            'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
            'echarts/chart/line',
            'echarts/chart/k'
        ],
        function (ec) {

            var datefield = $('#datefield').datebox('getValue');
            var dategap = $('#dategap').val();
            var sensorno = $('#sensorno').val();
            //console.info('sensorno:' + sensorno + ',datefield:' + datefield + ',dategap=' + dategap);
            if (datefield == '' || dategap == '') {
                alert('有输入项为空！');
                return;
            }
            $.ajax({
                    type: 'post',
                    url: 'statictisSensorAction.action',
                    data: "datefield=" + datefield + "&dategap=" + dategap + "&sensorno=" + sensorno,
                    success: function (res) {
                        // 基于准备好的dom，初始化echarts图表
                        var ores = eval("(" + res + ")");
                        if(ores.channelNo==2){
                           var para =  $("<div  id='main1' style='height:400px' ></div>");
                            $("#main").after(para);
                            var myChart = ec.init(document.getElementById('main'));
                            var myChart1 = ec.init(document.getElementById('main1'));
                            var option = optionStatictisFactory(ores);
                            myChart.setOption(option[0]);
                            myChart1.setOption(option[1]);
                        }
                        else if(ores.channelNo==3){
                            var para1 =  $("<div  id='main1' style='height:400px' ></div>");
                            var para2=  $("<div  id='main2' style='height:400px' ></div>");
                            $("#main").after(para1);
                            $("#main").after(para2);
                            var myChart = ec.init(document.getElementById('main'));
                            var myChart1 = ec.init(document.getElementById('main1'));
                            var myChart2 = ec.init(document.getElementById('main2'));
                            var option = optionStatictisFactory(ores);
                            myChart.setOption(option[0]);
                            myChart1.setOption(option[1]);
                            myChart2.setOption(option[2]);
                        }
                        else if(ores.channelNo==4){
                            var para1 =  $("<div  id='main1' style='height:400px' ></div>");
                            var para2=  $("<div  id='main2' style='height:400px' ></div>");
                            var para3=  $("<div  id='main3' style='height:400px' ></div>");
                            $("#main").after(para1);
                            $("#main").after(para2);
                            $("#main").after(para3)
                            var myChart = ec.init(document.getElementById('main'));
                            var myChart1 = ec.init(document.getElementById('main1'));
                            var myChart2 = ec.init(document.getElementById('main2'));
                            var myChart3 = ec.init(document.getElementById('main3'));
                            var option = optionStatictisFactory(ores);
                            myChart.setOption(option[0]);
                            myChart1.setOption(option[1]);
                            myChart2.setOption(option[2]);
                            myChart3.setOption(option[3]);
                        }else {
                            var myChart = ec.init(document.getElementById('main'));
                            // 将数据变成echarts接手的option
                            var option = optionStatictisFactory(ores);
                            myChart.setOption(option[0]);
                        }


                    },
                    error: function (e) {
                        var error = eval("(" + e + ")");
                        if (error.error == undefined) {
                            alert('Error: ' + e);
                        } else {
                            alert('Error: ' + error.error);
                        }
                    }
                }
            );
        });
}

function optionStatictisFactory(res){

    var optStaS = [];
    //
    var opdata =  [];
    var op1data =  [];
    var op2data =  [];
    var op3data =  [];
    //console.info('记录数目：'+res.statictisNo);
    //console.info('维度：'+res.channelNo);
    if(res.channelNo == 2){
        var serkmax  = res.static_0max.split(',');
        var serkmin  = res.static_0min.split(',');
        var serk1max  = res.static_1max.split(',');
        var serk1min  = res.static_1min.split(',');
        for(var i = 0;i<res.statictisNo;i++){
            opdata.push([serkmin[i],serkmax[i],serkmin[i],serkmax[i]]);
            op1data.push([serk1min[i],serk1max[i],serk1min[i],serk1max[i]]);
        }
    }else if(res.channelNo == 3){
        var serkmax  = res.static_0max.split(',');
        var serkmin  = res.static_0min.split(',');
        var serk1max  = res.static_1max.split(',');
        var serk1min  = res.static_1min.split(',');
        var serk2max  = res.static_2max.split(',');
        var serk2min  = res.static_2min.split(',');
        for(var i = 0;i<res.statictisNo;i++){
            opdata.push([serkmin[i],serkmax[i],serkmin[i],serkmax[i]]);
            op1data.push([serk1min[i],serk1max[i],serk1min[i],serk1max[i]]);
            op2data.push([serk2min[i],serk2max[i],serk2min[i],serk2max[i]]);
        }
    }else if(res.channelNo == 4){
        var serkmax  = res.static_0max.split(',');
        var serkmin  = res.static_0min.split(',');
        var serk1max  = res.static_1max.split(',');
        var serk1min  = res.static_1min.split(',');
        var serk2max  = res.static_2max.split(',');
        var serk2min  = res.static_2min.split(',');
        var serk3max  = res.static_3max.split(',');
        var serk3min  = res.static_3min.split(',');
        for(var i = 0;i<res.statictisNo;i++){
            opdata.push([serkmin[i],serkmax[i],serkmin[i],serkmax[i]]);
            op1data.push([serk1min[i],serk1max[i],serk1min[i],serk1max[i]]);
            op2data.push([serk2min[i],serk2max[i],serk2min[i],serk2max[i]]);
            op3data.push([serk3min[i],serk3max[i],serk3min[i],serk3max[i]]);
        }
    }else{
        var serkmax  = res.static_0max.split(',');
        var serkmin  = res.static_0min.split(',');

        for(var i = 0;i<res.statictisNo;i++){
            opdata.push([serkmin[i],serkmax[i],serkmin[i],serkmax[i]]);
        }
    }

    for(var indexc = 0;indexc<res.channelNo;indexc++){
        if(indexc == 0){
            var yAxis_name = res.unit0name;
            var yAxis_abbr = res.unit0abbr;
        }else if(indexc == 1){
            var yAxis_name = res.unit1name;
            var yAxis_abbr = res.unit1abbr;
        }else if(indexc == 2){
            var yAxis_name = res.unit2name;
            var yAxis_abbr = res.unit2abbr;
        }else if(indexc == 3){
            var yAxis_name = res.unit3name;
            var yAxis_abbr = res.unit3abbr;
        }
        var optionStatictis = {
            tooltip: {
                trigger: 'axis',
                formatter: function (params) {
                    var res = params[0].name;
                    res += '<br/>  最小值 : ' + params[0].value[2] + ' 最大值 : ' + params[0].value[3];
                    res +='<br> 平均值：' +params[1].value;
                    return res;
                }
            },
            legend: {
                data: ['最大值最小值', '平均值']
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    axisTick: {onGap: false},
                    splitLine: {show: false},
                    data: res.statictisDate.split(',')
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name:yAxis_name,
                    scale: true,
                    min:serkmin[0]-3,
                    axisLabel: {
                        formatter: '{value} ' + yAxis_abbr
                    }
                }
            ],
            series: [
                {
                    name: '最大值最小值',
                    type: 'k',
                    itemStyle:{
                        normal:{
                            barBorderColor:'rgba(0,0,0,0)',
                            color:'#ff7f50'
                        }
                    },
                    data: opdata
                },
                {
                    name: '平均值',
                    type: 'line',
                    itemStyle : { normal: {label : {show: true, position: 'top'}}},
                    data: res.static_0avg.split(',')
                }
            ]
        };
        optStaS.push(optionStatictis);

    }

    return optStaS;
}