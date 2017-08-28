        $('.kexuangeshu').on('click', function () {
		    	function ObjStory(id,type){
		    		this.label = id;
		    		this.value = type;
		    	}
		    	var xuanxiangs=$('.jianshao').length;
		    	var choicexuanxiangs=new Array();
				var arr = new Array();
                if(xuanxiangs>2){
                    xuanxiangs=xuanxiangs-1;
                }
		    	for (var i = 2; i <=xuanxiangs; i++) {
					var writer= new ObjStory(i,i);
		    		arr[i-2]=writer;
		    	}

        weui.picker(arr, {
            onConfirm: function (result) {
                $('.kexuangeshu .snaume').text(result);
            },
            defaultValue: $('input').length-1,
        });
    });
		var newinput='<div class="xuanxiangcontainer"><img src="img/js.svg" class="jianshao"/><input type="text" placeholder="选项描述"></div>'
		var shijian={
			removeinput : function(event){
				if ($('.jianshao').length<=2) {
					alert("少于两个选项的话就不用投票了嘛~");
				}else{
					event.parent().remove();
				}
			},
			addinput : function(){
				$('.tianjiaxuanxiang').before(newinput);
                $('.jianshao').unbind();
				$('.jianshao').on('click',function(event){
					shijian.removeinput($(this));
				})
			},
		}
		$('.jianshao').on('click',function(event){
			shijian.removeinput($(this));
		})
		$('.tianjia').on('click',function(){
			shijian.addinput();
		})


		$('.duoxuan').find('img').on('click',function(){
			if($(this).attr('src')=="img/dx1.svg"){
				$(this).attr('src',"img/dx2.svg");
				$('.kexuangeshu').css('display','block');
                $('.snaume').text(2);
			}else{
				$(this).attr('src',"img/dx1.svg");
				$('.kexuangeshu').css('display','none');
                $('.snaume').text(1);
			}
		})

        var ifniming=0;
		$('.niming').find('img').on('click',function(){
			if($(this).attr('src')=="img/dx1.svg"){
				$(this).attr('src',"img/dx2.svg");
                ifniming=1;
			}else{
				$(this).attr('src',"img/dx1.svg");
                ifniming=0;
			}
		})



		$('document').ready(function(){
			var date=new Date();
			var year=date.getFullYear();
			var month=date.getMonth()+1;
            var day=date.getDate();
			var day2=date.getDate()+3;
			var hour=date.getHours();
			var minues=date.getMinutes();
			    if (hour<10) {
                	hour='0'+hour;
                }
                if (minues<10) {
                	minues='0'+minues;
                }
            var choice_date_q=year+'-'+month+'-'+day;
			var choice_date_q2=year+'-'+month+'-'+day2;
            $('.year_mouth_date').text(choice_date_q);
            $('.year_mouth_date2').text(choice_date_q2);
            var choice_date_q1=hour+':'+minues;
            $('.choice_date').text(choice_date_q1);
            $('.choice_date2').text(choice_date_q1);
		});
  $('.showDatePicker').click(function(event) {
        var _this = this;
        weui.datePicker({
            start: new Date().getFullYear(),
            end: new Date().getFullYear()+1,
            defaultValue: [new Date().getFullYear(), new Date().getMonth()+1, new Date().getDate()],
            onConfirm: function(result){
                // 二级调用：时间
                var choice_date_q=result[0]+'-'+result[1]+'-'+result[2];
                $('.year_mouth_date').text(choice_date_q);
                $('.ma_expect_date_picker .weui-picker').on('animationend webkitAnimationEnd', function() {
                    show_expect_time_picker1(_this, result);
                });
            },
            id: 'ma_expect_date',
            className: 'ma_expect_date_picker'
        });
    });
    var hours = [],
        minites = [],
        symbol = [{ label: ':', value: 0 }];
    function show_expect_time_picker1(_this, date) {
        var date = date[0].label + date[1].label + date[2].label;
        if (!hours.length) {
            for (var i = 0; i< 24; i++) {
                var hours_item = {};
                hours_item.label = ('' + i).length === 1 ? '0' + i : '' + i;
                hours_item.value = i;
                hours.push(hours_item);
            }
        }
        if (!minites.length) {
            for (var j= 0; j < 60; j++) {
                var minites_item = {};
                minites_item.label = ('' + j).length === 1 ? '0' + j : '' + j;
                minites_item.value = j;
                minites.push(minites_item);
            }
        }

        weui.picker(hours, symbol, minites, {
            defaultValue: [new Date().getHours(), 0, new Date().getMinutes()],
            onConfirm: function(result) {
                var time = result[0].label + ':' + result[2].label;
                var expect_date = date + ' ' + time;
                $(_this).find('.weui-cell__ft').text(expect_date);
                if (result[0]<10) {
                	result[0]='0'+result[0];
                }
                if (result[2]<10) {
                	result[2]='0'+result[2];
                }
                var choice_date_q1=result[0]+':'+result[2];
                $('.choice_date').text(choice_date_q1);
            },
            id: 'ma_expect_time'
        });
    }



    $('.showDatePicker2').click(function(event) {
        var _this = this;
        weui.datePicker({
            start: new Date().getFullYear(),
            end: new Date().getFullYear()+1,
            defaultValue: [new Date().getFullYear(), new Date().getMonth()+1, new Date().getDate()+3],
            onConfirm: function(result){
                // 二级调用：时间
                var choice_date_q=result[0]+'-'+result[1]+'-'+result[2];
                $('.year_mouth_date2').text(choice_date_q);
                $('.ma_expect_date_picker .weui-picker').on('animationend webkitAnimationEnd', function() {
                    show_expect_time_picker(_this, result);
                });
            },
            id: 'ma_expect_date',
            className: 'ma_expect_date_picker'
        });
    });
    var hours = [],
        minites = [],
        symbol = [{ label: ':', value: 0 }];
    function show_expect_time_picker(_this, date) {
        var date = date[0].label + date[1].label + date[2].label;
        if (!hours.length) {
            for (var i = 0; i< 24; i++) {
                var hours_item = {};
                hours_item.label = ('' + i).length === 1 ? '0' + i : '' + i;
                hours_item.value = i;
                hours.push(hours_item);
            }
        }
        if (!minites.length) {
            for (var j= 0; j < 60; j++) {
                var minites_item = {};
                minites_item.label = ('' + j).length === 1 ? '0' + j : '' + j;
                minites_item.value = j;
                minites.push(minites_item);
            }
        }

        weui.picker(hours, symbol, minites, {
            defaultValue: [new Date().getHours(), 0, new Date().getMinutes()],
            onConfirm: function(result) {
                var time = result[0].label + ':' + result[2].label;
                var expect_date = date + ' ' + time;
                $(_this).find('.weui-cell__ft').text(expect_date);
                if (result[0]<10) {
                	result[0]='0'+result[0];
                }
                if (result[2]<10) {
                	result[2]='0'+result[2];
                }
                var choice_date_q1=result[0]+':'+result[2];
                $('.choice_date2').text(choice_date_q1);
            },
            id: 'ma_expect_time'
        });
    }

    var post=1;
    $('button').on('click',function(){
        if ($('.vote_text__title_text').val()=='') {
            alert("投票标题未填写");
            $('.vote_text__title_text').focus();
        }else{
            for (var i = 0; i < $('.xuanxiang input').length; i++) {
            if($('.xuanxiang input').eq(i).val()==''){
                alert("有选项为空");
                $('.xuanxiang input').eq(i).focus();
                post=0;
                break;
                }else{
                    post=1;
                }
             }
            if (post) {
                var xuanxiang=new Array();
                function contentT(content_text){
                    this.content=content_text;
                }
                for (var i = 0; i < $('.xuanxiangcontainer input').length; i++) {
                    xuanxiang[i]=new contentT($('.xuanxiangcontainer input').eq(i).val());
                }
                

                var resultN=new Object();
                resultN.title=$('.vote_text__title_text').val();
                resultN.description=$('.vote_text__title_describe_text').val();
                resultN.start_time=$(".year_mouth_date").text()+" "+$(".choice_date").text()+":00";
                resultN.end_time=$(".year_mouth_date2").text()+" "+$(".choice_date2").text()+":00";
                resultN.votes_per_user=parseInt($('.snaume').text());
                resultN.counts=1;
                resultN.uid="zeffeeff";
                resultN.anonymous=ifniming;
                resultN.options=xuanxiang;
                var saveData = JSON.stringify(resultN);
   //                $.post("https://vote.zeffee.com:8443/addTheme",saveData,function(data){
   //   alert(data.name); // John
   //   console.log(data.time); //  2pm
   // },"json");
                $.ajax({
type : "POST",
url : "https://vote.zeffee.com:8443/addTheme",
contentType : "application/json;charset=utf-8",
data:saveData,
dataType : "json",
success:function(data){
    console.log(data);
    data = JSON.stringify(data);
    var data = eval("("+data+")");
    console.log(data);
    console.log(data.data.tid);
},
error: function (message) {
                            
                        }
});

                    }

    }});