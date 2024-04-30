Date.prototype.addDays = function(days) {
	var date = new Date(this.valueOf());
	date.setDate(date.getDate() + days);
	return date;
}
function IsAlphaNumeric(ee) {
	var keyCode = ee.keyCode == 0 ? ee.charCode : ee.keyCode;
	// 예외키 규정
	var specialKeys = new Array();
	specialKeys.push(8); //Backspace
	specialKeys.push(9); //Tab
	specialKeys.push(46); //Delete
	specialKeys.push(36); //Home
	specialKeys.push(35); //End
	specialKeys.push(37); //Left
	specialKeys.push(39); //Right
	var ret = ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (specialKeys.indexOf(ee.keyCode) != -1 && ee.charCode != ee.keyCode));
	// error message
	if (!ret) { alert("only alphanumeric can be allowed to input."); }
	return ret;
}
let postFilter = function( options, originalOptions, jqXHR ) {
	if(isNull(options.timeout)) options.timeout = 10*60*1000;
	options.error = function(jqXHR, textStatus, errorThrown)
		{

			if(typeof originalOptions.error == "function"){
				originalOptions.error(jqXHR, textStatus, errorThrown);
				return;
			}
			//alert('서버의 응답이 지연되고 있습니다. 잠시 후 다시 이용하여 주십시오.[101]');
			$("#mapLoadingLayer").fadeOut(250, function () {
				$("#mapLoadingLayer").remove();
			});
		},
		options.complete = function(jqXHR, textStatus){
			$("#loadingLayer").fadeOut();
			if(typeof originalOptions.complete === "function"){
				originalOptions.complete(jqXHR, textStatus);
			}
			if(textStatus === 'timeout'){
				//alert('서버의 응답이 지연되고 있습니다. 잠시 후 다시 이용하여 주십시오.[102]');
				return;
			}
		}
};
$.ajaxPrefilter(postFilter);

/*ggits common worker*/
const GGITS_WORKER = new Worker("/statics/js/ggits.worker.js?t="+new Date().getTime());
var dataTimer;

