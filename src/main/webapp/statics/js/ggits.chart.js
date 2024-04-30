// 차트 라이브러리 커스텀

const GITSChartType = {POLAR : "polarArea", SCATTER : "scatter",RADAR : "radar", BAR : "bar", LINE : "line", DOUGHNUT : "doughnut", MATRIX : "matrix"};
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
    let _isViewLabel = false;
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
                        tickColor:'transparent',
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
                        tickColor:'transparent',
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
                        padding:10,
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
                    radius: 2,
                },
            },
			options:{
				indexAxis:'y',
			},
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize:1,
                        color :'#fff',
						font: {
							family:'Pretendard',
						},
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'transparent'
                    }
                },
                x: {
                    beginAtZero: true,
                    ticks: {
                        color:'#fff',
						font: {
							family:'Pretendard',
						}
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'transparent',
                        display:false
                    }
                },
            },
            plugins: {
                legend: {
                    position:'bottom',
                    labels: {
						padding:10,
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
						padding:10,
                        color:"#fff",
						boxWidth:16,
						font: {
							size:12,
						}
                    }
                },
            },
		},
        polarArea : {
            animation: {
                duration: 0
            },
            maintainAspectRatio :false,
            plugins: {
                legend: {
                    position:'bottom',
                    labels: {
                        padding:10,
                        color:"#fff",
                        boxWidth:16,
                        font: {
                            size:12,
                        }
                    }
                },
            },
        },
        scatter : {
            animation: {
                duration: 0
            },
            maintainAspectRatio :false,
            elements: {
                point: {
                    radius: 2,
                },
            },
            options:{
                indexAxis:'y',
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize:1,
                        color :'#fff',
                        font: {
                            family:'Pretendard',
                        },
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'transparent'
                    }
                },
                x: {
                    beginAtZero: true,
                    ticks: {
                        color:'#fff',
                        font: {
                            family:'Pretendard',
                        }
                    },
                    grid: {
                        color:'rgba(255, 255, 255, 0.44)',
                        tickColor:'transparent',
                        display:false
                    }
                },
            },
            plugins: {
                legend: {
                    position:'bottom',
                    labels: {
                        padding:10,
                        color:"#fff",
                        boxWidth:16,
                        font: {
                            size:12,
                        }
                    }
                },
            },
        },
        radar : {
            animation: {
                duration: 0
            },
            maintainAspectRatio :false,
            plugins: {
                legend: {
                    position:'bottom',
                    labels: {
                        padding:10,
                        color:"#fff",
                        boxWidth:16,
                        font: {
                            size:12,
                        }
                    }
                },
            },
        },
        matrix :{
            aspectRatio: 5,
            plugins: {
                legend: false,
                tooltip: {
                    displayColors: false,
                    callbacks: {
                        title() {
                            return '';
                        },
                        label(context) {
                            const v = context.dataset.data[context.dataIndex];
                            return ['v: ' + v];
                        }
                    }
                },
            },
            scales: {
                x: {
                    type: 'category',
                    labels: ['00:00', '01:00', '02:00'],
                    ticks: {
                        display: true,
						color:'#fff',
						font: {
							family:'Pretendard',
						},
                    },
                    grid: {
                        display: false
                    }
                },
                y: {
                    type: 'category',
                    labels: ['화양로', '삼평로', '여기로'],
                    offset: true,
                    ticks: {
						color:'#fff',
                        display: true,
						font: {
							family:'Pretendard',
						},
                    },
                    grid: {
                        display: false
                    }
                }
            }
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
    _self.setLittleFontSize = function() {
        _opt = $.extend(_opt, {
            scales : {
                y: {
                    ticks: {
                        font: {
                            size: 10
                        }
                    }
                },
                x: {
                    ticks: {
                        font: {
                            size: 10
                        }
                    }
                },
            },
            plugins: {
                legend: {
                    labels: {
                        font: {
                            size:10,
                        }
                    },

                },
            }
        }, {});
        return _self;
    }
    _self.setViewLabel = function(){
        _isViewLabel = true;
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
        }catch(e) {console.error('option error')}
        return _self;
    }

	// tick color
	_self.setTickColorX = function(tickColorX) {
		try {
			_opt.scales.x.grid.tickColor = tickColorX
		}catch(e) {console.error('option error')}
		return _self;
	}
	_self.setTickColorY = function(tickColorY) {
		try {
			_opt.scales.y.grid.tickColor = tickColorY
		}catch(e) {console.error('option error')}
		return _self;
	}
	_self.setTickX = function(tickX) {
		try {
			_opt.scales.x.ticks.color = tickX
		}catch(e) {console.error('option error')}
		return _self;
	}
	_self.setTickY = function(tickY) {
		try {
			_opt.scales.y.ticks.color = tickY
		}catch(e) {console.error('option error')}
		return _self;
	}
	//y축 ticks display
	_self.setTickDisplayY = function(displayY) {
		try {
			_opt.scales.y.ticks.display = displayY
		}catch(e) {console.error('option error')}
		return _self;
	}

    // 라벨 온오프
    _self.setLabelDisplay = function(display) {
        try{
            _opt.plugins.legend.display = display;
        }catch(e) {console.error('option error')}
        return _self;
    }

	_self.setLabelPadding = function(padding) {
		try {
			_opt.plugins.legend.labels.padding = padding
		}catch(e) {console.error('option error')}
		return _self;
	}
	
	_self.SetMaxWidth = function(width) {
		try {
			_opt.maxBarThickness = width
		}catch(e) {console.error('option error')}
		return _self;
	}
    // Y축 stepSize
    _self.setTicksStep = function(stepSize) {
        try {
            _opt.scales.y.ticks.stepSize = stepSize
        } catch(e) {console.error('option error')}
        return _self;
    }

    // X축 stepSize
    _self.setTickStepX = function(stepSizeX) {
        try {
            _opt.scales.x.ticks.stepSize = stepSizeX
        } catch(e) {console.error('option error')}
        return _self;
    }

    // 축 Axis
    _self.setAxis = function(indexAxis) {
        try {
            _opt.indexAxis = indexAxis
        } catch(e) {console.error('option error')}
        return _self;
    }

    // X축 data 누적
    _self.setAxisStackedX = function(stackedX) {
        try {
            _opt.scales.x.stacked = stackedX
        } catch(e) {console.error('option error')}
        return _self;
    }

    // Y축 data 누적
    _self.setAxisStackedY = function(stackedY) {
        try {
            _opt.scales.y.stacked = stackedY
        } catch(e) {console.error('option error')}
        return _self;
    }

    // grid bar Y축
    _self.setBarGridY = function(barGridY) {
        try {
            _typeOpts.bar.scales.y.grid.display = barGridY
        } catch(e) {console.error('option error')}
        return _self;
    }

    // grid bar X축
    _self.setBarGridX = function(barGridX) {
        try {
            _typeOpts.bar.scales.x.grid.display = barGridX
        } catch(e) {console.error('option error')}
        return _self;
    }

    // X축 display
    _self.setDisplayX = function(Xdisplay) {
        try {
            _typeOpts.bar.scales.x.display = Xdisplay
        } catch(e) {console.error('option error')}
        return _self;
    }
    
    // Y축 display
    _self.setDisplayY = function(Ydisplay) {
        try {
            _typeOpts.bar.scales.y.display = Ydisplay
        } catch(e) {console.error('option error')}
        return _self;
    }

	_self.setPoint = function(point) {
		try {
			_typeOpts.line.elements.point.radius = point
		} catch(e) {console.error('option error')}
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

	//lable name change
	_self.labelCallback = function(item) {
		try {
			_opt.scales.y.ticks.callback = item 
		} catch(e) {console.error('option error')}
		return _self;
	}

    //차트 호출
    _self.draw = function(){
        if(typeof Chart === "undefined") {
            console.error("chart.js가 import 되어 있지 않습니다.");
            return;
        }
        let obj = {
            type:_type,
            data: _data,
            options: _opt
        };
        if(_isViewLabel) obj.plugins = [ChartDataLabels];
        return new Chart(_element, obj);
    }

	_self.setLabelPosition = function(position) {
		try {
		_opt.plugins.legend.position = position
		} catch(e) {console.error('option error')}
		return _self;
	}
}