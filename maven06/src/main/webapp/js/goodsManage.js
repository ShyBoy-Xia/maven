var activity = [];
var currentPage = 1;
$(document).ready(function () {

    var path = $("#path").text();


    to_page(path, 1);

});


$(document).on("click",".description",function () {
    $(this).popover();
});

$(document).on("click",".templatemo-edit-btn",function () {
    $("#update-goods").modal({
        backdrop:'static'
    });

    //获取当前点击商品的数据
    var upGoodsid = $(this).parents("tr").find("td:eq(0)").text();
    var upGoodsname = $(this).parents("tr").find("td:eq(1)").text();
    var upGoodsPrice = $(this).parents("tr").find("td:eq(2)").text();
    var upGoodsNum = $(this).parents("tr").find("td:eq(3)").text();
    var upGoodsCate1 = $(this).parents("tr").find("td:eq(4)").text();
	var upGoodsCate2 = $(this).parents("tr").find("td:eq(5)").text();
    var upGoodsDes = $(this).parents("tr").find("td:eq(6)").text();
      

    $("#goodsid").text(upGoodsid);
    $("#goodsname").val(upGoodsname);
    $("#price").val(upGoodsPrice);
    $("#num").val(upGoodsNum);
    $("#category").val(upGoodsCate1);
	$("#category2").val(upGoodsCate2);
    $("#description").val(upGoodsDes);
});

//修改商品信息
$(document).on("click","#saveUpdate",function () {
    var ugoodsid = $("#goodsid").text();
    var ugoodsname = $("#goodsname").val();
    var uprice = $("#price").val();
    var unum = $("#num").val();
    var udescription = $("#description").val();
    var ucategory = $("#category").find("option:selected").val();
    var ucategory2 = $("#category2").find("option:selected").val();

    $.ajax({
        url:"/maven06/admin/goods/update/",
        type:"POST",
        data:{
            goodsid:ugoodsid,
            goodsname:ugoodsname,
            price:uprice,
            num:unum,
            description:udescription,
            category:ucategory,
            category2:ucategory2,
        },
        success:function(result){
            $("#update-goods").modal('hide');
            swal(result.msg,'','success');
            to_page('/maven06',currentPage);
        },
        error:function(){
            alert("错误！！");
        }
    });

    /*var goodsid = $("#goodsid").text();
    var updateForm = new FormData(document.getElementById("update-goods"));
    $.ajax({
        url:"/shop/admin/goods/update/" + goodsid,
        type:"post",
        data:updateForm,
        processData:false,
        contentType:false,
        success:function(result){
            swal(result.msg,'','success');
        },
        error:function(){
            alert("错误！！");
            window.clearInterval(timer);
        }
    });*/
});

$(document).on("click",".templatemo-delete-btn",function () {
    var goodsname = $(this).parents("tr").find("td:eq(1)").text();
    var goodsid = $(this).parents("tr").find("td:eq(0)").text();
    swal({
            title: "确定删除" + goodsname + "吗？",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定删除！",
            closeOnConfirm: false,
        },
        function () {
            /*swal("删除！", "你的虚拟文件已经被删除。", "success");*/
            $.ajax({
                url: "/maven06/admin/goods/delete/" + goodsid,
                type: "DELETE",
                success:function (result) {
                    swal(result.msg, "","success");
                    to_page('/maven06',currentPage);
                },
                error:function () {
                    to_page('/maven06',currentPage);
                }
            });
        });
});


function showActInfo(activityId) {
    $('#activityname').text(activity[activityId-1].activityname);
    $('#activitydes').text(activity[activityId-1].activitydes);
    $('#discount').text(activity[activityId-1].discount);
    $('#fullprice').text(activity[activityId-1].fullprice);
    $('#reduceprice').text(activity[activityId-1].reduceprice);
    $('#fullnum').text(activity[activityId-1].fullnum);
    $('#reducenum').text(activity[activityId-1].reducenum);
}

$("#activity-id").change(function () {
    showActInfo($(this).val());
});



function to_page(path, page) {
    $.ajax({
        url: path + "/maven06/admin/goods/showjson",
        data: "page=" + page,
        type: "get",
        success: function (result) {

            //解析显示
            build_goods_table(path, result);

            //页面信息
            build_page_info(path, result);

            //分页
            build_page_nav(path, result);

            currentPage = page;
        }
    });
}


function build_page_info(path,result) {
    $("#page-info-area").empty();
    $("#page-info-area").append("当前第"+ result.info.pageInfo.pageNum +"页，总共"+ result.info.pageInfo.pages +"页，总共"+ result.info.pageInfo.total +"记录")
}

function build_page_nav(path,result) {
    $("#page-div-nav ul").empty();
    var pageUl = $("<ul></ul>").addClass("pagination")

    var firstPage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"></span>")
            .append("首页")));

    var prePage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"><i class=\"fa fa-backward\"></i></span>")));

    if(!result.info.pageInfo.hasPreviousPage) {
        prePage.addClass("li-none");
    } else {
        prePage.click(function () {
            to_page('/shop',result.info.pageInfo.prePage);
        });
    }

    //跳转
    firstPage.click(function () {
        to_page('/shop',1);
    });

    var nextPage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"><i class=\"fa fa-forward\"></i></span>")));

    var lastPage = $("<li></li>").append($("<a aria-label=\"Next\"></a>")
        .append($("<span aria-hidden=\"true\"></span>")
            .append("末页")));

    if(!result.info.pageInfo.hasNextPage) {
        nextPage.addClass("li-none");
    } else {
        nextPage.click(function () {
            to_page('/shop',result.info.pageInfo.nextPage);
        });
    }

    lastPage.click(function () {
        to_page('/shop',result.info.pageInfo.lastPage);
    });

    pageUl.append(firstPage).append(prePage);

    $.each(result.info.pageInfo.navigatepageNums,function (index,item) {
        var numLi = $("<li></li>").append($("<a></a>")
            .append($("<span aria-hidden=\"true\"></span>").append(item)));
        if(result.info.pageInfo.pageNum === item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page('/shop',item);
        });
        pageUl.append(numLi);
    });

    pageUl.append(nextPage).append(lastPage).appendTo("#page-div-nav");
}

