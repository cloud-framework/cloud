$(function(){
	
	$("#text_revert_time_long").val(Date.parse(new Date()));
	$("#btn_revert_time").click(function() { 
        $.ajax( { 
            type : "GET", 
            url : "/help/revert_time/time/"+$("#text_revert_time_long").val()
            	+"/format/"+$("#text_revert_time_format").val(), 
//            data : "name=zhangsan&age=20", 
//            dataType: "text", 
            success : function(msg) { 
            	$("#div_revert_time_string").html(msg); 
            } 
        }); 
    });
});