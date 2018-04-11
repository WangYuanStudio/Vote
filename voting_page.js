$(document).ready(function(){
	url=window.location.href;
parameter=url.split("/");
parameter=parameter[parameter.length-1].split("?");
tid=parameter[1].split('&')[0].split('=')[1];

//获得url中的oid值
var choiceOid=new Array();
var choiceOidindex=0;
var oidtext='';
for (let i = 0; i < parameter[1].split(/[&=]/).length; i++) {
	if(parameter[1].split(/[&=]/)[i]=='oid'){
		oidtext=parameter[1].split(/[&=]/)[i+1];
		// choiceOidindex=choiceOidindex+1;
	}
}
oidtext=oidtext.split('{')[1].split('}')[0];
choiceOid=oidtext.split(',');
function check(){
	$.ajax({
	   url: "https://vote.zeffee.com:8443/checkVoted/"+tid,
	   xhrFields: {
	      withCredentials: true
	   },
	   success:function(data){
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
			$("#voting_button_1").on("touchStart",function(){
				document.getElementById("voting_button_1").style.backgroundColor="#0569a4";
			});
			$("#voting_button_1").on("touchEnd",function(){
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
							alert("投票成功");
					        	window.location.href = "tpjg.html?tid="+tid;
					        },
					        error: function (message) {
					            alert("投票失败\r"+message);
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
    console.log(json);
    return json;
}
function contains(arr, obj) {  
    var i = arr.length;  
    while (i--) {  
        if (arr[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
}  

url="https://vote.zeffee.com:8443/getThemeDetail/"+tid;
var votes_per_user;
$.ajax({
   url: url,
   xhrFields: {
      withCredentials: true
   },
   success:function(data){
	data = JSON.stringify(data);
	data = eval("("+data+")");
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
		// parameter
		if(contains(choiceOid,data.data.options[options]["oid"].toString()))
		{
			// newevent.innerHTML="<img src="+img_src+"><span>"+data.data.options[options]["content"]+"</span><span class='sharer'>("+parameter[1]+"投此项)</span>";
			newevent.innerHTML="<img src="+img_src+"><span>"+data.data.options[options]["content"]+"</span><span class='sharer'>(分享者投此项)</span>";
		}
		else
		{
			newevent.innerHTML="<img src="+img_src+"><span>"+data.data.options[options]["content"]+"</span>";
		}
		allevent.appendChild(newevent);
		options++;
	}
	if(new Date(Date.parse(data.data.start_time.replace(/-/g,  "/")))>new Date())
	{
		$('title').text(data.data.title+"(未开始)|零壹派投票系统");				
		document.getElementById("title").innerHTML=data.data.title+"<span id='state'>(未开始)</span>";
		document.getElementById('voting_button_1').innerHTML = "投票未开始"
		document.getElementById('voting_button_1').style.backgroundColor="#888"
	}
	else
	{
		if(new Date(Date.parse(data.data.end_time.replace(/-/g,  "/")))<new Date()){
			alert('投票已结束');
			location.href = '/tpjg.html?tid='+tid
			return 
		}
		check()
		$('title').text(data.data.title+"(进行中)|零壹派投票系统");		
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
