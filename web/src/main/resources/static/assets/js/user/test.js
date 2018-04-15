/**
 * Created by luomouren on 2018-01-28.
 */

function findUserInfoByUsername(){
    var url = "getByUserName";
    // 用户名
    var username=$("#username").val();
    var param={};
    param["username"] = username;
    $.ajax({
        url:url,
        type:"GET",
        dataType:'json',
        data:param,
        success:function(data) {
            console.log("data",data);
            // {"userId":"e5792c05-fa1c-4ffa-9adb-aa66f2cd8863","userName":"bzh","realName":"管理员","userPassword":"25d55ad283aa400af464c76d713c07ad","email":"","cellphone":"","createdTime":"2013-04-03T16:00:00.000+0000","description":null,"deleted":false,"firstLogin":false,"showStartPage":true,"enable":true}
            var returnMsg = '<h4>userId：' + data.userId + '</h4>'
                +'<h4>userName：' + data.userName + '</h4>'
                +'<h4>realName：' + data.realName + '</h4>';
            $("#userInfo").html(returnMsg);
        },
        error:function(e){
            console.log("login error",e)
        }
    });
}