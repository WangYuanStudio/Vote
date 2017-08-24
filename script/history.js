function GetRTime(){
    var EndTime= new Date('2017/07/29 16:25:00');
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

      document.getElementById("day").innerHTML = d + "天";
      document.getElementById("hour").innerHTML = h + "时";
      document.getElementById("minute").innerHTML = m + "分";
      document.getElementById("second").innerHTML = s + "秒";
    }

    if (t<=0) {
      var time = document.getElementById("time");
      var childs = time.childNodes;
      for (var i = 0; i < childs.length; i++) {
        time.removeChild(childs[i]);
      }
      document.getElementById("time").innerHTML = '<span>已结束</span>';
    }
  }
  setInterval(GetRTime,0);