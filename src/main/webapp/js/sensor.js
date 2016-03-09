// 路径配置
require.config({
    paths: {
        echarts: 'dist'
    }
});

// 使用 简单查询
function doAjaxdemo() {
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
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main'));
            var datefield = $('#datefield').datebox('getValue');
            var dategap = $('#dategap').val();
            var sensorno = $('#sensorno').val();
           // console.info('sensorno:' + sensorno + ',datefield:' + datefield + ',dategap=' + dategap);
            if (datefield == '' || dategap == '') {
                alert('有输入项为空！');
                return;
            }
            $.ajax({
                    type: 'post',
                    url: 'querySensorAction.action',
                    data: "datefield=" + datefield + "&dategap=" + dategap + "&sensorno=" + sensorno,
                    success: function (res) {
                        // 将数据变成echarts接手的option
                        var ores = eval("(" + res + ")");
                        var option = optionFactory(ores);
                        // myChart.hedeLoading();
                        // 为echarts对象加载数据
                        myChart.setOption(option);
                    },
                    error: function (e) {
                        var error = eval("(" + e + ")");
                        if (error.error == undefined) {
                            alert('服务器运行缓慢，请稍后刷新！');
                        } else {
                            alert('服务器运行缓慢，请稍后刷新');
                        }

                    }
                }
            );
        }
    )
}