$(function(){

	$(document).tooltip({
		track : true,
		position: {
			my: "center bottom-20",
			at: "center top",
			using: function( position, feedback ) {
				$( this ).css( position );
				$( "<div>" )
					.addClass( "arrow" )
					.addClass( feedback.vertical )
					.addClass( feedback.horizontal )
					.appendTo( this );
			}
		}
	});

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
            $(_this).siblings("p").text("이메일 형식에 맞춰 입력해주세요.");
			$(_this).parent().next('div').css("margin-top","3.2rem");
			return false;
		}
		else if (email.length > 64) {
            $(_this).siblings("p").text("이메일은 64자가 넘을 수 없습니다 .");
			$(_this).parent().next('div').css("margin-top","3.2rem");
		}
		else if (!emailRegExp.test(email)) {
			$(_this).siblings("p").text("이메일 형식에 맞춰 입력해주세요.");
			$(_this).parent().next('div').css("margin-top","3.2rem");
		}
		else {
			$(_this).siblings("p").text("");
			$(_this).parent().next('div').css("margin-top","1.5rem");
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
			$(_this).parent().next().css("margin-top","2.3rem");
			return false;
		}
		else if(checkAsterisk <0 || checkNumber <0|| checkEnglish<0){
            $(_this).siblings("p").text("영문, 숫자, 특수문자 조합 8~32자리로 입력해주세요.");
			$(_this).parent().next().css("margin-top","2.3rem");
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
				new ModalBuilder().init().alertBoby("시작일자가 종료일자보다 늦을 수 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
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
				new ModalBuilder().init().alertBoby("종료일자가 시작일자보다 빠를 수 없습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
				modalAlertWrap();
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
			new ModalBuilder().init().alertBoby("마지막 행은 삭제 불가합니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
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
		$(".end_date_picker").val('');
		$('.selectBox option').prop('selected', function(){
			return this.defaultSelected;
		});
		$('#searchContent').val('');
		
		var dayOfWeek = $(".group_btn_item");
		$('#dayOfTheWeekStr').val('');
		$(dayOfWeek).each(function(){
			$(this).removeClass("is-darkgreen-btn");
			$(this).find('input').prop('checked', false);	
		})
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
        $(this).closest("ul").children("li").find(".tab_btn_item").removeClass("is-darkgreen-btn");
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
                    /*$(".is-side-btn").removeClass("active");*/
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
		var ptternType = $(this).data("pttern-type");
		if(ptternType != 'EVC001' && ptternType != 'EVC010' && ptternType != 'EVC016'){
	        $(this).closest(".depth2_wrap").find(".depth2_item_box").find(".depth2_box").find(".depth2_item").removeClass("svg_active");
	        $(this).addClass("svg_active");
		}
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

/*
 author : KY.LEE
 date  : 2024.01.09
 date to String format
*/
function numberToTimeString(value) {
    if (value >= 10) {
        return value;
    }

    return `0${value}`;
}

function dateToStringFormat(date, delimiter = '-') {
    let getYear = date.getFullYear();
    let getMonth = numberToTimeString(date.getMonth() + 1);
    let getDay = numberToTimeString(date.getDate());

    return [getYear, getMonth, getDay].join(delimiter);
}




//호출
$(function(){
	//시간, 달력
	datePickerInit();
	dateTiemInit();
	
	//bigdataTab
	bigdataTabMenu();
	
	//gis check active
	gisCheckInit();

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
function datePickerInit(autoSetTimeDisabled = false){
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
		/*maxDate:'0D',*/
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
		/*maxDate:'0D',*/
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
		var nowDate = new Date();
		var nowYear = nowDate.getFullYear();
		if(searchYearValue != null){
			const minDate = searchYearValue+"-01-01";
			const maxDate = searchYearValue+"-12-31";
		    $(".date_picker").datepicker("option", "minDate",minDate);
		    $(".date_picker").datepicker("option", "maxDate",maxDate);
			$(".date_picker").datepicker("option", "defaultDate", minDate);
		    $(".end_date_picker").datepicker("option", "minDate",minDate);
		    $(".end_date_picker").datepicker("option", "maxDate",maxDate);
		}
		if(searchYearValue == nowYear){
			$(".date_picker").datepicker("option", "defaultDate", new Date());
		}
	
	});

	if(!autoSetTimeDisabled) {
		$('.date_picker').on('change', function () {
			var getHours = toDayTime.getHours();
			var afterTime = $("#endTime").val();
			var beforeTime = $("#startTime").val();
			var beforeDate = $(".date_picker").val();
			var afterDate = $(".end_date_picker").val();

			//오늘 날짜와 시작날짜가 같다면
			if (dateString == beforeDate) {
				$.each($('#endTime option'), function () {
					var idx = $(this).index();
					if (getHours <= idx) {
						$(this).attr('disabled', 'disabled');
					} else {
						$(this).removeAttr('disabled').prop('selected', true);
					}
				})
			} else {
				$('#endTime option').removeAttr('disabled').prop('selected', true);
				$("#endTime").val(afterTime);
			}

			//종료날짜와 오늘날짜가 같다면
			if (afterDate == dateString) {
				$.each($('#endTime option'), function () {
					var idx = $(this).index();
					if (getHours <= idx) {
						$(this).attr('disabled', 'disabled');
					} else {
						$(this).removeAttr('disabled').prop('selected', true);
					}
				})
			}

			//시작,종료일이 같을때 같은시간 선택 하지 못하게
			if (beforeDate == afterDate) {
				$.each($('#startTime option'), function () {
					var idx = $(this).index();
					if (getHours <= idx) {
						$(this).attr('disabled', 'disabled');
					} else {
						$(this).removeAttr('disabled').prop('selected', true);
					}
				})
			}

			if (dateString == beforeDate) {
				$.each($('#startTime option'), function () {
					var idx = $(this).index();
					if (getHours <= idx) {
						$(this).attr('disabled', 'disabled');
						$("#startTime").val(null);
					} else {
						$(this).removeAttr('disabled').prop('selected', true);
					}
				})
			} else {
				$('#startTime option').removeAttr('disabled').prop('selected', true);
				$("#startTime").val(beforeTime);
			}
		})

		//날짜비교 -> select 시간 제어
		$('.end_date_picker').on('change', function () {
			var getHours = toDayTime.getHours();
			var afterTime = $("#endTime").val();
			var beforeTime = $("#startTime").val();
			var beforeDate = $(".date_picker").val();
			var afterDate = $(".end_date_picker").val();

			//오늘 날짜와 시작날짜가 같다면
			if (dateString == beforeDate) {
				$.each($('#endTime option'), function () {
					var idx = $(this).index();
					if (getHours <= idx) {
						$(this).attr('disabled', 'disabled');
					} else {
						$(this).removeAttr('disabled').prop('selected', true);
					}
				})
			} else {
				$('#endTime option').removeAttr('disabled').prop('selected', true);
				$("#endTime").val(afterTime);
			}
			//오늘 날짜와 종료날짜가 같다면
			if (dateString == afterDate) {
				$.each($('.effect_start_time option'), function () {
					var idx = $(this).index();
					if (getHours <= idx) {
						$(this).attr('disabled', 'disabled');
					} else {
						$(this).removeAttr('disabled').prop('selected', true);
					}
				})
			} else {
				$('#startTime option').removeAttr('disabled').prop('selected', true);
				$("#startTime").val(beforeTime);
			}
			//종료날짜와 오늘날짜가 같다면
			if (afterDate == dateString) {
				$.each($('#endTime option'), function () {
					var idx = $(this).index();
					if (getHours <= idx) {
						$(this).attr('disabled', 'disabled');
					} else {
						$(this).removeAttr('disabled').prop('selected', true);
					}
				})
			}

		})
	}
	
	//오늘날짜, 어제날짜 찍어주기
	$('.yesterday').datepicker('setDate', yesterDateString);	
	$('.today').datepicker('setDate', dateString);
}


function dateTiemInit(isTime = true, is30min = false){
	var optionHtml = "";
	if(isTime){
		if(!is30min) {
			for (var i = 0; i < 24; i++) {
				if (i < 10) {
					optionHtml += "<option value=0" + i + ">0" + i + ":00</option>";
				} else {
					optionHtml += "<option value=" + i + ">" + i + ":00</option>";
				}
			}
		}else{
			for (var i = 0; i < 24; i++) {
				if (i < 10) {
					optionHtml += "<option value=0" + i + "00>0" + i + ":00</option>";
					optionHtml += "<option value=0" + i + "30>0" + i + ":30</option>";
				} else {
					optionHtml += "<option value=" + i + "00>" + i + ":00</option>";
					optionHtml += "<option value=" + i + "30>" + i + ":30</option>";
				}
			}
		}
	} else{
		for(var i = 0; i < 24; i++){
			if(i < 10){
				optionHtml += "<option value=0"+i+">"+i+"시 ~ "+(i+1)+"시"+"</option>";
			}else{
				optionHtml += "<option value="+i+">"+i+"시 ~ "+(i+1)+"시"+"</option>";
			}
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
				$("#endTime").val(null);
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
		$(this).closest('.result_item').find('.tab_item_box').find('.group_btn_item').removeClass('is-darkgreen-btn');
		$(this).closest('.result_item').find('.tab_item_box').find('.group_btn_item').find('input').prop("checked", false);
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
	/*$('.original_result_btn').on('click', function(){*/
		$('.dashboard_box').empty();
		$('.chart_video_header').removeClass("none");
	/*})*/
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
        $(this).parent().find(".remarks_wrap").slideToggle(200);
        $(this).find(".remarks_title").toggleClass("rotate");
    })
}

// 숫자 3자리수 마다 comma
function numberComma(number) {
	if(number == null) return 0;
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function mapPopupClose() {
	$(".mapboxgl-popup").remove()
}
function openRoutePrdctnInfo(){
	const html = `
		<img src="/statics/images/route_prdctn_01.jpg" style="display:block;width:100%;max-width:1200px;"/>
		<img src="/statics/images/route_prdctn_02.jpg" style="display:block;width:100%;max-width:1200px;"/>
	`;
	new ModalBuilder().init().body(html).footer('FOOTER_ONE_BUTTON','확인',function(button, modal){
		modal.close();
	}).open();
}
function openPopulationPrdctnInfo(){
	const html = `
		<img src="/statics/images/population_prdctn_01.jpg" style="display:block;width:100%;max-width:1200px;"/>
		<img src="/statics/images/population_prdctn_02.jpg" style="display:block;width:100%;max-width:1200px;"/>
	`;
	new ModalBuilder().init().body(html).footer('FOOTER_ONE_BUTTON','확인',function(button, modal){
		modal.close();
	}).open();
}
function openBusArrivePrdctnInfo(){
	const html = `
		<img src="/statics/images/bus_arv_prdctn_01.jpg" style="display:block;width:100%;max-width:1200px;"/>
		<img src="/statics/images/bus_arv_prdctn_02.jpg" style="display:block;width:100%;max-width:1200px;"/>
	`;
	new ModalBuilder().init().body(html).footer('FOOTER_ONE_BUTTON','확인',function(button, modal){
		modal.close();
	}).open();
}
function openSvcCongestionCalcInfo(){
	const html = `
		<ul style="list-style: circle;margin-left: 20px;">
			<li style="margin-bottom:10px;">경기도 자체 관리하는 서비스 제공구간 1시간단위 평균 속도 데이터 활용(1개월 집계)</li>
			<li style="margin-bottom:10px;">일평균 2시간 이상 정체가 발생하는 구간</li>
			<li style="margin-bottom:10px;">
			산출 방법
			<p style="margin-top: 5px;line-height: 1.4em;padding: 5px;background: #fff7cb;padding: 10px;text-align: center;    border-radius: 5px;">“일일 총 정체시간 = 정체건수 / 단위기간(일)”<br/>
     		“정체건수 : 경기도 자체 관리하는 서비스 제공구간 1시간단위 평균 속도 데이터의 단위기간(일)동안 발생한 정체건수”</p>
			</li>
		</ul>
		<table class="popup_table">
		<thead>
		<tr>
		<th>정의</th>
		<th>정체</th>
		</tr>
		</thead>
		<tbody>
		<tr>
		<td>고속도로</td>
		<td>40km/h 미만</td>
		</tr>
		<tr>
		<td>도시고속도로</td>
		<td>30km/h 미만</td>
		</tr>
		<tr>
		<td>국도</td>
		<td>20km/h 미만</td>
		</tr>
		<tr>
		<td>지방도,시내부</td>
		<td>15km/h 미만</td>
		</tr>
		</tbody>
	`;
	new ModalBuilder().init().body(html).footer('FOOTER_ONE_BUTTON','확인',function(button, modal){
		modal.close();
	}).open();
}
function openCongestionGradeInfo(){
	const html = `
		<table class="popup_table">
		<thead>
		<tr>
		<th>도로등급</th>
		<th>속도</th>
		<th>소통등급</th>
		</tr>
		</thead>
		<tbody>
		<tr>
		<th rowspan="3">고속도로</th>
		<td>0 ~ 29 km/h</td>
		<td>정체</td>
		</tr>
		<tr>
		<td>30 ~ 69 km/h</td>
		<td>지체(서행)</td>
		</tr>
		<tr>
		<td>70 km/h ~</td>
		<td>원활</td>
		</tr>
		<tr>
		<th rowspan="3">도시고속도로</th>
		<td>0 ~ 29 km/h</td>
		<td>정체</td>
		</tr>
		<tr>
		<td>30 ~ 49 km/h</td>
		<td>지체(서행)</td>
		</tr>
		<tr>
		<td>50 km/h ~</td>
		<td>원활</td>
		</tr>
		<tr>
		<th rowspan="3">그 외</th>
		<td>0 ~ 19 km/h</td>
		<td>정체</td>
		</tr>
		<tr>
		<td>20 ~ 29 km/h</td>
		<td>지체(서행)</td>
		</tr>
		<tr>
		<td>30 km/h ~</td>
		<td>원활</td>
		</tr>
		</tbody>
		</table>`;
	new ModalBuilder().init().body(html).footer('FOOTER_ONE_BUTTON','확인',function(button, modal){
		modal.close();
	}).open();
}
function openWarningFilter(){
	const filter = window.map.getSavedWarningFilter();
	const html = `
		<table id="warningFilterTable" class="popup_table">
		<thead>
		<tr>
		<th>수집원</th>
		<th>ON/OFF</th>
		</tr>
		</thead>
		<tbody>
		<tr>
		<td>경기도교통정보센터</td>
		<td>
		<label class="flex-center">
			<input role="switch" type="checkbox" class="warning_filter_switch facility_input" name="GITS" ${filter.indexOf("GITS") > -1 ? "" : "checked"}>
		</label>
		</td>
		</tr>
		<tr>
		<td>도로교통공단</td>
		<td>
		<label class="flex-center">
			<input role="switch" type="checkbox" class="warning_filter_switch facility_input" name="UTIC" ${filter.indexOf("UTIC") > -1 ? "" : "checked"}>
		</label>
		</td>
		</tr>
		<tr>
		<td>T-map</td>
		<td>
		<label class="flex-center">
			<input role="switch" type="checkbox" class="warning_filter_switch facility_input" name="SK" ${filter.indexOf("SK") > -1 ? "" : "checked"}>
		</label>
		</td>
		</tr>
		<tr>
		<td>한국도로공사</td>
		<td>
		<label class="flex-center">
			<input role="switch" type="checkbox" class="warning_filter_switch facility_input" name="EX" ${filter.indexOf("EX") > -1 ? "" : "checked"}>
		</label>
		</td>
		</tr>
		<tr>
		<td>경기소방본부</td>
		<td>
		<label class="flex-center">
			<input role="switch" type="checkbox" class="warning_filter_switch facility_input" name="119" ${filter.indexOf("119") > -1 ? "" : "checked"}>
		</label>
		</td>
		</tr>
		<tr>
		<td>경기도 내 터널</td>
		<td>
		<label class="flex-center">
			<input role="switch" type="checkbox" class="warning_filter_switch facility_input" name="SISUL" ${filter.indexOf("SISUL") > -1 ? "" : "checked"}>
		</label>
		</td>
		</tr>
		</tbody>
		</table>`;
	new ModalBuilder().init().body(html).footer(2,'저장',function(button, modal){
		let filter = [];
		$("#warningFilterTable").find("input.warning_filter_switch").each(function(){
			if(!$(this).is(":checked")) {
				filter.push($(this).attr("name"));
				if($(this).attr("name") === "UTIC") {
					filter.push("UTIS");
				}
			}
		});
		window.map.setSavedWarningFilter(filter);
		modal.close();
	}).open();
}


//30분 로그아웃

function resetTimer() {
	var loginNotTiem = $('body section');
	var pageUrl = window.location.href;
	if(pageUrl.indexOf('monitoring') > -1){

	} else if(loginNotTiem.hasClass('login_container')){

	} else {
		GGITS_WORKER.postMessage({
			"event" : "LOGIN.TIMEOUT_START"
		});
	}
}
GGITS_WORKER.onmessage = function(e){
	switch(e.data.event) {
		case "LOGIN.INTERVAL_1000" :
			const min = e.data.min;
			const sec = e.data.sec;
			modalAlertClose();
			new ModalBuilder().init().alertBoby(+ min +"분"+ sec + "초 후에 로그아웃됩니다.").footer(5,'연장하기',function(button, modal){
				GGITS_WORKER.postMessage({
					"event" : "LOGIN.TIMEOUT_START"
				});
				$.ajax({
					type : "get",
					url : __contextPath__ + "/common/update/sessionTime.ajax",
					success : function(data) {
						if(data.code == '200'){
							window.location.reload();
						}
					}
				});
				modalAlertClose()
			},'로그아웃',function(button, modal){
				GGITS_WORKER.postMessage({
					"event" : "LOGIN.TIMEOUT_START"
				});
				$.ajax({
					type : "get",
					url : __contextPath__ + "/logout.ajax",
					success : function(data) {
						location.href = __contextPath__ + "/login.do";
					},complete : function(){
						location.href = __contextPath__ + "/login.do";
					},error : function(){
						location.href = __contextPath__ + "/login.do";
					}
				});
			}).open();
			break;
		case "LOGIN.DO_LOGOUT" :
			$.ajax({
				type : "get",
				url : __contextPath__ + "/logout.ajax",
				success : function(data) {
					window.location.href = __contextPath__ + "/login.do";
				},
				complete : function(){
					window.location.href = __contextPath__ + "/login.do";
				}
			});
			break;
		case "EXCEL.GENERATE" :
			endLoading();
			let blob = new Blob([e.data.uint8], {type:"application/octet-stream"});
			let url = URL.createObjectURL(blob);
			let a = document.createElement("a");
			a.download = e.data.filename;
			a.href = url;
			document.body.appendChild(a);
			a.click();
			break;
	}
}

window.onload = resetTimer;
window.onclick = resetTimer;

//결과보기 연속 클릭 방지
function resultChange(){
	
    var newButton = $(`<button type="button" class="is-dark-btn result_completion">결과보기</button>`)
    $('.original_result_btn').addClass('none');
    $('.bottom_btn').append(newButton);

	$('.result_completion').on('click', function(){
   		new ModalBuilder().init().alertBoby("검색 조건을 변경 후 다시 결과보기를 진행해 주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
   		modalAlertWrap();		
	})

	$('.result_change').on('change', function(){
		$('.result_completion').remove();
		$('.original_result_btn').removeClass('none');
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
    return (value === undefined || value === null || value === '') ? true : false;
}

function dateFormatHH24MI(paramDate){
	var date = new Date(paramDate);
	var hh = date.getHours();
	var mi = date.getMinutes();
	if(mi < 10){
		mi = "0"+mi;
	}
	return hh+"시 "+mi+"분";
	 
}
function getDongListBySggCd(sggCd, targetSelector){
	return new Promise(function(resolve, reject){
		$.ajax({
			type : "get",
			url : __contextPath__+"/common/getDongList.ajax?sggCd="+sggCd,
			success : function(list){
				if($(targetSelector).length > 0){
					let html = "<option value=''>전체</option>";
					for(dongNm of list) {
						html += `<option value="${dongNm.adstdgNm}">${dongNm.adstdgNm}</option>`;
					}
					$(targetSelector).html(html);
				}
				resolve(list);
			}
		})
	});
}

function fnDownloadExcelWorker({exportType, header, metadata, rows, filename}){
	startLoading();
	GGITS_WORKER.postMessage({
		exportType : exportType ? exportType : "default",
		header : header,
		metadata, metadata,
		rows, rows,
		filename : filename,
		event : "EXCEL.GENERATE"
	})
}
function fnDownloadExcelChartWorker(chartId,filename="차트엑셀", isReverse = false){
	const data = Chart.getChart(chartId).data;
	let rows = [];
	let header = []
	if(isReverse){
		header = data.datasets.reduce(function(prev, cur){
			prev.push(cur.label);
			return prev;
		}, [""])
		rows = data.labels.reduce(function(prev, cur, index){
			let row = [];
			row.push(cur);
			for (const dataset of data.datasets) {
				row.push(dataset.data[index])
			}
			prev.push(row);
			return prev;
		},[]);
	}else {
		const label = data.labels;
		const datasets = data.datasets;
		label.unshift(" ");
		header = label;
		for (const dataset of datasets) {
			let datasetData = [...dataset.data]
			datasetData.unshift(dataset.label);
			rows.push(datasetData);
		}
	}
	fnDownloadExcelWorker({
		exportType :"chartJs",
		header : header,
		metadata : null,
		rows : rows,
		filename : filename
	})

}
/*차트캔버스 이미지로 다운로드 기능*/
function fnDownloadChartImage(canvasId, filename="chart image"){
	const MAX_WIDTH = 400;
	const MAX_HEIGHT = 300;
	const img = new Image();
	img.crossOrigin = '';
	img.onload = () => {
		const wRatio = MAX_WIDTH / img.width;
		const hRatio = MAX_HEIGHT / img.height;
		let width, height;
		if(wRatio > hRatio) {
			width = MAX_WIDTH;
			height = wRatio * img.height;
		}
		else {
			width = hRatio * img.width;
			height = MAX_HEIGHT;
		}
		const canvas = document.createElement('canvas');
		canvas.width = width;
		canvas.height = height;
		const ctx = canvas.getContext('2d');
		ctx.fillStyle = "rgba(0,0,0,0.5)";
		ctx.fillRect(0, 0, canvas.width, canvas.height);
		ctx.drawImage(img, 0, 0, width, height);
		//const finalImage = b64toFile(canvas.toDataURL("image/jpg"));
		const imgElement = document.createElement('img');
		imgElement.src = canvas.toDataURL('image/jpg');
		//document.body.appendChild(imgElement);
		let a = document.createElement('a');
		a.href = canvas.toDataURL('image/png',1);
		a.download = filename+'.png';
		a.click();
	};
	img.src = document.getElementById(canvasId).toDataURL('image/png',1);
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
								'<td><input type="radio" id="listItem'+index+'" name="searchCrossroadsLinkId" class="crossroads_list_radio" value="'+item.nodeId+'"/></td>'+
								'<td>'+'<label for="listItem'+index+'">'+item.rownum+'</label>'+'</td>'+
								'<td>'+'<label for="listItem'+index+'">'+item.crsrdNm+'</label>'+'</td>'+
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

function col_format(value){
	return value.replace(/[^a-zA-Z0-9_-]/g,'');
}

function keyupColEvent(_this){
	let value = _this.value;
	_this.value = col_format(value);
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

// history back 방지
window.onpageshow = function(event){
    if(event.persisted || (window.performance && window.performance.navigation.type == 2)){
       $("#mapLoadingLayer").fadeOut(250, function () {
            $("#mapLoadingLayer").remove();
        });
    }
}

// 로딩화면
startLoading = function(){
    if($("#mapLoadingLayer").length === 0) {
        const layer = $(`<div id="mapLoadingLayer"><img src="/statics/images/loading_loop.gif"/></div>`);
        $("body").append(layer);
    }
}
endLoading = function(){
    if(window.map && window.map.getJobList().length == 0) {
        $("#mapLoadingLayer").fadeOut(250, function () {
            $("#mapLoadingLayer").remove();
        });
    }else {
		$("#mapLoadingLayer").fadeOut(250, function () {
			$("#mapLoadingLayer").remove();
		});
	}
}

// 영향평가 로딩화면
impactEndLoading = function(){
    $("#mapLoadingLayer").fadeOut(250, function () {
        $("#mapLoadingLayer").remove();
    });
}

const slideToggleByTarget = function(target) {
	if($(target).is(":visible")) {
		$(target).slideUp();
	}else{
		$(target).slideDown();
	}
}

const toggleClassByTarget = function(target, classname) {
	if($(target).hasClass(classname)) {
		$(target).removeClass(classname);
	}else{
		$(target).addClass(classname);
	}
}

//2023-11-16 NK.KIM 빅데이터 검색 세팅
var bigdataSearchForm;
function settingBigdataSearchParam(type){
	if(!isNull(bigdataSearchForm)){
		
		var pageType = bigdataSearchForm.pageType;
		if(type == pageType){
			//검색 년도
			var searchYear = bigdataSearchForm.searchYear;
			if(!isNull(searchYear)){
				$("select[name='searchYear']").val(searchYear);
			}
			
			//기간
			var searchPeriod = bigdataSearchForm.searchPeriod;
			if(!isNull(searchPeriod)){
				var searchPeriodInput = $("input[name='searchPeriod']");
				if(searchPeriodInput.attr("type") != 'hidden'){
					searchPeriodInput.parent().removeClass("is-darkgreen-btn");
					searchPeriodInput.attr("checked",false);
					searchPeriodInput.each(function(idx,item){
						if($(item).val() == searchPeriod){
							$(item).parent().addClass("is-darkgreen-btn");
							$(item).prop("checked",true);
							$("#directDate").addClass("none");
							if($(item).val() == 'directDate'){
								$("#directDate").removeClass("none");
							}
						}
					})
				}
			}
			//기간 시작 날짜
			var startDate = bigdataSearchForm.startDate;
			if(!isNull(startDate)){
				$("input[name='startDate']").val(startDate)
			}
			//기간 종료 날짜
			var endDate = bigdataSearchForm.endDate;
			if(!isNull(endDate)){
				$("input[name='endDate']").val(endDate)
			}
						
			//시간
			var searchTime = bigdataSearchForm.searchTime;
			if(!isNull(searchTime)){
				var searchTimeInput = $("input[name='searchTime']");
				searchTimeInput.parent().removeClass("is-darkgreen-btn");
				searchTimeInput.attr("checked",false);
				searchTimeInput.each(function(idx,item){
					if($(item).val() == searchTime){
						$(item).parent().addClass("is-darkgreen-btn");
						$(item).prop("checked",true);
						$("#directTime").addClass("none");
						if(searchTime == 'directTime'){
							$("#directTime").removeClass("none");
						}
					}
				})
			}
			var startTime = bigdataSearchForm.startTime;
			if(!isNull(startTime)){
				$("select[name='startTime']").val(startTime)
			}
			var endTime = bigdataSearchForm.endTime;
			if(!isNull(endTime)){
				$("select[name='endTime']").val(endTime)
			}
			
			//지역별
			var searchLocation = bigdataSearchForm.searchLocation;
			if(!isNull(searchLocation)){
				$("select[name='searchLocation']").val(searchLocation);
			}
			
			//도로
			var searchRoadRank = bigdataSearchForm.searchRoadRank;
			if(!isNull(searchRoadRank)){
				var searchRoadRankArr = searchRoadRank.split(",");
				if(searchRoadRankArr.length < 8){
					$("#searchAllRoadRankBtn").removeClass("is-darkgreen-btn");
					var roadRankInput = $("input[name='roadRank']");
					roadRankInput.parent().removeClass("is-darkgreen-btn");
					roadRankInput.each(function(idx,item){
						var _thisVal = $(item).val();
						if(searchRoadRankArr.includes(_thisVal)){
							$(item).parent().addClass("is-darkgreen-btn");
						}
					})
				}
			}
			
			//집계기준
			var searchResultType = bigdataSearchForm.searchResultType;
			if(!isNull(searchResultType)){
				if(pageType == 'BD_PB_TRANS_007'){
					$("select[name='searchResultType']").val(searchResultType);					
				}else{
					var searchResultTypeInput = $("input[name='searchResultType']");
					searchResultTypeInput.parent().removeClass("is-darkgreen-btn");
					searchResultTypeInput.each(function(idx,item){
						if($(item).val() == searchResultType){
							$(item).parent().addClass("is-darkgreen-btn");
							$(item).attr("checked",true);		
						}
					})
				}
			}
			
			//도로 위험 유형별
			var dangerType = bigdataSearchForm.dangerType;
			if(!isNull(dangerType)){
				$("select[name='dangerType']").val(dangerType);
			}
				
			//사고 유형별
			var accidentType = bigdataSearchForm.accidentType;
			if(!isNull(accidentType)){
				$("select[name='accidentType']").val(accidentType);
			}
				
			//지역별
			var sigunCdId = bigdataSearchForm.sigunCdId;
			if(!isNull(sigunCdId)){
				$("select[name='sigunCdId']").val(sigunCdId);
			}	
			
			//검색어
			var searchContent = bigdataSearchForm.searchContent;
			if(!isNull(searchContent)){
				$("input[name='searchContent']").val(searchContent);
				fnSearchList();
			}
			var routeNm = bigdataSearchForm.routeNm;
			if(!isNull(routeNm)){
				$("input[name='routeNm']").val(routeNm);
				fnSearchList();
			}
			
			//노선찾기 > 출발지/도착지	
			var startStationNm = bigdataSearchForm.startStationNm;
			var endStationNm = bigdataSearchForm.endStationNm;
			if(!isNull(startStationNm) && !isNull(endStationNm)){
				$("input[name='startStationNm']").val(startStationNm);
				$("input[name='endStationNm']").val(endStationNm);
				var routeId = bigdataSearchForm.routeId
				var endStationId = bigdataSearchForm.endStationId
				var startStationId = bigdataSearchForm.startStationId
				$("#routeId").val(routeId)
				$("#endStationId").val(endStationId)
				$("#startStationId").val(startStationId)
				var page = bigdataSearchForm.page;
				pageMove(page);
				
				
			}
			
			//정류장 찾기
			var searchContent = bigdataSearchForm.searchContent;
			if(!isNull(searchContent)) {
				$("input[name='searchContent']").val(searchContent);
				// TODO:: checked 값 주기
				var page = bigdataSearchForm.page;
				pageMove(page);
			}
		}	
	}


}
