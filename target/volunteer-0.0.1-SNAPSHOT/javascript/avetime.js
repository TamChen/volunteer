$(function () {
	$.ajax({
		type:'POST',
		dataType:'JSON',
		url:'${pageContext.request.contextPath}/getAverTimeDataAction.action',
			success:function(result){
			    $('#avetime').highcharts({
			        chart: {
			            type: 'line'
			        },
			        title: {
			        	text: '在线平均时长走势图'
			        },
			        subtitle: {
			        	  text: '来源: 每天记录的信息'
			        },
			        xAxis: {
			        	categories:result.listXdata 
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
			                    enabled: true
			                },
			                enableMouseTracking: true
			            }
			        },
			        series: result.data
			    });
			}
    });
});	
