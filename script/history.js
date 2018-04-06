function GetRTime(thisEndTime){
    var EndTime= new Date(thisEndTime);//'2017-12-29 16:25:00'
    var NowTime = new Date();
    var t =EndTime.getTime() - NowTime.getTime();
    var d=0;
    var h=0;
    var m=0;
    var s=0;

    if(t>=0){
      d=Math.floor(t/1000/60/60/24);
      h=Math.floor(t/1000/60/60%24);
      m=Math.floor(t/1000/60%60);
      s=Math.floor(t/1000%60);
      if (h<10) {h = "0"+h};
      if (m<10) {m = "0"+m};
      if (s<10) {s = "0"+s};
      return '<span>距离投票结束还有</span><span id="day">'+d+'天</span><span id="hour">'+h+'时</span><span id="minute">'+m+'分</span ><span id="second">'+s+'秒</span>';
    }
    if (t<=0) {
      var time = document.getElementById("time");
      var childs = time.childNodes;
      for (var i = 0; i < childs.length; i++) {
        time.removeChild(childs[i]);
      }
      return '<span>投票已结束</span>';
    }
  }

//显示所有列表
function showAll(){
    //让取消消失
    $('.btn').css('display','none');
    
    //搜索框的内容消失
    $('.search').val(''); 
    $('.items').empty();
    //访问服务器获得所有的历史投票记录
    $.ajax({
       url: "https://vote.zeffee.com:8443/getMyThemeList/",
       xhrFields: {
          withCredentials: true
       },
       success:function(data){
      data = JSON.stringify(data);
      var data = eval("("+data+")");
      if(data.data.length==0){
        window.location.href="/no_history.html";
      };
      for (let i = 0; i < data.data.length; i++) {
        addHistoryItem(data.data[i].tid,data.data[i].photo,data.data[i].title,data.data[i].end_time);
      }
      //遍历添加点击跳转事件
      addJump();
      changeTime();
      setInterval(changeTime,1000);
    }
    });
  }
showAll();

function addHistoryItem(tid,imgSrc,voteItemTitle,endTime){
  var historyItem='<div class="item" tid="'+tid+'"><div class="head-name"><img src="'+imgSrc+'" alt="" class="head"> <span class="name">'+voteItemTitle+'</span></div><div class="voteTid">#'+tid+'</div><div class="ttime">'+endTime+'</div><div class="time" id="time"></div><div class="clear"></div></div>';
  $('.items').prepend(historyItem);
}

//遍历添加点击跳转事件
function addJump(){
  $('.item').on('click',function(e) {
    var tid=parseInt($(this).attr('tid'));
    window.location.href="/voting_page_initiator.html?tid="+tid;
  });
}
//循环改变时间
function changeTime(thisEndTime){
  for (let index = 0; index < $('.item').length; index++) {
    var endTime=GetRTime($('.item').eq(index).find('.ttime').text());
    $('.item').eq(index).find('.time').text('');
    $('.item').eq(index).find('.time').append(endTime);
  }
}

//搜索监听
$(document).ready(function(){
  $('.search').bind('input propertychange',function(e){
  $('.btn').css('display','block');
  $(".btn").unbind("click");
  $('.btn').on('click',function(){
    showAll();
  });
  var searValue=$('.search').val();
  $.ajax({
     url: "https://vote.zeffee.com:8443/getMyThemeList.search?content="+searValue,
     xhrFields: {
        withCredentials: true
     },
     success:function(data){
    data = JSON.stringify(data);
    var data = eval("("+data+")");
    $('.items').empty();
    if(data.data.length==0){
      var historyItem='<div class="item"><div class="head-name"><img src="" alt="" class="head"> <span class="name">无相关搜索结果</span></div><div class="time" id="time" style="display:none;"></div><div class="clear"></div></div>';
      $('.items').prepend(historyItem);  
    }else{
      for (let i = 0; i < data.data.length; i++) {
        addHistoryItem(data.data[i].tid,data.data[i].photo,data.data[i].title,data.data[i].end_time);
      }
      //遍历添加点击跳转事件
      addJump();
      changeTime();
      setInterval(changeTime,1000);
    }
  }
  });  


});

$('.home-container').on('click',function(){
   window.location.href="/";
})
})

