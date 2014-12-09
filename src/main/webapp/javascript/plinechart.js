$(function () {
	$.ajax({
		type:'POST',
		dataType:'JSON',
		url:'${pageContext.request.contextPath}/getPlineDataAction.action',
			success:function(result){
			    $('#plinechart').highcharts({
			        chart: {
			            type: 'line'
			        },
			        title: {
			            text: '个人在线时长折线图'
			        },
			        subtitle: {
			            text: ''
			        },
			        xAxis: {
			            categories: result.listXdata,
//			            labels: {rotation: -25,	align: 'right',	style: { font: 'normal 1px Verdana, sans-serif'}}
			        },
			        yAxis: {
			            title: {
			                text: '时长(小时)'
			            }
			        },
			        tooltip: {
			            enabled: true,
			            formatter: function() {
			                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'h';
			            }
			        },
			        plotOptions: {
			            line: {
			                dataLabels: {
			                    enabled: false
			                },
			                enableMouseTracking: true
			            }
			        },
			        series: result.data
			    });
			}
    });
});				