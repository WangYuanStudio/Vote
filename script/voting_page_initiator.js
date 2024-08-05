$(document).ready(function(){
	url=window.location.href;
parameter=url.split("/");
parameter=parameter[parameter.length-1].split("?");
tid=parameter[1].split('&')[0].split('=')[1];
url="https://vote.zeffee.com:8443/getThemeDetail/"+tid;
parameter[0]="none";
var votes_per_user;
function check (){
	$.ajax({
	   url: "https://vote.zeffee.com:8443/checkVoted/"+tid,
	   xhrFields: {
	      withCredentials: true
	   },
	   success: function(data){
		if(data.status==500)
		{
			document.getElementById("voting_button_1").style.backgroundColor="#0569a4";
			document.getElementById("voting_button_1").style.color="#c0bebf";
			document.getElementById("voting_button_1").innerHTML="已参与投票";
			alert("您已投票！");			
			window.location.href="/tpjg.html?tid="+tid; 				
		}
		else
		{
			$("#voting_button_1").on("touchstart",function(){
				document.getElementById("voting_button_1").style.backgroundColor="#0569a4";
			});
			$("#voting_button_1").on("touchend",function(){
				document.getElementById("voting_button_1").style.backgroundColor="#0685cc";
			});
			$("#voting_button_1").on("click",function(){
				if(!GetJsonData().oid.length)
				{
					alert("你还没选择任何选项");
					return;
				}
				var con=confirm("你确认要投票吗?"); 
				if(con==true)
				{
					$.ajax({
					        type: "POST",
					        url: "https://vote.zeffee.com:8443/takeVote",
					        contentType: "application/json; charset=utf-8",
					        xhrFields: {
					           withCredentials: true
					        },
					        data: JSON.stringify(GetJsonData()),
					        dataType: "json",
					        success: function (message) {
					        	console.log(JSON.stringify(GetJsonData()));
					        	window.location.href = "tpjg.html?tid="+tid;
					        },
					        error: function (message) {
					            alert(message);
					        }
					});
				}
				
			});
		}
	}
	});
}

function GetJsonData() {
    oid=new Array();
    num_oid=0;
    while(num_oid<$("#voting_select .select_son_s").length)
    {
    	oid[num_oid]=parseInt($("#voting_select .select_son_s").eq(num_oid).attr("oid"));
    	num_oid++;
    }
    var json = {
        "tid": parseInt(tid),
        "oid":oid
    };
    return json;
}

$.ajax({
   url: url,
   xhrFields: {
      withCredentials: true
   },
   success: function(data){
	data = JSON.stringify(data);
	var data = eval("("+data+")");
	if(!data)
	{
		alert('获取投票信息失败')
		return;
	}
	if(data.data.counts)
	{
		document.getElementById("voting_button_3").style.backgroundColor="#ba3a3b";
		document.getElementById("voting_button_2").style.color="#c0c0c0";
		document.getElementById("voting_button_3").style.color="#fffeff";
	}
	else
	{
		$("#voting_button_2").on("touchstart",function(){
			document.getElementById("voting_button_2").style.backgroundColor="#bebebe";
		});
		$("#voting_button_2").on("touchend",function(){
			document.getElementById("voting_button_2").style.backgroundColor="";
		});
		$('#voting_button_2').on('click',function(event) {
			window.location.href = "/changeVote.html?tid="+tid;
		});
		$("#voting_button_3").on("touchstart",function(){
			document.getElementById("voting_button_3").style.backgroundColor="#ba3a3b";
		});
		$("#voting_button_3").on("touchend",function(){
			document.getElementById("voting_button_3").style.backgroundColor="#e84848";
		});
		$("#voting_button_3").on("click",function(){
			var con=confirm("你确认要删除吗?"); 
			if(con==true)
			{
				$.ajax({
				        type: "DELETE",
				        url: "https://vote.zeffee.com:8443/deleteTheme/"+tid,
				        contentType: "application/json; charset=utf-8",
				        xhrFields: {
				           withCredentials: true
				        },
				        dataType: "json",
				        success: function (message) {
				        	alert("删除成功");
						window.location.href = "index.html";
				        },
				        error: function (message) {
				            alert("删除失败");
				        }
				    });
			}
		});
	}
	document.getElementById("title").setAttribute("tid",data.data.tid);
	document.getElementById("main").innerHTML=data.data.description;
	document.getElementById("end_time").innerHTML=data.data.end_time;
	document.getElementById("explain").innerHTML="本次投票信息将被公开";
	if(data.data.anonymous)
	{
		document.getElementById("explain").innerHTML="本次投票信息将不被公开";
	}
	img_src="img/radio1.svg";
	votes_per_user=data.data.votes_per_user;
	if(data.data.votes_per_user>1)
	{
		img_src="img/multiselect1.svg";
	}
	options=0;
	while(data.data.options[options]!=undefined)
	{
		allevent=document.getElementById("voting_select");
		newevent=document.createElement("div");
		newevent.setAttribute("class", "select_son");
		newevent.setAttribute("oid", data.data.options[options]["oid"]);
		newevent.innerHTML="<img src="+img_src+"><span>"+data.data.options[options]["content"]+"</span>";
		allevent.appendChild(newevent);
		options++;
	}
	if(new Date(Date.parse(data.data.start_time.replace(/-/g,  "/")))>new Date()){
		document.getElementById("title").innerHTML=data.data.title+"<span id='state'>(未开始)</span>";
		document.getElementById('voting_button_1').innerHTML = "投票未开始"
		document.getElementById('voting_button_1').style.backgroundColor="#888"
	}
	else{
		if(new Date(Date.parse(data.data.end_time.replace(/-/g,  "/")))<new Date()){
			alert('投票已结束');
			location.href = '/tpjg.html?tid='+tid
			return 
		}
		check()
		document.getElementById("title").innerHTML=data.data.title+"<span id='state'>(进行中)</span>";
	}
	$("#voting_select .select_son").click(function(){
		if($(this).children('img').attr("src").match("radio"))
		{
			if($(this).children('img').attr("src").match("1"))
			{
				$("#voting_select img").attr("src","img/radio1.svg");
				//$("#voting_select").attr("class","select_son");
				$(this).children('img').attr("src","img/radio2.svg");
				$(this).attr("class","select_son_s");
			}
			else
			{
				$(this).children('img').attr("src","img/radio1.svg")
				$(this).attr("class","select_son");
			}
		}
		else
		{
			if($(this).children('img').attr("src").match("1"))
			{
				
				if($("#voting_select .select_son_s").length==votes_per_user)
				{
					return;
				}
				$(this).children('img').attr("src","img/multiselect2.svg");
				$(this).attr("class","select_son_s");
			}
			else
			{
				$(this).children('img').attr("src","img/multiselect1.svg")
				$(this).attr("class","select_son");
			}
		}
		
	});
}
 });

})