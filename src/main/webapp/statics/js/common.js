$(function(){
    // email validation
    isEmailValidated = function(_this) {
        let emailRegExp = /^[a-z0-9A-Z._-]+@[a-z0-9A-Z_-]+\.[a-zA-Z.]*$/i;
        $(_this).val($(_this).val().trim());

        let element = $(_this);
        let email = $(_this).val();
        let inputWrap = element.closest('.input_wrap');

        var chkB = email.replace(/[^A-Za-z0-9@._-]/gi, '');
        $(_this).val(chkB);

        if (email == "" || email.length < 1) {
            $(_this).siblings("p").text("이메일 양식에 맞춰 입력해주세요.");
			return false;
		}
		else if (email.length > 64) {
            $(_this).siblings("p").text("이메일은 64자가 넘을 수 없습니다 .");
		}
		else if (!emailRegExp.test(email)) {
			$(_this).siblings("p").text("이메일 양식에 맞춰 입력해주세요.");
		}
		else {
			$(_this).siblings("p").text("");
			return true;
		}
    }

	// name validation
	isNameValidated = function(_this){
		let element = $(_this);
		let inputWrap = element.closest('.input_wrap');
		let name = $(_this).val();
		var chkB = name.replace(/[^a-zA-Zㄱ-힣._-]/gi, '');
		$(_this).val(chkB);
		
		if (name == "" || name.length <= 1) {
			$(_this).siblings("p").text("이름을 입력해주세요.");
			return false;
		} else if (name.length > 4) {
            $(_this).siblings("p").text("이름은 최대 4자까지 입력해주세요.");
			return false;
		} else {
			$(_this).siblings("p").text("");
			return true;
		}
	}

    // password validation
	isPwValidated = function(_this){
		$(_this).val($(_this).val().trim());
		var userPw = $(_this).val();
		let element = $(_this);
		let inputWrap = element.closest('.input_wrap');
		let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/

		let checkAsterisk = userPw.search(/[!@#$%^*+=-]/g);
		let checkNumber = userPw.search(/[0-9]/g);
		let checkEnglish = userPw.search(/[a-z]/ig);
	
		if (userPw.length > 32) {
            $(_this).siblings("p").text("비밀번호는 최대 32자까지 입력해주세요.");
			return false;
		}
		else if(checkAsterisk <0 || checkNumber <0|| checkEnglish<0){
            $(_this).siblings("p").text("비밀번호는 8~32자 이며 1개 이상의 특수문자 입력이 필요합니다.");
			return false;
		}
		else {
			$(_this).siblings("p").text("");
		}

        //password check
        const chkPw = $(".chkPw");
        const chkNewPw = $(".chkNewPw");
        const newPw = chkPw.val();
        const newCheck = chkNewPw.val();

        if(newPw != newCheck){
            $(chkNewPw).siblings("p").text("비밀번호가 불일치합니다.");
            return false;
        }
        $(chkNewPw).siblings("p").text("");
		return true;
    
	}

    //phone validation
    isPhoneValidated = function(_this) {
        let phoneRegExp = /^(010{1})[0-9]{3,4}[0-9]{4}$/i;
        $(_this).val($(_this).val().trim());

        let element = $(_this);
        let phone = $(_this).val();
        let inputWrap = element.closest('.input_wrap');

		var chkB = phone.replace(/[^0-9]/gi, '');		
		$(_this).val(chkB);
		
        if (phone == "" || phone.length < 1) {
            $(_this).siblings("p").text("연락처는 '-'를 제외하고 입력해 주세요.");
			return false;
		}
		else if (phone.length > 11) {
            $(_this).siblings("p").text("번호는 11자가 넘을 수 없습니다.");
		}
		else if (!phoneRegExp.test(phone)) {
			$(_this).siblings("p").text("연락처는 '-'를 제외하고 입력해 주세요.");
		}
		else {
			$(_this).siblings("p").text("");
			return true;
		}
    }
    
    // 일자 검색 validation
    $("#strDt").on("change",function(){
    	var prevDateStr = $(this).data('value');
    	var strDt = new Date($("#strDt").val());
    	var endDt = new Date($("#endDt").val());
    	if((!isNull(strDt) && strDt != '') && (!isNull(endDt) && endDt != '')){
    		if(strDt > endDt){
    			alert("시작일자가 종료일자보다 늦을 수 없습니다.");
    			$(this).val(prevDateStr);
    			return false;
    		}
    	}
    })
    
    $("#endDt").on("change",function(){
    	var prevDateStr = $(this).data('value');
    	var strDt = new Date($("#strDt").val());
    	var endDt = new Date($("#endDt").val());
    	if((!isNull(strDt) && strDt != '') && (!isNull(endDt) && endDt != '')){
    		if(strDt > endDt){
    			alert("종료일자가 시작일자보다 빠를 수 없습니다.");
    			$(this).val(prevDateStr);
    			return false;
    		}
    	}
    })
	
    // password eye    
    const eye = $(".eye_img");
    eye.on("click", function(e){
        $(this).find("img").attr("src", function (index, attr) {
            if (attr.match("_on")){
                return attr.replace("_on.png", "_off.png");
            } else {
                return attr.replace("_off.png", "_on.png");
            }
        });
        if($(this).siblings("input").attr("type") == "password"){
            $(this).siblings("input").attr("type", "text");
        } else {
            $(this).siblings("input").attr("type", "password");
        }
    })

	// input text remove
	$('.administrative_btn').on('click', function(){
		$(this).parent('.admin_input_wrap').find('.input_label').addClass('title_up');
	})

    // header user info
    $('.userinfo_btn').on('click', function(){
    	$('.userinfo_box').toggleClass('none');
    })
    
    //depth2 open, close
    $(".depth2_open_btn").click(function(){
        $(this).toggleClass("open");
        $(".depth2_wrap").toggleClass("close");
    })

    //select toggle
    $('.btn_select').on("click", function(e){
        $(this).siblings(".list_member").toggleClass("on");
    })

    // 테이블 삭제하기
    $('.table_delete').click(function(){
       let i = $(this).closest('tr').siblings().length;
       if ( i < 3 ) {
             alert('마지막 행은 삭제 불가합니다.')
        } else {
            $(this).closest('tr').remove();
        }
    })
    

	//GIS 전체선택/해제
	const allSelectorList =  $('.all_selector').siblings();
    const groupBtnInput = $(".group_btn_item input");
    $(".all_selector").off().on('click', function(){
        if (allSelectorList.hasClass("is-darkgreen-btn")) {
            $(this).removeClass("is-darkgreen-btn");
            $(this).siblings("label").removeClass("is-darkgreen-btn");
            $(this).siblings("label").find('input').prop('checked', false);
        } else {
            $(this).addClass("is-darkgreen-btn");
            $(this).siblings("label").addClass("is-darkgreen-btn");
            groupBtnInput.checked = true;
            $(this).siblings("label").find('input').prop('checked', true);
        }
    })

	// form reset
	$('.selected_reset').on('click', function(){
		$(".date_picker").val('');
		$('.selectBox option').prop('selected', function(){
			return this.defaultSelected;
		});
		$(groupBtn).removeClass('is-darkgreen-btn');
	})
	
	//label button single active
	$('.single-toggle').on('change', function(){
		$(this).toggleClass('is-darkgreen-btn');
		$(this).find('input').prop('checked', function(){
			return $(this).prop('checked');
		})
	})

    // tab menu
    $(".tab_fc div button").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-index"); i++){
                if($(this).attr("data-index") == i){
                    $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).show();
                    $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).siblings().hide();
                }
            }
        });
    });
    $(".tab_btn_item").click(function(){
        $(this).parent().find(".tab_btn_item").removeClass("is-darkgreen-btn");
        $(this).addClass("is-darkgreen-btn");
    })
    
    // control
    $(".control_open_btn").click(function(){
        $(this).parent().siblings(".control_box").find(".control_on").toggleClass('none');
        $(this).toggleClass("open");
    })

    // dashboard close
    $(".dashboard_close").click(function(){
        $(this).closest(".dashboard_itembox").addClass("none");
        $(this).closest(".tab_box_close").addClass("none");
        $(this).closest(".tab_bigbox_close").addClass("none");
        $(this).closest(".dashboard_box_close").addClass("none");
        $(this).closest(".mapping_container").addClass("none");
        $(".mapping_toggle_img").removeClass("active");
    })

	//chart vidoe close	
	$('.monitor_tab_close').on('click', function(){
		$('.chart_video_container').hide();
	})
	
    // depth2 section active
    $(".depth2_box  .depth2_item  button").each(function(){
        $(this).click(function(){
            for(var i = 1; i <= $(this).parent().children().last().attr("data-depth"); i++){
                if($(this).attr("data-depth") == i){
                    $(".tab_bigbox_close").addClass("none");
                    $(".is-side-btn").removeClass("active");
                    $(this).closest(".header").siblings(".main").find(".main_container").children(".depth2_section"+i).removeClass("none");
                    $(this).closest(".header").siblings(".main").find(".main_container").children(".depth2_section"+i).siblings(".dashboard_section").addClass("none");
                    $(".dashboard_section").find(".tab_btn").find("ul").find(".side_item").find(".active_on1").addClass("active");
                    $(".tab_bigbox_active").removeClass("none");
					$('.chart_video_container').hide();
                }
            }
        })
    })
    $(".depth2_box .depth2_item").click(function(){
        $(this).closest(".depth2_wrap").find(".depth2_item_box").find(".depth2_box").find(".depth2_item").removeClass("svg_active");
        $(this).addClass("svg_active");
    })

    // dashboard side tab
    $(".tab_btn ul li").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-index"); i++){
                if($(this).attr("data-index") == i){
                    $(this).closest(".tab_side").find(".tab_box").children(".side_box"+i).toggleClass("none");
                    $(this).find(".is-side-btn").toggleClass("active");
                    $(".tab_item_box").find("button").removeClass("is-darkgreen-btn");
                    $(".tab_box_close").removeClass("none");
                    $(".result_basic").removeClass("active");
                    $(".map_fusion_container").addClass("none");
                    $(".road_sataty_bigbox").addClass("none");
                    $(".mapping_toggle_img").removeClass("active");
                    $(".sub_none").addClass("none");
					$('.tab_box_header').removeClass('none');
                }
            }
        });
    });

    $(".tab_one .tab_btn ul li").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-index"); i++){
                if($(this).attr("data-index") == i){
                    $(this).closest(".tab_side").find(".tab_box").children(".side_box"+i).siblings().addClass("none");
                    $(this).siblings().find(".is-side-btn").removeClass("active");
                }
            }
        });
    });
})

