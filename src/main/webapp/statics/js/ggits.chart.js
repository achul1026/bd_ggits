// 차트 라이브러리 커스텀

const GITSChartType = {BAR : "bar", LINE : "line", DOUGHNUT : "doughnut"};
let GITSChart = function(type){
    //GITSChart 의 function
    let _self = this;

    // 받아올 ID값
    let _element = null;

    // data 값
    let _data = {};

    // labels 값
    let _labels = [];

    // data와 부가 옵션
    let _datasets = [];

    // bar인지 line인지
    const _type = type;

    //옵션
    let _opt = {};

    // bar, line 의 옵션
    let _typeOpts = {
        bar : {
            animation: {
                duration: 0
            },
            maintainAspectRatio :false,
            scales: {
                y: {
                    beginAtZero: true,
                    stacked:true,
                    ticks: {
                        stepSize:2,
                        color :'#fff',
						font: {
							family:'Pretendard',
						}
			
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'black',
                        display:false
                    }
                },
                x: {
                    beginAtZero: true,
                    stacked:true,
                    ticks: {
                        color:'#fff',
						font: {
							family:'Pretendard',
						}
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'black',
                        display:false
                    }
                },
            },
            plugins: {
                legend: {
                    position:'bottom',
                    usePointStyle:true,
                    labels: {
                        color:"#fff",
                        padding:5,
						boxWidth:16,
						font: {
							family:'Pretendard',
							size:12,
						}
                    },
                    
                },
            },
        },
        line : {
            animation: {
                duration: 0
            },
            maintainAspectRatio :false,
            elements: {
                point: {
                    radius: 0,
                },
            },
            scales: {
                y: {
                    beginAtZero: false,
                    ticks: {
                        stepSize:1,
                        color :'#fff',
						font: {
							family:'Pretendard',
						}
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'black'
                    }
                },
                x: {
                    beginAtZero: false,
                    ticks: {
                        color:'#fff',
						font: {
							family:'Pretendard',
						}
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'black',
                        display:false
                    }
                },
            },
            plugins: {
                legend: {
                    position:'bottom',
                    labels: {
                        color:"#fff",
						boxWidth:16,
						font: {
							size:12,
						}
                    }
                },
            },
        },
		doughnut : {
            animation: {
                duration: 0
            },
			maintainAspectRatio :false,
            plugins: {
                legend: {
                    position:'bottom',
                    labels: {
                        color:"#fff",
						boxWidth:16,
						font: {
							size:12,
						}
                    }
                },
            },
		}
    }

    const clearData = function(){
        let _element = null;
        let _data = {};
        let _labels = [];
        let _datasets = [];
        let _opt = {};
    }

    // return _self <- 체인함수
    //id 값 받아오기
    _self.init = function(elementId) {
        clearData();
        _element = document.getElementById(elementId);
        _opt = _typeOpts[_type];
        return _self;
    }

    // 옵션 값 받아오기
    _self.setOption = function(opt) {
        _opt = $.extend(_opt, opt, {});
        return _self;
    }

    //데이터값 받아오기
    _self.setData = function(data) {
        _data = data;
        return _self;
    }

    //es6 분법  args 여러 매개변수를 받아온다
    _self.setDataSetLabel = function(...args) {
		
        _data = {};
        _labels = args;
		_data.labels = _labels;
        return _self;
    }

 	_self.setDataSetArrayLabel = function(array) {
        _data = {};
        _labels = array;
		_data.labels = array;
        return _self;
    }

 	_self.setDataSetArrayLabel = function(array) {
        _data = {};
        _labels = array;
		_data.labels = array;
        return _self;
    }

    // 그리드컬러변경
    _self.setGridColor = function(color) {
        try{
            _opt.scales.y.grid.color = color;
        }catch(e) {}
        return _self;
    }

	// tick color
	_self.setTickColorX = function(tickColorX) {
		try {
			_opt.scales.x.grid.tickColor = tickColorX
		}catch(e) {}
		return _self;
	}
	_self.setTickColorY = function(tickColorY) {
		try {
			_opt.scales.y.grid.tickColor = tickColorY
		}catch(e) {}
		return _self;
	}
	_self.setTickX = function(tickX) {
		try {
			_opt.scales.x.ticks.color = tickX
		}catch(e) {}
		return _self;
	}
	_self.setTickY = function(tickY) {
		try {
			_opt.scales.y.ticks.color = tickY
		}catch(e) {}
		return _self;
	}

    // 라벨 온오프
    _self.setLabelDisplay = function(display) {
        try{
            _opt.plugins.legend.display = display;
        }catch(e) {}
        return _self;
    }

	_self.setLabelPadding = function(padding) {
		try {
			_opt.plugins.legend.labels.padding = padding
		}catch(e) {}
		return _self;
	}
	
	
	_self.SetMaxWidth = function(width) {
		try {
			_opt.maxBarThickness = width
		}catch(e) {}
		return _self;
	}
    // Y축 stepSize
    _self.setTicksStep = function(stepSize) {
        try {
            _opt.scales.y.ticks.stepSize = stepSize
        } catch(e) {}
        return _self;
    }

    // X축 stepSize
    _self.setTickStepX = function(stepSizeX) {
        try {
            _opt.scales.x.ticks.stepSize = stepSizeX
        } catch(e) {}
        return _self;
    }

    // 축 Axis
    _self.setAxis = function(indexAxis) {
        try {
            _opt.indexAxis = indexAxis
        } catch(e) {}
        return _self;
    }

    // X축 data 누적
    _self.setAxisStackedX = function(stackedX) {
        try {
            _opt.scales.x.stacked = stackedX
        } catch(e) {}
        return _self;
    }

    // Y축 data 누적
    _self.setAxisStackedY = function(stackedY) {
        try {
            _opt.scales.y.stacked = stackedY
        } catch(e) {}
        return _self;
    }

    // grid bar Y축
    _self.setBarGridY = function(barGridY) {
        try {
            _typeOpts.bar.scales.y.grid.display = barGridY
        } catch(e) {}
        return _self;
    }

    // grid bar X축
    _self.setBarGridX = function(barGridX) {
        try {
            _typeOpts.bar.scales.x.grid.display = barGridX
        } catch(e) {}
        return _self;
    }

    // X축 display
    _self.setDisplayX = function(Xdisplay) {
        try {
            _typeOpts.bar.scales.x.display = Xdisplay
        } catch(e) {}
        return _self;
    }
    
    // Y축 display
    _self.setDisplayY = function(Ydisplay) {
        try {
            _typeOpts.bar.scales.y.display = Ydisplay
        } catch(e) {}
        return _self;
    }

	_self.setPoint = function(point) {
		try {
			_typeOpts.line.elements.point.radius = point
		} catch(e) {}
		return _self;
	}
    


    //데이터 넣기
    _self.setDataSet = function(...args){
        // if(_labels.length !== args.length) {
        //     alert("라벨과 데이터셋 갯수가 다릅니다.");
        //     return;
        // }
        _datasets = args;
        _data.datasets = _datasets;
        return _self;
    }

    _self.setDataArraySet = function(args){
        // if(_labels.length !== args.length) {
        //     alert("라벨과 데이터셋 갯수가 다릅니다.");
        //     return;
        // }
        _datasets = args;
        _data.datasets = _datasets;
        return _self;
    }

    //차트 호출
    _self.draw = function(){
        if(typeof Chart === "undefined") {
            console.error("chart.js가 import 되어 있지 않습니다.");
            return;
        }
        new Chart(_element, {
            type:_type,
            data: _data,
            options: _opt
        });
    }
}