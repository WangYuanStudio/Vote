$('document').ready(function(){


  
url=window.location.href;
parameter=url.split("/");
parameter=parameter[parameter.length-1].split("?");
tid=parameter[1].split('&')[0].split('=')[1];
if (parameter[1].split('&')[1]) {
	if (parameter[1].split('&')[2]) {
		var jupoid=parameter[1].split('&')[2].split('=')[1];
    	var juplpurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3b3c63ee9e929594&redirect_uri=http%3A%2F%2Fwww.zeffee.com%2Fredirect.php&response_type=code&scope=snsapi_userinfo&state=vote-"+tid+'-{'+jupoid+'}'+"#wechat_redirect";
    	window.location.href=juplpurl; 
	}else{
    	var juplpurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3b3c63ee9e929594&redirect_uri=http%3A%2F%2Fwww.zeffee.com%2Fredirect.php&response_type=code&scope=snsapi_userinfo&state=vote-"+tid+"#wechat_redirect";
		window.location.href=juplpurl; 
	}
}
 $.ajax({
   url: "https://vote.zeffee.com:8443/getShareInfo?url="+window.location.href,
   xhrFields: {
      withCredentials: true
   },
   success:function(data){
    data = JSON.stringify(data);
    var data = eval("("+data+")");
    console.log(data);
    wx.config({  
    	debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。  
    	appId: data.appId, // 必填，公众号的唯一标识  
    	timestamp: data.timestamp, // 必填，生成签名的时间戳  
    	nonceStr: data.nonceStr, // 必填，生成签名的随机串  
    	signature: data.signature,// 必填，签名，见附录1  
    	jsApiList: ['onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2  
	});
wx.ready(function(){  
	wx.onMenuShareAppMessage({
		title: '邀请你参与投票《'+$('.tpjg_title_text').text()+'》', // 分享标题
		desc: '点此链接参与', // 分享描述
		link: window.location.href+'&fenxiang=1', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		imgUrl: 'https://vote.zeffee.com/img/create_voting.svg', // 分享图标
		type: '', // 分享类型,music、video或link，不填默认为link
		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		success: function () {
			alert('分享投票成功！');
		},
		cancel: function () {
			alert('您取消了分享。');

// 用户取消分享后执行的回调函数
			}
	});

});  
wx.error(function(res){  
   console.log(res);
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
    var juplpurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3b3c63ee9e929594&redirect_uri=http%3A%2F%2Fwww.zeffee.com%2Fredirect.php&response_type=code&scope=snsapi_userinfo&state=vote-"+tid+"#wechat_redirect";
	window.location.href=juplpurl;  
}); 

}
});
var ytp=new Array();


$.ajax({
   url: "https://vote.zeffee.com:8443/getThemeDetail/"+tid,
   xhrFields: {
      withCredentials: true
   },
   success:function(data){
    data = JSON.stringify(data);
    var data = eval("("+data+")");
    $('.tpjg_title_text').text(data.data.title);
    $('title').text(data.data.title+'的投票结果|零壹派投票系统');
    $('.tgjg_text').text(data.data.description);
    var endData=new Date(Date.parse(data.data.end_time.replace(/-/g,  "/")));
    jcshijian(endData);//检测倒计时
    xhbltj(data.data.options);//添加选项
    changebl();//改变票的显示
    anonymous(data.data.anonymous);//匿名隐藏
    $(".jiezhishijian").text("(投票截止时间："+data.data.end_time+")");
}
});

$.ajax({
   url: "https://vote.zeffee.com:8443/self_record/"+tid,
   xhrFields: {
      withCredentials: true
   },
   success:function(data){
    sdata = JSON.stringify(data);
    var sdata = eval("("+sdata+")");
    ytp=sdata.data;
    changebl();
}
});
var tprm1='';
var tprs1;
var thisAnonymous;
function anonymous(anonymous){
    if(anonymous){
        $('.hmpct').remove();
        thisAnonymous=1;
    }
}
function tprsrm(oid){
    $.ajax({
       url: "https://vote.zeffee.com:8443/option/userList/"+oid,
       xhrFields: {
          withCredentials: true
       },
       success:function(data){
        data = JSON.stringify(data);
        var data = eval("("+data+")");
        if(data.data.length){
            for(var ul=0;ul<data.data.length;ul++){
                tprm1+=data.data[ul]+'、'
            }
            console.log(tprm1)
            tprm1=tprm1.substring(0,tprm1.length-1)
            $('.xuanxiangson[oid="'+oid+'"]').find('.hmpct_word').text(tprm1+' 选了此项');
        }
        else{
            $('.xuanxiangson[oid="'+oid+'"]').find('.hmpct_word').text('0人选了此项');
        }
        tprm1=''
        }
     });
}
    function changebl(){
        
            var totalpiaoshu=0;
            var xianshizhanbiwidth=parseInt($('.xianshizhanbi').css('width'));
            for (var i = 0; i < $('.piaoshuN').length; i++) {
                totalpiaoshu=totalpiaoshu+parseInt($('.piaoshuN').eq(i).text());
            }
            for (var i = 0; i < $('.xianshizhanbi').length; i++) {
                var bilu=$('.piaoshuN').eq(i).text()/totalpiaoshu;
                $('.xianshizhanbi').eq(i).css('border-left-width',xianshizhanbiwidth*bilu+"px");
                $('.xianshizhanbi').eq(i).css('width','calc(100% - '+xianshizhanbiwidth*bilu+"px - 3.8rem)");
                var thisOid=parseInt($('.xuanxiangson').eq(i).attr('oid'));            
                for (var j = 0; j < ytp.length; j++) {
                    if(thisOid==parseInt(ytp[j])){
                        $('.container .vote_text_container .xuanxiangsoncontainer .xuanxiangson .xianshizhanbi').eq(i).css('border-color','#0685cc');
                    }
                }
            }
    }

    changebl();


    function xuangxiang(content,counts,hmpct_word,oid){
        this.content=content;
        this.counts=counts;
        this.hmpct_word=hmpct_word;
        this.oid=oid;
    }//gai

//选项排序
var arr = new Array();
var arrSorted = new Array();
function xxpaixu(){
    if (arr.length==0) {
    for (var i = 0; i <$('.xuanxiangsontitle').length; i++) {
            var xuanxiangall= new xuangxiang($('.xuanxiangsontitle').eq(i).find('.xuanxiangname').text(),parseInt($('.xuanxiangsontitle').eq(i).find('.piaoshuN').text()),$('.xuanxiangsontitle').eq(i).find('.hmpct_word').text(),$('.xuanxiangson').eq(i).attr('oid'));
            arr[i]=xuanxiangall;
        }
    for (var i = 0; i <arr.length; i++) {
        arrSorted[i]=arr[i];
    }
    arrSorted=bubbleSort(arrSorted);
    }
}
    function bubbleSort(arrSorted) {
    var i = arrSorted.length, j;
    var tempExchangVal;
    while (i > 0) {
        for (j = 0; j < i - 1; j++) {
            if (arrSorted[j].counts < arrSorted[j + 1].counts) {
                tempExchangVal = arrSorted[j];
                arrSorted[j] = arrSorted[j + 1];
                arrSorted[j + 1] = tempExchangVal;
            }
        }
        i--;
    }
    return arrSorted;
    }

    function searchdsrxl(){
        $('.hmpct').unbind();
        $('.hmpct').on('click',function(){
            if($(this).find('img').attr('class')!='ic'){
            $(this).find('img').addClass('ic');
            $(this).parent().parent().find('.hmpct_word').css('display','block');
            }else{
            $(this).find('img').removeClass('ic');
            $(this).parent().parent().find('.hmpct_word').css('display','none');
        }
    });
    }
    //添加单个选项
    function addxuanxiang(xuangxiangname,piaoshu,oid){
        var xuanxiang='<div class="xuanxiangson"'+'oid='+oid+'><div class="xuanxiangsontitle"><span class="xuanxiangname">'+xuangxiangname+'</span><span class="piaoshuc">(<span class="piaoshuN">'+piaoshu+'</span>票)</span></div><div class="xs"><div class="xianshizhanbi"></div><div class="hmpct"><img src="img/ckrs.png"></div></div><div class="hmpct_word">0人选了此项</div></div>';
        $('.xuanxiangsoncontainer').append(xuanxiang);
        searchdsrxl();
        tprsrm(oid);        
    }
    //添加单个选项2
    function addxuanxiang1(xuangxiangname,piaoshu,xuanxiang,oid){
        var xuanxiang='<div class="xuanxiangson"'+'oid='+oid+'><div class="xuanxiangsontitle"><span class="xuanxiangname">'+xuangxiangname+'</span><span class="piaoshuc">(<span class="piaoshuN">'+piaoshu+'</span>票)</span></div><div class="xs"><div class="xianshizhanbi"></div><div class="hmpct"><img src="img/ckrs.png"></div></div><div class="hmpct_word">'+xuanxiang+'</div></div>';
        $('.xuanxiangsoncontainer').append(xuanxiang);
        searchdsrxl();
        tprsrm(oid);
    }

    //循环遍历添加
    function xhbltj(xuangxiangzu){
        for (var i = 0; i < xuangxiangzu.length; i++) {
        addxuanxiang(xuangxiangzu[i].content,xuangxiangzu[i].counts,xuangxiangzu[i].oid);
        }
        searchdsrxl();
    }


    //循环遍历添加2
    function xhbltj1(xuangxiangzu){
            for (var i = 0; i < xuangxiangzu.length; i++) {
        addxuanxiang1(xuangxiangzu[i].content,xuangxiangzu[i].counts,xuangxiangzu[i].hmpct_word,xuangxiangzu[i].oid);
            }
            searchdsrxl();
    }

    //排序的标题样式变换
    $('.sxpaixu').on('click',function(){
        $('.sxpaixu').removeClass('sxchoice');
        $(this).addClass('sxchoice');
    });

    //票数排序
    $('.piaoshu').on('click',function(){
        xxpaixu();
        $('.xuanxiangsoncontainer').empty();
        xhbltj1(arrSorted);
        changebl();
        anonymous(thisAnonymous);
    })
    //默认排序
    $('.moren').on('click',function(){
        xxpaixu();
        $('.xuanxiangsoncontainer').empty();
        xhbltj1(arr);
        changebl();
        anonymous(thisAnonymous);        
    });

    //复制投票链接
    $('.fuzhi').on('click',function(){
        $('.fuzhi').attr("data-clipboard-text","https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3b3c63ee9e929594&redirect_uri=http%3A%2F%2Fwww.zeffee.com%2Fredirect.php&response_type=code&scope=snsapi_userinfo&state=vote-"+tid+"#wechat_redirect");
        var clipboard = new Clipboard('.fuzhi');
        alert("点击右上角即可分享给朋友");
  $.ajax({
   url: "https://vote.zeffee.com:8443/getShareInfo?url="+window.location.href,
   xhrFields: {
      withCredentials: true
   },
   success:function(data){
    data = JSON.stringify(data);
    var data = eval("("+data+")");
    console.log(data);
    wx.config({  
    	debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。  
    	appId: data.appId, // 必填，公众号的唯一标识  
    	timestamp: data.timestamp, // 必填，生成签名的时间戳  
    	nonceStr: data.nonceStr, // 必填，生成签名的随机串  
    	signature: data.signature,// 必填，签名，见附录1  
    	jsApiList: ['onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2  
	});
wx.ready(function(){  
	wx.onMenuShareAppMessage({
		title: '邀请你参与投票《'+$('.tpjg_title_text').text()+'》', // 分享标题
		desc: '点此链接参与投票', // 分享描述
		link: window.location.href+'&fenxiang=1', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		imgUrl: 'https://vote.zeffee.com/img/create_voting.svg', // 分享图标
		type: '', // 分享类型,music、video或link，不填默认为link
		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		success: function () {
			alert('分享投票成功！');
		},
		cancel: function () {
			alert('您取消了分享。');

// 用户取消分享后执行的回调函数
			}
	});

});  
wx.error(function(res){  
   console.log(res);
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
    	var juplpurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3b3c63ee9e929594&redirect_uri=http%3A%2F%2Fwww.zeffee.com%2Fredirect.php&response_type=code&scope=snsapi_userinfo&state=vote-"+tid+"#wechat_redirect";
		window.location.href=juplpurl;  
}); 

}
});
    });
    //拉票
    $('.lapiao').on('click',function(){
        var newOID='';
        var newword='';
        for (var i = 0; i < ytp.length; i++) {
            newOID+=ytp[i]+',';
            newword+=' 【'+$(".xuanxiangson[oid='"+ytp[i]+"']").find('.xuanxiangname').text()+'】 ';
        }
        newOID=(newOID.substring(newOID.length-1)==',')?newOID.substring(0,newOID.length-1):newOID;
        var lpurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3b3c63ee9e929594&redirect_uri=http%3A%2F%2Fwww.zeffee.com%2Fredirect.php&response_type=code&scope=snsapi_userinfo&state=vote-"+tid+'-{'+newOID+'}'+"#wechat_redirect";
        $('.lapiao').attr("data-clipboard-text",lpurl);
        var clipboard = new Clipboard('.lapiao');
        alert("点击右上角分享给朋友即可拉票，朋友可以看到你的投票对象。");
  $.ajax({
   url: "https://vote.zeffee.com:8443/getShareInfo?url="+window.location.href,
   xhrFields: {
      withCredentials: true
   },
   success:function(data){
    data = JSON.stringify(data);
    var data = eval("("+data+")");
    console.log(data);
    wx.config({  
    	debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。  
    	appId: data.appId, // 必填，公众号的唯一标识  
    	timestamp: data.timestamp, // 必填，生成签名的时间戳  
    	nonceStr: data.nonceStr, // 必填，生成签名的随机串  
    	signature: data.signature,// 必填，签名，见附录1  
    	jsApiList: ['onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2  
	});
wx.ready(function(){  
	wx.onMenuShareAppMessage({
		title: '我参与了投票《'+$('.tpjg_title_text').text()+'》，快帮我给我的选项投上一票', // 分享标题
		desc: '请给'+newword+'投上一票', // 分享描述
		link: window.location.href+'&fenxiang=1'+"&oid="+newOID, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		imgUrl: 'https://vote.zeffee.com/img/create_voting.svg', // 分享图标
		type: '', // 分享类型,music、video或link，不填默认为link
		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		success: function () {
			alert('分享拉票成功！');
		},
		cancel: function () {
			alert('您取消了拉票。');

// 用户取消分享后执行的回调函数
			}
	});

});  
wx.error(function(res){  
   console.log(res);
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
    	var juplpurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3b3c63ee9e929594&redirect_uri=http%3A%2F%2Fwww.zeffee.com%2Fredirect.php&response_type=code&scope=snsapi_userinfo&state=vote-"+tid+"#wechat_redirect";
		window.location.href=juplpurl; 
}); 

}
});


    });

    //下拉查看投票人
    $('.hmpct').on('click',function(){
        if($(this).find('img').attr('class')!='ic'){
            $(this).find('img').addClass('ic');
            $(this).parent().find('.hmpct_word').css('display','block');
        }else{
            $(this).find('img').removeClass('ic');
            $(this).parent().find('.hmpct_word').css('display','none');
        }
    });    

//检测倒计时
function jcshijian(endData){
    if (endData<new Date()) {
        $('.daojishi').text("已结束");
        $('.lapiao').css('color','#bfbdbd');
        $('.lapiao').unbind(); 
    $('.lapiao').on('click',function(){
        alert("投票已结束，不能拉票啦");
    })
    }else{
        $('.daojishi').text("投票未结束");
        if((endData-new Date())/1000/60/60/24>=1){
            $('.daojishi').text("还有"+parseInt((endData-new Date())/1000/60/60/24)+"天结束");
        }else if((endData-new Date())/1000/60/60>=1){
            $('.daojishi').text("还有"+parseInt((endData-new Date())/1000/60/60)+"小时结束");
        }else if((endData-new Date())/1000/60>=1){
            $('.daojishi').text("还有"+parseInt((endData-new Date())/1000/60)+"分钟结束");
        }else if((endData-new Date())/1000>=1){
            $('.daojishi').text("还有"+parseInt((endData-new Date())/1000/60)+"秒结束");
        }
    }
}



});