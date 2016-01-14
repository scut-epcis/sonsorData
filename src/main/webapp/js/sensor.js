// 路径配置
require.config({
    paths: {
        echarts: 'dist'
    }
});

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
            var datefield = $('#datefield').datebox('getValue');
            var dategap = $('#dategap').val();
            var sensorno = $('#sensorno').val();
            console.info(sensorno);

            $.ajax({
                    type:'post',
                    url:'querySensorAction.action',
                    data : "datefield=" + datefield +  "&dategap=" + dategap + "&sensorno=" + sensorno,
                    success:function(res){
                        // 将数据变成echarts接手的option
                        var ores = eval ("(" + res + ")");
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

function optionFactory(res) {
    var result1 = res.result1.split(",");
    var result2 = res.result2.split(",");
    var shafts = res.shaft.split(",");


    var option = {
        tooltip : {
            trigger: 'axis'
        },

        calculable : true,
        legend: {
            data:[res.util1name,res.util2name]
        },
        xAxis : [
            {
                type : 'category',
                data : shafts
            }
        ],
        yAxis : [
            {
                type : 'value',
                name : res.util1name,
                axisLabel : {
                    formatter: '{value} '+res.util1
                }
            },
            {
                type : 'value',
                name : res.util2name,
                axisLabel : {
                    formatter: '{value} '+res.util2
                }
            }
        ],
        series : [
            {
                name:res.util1name,
                type:'line',
                data:result1
            },
            {
                name:res.util2name,
                type:'line',
                yAxisIndex: 1,
                data:result2
            }
        ]
    };
    return option;
}
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
