<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <title>Insert title here</title>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>

</head>
<body>
    <b>Being Java Guys | Registration Form </b>
    <script>
        function doAjaxPost() {


            var name = $('#name').val();
            var gender = $('#gender').val();
            var email = $('#email').val();
            var phone = $('#phone').val();
            var city = $('#city').val();

            $.ajax({
                type : "Post",
                url : "adduser.action",
                data : "name=" + name +  "&city=" + city,
                success : function(data) {
                    var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
                    //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来

                    $("#s_name").text(""+d.name+"");
                    $("#s_age").text(""+d.age+"");
                    $("#s_position").text(""+d.position+"");
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            });
        }
    </script>
    <div id="form">
        <form method="get">
            <table>
                <tr>
                    <td>Name :</td>
                    <td><input type="text" id="name" /></td>
                </tr>
                <tr>
                    <td>Gender :</td>
                    <td><input type="radio" id="gender" value="male" /> Male

                        <input type="radio" id="gender" value="female" /> Female</td>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td><input type="text" id="email" /></td>
                </tr>
                <tr>
                    <td>Phone :</td>
                    <td><input type="text" id="phone" /></td>
                </tr>
                <tr>
                    <td>City :</td>
                    <td><select id="city"><option value="noida">Noida</option>
                        <option value="delhi">Delhi</option>
                        <option value="gurgaon">Gurgaon</option>
                        <option value="others">Others</option>
                    </select></td>
                </tr>
                <tr>
                    <td> </td>
                    <td><input type="button" value="Save" onclick="doAjaxPost();" />
                    </td>
                </tr>
            </table>
        </form>

    </div>


    <h5>显示结果</h5>
    <br />
    <ul>
        <li>姓名：<span id="s_name">赞无数据</span></li>
        <li class="li_layout">年龄：<span id="s_age">暂无数据</span></li>
        <li class="li_layout">职务：<span id="s_position">暂无数据</span></li>
    </ul>
</body>
</html>