// 투명도 조절
$(function() {
    $(".opa_slider").slider({
       animate : true,
       range : "min",
       min: 30,
       max:100,
       value : 100,
       slide : function(event,ui){
         $(this).parents().eq(3).css("opacity", ui.value/100);
       }
    });

    $(".chart_video_slider").slider({
       animate : true,
       range : "min",
       min: 30,
       max:100,
       value : 100,
       slide : function(event,ui){
         $(this).closest('.chart_video_container').css("opacity", ui.value/100);
       }
    });
});

//table remove
$(function(){
    $('.tr_delete').on('click', function(){
        $(this).closest('tr').remove();
    })
})

//호출
$(function(){
	//시간, 달력
	datePickerInit();
	dateTiemInit();
	
	//bigdataTab
	bigdataTabMenu();
	
	//gis check active
	gisCheckInit();
	
	//bigdata 결과보기
	resultRemove();
});

//gis check active
function gisCheckInit() {
    const groupBtn = $(".group_btn_item");
    const roadBtnInput = $(".road_item input");
	
    $(".road_all_selector").on("click", function(){
        if ($(".road_item").hasClass("is-darkgreen-btn")) {
            $(this).removeClass("is-darkgreen-btn");
            $(this).siblings("label").removeClass("is-darkgreen-btn");
            $(this).siblings(".road_item").find(roadBtnInput).prop('checked', false);
        } else {
            $(this).addClass("is-darkgreen-btn");
            $(this).siblings("label").addClass("is-darkgreen-btn");
            roadBtnInput.checked = true;
            $(this).siblings(".road_item").find(roadBtnInput).prop('checked', true);
        }
    })

    $(groupBtn).on("change", function(){
        $(this).closest(".tab_item_box").find(groupBtn).find("input").prop('checked', false);
        $(this).closest(".tab_item_box").find(groupBtn).removeClass("is-darkgreen-btn");
        $(this).closest(".flex-center").siblings(".direct_time").addClass("none");
        $(this).toggleClass("is-darkgreen-btn");
		$(this).find('input').prop('checked', true);
    })

    $(".direct").on("change", function(){
        $(this).closest(".flex-center").siblings(".direct_time").toggleClass("none");
    })

    $(".road_item").on("change", function(){
        $(this).toggleClass("is-darkgreen-btn");
        $(this).siblings(".road_all_selector").removeClass("is-darkgreen-btn");
    })
}

	//정체구간 개선효과 기본값
	var toDayTime = new Date();
	var yesterDayTime = new Date();
	var yesterDay = -1;
	yesterDayTime.setDate(yesterDayTime.getDate() + yesterDay);
	
	var year = toDayTime.getFullYear();
	var month = ('0' + (toDayTime.getMonth() + 1)).slice(-2);
	var day = ('0' + toDayTime.getDate()).slice(-2);
	
	var yesterYear = yesterDayTime.getFullYear();
	var yesterMonth = ('0' + (yesterDayTime.getMonth() + 1)).slice(-2);
	var yesterDay2 = ('0' + yesterDayTime.getDate()).slice(-2);
	
	var dateString = year + '-' + month  + '-' + day;
	var yesterDateString = yesterYear + '-' + yesterMonth + '-' + yesterDay2;

	