function optionFactory(res) {
    var metaSum = res.metaSum;
    var dataSum = res.dataSum;
    //if(dataSum == 0){
    //    return defaultOption(res);
    //}
    var legend_data = {};
    var yAxis_data = [];
    var series_data = [];
    if (metaSum == '1') {
        legend_data = {"data": res.unit0name};
        yAxis_data = [{
            type: 'value',
            name: res.unit0name,
            axisLabel: {
                formatter: '{value} ' + res.unit0abbr
            }
        }];
        if (dataSum == 0) {
            series_data = [
                {
                    name: res.unit0name,
                    type: 'line',
                    data: '-'
                }];
        } else {
            var result0 = res.result0.split(",");
            series_data = [
                {
                    name: res.unit0name,
                    type: 'line',
                    symbol:'emptyCircle',
                    symbolSize:1,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                width: 1,
                                type:'dotted',
                                shadowColor : 'rgba(255,165,0,0.6)',
                                shadowBlur: 1000
                            }
                        }

                    },
                    data: result0,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }];
        }

    } else if (metaSum == '2') {
        legend_data = {"data": [res.unit0name, res.unit1name]};
        yAxis_data = [{
            type: 'value',
            name: res.unit0name,
            axisLabel: {
                formatter: '{value} ' + res.unit0abbr
            }
        }, {
            type: 'value',
            name: res.unit1name,
            axisLabel: {
                formatter: '{value} ' + res.unit1abbr
            }

        }
        ];
        if (dataSum == 0) {
            series_data = [
                {
                    name: res.unit0name,
                    type: 'line',
                    data: '-'
                }, {
                    name: res.unit1name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: '-'
                }
            ];
        } else {

            var result0 = res.result0.split(",");
            var result1 = res.result1.split(",");
            series_data = [
                {
                    name: res.unit0name,
                    type: 'line',
                    symbol:'emptyCircle',
                    symbolSize:1,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                width: 2,
                                color: 'red',
                                type:'dotted',
                                shadowColor : 'rgba(255,165,0,0.6)',
                                shadowBlur: 1000
                            }
                        }
                    },
                    data: result0,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }, {
                    name: res.unit1name,
                    type: 'line',
                    symbol:'emptyCircle',
                    symbolSize:1,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                width: 2,
                                color: '#30e0e0',
                                type:'dotted',
                                shadowColor : 'rgba(255,165,0,0.6)',
                                shadowBlur: 1000
                            }
                        }

                    },
                    yAxisIndex: 1,
                    data: result1,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }
            ];
        }

    } else if (metaSum == '3') {
        legend_data = {"data": [res.unit1name, res.unit0name, res.unit2name]};
        yAxis_data = [{
            type: 'value',
            name: res.unit1name,
            axisLabel: {
                formatter: '{value} ' + res.unit1abbr
            }
        }, {
            type: 'value',
            name: res.unit0name,
            axisLabel: {
                formatter: '{value} ' + res.unit0abbr
            }
        }, {
            type: 'value',
            name: res.unit2name,
            axisLabel: {
                formatter: '{value} ' + res.unit2abbr
            }
        }
        ];
        if (dataSum == 0) {
            series_data = [
                {
                    name: res.unit1name,
                    type: 'line',
                    data: '-'
                }, {
                    name: res.unit0name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: '-'
                }, {
                    name: res.unit2name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: '-'
                }
            ];
        } else {
            var result0 = res.result0.split(",");
            var result1 = res.result1.split(",");
            var result2 = res.result2.split(",");
            series_data = [
                {
                    name: res.unit1name,
                    type: 'line',
                    symbol:'triangle',
                    symbolSize:1,
                    itemStyle: {
                        normal: {
                            color: 'red',
                            lineStyle: {        // 系列级个性化折线样式
                                width: 2,
                                type: 'dashed'
                            }
                        }
                    },
                    data: result1,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }, {
                    name: res.unit0name,
                    type: 'line',
                    symbol:'emptyDiamond',
                    symbolSize:1,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                width: 2,
                                color: '#ff6347',
                                type:'dotted',
                                shadowColor : 'rgba(255,165,0,0.6)',
                                shadowBlur: 1000
                            }
                        }

                    },
                    yAxisIndex: 1,
                    data: result0,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }, {
                    name: res.unit2name,
                    type: 'line',
                    symbol:'emptyCircle',
                    symbolSize:1,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                width: 2,
                                color: '#30e0e0',
                                type:'dotted',
                                shadowColor : 'rgba(255,165,0,0.6)',
                                shadowBlur: 1000
                            }
                        }

                    },
                    yAxisIndex: 1,
                    data: result2,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }
            ];
        }

    } else if (metaSum == '4') {
        legend_data = {"data": [res.unit0name, res.unit1name, res.unit2name, res.unit3name]};
        yAxis_data = [{
            type: 'value',
            name: res.unit0name,
            axisLabel: {
                formatter: '{value} ' + res.unit0abbr
            }
        }, {
            type: 'value',
            name: res.unit1name,
            axisLabel: {
                formatter: '{value} ' + res.unit1abbr
            }
        }, {
            type: 'value',
            name: res.unit2name,
            axisLabel: {
                formatter: '{value} ' + res.unit2abbr
            }
        }, {
            type: 'value',
            name: res.unit3name,
            axisLabel: {
                formatter: '{value} ' + res.unit3abbr
            }
        }
        ];
        if (dataSum == 0) {
            series_data = [
                {
                    name: res.unit0name,
                    type: 'line',
                    data: '-'
                }, {
                    name: res.unit1name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: '-'
                }, {
                    name: res.unit2name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: '-'
                }, {
                    name: res.unit3name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: '-'
                }
            ];
        } else {
            var result0 = res.result0.split(",");
            var result1 = res.result1.split(",");
            var result2 = res.result2.split(",");
            var result3 = res.result3.split(",");
            series_data = [
                {
                    name: res.unit0name,
                    type: 'line',
                    data: result0
                }, {
                    name: res.unit1name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: result1
                }, {
                    name: res.unit2name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: result2
                }, {
                    name: res.unit3name,
                    type: 'line',
                    yAxisIndex: 1,
                    data: result3
                }
            ];
        }
    } else {
        return;
    }

    var shafts = res.shaft.split(",");

    var option = {
        tooltip: {
            trigger: 'axis'
        },

        calculable: true,
        legend: legend_data,
        xAxis: [
            {
                type: 'category',
                data: shafts
            }
        ],
        yAxis: yAxis_data,
        series: series_data
    };
    return option;
};

function defaultOption() {
    var defalutOp = {
        tooltip: {
            trigger: 'axis'
        },

        calculable: true,
        legend: {
            data: ['-']
        },
        xAxis: [
            {
                type: 'category',
                data: ['-']
            }
        ],
        yAxis: [{
            type: 'value'
        }],
        series: [{
            name: '-',
            type: 'line',
            data: ['-']
        }]
    };

    return defalutOp;
}
$.extend($.fn.validatebox.defaults.rules, {
    md: {
        validator: function (value, param) {
            var d1 = $.fn.datebox.defaults.parser(param[0]);
            var d2 = $.fn.datebox.defaults.parser(value);
            return d2 <= d1;
        },
        message: 'The date must be less than or equals to {0}.'
    }
})
function myformatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
        return new Date(y, m - 1, d);
    } else {
        return new Date();
    }
}

/**
 * 日期转换
 */
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth()),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}
/**
 * 解析输入的dateStr，返回Date类型。
 * dateStr: XXXX-XX-XX
 */
