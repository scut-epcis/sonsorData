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

            var name = $('#name').val();
            var gender = $('#gender').val();
            var address = $('#address').val();
            var phone = $('#phone').val();
            var city = $('#city').val();

            $.ajax({
                    type:'post',
                    url:'querySensorAction.action',
                    data : "datefield='2015-10-12'" +  "&dategap='day'"  + "&sensorno='1'" ,
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