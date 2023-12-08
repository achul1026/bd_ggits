$(function(){
    //api toggle
    const apiTitle = $(".api_item_title");
    const apiBox = $(".api_toggle_box");
    $(apiTitle).click(function(){
        $(this).siblings(apiBox).slideToggle(300);
        $(this).toggleClass("arrow_change");
    })

    // 신규조사등록 
    $('.upload_btn').click(function(){
        $('.csv_upload').css('display','block');
    })
    $('.csv_close').click(function(){
        $('.csv_upload').css('display','none');
    })
    
    //csv 파일 업로드
    $("#file").on('change',function(){
        let fileName = $("#file").val();
        $(".upload_name").val(fileName);
        });

    //모니터링 차트 버튼 클릭 온
    $(".tab_box_chart_btn ul li label").click(function(){
        $(this).addClass('on');
        $('.tab_box_chart_btn ul li label').not(this).removeClass('on');
    })
    

    $(".event_item_on").click(function(){
        $(".event_item").toggleClass("none");
        $(".event_pop").addClass("none");
		$(this).closest('.tab_box_content').siblings().find('.unex_content').slideUp(200);
		$(this).closest('.tab_box_content').find('.unex_content').slideToggle(200);
    })
	const floatingPopulation = $('.depth1:nth-child(1) .depth2_wrap .depth2_item_box:nth-child(1) .depth2_item:nth-child(7)')
	$(floatingPopulation).on('click', function(){
		$(".density_history").removeClass("none");
	})

    // 데이터수집이력 버튼 클릭
      $(".dash_dark_black").click(function(){
      $(this).addClass('on');
      $('.dash_dark_black').not(this).removeClass('on');
   })
    // 데이터 수집이력 상세 내용 보기
    $(".data_node_item").click(function(){
        $('.dashboard_wrap').css('display',"none");
        $('.dashboard_wrap_detail').css('display',"block");
    })
    $('.prev_icon').click(function(){
        $('.dashboard_wrap').css('display',"block");
        $('.dashboard_wrap_detail').css('display',"none");
    })
    
    
    //데이터 수집 장애이력 클릭
    $('.sub_data_btn').click(function(){
        // alert('asd')
        $(this).addClass('on');
        $('.sub_data_btn').not(this).removeClass('on');
    })
    
    // 대시보드 움직이는거
    $(".sortable").sortable({
        connectWith: ".dashboard_sub_con2" 
    });

    // 대시보드 버튼 클릭
    $('.mini_dash_box_btn label:first-child').addClass('on');
    $('.mini_dash_box_btn label').click(function(){
        $(this).addClass('on');
        $(this).siblings().removeClass('on');
    })

    //통계분석 상세보기 토글버튼 
    $('.search_detail_btn').on('click', function(){
        $('.search_detail_wrap').slideToggle();
       	$(this).find('i').toggleClass('arrow_rotate');
    })

    // 검색필터 검색값 초기화
    $( '.uncheck_all' ).click( function() {
        $("input[type=checkbox]").prop("checked", false);
        $( '.group_btn_item' ).removeClass('is-darkgreen-btn');
     });

    $('.check_delete').click(function(){
        if(confirm("삭제하시겠습니까?")){
            $("input[name=checkRow]:checked").each(function(){
                var tr_value =$(this).val();
                var tr=$("tr[data-tr_value='"+tr_value+"']");
                tr.remove();
            });
        }else{
            return false;
        }
    });

    //datePicker
	previousDay();
})


//정체구간 개선활동
function imporovement(){
    $(".map_fusion_data_arrow").click(function(){
        $(this).closest(".map_fusion_dataitem").toggleClass("active");
        $(this).parent(".fusion_data_titlebox").siblings(".fusion_data_inputbox").slideToggle(100);
        $(this).toggleClass("active");
    })
}

//datePicker
function previousDay(){
	$('#previousDay').datepicker({
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
	});
		
	$('#toDay').datepicker({
		dateFormat:'yy-mm-dd',
		changMonth:true,
		changYear:true,
		nextText:'다음 달',
		prevText:'이전 달',
		dayNames:['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
		dayNamesMin:['월','화','수','목','금','토','일'],
		monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		showMonthAfterYear:true,
		yearSuffix:'년',
	    format: 'YYYY',
		minViewMode: 'years',
	    viewMode: "years", 
		minDate:'0D',
		maxDate:'0D'
	})
}

//설정버튼 누를시 컨트롤러 show/hide
$(function(){
	$('.userinfo_btn').on('click', function(){
		$('.control_container').toggleClass('none');
	})
})


