$(document).ready(function(){
    $("#username").text($("nameVal").text());
	//$("#telephone").text($("#telephoneVal").text());
    $("#year").val($("#year").text());
    $("#month").val($("#month").text());
	$("#date").val($("#date").text());
    $("#changeInfo").click(function(){
        $("#update-info").modal({
            backdrop:'static'
        });
    });



    $("#saveInfo").click(function (){
        var saveInfo={};
        saveInfo.username=$("#username").val();
        saveInfo.year=$("#year").val();
        saveInfo.month=$("#month").val();
		saveInfo.date=$("#date").val();
		//saveInfo.telephone=$("#telephone").val();
        $.ajax({
            type: "POST",
            url: "/maven06/saveInfo",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:saveInfo,
            dateType:"json",
            success: function(result){
                if (result.msg=="更新失败")
                {
                    swal(result.msg);
                }
                else {
                    $("#update-info").modal('hide');
                    swal("修改成功", "", "success");
                    $("button").click(function (){
                        location.reload();
                    });
                }
            },
            error:function (){
                alert("更新失败");
            }
        });
    });

  

});


