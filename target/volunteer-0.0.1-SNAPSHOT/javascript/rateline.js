$(function () {
	$.ajax({
		type:'POST',
		dataType:'JSON',
		url:'${pageContext.request.contextPath}/getEverRateAction.action',
			success:function(result){
			    $('#rateline').highcharts({
			        chart: {
			            type: 'line'
			        },
			        title: {
			        	  text: '到勤率折线图'
			        },
			        subtitle: {
			        	  text: '来源: 每天记录的信息'
			        },
			        xAxis: {
			        	categories:result.listXdata 
			        },
			        yAxis: {
			            title: {
			                text: '占比(%)'
			            }
			        },
			        tooltip: {
			            enabled: true,
			            formatter: function() {
			                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'%';
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
