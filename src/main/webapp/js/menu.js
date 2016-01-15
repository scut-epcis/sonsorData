/**
 * Created by bryan on 2016/1/14.
 */

$(document).ready(function(){
    // //动态菜单数据
    var treeData = [
        {
            text: "主目录",
            children: []
        }
    ];

    $.ajax({
            type:'post',
            url:'getMenuSensorAction.action',
            data : "datefield=" +  "&dategap="  + "&sensorno=" ,
            success:function(res){
                // 将数据变成echarts接手的option
                var ores = eval ("(" + res + ")");
                treeData=optionMenu(ores);

                //实例化树形菜单
                $("#tree").tree({
                    data: treeData,
                    lines: true,
                    onClick: function (node) {
                        if (node.attributes) {
                            Open(node.text, node.attributes.url,node.attributes.key);
                        }
                    }
                });

                //绑定tabs的右键菜单
                $("#tabs").tabs({
                    onContextMenu: function (e, title) {
                        e.preventDefault();
                        $('#tabsMenu').menu('show', {
                            left: e.pageX,
                            top: e.pageY
                        }).data("tabTitle", title);
                    }
                });

                //实例化menu的onClick事件
                $("#tabsMenu").menu({
                    onClick: function (item) {
                        CloseTab(this, item.name);
                    }
                });
            },
            error : function(e) {
                alert('Error: ' + e);
            }
        }

    );


});

$(function () {

 //console.info("fa");
});

//在右边center区域打开菜单，新增tab
function Open(text, url,buttonkey) {
    if ($("#tabs").tabs('exists', text)) {
        $('#tabs').tabs('select', text);
    } else {
        console.info(buttonkey);
        var tabiframename = "iframe_"+ buttonkey + "tab";
        console.info(tabiframename);
        var contents = '<iframe scrolling="auto" frameborder="0" name="'+ tabiframename +'"  style="width:100%;height:100%;"></iframe>';
        $('#tabs').tabs('add', {
            title: text,
            closable: true,
            content: contents,
            fit:true,
            selected:true
        });
        window.open(url+"?id="+buttonkey,tabiframename);
    }
}

//几个关闭事件的实现
function CloseTab(menu, type) {
    var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tabs");

    if (type === "close") {
        tabs.tabs("close", curTabTitle);
        return;
    }

    var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];

    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != curTabTitle && type === "Other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && type === "All") {
            closeTabsTitle.push(opt.title);
        }
    });

    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}

function optionMenu(menus){

    if(menus.firstMenu == undefined){
        return;
    }
    var first = menus.firstMenu.split(',');
    //console.info(first);
    var firstNo = first.length;
    var firChildren = [];
    for(var firIdx = 0;firIdx < first.length;firIdx++){

        var second = {};
        second["text"] = first[firIdx];
        second["state"] = "closed";
        var secChild = [];
        if(firIdx==0){
            var secChildName = menus.second_0name.split(',');
            var secChildKey = menus.second_0key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==1){
            var secChildName = menus.second_1name.split(',');
            var secChildKey = menus.second_1key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==2){
            var secChildName = menus.second_2name.split(',');
            var secChildKey = menus.second_2key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==3){
            var secChildName = menus.second_3name.split(',');
            var secChildKey = menus.second_3key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==4){
            var secChildName = menus.second_4name.split(',');
            var secChildKey = menus.second_4key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==5){
            var secChildName = menus.second_5name.split(',');
            var secChildKey = menus.second_5key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==6){
            var secChildName = menus.second_6name.split(',');
            var secChildKey = menus.second_6key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==7){
            var secChildName = menus.second_7name.split(',');
            var secChildKey = menus.second_7key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==8){
            var secChildName = menus.second_8name.split(',');
            var secChildKey = menus.second_8key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==9){
            var secChildName = menus.second_9name.split(',');
            var secChildKey = menus.second_9key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }
        if(firIdx==10){
            var secChildName = menus.second_10name.split(',');
            var secChildKey = menus.second_10key.split(',');
            for(var secIdx = 0;secIdx < secChildName.length; secIdx ++ ){
                var secondChildren = {};
                secondChildren["text"] = secChildName[secIdx];
                var secChildAttr = {};
                secChildAttr["url"] =  "sensordemo.html";
                secChildAttr["key"] = secChildKey[secIdx];
                secondChildren["attributes"] = secChildAttr;
                secChild.push(secondChildren);
            }
        }


        second["children"] = secChild;
        firChildren.push(second);

    }
    //动态菜单数据
    var treeData = [
        {
            text: "主目录",
            children: firChildren

        }
    ];


    return treeData;
}