function addDate(dateStr, daysgap) {
    var strArray = dateStr.split("-");
    if (strArray.length == 3) {
        var dateadd = new Date(strArray[0], strArray[1], strArray[2]);
        dateadd.setDate(dateadd.getDate() + daysgap);
        return dateadd;
    } else {
        return new Date();
    }
}
function deleteDate(dateStr, daysgap) {
    var strArray = dateStr.split("-");
    if (strArray.length == 3) {
        var datedele = new Date(strArray[0], strArray[1], strArray[2]);
        datedele.setDate(datedele.getDate() - daysgap);
        return datedele;
    } else {
        return new Date();
    }
}
/**
 * 向前查询
 */
function doQueryPrev() {

    $("#main1").remove();
    $("#main2").remove();
    $("#main3").remove();

    var datebefore = $('#datefield').datebox('getValue');	// get datebox value
    if (datebefore == '') {
        datebefore = formatDate(new Date());
    }

    var gap;
    var datesgap = $('#dategap').val();
    if (datesgap == 'month') {
        gap = 30;
    } else if (datesgap == 'week') {
        gap = 7;
    } else {
        gap = 1;
    }
    var datePrev = formatDate(deleteDate(datebefore, gap));
    $('#datefield').datebox('setValue', datePrev);	// set datebox value
    //var vprev = $('#datefield').datebox('getValue');	// get datebox value
    //console.info('prev:'+vprev);
    doAjaxdemo();
}
/**
 * 向后查询
 */
function doQueryNext() {

    $("#main1").remove();
    $("#main2").remove();
    $("#main3").remove();

    var datebefore = $('#datefield').datebox('getValue');	// get datebox value
    if (datebefore == '') {
        datebefore = formatDate(new Date());
    }
    var gap;
    var datesgap = $('#dategap').val();
    if (datesgap == 'month') {
        gap = 30;
    } else if (datesgap == 'week') {
        gap = 7;
    } else {
        gap = 1;
    }
    var deteNext = formatDate(addDate(datebefore, gap));
    $('#datefield').datebox('setValue', deteNext);	// set datebox value
    //var vnext = $('#datefield').datebox('getValue');	// get datebox value
    //console.info('next:'+vnext);
    doAjaxdemo();
}
/**
 * 查询初始数据
 */
function doAjaxHead() {
    $("#main1").remove();
    $("#main2").remove();
    $("#main3").remove();

    var datefield = $('#datefield').datebox('getValue');
    var dategap = $('#dategap').val();
    var sensorno = $('#sensorno').val();
    //console.info('sensorno:' + sensorno + ',datefield:' + datefield + ',dategap=' + dategap);
    if (sensorno == '' || dategap == '') {
        alert('有输入项为空！');
        return;
    }
    $.ajax({
            type: 'post',
            url: 'headSensorAction.action',
            data: "datefield=" + datefield + "&dategap=" + dategap + "&sensorno=" + sensorno,
            success: function (res) {
                var ores = eval("(" + res + ")");
                //console.info('headTime :' + ores.headDate);
                $('#datefield').datebox('setValue', ores.headDate);	// set datebox value
                doAjaxdemo();
            },
            error: function (e) {
                var error = eval("(" + e + ")");
                if (error.error == undefined) {
                    alert('服务器运行缓慢，请稍后刷新！');
                } else {
                    alert('服务器运行缓慢，请稍后刷新');
                }
            }
        }
    );
}
/**
 * 查询最新数据
 */
function doAjaxTail() {
    $("#main1").remove();
    $("#main2").remove();
    $("#main3").remove();

    var datefield = $('#datefield').datebox('getValue');
    var dategap = $('#dategap').val();
    var sensorno = $('#sensorno').val();
    //console.info('sensorno:' + sensorno + ',datefield:' + datefield + ',dategap=' + dategap);
    if (sensorno == '' || dategap == '') {
        alert('有输入项为空！');
        return;
    }
    $.ajax({
            type: 'post',
            url: 'tailSensorAction.action',
            data: "datefield=" + datefield + "&dategap=" + dategap + "&sensorno=" + sensorno,
            success: function (res) {
                var ores = eval("(" + res + ")");
                //console.info('tailDate :' + ores.tailDate);
                $('#datefield').datebox('setValue', ores.tailDate);	// set datebox value
                doAjaxdemo();
            },
            error: function (e) {
                var error = eval("(" + e + ")");
                if (error.error == undefined) {
                    alert('服务器运行缓慢，请稍后刷新！' );
                } else {
                    alert('服务器运行缓慢，请稍后刷新');
                }
            }
        }
    );
}