//시간, 달력
function datePickerInit(){
	//startDate
	$('.date_picker').datepicker({
		dateFormat:'yy-mm-dd',
		changMonth:true,
		changYear:true,
		nextText:'다음 달',
		prevText:'이전 달',
		dayNames:['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		showMonthAfterYear:true,
		yearSuffix:'년',
	    format: 'YYYY',
		minViewMode: 'years',
	    viewMode: "years",
		maxDate:'-1D',
	})

	//endDate
	$('.end_date_picker').datepicker({
		dateFormat:'yy-mm-dd',
		changMonth:true,
		changYear:true,
		nextText:'다음 달',
		prevText:'이전 달',
		dayNames:['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		showMonthAfterYear:true,
		yearSuffix:'년',
	    format: 'YYYY',
		minViewMode: 'years',
	    viewMode: "years",
		maxDate:'0D',
	})
	
	//start date alert
	const datePickerVal = typeof($('.date_picker').val()) != 'undefined' ? $('.date_picker').val():'';
	if(datePickerVal == '') {
		$('.date_picker_block').on('click', function(){
			new ModalBuilder().init().alertBoby("시작 날짜를 먼저 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();			
		})
	}
	
	$("input[name='startDate']").on('change',function(){
		$('.date_picker_block').remove();
	});
	
	//start ~ end date option
	$('.date_picker').datepicker();
    $('.date_picker').datepicker("option", "onClose", function (selectedDate) {
        $(".end_date_picker").datepicker("option", "minDate", selectedDate);
    });

    $('.end_date_picker').datepicker();
    $('.end_date_picker').datepicker("option", "minDate", $(".date_picker").val());
    $('.end_date_picker').datepicker("option", "onClose", function (selectedDate) {
	        $(".date_picker").datepicker("option", "maxDate", selectedDate);
    });

	//select validation
	var searchYear = $("select[name='searchYear']");
	$(searchYear).off().on('change', function(){
		const searchYearValue = $("select[name='searchYear']").val();
		if(searchYearValue != null){
			const minDate = searchYearValue+"-01-01";
			const maxDate = searchYearValue+"-12-31";
		    $(".date_picker").datepicker("option", "minDate",minDate);
		    $(".date_picker").datepicker("option", "maxDate",maxDate);
			$(".date_picker").datepicker("option", "defaultDate", minDate);
		    $(".end_date_picker").datepicker("option", "minDate",minDate);
		    $(".end_date_picker").datepicker("option", "maxDate",maxDate);
		}
	});
	
	
	//날짜비교 -> select 시간 제어
	$('.end_date_picker').on('change', function(){
		var getHours = toDayTime.getHours();
		var afterTime = $("#endTime").val();
		var beforeTime = $("#startTime").val();
		var beforeDate = $(".date_picker").val();
		var afterDate = $(".end_date_picker").val();
		
		//오늘 날짜와 시작날짜가 같다면
		if(dateString == beforeDate){
			$.each($('#endTime option'), function(){
			var idx = $(this).index();
				if (getHours <= idx){
			       $(this).attr('disabled','disabled');
			    } else {
				   $(this).removeAttr('disabled').prop('selected', true);
				}
			})
		} else {
			$('#endTime option').removeAttr('disabled').prop('selected', true);
			$("#endTime").val(afterTime);
		}
		//오늘 날짜와 종료날짜가 같다면
		if(dateString == afterDate){
			$.each($('.effect_start_time option'), function(){
			var idx = $(this).index();
				if (getHours <= idx){
			       $(this).attr('disabled','disabled');
			    } else {
				   $(this).removeAttr('disabled').prop('selected', true);
				}
			})		
		} else {
			$('#startTime option').removeAttr('disabled').prop('selected', true);
			$("#startTime").val(beforeTime);
		}
		//종료날짜와 오늘날짜가 같다면
		if(afterDate == dateString){
			$.each($('#endTime option'), function(){
			var idx = $(this).index();
				if (getHours <= idx){
			       $(this).attr('disabled','disabled');
			    } else {
				   $(this).removeAttr('disabled').prop('selected', true);
				}
			})
		}
		
		//시작,종료일이 같을때 같은시간 선택 하지 못하게
		if(beforeDate == afterDate) {
			$('#startTime').on('change', function(){
				var beforeTime = $("#startTime").val();
				$.each($('#endTime option'), function(){
				var idx = $(this).index();
					if (beforeTime >= idx){
				       $(this).attr('disabled','disabled');
				    } else {
					   $(this).removeAttr('disabled').prop('selected', true);
					}
				})
			})
		}
	})
	
	//오늘날짜, 어제날짜 찍어주기
	$('.yesterday').datepicker('setDate', yesterDateString);	
	$('.today').datepicker('setDate', dateString);
}


function dateTiemInit(){
	var optionHtml = "";
	for(var i = 0; i < 24; i++){
		if(i < 10){
			optionHtml += "<option value=0"+i+">0"+i+":00</option>";
		}else{
			optionHtml += "<option value="+i+">"+i+":00</option>";
		}
	}
	$(".selectTime").append(optionHtml);
	
	$("#endTime").on('change',function(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		const beforeDate = $('.date_picker').val();
		const afterDate = $(".end_date_picker").val();
		
		var forBeforeDate = new Date(beforeDate);
		var forAfterDate = new Date(afterDate);
		
		var dayInterval = Math.abs((forBeforeDate-forAfterDate) / (1000 * 60 * 60 * 24));
		
		if(dayInterval == 0){
			if(startTime > endTime){
				new ModalBuilder().init().alertBoby("시작 시간보다 종료 시간이 낮을 수 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
				$("#endTime").val(startTime);
				return false;
			} else {
	 		    var theSelectedIndex = $(this)[0].selectedIndex;
	 		    $.each($('select[name=startTime] option'), function(){
	 		        var startOptionIndex = $(this).index();
	 		        if (theSelectedIndex <= startOptionIndex){
	 		           $(this).attr('disabled','disabled');
	 		        } else{
	 		           $(this).removeAttr('disabled').prop('selected', true);
		        	}
	 		    });
				$("#startTime").val(startTime);
			}
		} 
		
		var startDate = $(".date_picker").val();
		var endDate = $(".end_date_picker").val();
		if(startDate == endDate) {
			if(startTime == endTime) {
				new ModalBuilder().init().alertBoby("같은 날짜의 같은 시간 선택은 불가능합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
			}		
		}
	});
	
	$('.effect_start_time').on('change', function(){
		const getHours = toDayTime.getHours();
		const beforeTime = $('#startTime').val();
		const afterDate = $(".end_date_picker").val();
	
		if(afterDate != null){
			if(dateString == afterDate){
				if(getHours < beforeTime){
					$.each($('#endTime option'), function(){
						var idx = $(this).index();
						if (getHours <= idx){
					       $(this).attr('disabled','disabled');
					    } else {
						   $(this).removeAttr('disabled').prop('selected', true);
						}
					})
				   $('#endTime').val(getHours);
				} else {
					$.each($('#endTime option'), function(){
						var idx = $(this).index();
						if (getHours <= idx){
					       $(this).attr('disabled','disabled');
					    } else {
						   $(this).removeAttr('disabled').prop('selected', true);
						}
					})
				   $('#endTime').val(beforeTime);
				}
			} else {
			   $('#endTime option').removeAttr('disabled').prop('selected', true);
			   $('#endTime').val(beforeTime);
			}
		}
	}) 
	
	$('.effect_end_time').on('change', function(){
		const getHours = toDayTime.getHours();
		const afterTime = $('#endTime').val();
		const beforeDate = $('.date_picker').val();
		const afterDate = $(".end_date_picker").val();
		
		if(dateString == afterDate){
			$.each($('#endTime option'), function(){
				var idx = $(this).index();
				if (getHours <= idx){
			       $(this).attr('disabled','disabled');
			    } else {
				   $(this).removeAttr('disabled').prop('selected', true);
				}
			})
			$('#endTime').val(afterTime);	
		}
		var forBeforeDate = new Date(beforeDate);
		var forAfterDate = new Date(afterDate);
		
		var dayInterval = Math.abs((forBeforeDate-forAfterDate) / (1000 * 60 * 60 * 24));
		
		if(dayInterval == 1){
			$.each($('#startTime option'), function(){
				var idx = $(this).index();
				if (afterTime < idx){
			       $(this).attr('disabled','disabled');
			    } else {
				   $(this).removeAttr('disabled').prop('selected', true);
				}
			})
			$('#startTime').val(afterTime);
			$('#endTime').val(afterTime);
		} else {
			$.each($('#startTime option'), function(){
			   $(this).removeAttr('disabled').prop('selected', true);
			})
			$('#startTime').val(afterTime);
		}

		if(afterDate == dateString){
			$.each($('#startTime option'), function(){
			var idx = $(this).index();
				if (getHours <= idx){
			       $(this).attr('disabled','disabled');
			    } else {
				   $(this).removeAttr('disabled').prop('selected', true);
				}
			})
			$('#startTime').val(afterTime);	
		}
	})	
}




// input 입력값 전환
function transferValue() {
	var valueFromInput1 = $('#inputChange1').val();
	var valueFromInput2 = $('#inputChange2').val();
	$('#inputChange1').val(valueFromInput2);
	$('#inputChange2').val(valueFromInput1);
}

// bigdata title css 변경
function tabTitleCss() {
	tabBoxUrl = window.location.href;
	if (tabBoxUrl.indexOf('bigdata')){
		$('.tab_box_header').addClass('tab_title_box');
		$('.dashboard_wrap').css('padding','0');
		$('.tab_box_body').css('padding', '1rem 1.5rem');
	}
}

// GIS 돌발 현황 show/hide
function outbreakList() {
    $('.outbreak_box').toggleClass('none');
}
function outbreakPushClose(e) {
    $(e).parent('.outbreak_push_item').addClass('none');
}

//bigdata popup tab
function bigdataTabMenu() {
	$(".tab_fc ul li button").each(function() {
	    $(this).click(function(){
	        for( var i = 1;  i <= $(this).parent().children().last().attr("data-index"); i++){
	            if($(this).attr("data-index") == i){
	            	$(this).closest(".tab_fc").hide();
	                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).show();
	                $(this).closest(".tab_set").find(".tab_area").children(".tab"+i).siblings().hide();
					$('.tab_box_header').addClass('none');
	            }
	        }
	    });
	});
}

//bigdata popup prev
function bigdataPopupPrev() {
	$('.prev_text').on('click', function(){
		$(this).closest('.tab_area').siblings('.tab_fc').show();
		$(this).closest('.tab').hide();
		$('.tab_box_header').removeClass('none');
	})
}

//bigdata popup close
function bigdataPopupClose() {
	$('.tab_popup_close').on('click', function(){
		$(this).closest('.dashboard_box').addClass('none');
	})
}

//결과보기
function resultRemove() {
	$('.original_result_btn').on('click', function(){
		$('.dashboard_box').empty()
	})
}

//modal alert wrap
function modalAlertWrap() {
	$('.modal_wrap').addClass('modal_alert_wrap');
	$('.modal_content').css('min-width', '28rem');
}
function modalAlertClose() {
	$('.modal_container').remove();
}

//bigdata 검색 자동완성 on/off
function tabListOnOff() {
	$('.tab_list_on').on('change', function(){
		$('.tab_list').toggleClass('none');
	})
	
	$('.tab_list_off').on('change', function(){
		$('.tab_list').addClass('none');
	})
}

//범례 토글
function legendToggle(){
	$(".remarks_title_box").click(function(){
        $(".remarks_wrap").slideToggle(200);
        $(this).find(".remarks_title").toggleClass("rotate");
    })
}

$.fn.serializeObject = function() {
	var obj = null;
	try {
		if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
			var arr = this.serializeArray();
			if (arr) {
				obj = {};
				jQuery.each(arr, function() {
					obj[this.name] = this.value;
				});
			}
		}
	} catch (e) {
		console.log(e.message);
	} finally { }
	return obj;
}


function getPagingHtml(paging,page){
	var pagingHtml = 	'<div class="page_wrap">'+
							'<ul class="pagination">'+
								'<li><a href="javascript:pageMove('+paging.firstPageNo+')" class="first">처음 페이지</a></li>'+
			          			'<li><a href="javascript:pageMove('+paging.prevPageNo+')" class="arrow left"> &lt; </a></li>';
							for(var i = paging.startPageNo; i <= paging.endPageNo; i++){
								if(paging.startPageNo == 0) i = 1;
								if(i == page){
			pagingHtml +=			'<li><a href="javascript:pageMove('+i+')" class="active num">'+i+'</a></li>';
								}else{
			pagingHtml +=			'<li><a href="javascript:pageMove('+i+')" class="num">'+i+'</a></li>';						
								}
							}		
			pagingHtml +=  		'<li><a href="javascript:pageMove('+paging.nextPageNo+')" class="arrow right"> &gt; </a></li>'+
			          			'<li><a href="javascript:pageMove('+paging.finalPageNo+')" class="last">마지막 페이지</a></li>'+
			      			'</ul>'+
			    		'</div>';
	return pagingHtml;
}

function getGisPagingHtml(paging,page,pagingLocation = ''){
	var gisPagingHtml = 	'<div class="dashboard-pg-wrap">'+
							'<ul class="dashboard-pg">'+
			          			'<li><a href="javascript:pageMove('+paging.prevPageNo+',\''+pagingLocation+'\')" class="arrow left"> &lt; </a></li>';
							for(var i = paging.startPageNo; i <= paging.endPageNo; i++){
								if(paging.startPageNo == 0) i = 1;
								if(i == page){
			gisPagingHtml +=			'<li><a href="javascript:pageMove('+i+',\''+pagingLocation+'\')" class="active num">'+i+'</a></li>';
								}else{
			gisPagingHtml +=			'<li><a href="javascript:pageMove('+i+',\''+pagingLocation+'\')" class="num">'+i+'</a></li>';						
								}
							}		
			gisPagingHtml +=  		'<li><a href="javascript:pageMove('+paging.nextPageNo+',\''+pagingLocation+'\')" class="arrow right"> &gt; </a></li>'+
			      			'</ul>'+
			    		'</div>';
	return gisPagingHtml;
}

function pageMove(page,pagingLocation){
	$("input[name='page']").val(page);
	if(pagingLocation == 'bigdata'){
		fnSearchCrossListForBigdata();
	}else{
		fnSearchList();
	}
}

function isNull(value) {
    return (value === undefined || value === null) ? true : false;
}

function fnSearchCrossListForBigdata(){
		
	$("#crossroadsListBody > tr").remove();
	$("#pagingDiv > .dashboard-pg-wrap").remove();
	
	var page = $("#mapPage").val();
	var crsrdNm = $("#searchCrsrdNm").val();
	
	$.ajax({
		type : "get",
		data : {"crsrdNm" : crsrdNm , "page" : page},
		url : "/map/bigdata/crossroads/list.ajax",
		success : function(result) {
			var html = '';
			$(result.data.list).each(function(index, item){
				console.log(item)
				html += '<tr>'+
								/*'<td><input type="radio" name="searchCrossroadsLinkId" value="'+item.linkId+'"/></td>'+*/
								'<td colspan="2" class="left">'+
									'<label>'+
										'<input type="radio" class="apeend_input" name="searchCrossroadsLinkId" value="'+item.linkId+'"/>'+
										'<span>'+item.rownum+'</span>'+
										'<span class="apeend_span">'+item.crsrdNm+'</span>'+
									'</label>'+
								'</td>'+
								/*'<td>'+item.crsrdNm+'</td>'+*/
						'</tr>';
	    	})
			$("#crossroadsListBody").append(html)				
	    	var paging = result.data.paging;
			if(paging != null && paging != ''){
				$("#pagingDiv").append(getGisPagingHtml(paging,page,'bigdata'));
			}
		}
	});
}
	
/**
 * @author CM.KIM
 * Phone format parser
 * @param {string, number} num
 * @returns
 */
function phone_format(num){
    return num.replace(/[^0-9]/g, '')
    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "")
}

function keyupPhoneEvent(_this){
	let value = _this.value;
	_this.value = phone_format(value);
}

function chkSortNo(_this){
	let value = _this.value.replace(/[^0-9]/g, '')
	_this.value = value;
	
	if(_this.value >= 100){
		_this.value = 99;
	}
}

/**
 * @author KC.KIM
 * 한글 입력 체크
 * @returns
 */
function kor_format(value){
    return value.replace(/[^ㄱ-힣]/g, '')
}

function keyupKorEvent(_this){
	let value = _this.value;
	_this.value = kor_format(value);
}

/**
 * @author KC.KIM
 * 영어 입력 체크
 * @returns
 */
function eng_format(value){
    return value.replace(/[^A-Za-z]/g, '');
}

function keyupEngEvent(_this){
	let value = _this.value;
	_this.value = eng_format(value);
}

// 요일 검색
function chkDayOfWeek(){
	var dayOfWeek = $(".dayOfWeek");
	var dayOfWeekStr = "";
	for(var i = 0; i < dayOfWeek.length; i++){
		if(dayOfWeek.eq(i).is(":checked")){
			if(dayOfWeekStr == ''){
				dayOfWeekStr += dayOfWeek.eq(i).val();
			} else {
				dayOfWeekStr += ","+dayOfWeek.eq(i).val();
			}
		}
	}
	$("#dayOfTheWeekStr").val(dayOfWeekStr);
}
