$(document).ready(function (){
    var cateid={};
    var catesecid={};

   $("[name='changCate']") .click(function (){
       $("#update-cate").modal({
           backdrop:'static'
       });
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       $("#categorySecName").val();
	   cateid=$(this).parent().prev().prev().children().attr("cateId");
       catesecid=$(this).parent().prev().children().attr("cateSecId");
   });

   $("#saveCatename").click(function (){
       var categorySec={};
	   categorySec.catesecid=catesecid;
       categorySec.cateid=cateid;
       categorySec.catesecname=$("#cateSecName").val($(this).parent().prev().children().text());
       $.ajax({
           type:"POST",
           url:"/maven06/admin/goods/saveCate2",
           contentType:"application/x-www-form-urlencoded; charset=utf-8",
           data:categorySec,
           dataType:"json",
           success:function (result){
               if (result.msg=="名字已经存在")
               {
                   swal(result.msg);
               }
               else {
                   swal(result.msg);
                   $("button").click(function (){
                       location.reload();
                   });
               }
           },
           error:function (){
               alert("更新失败");
           }
       });
   })
    $("[name='deleteCate']").click(function (){
        var categorySec={};
        categorySec.catesecid=$(this).parent().prev().prev().children().attr("cateSecId");
        $.ajax({
            type:"POST",
            url:"/maven06/admin/goods/deleteCate2",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:categorySec,
            dataType:"json",
            success:function (result){
                swal(result.msg);
                $("button").click(function (){
                    location.reload();
                });
            },
            error:function (){
                alert("更新失败");
            }
        });
    })
});