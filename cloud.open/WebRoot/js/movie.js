$(document).ready(function () {
	$.ajax({ url: "/movie/movie/list/current_page/1/page_size/20", dataType: "json" })
    .done(function (result) {//ajax的done解析result
        $.each(result.content, function (i,item) {
        	$("#t1").append(
                    "<tr>" +
                    	"<td>" + item.id + "</td>" + 
	                    "<td>" + item.movieName+ "</td>" +
	                    "<td>" + item.doubanId + "</td>" +
                    "</tr>");
        });
    });
});