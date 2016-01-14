// 路径配置
require.config({
    paths: {
        echarts: '../dist'
    }
});
$.extend($.fn.validatebox.defaults.rules, {
    md: {
        validator: function(value, param){
            var d1 = $.fn.datebox.defaults.parser(param[0]);
            var d2 = $.fn.datebox.defaults.parser(value);
            return d2<=d1;
        },
        message: 'The date must be less than or equals to {0}.'
    }
})
function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}

// 使用

function doAjaxdemo(){
    require(
        [
            'echarts',
            'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
            'echarts/chart/line'
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main'));

            var datefield = $('#datefield').val();
            var dategap = $('#dategap').val();
            var sensorname = $('#sensorname').val();

            $.ajax({
                    type:'post',
                    url:'querySensorAction.action',
                    data : "datefield=" + datefield +  "&dategap=" + dategap + "&sensorname=" + sensorname,
                    success:function(res){
                        // 将数据变成echarts接手的option
                        var ores = eval ("(" + res + ")");
                        console.info(ores.name);
                        console.info(ores.city);

                        var option=optionFactory(ores);
                        // myChart.hedeLoading();
                        // 为echarts对象加载数据
                        myChart.setOption(option);
                    },
                    error : function(e) {
                        alert('Error: ' + e);
                    }
                }

            );
        }
    )


}
function convertonum(myArray){

    for(var i=0; i<myArray.length; i++) {
        myArray[i] = parseInt(myArray[i]);
    }
    return myArray;
}

function optionFactory(res) {
    var names = res.name.split(",");
    var citys = res.city.split(",");
    console.info(names);
    var lala = ['2','3','4','12','45','6','7'];

    var option = {
        tooltip : {
            trigger: 'axis'
        },

        calculable : true,
        legend: {
            data:['湿度','温度']
        },
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月']
            }
        ],
        yAxis : [
            {
                type : 'value',
                name : '湿度',
                axisLabel : {
                    formatter: '{value} ml'
                }
            },
            {
                type : 'value',
                name : '温度',
                axisLabel : {
                    formatter: '{value} °C'
                }
            }
        ],
        series : [
            {
                name:'湿度',
                type:'line',
                data:lala
            },
            {
                name:'温度',
                type:'line',
                yAxisIndex: 1,
                data:names
            }
        ]
    };
    return option;
}