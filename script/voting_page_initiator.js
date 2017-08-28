url=window.location.href;
parameter=url.split("/");
parameter=parameter[parameter.length-1].split("#");
tid=parameter[0];
url="https://vote.zeffee.com:8443/getThemeDetail/"+tid;
parameter[0]="none";
var votes_per_user;
$.get(url,function(data){
	data = JSON.stringify(data);
	var data = eval("("+data+")");
	if(!data)
	{
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
	if(data.data.start_time>new Date())
	{
		document.getElementById("title").innerHTML=data.data.title+"<span id='state'>(未开始)</span>";
	}
	else
	{
		document.getElementById("title").innerHTML=data.data.title+"<span id='state'>(进行中)</span>";
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
	$("#voting_select .select_son").click(function(){
		if($(this).children('img').attr("src").match("radio"))
		{
			if($(this).children('img').attr("src").match("1"))
			{
				$("#voting_select img").attr("src","img/radio1.svg");
				$("#voting_select").attr("class","select_son");
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
 });
$.get("https://vote.zeffee.com:8443/checkVoted/"+tid,function(data){
		if(data.status==500)
		{
			document.getElementById("voting_button_1").style.backgroundColor="#0569a4";
			document.getElementById("voting_button_1").style.color="#c0bebf";
			document.getElementById("voting_button_1").innerHTML="Have voted already";
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
					        data: JSON.stringify(GetJsonData()),
					        dataType: "json",
					        success: function (message) {
					        	window.location.href = "tpjg.html";
					        },
					        error: function (message) {
					            alert(messagr);
					        }
					});
				}
				
			});
		}
	});
function GetJsonData() {
    oid=new Array();
    num_oid=0;
    while(num_oid<$("#voting_select .select_son_s").length)
    {
    	oid[num_oid]=$("#voting_select .select_son_s").eq(num_oid).attr("oid");
    	num_oid++;
    }
    var json = {
        "tid": tid,
        "oid":oid
    };
    return json;
}
