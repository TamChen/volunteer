function uploadCropperPic(){
	var userno=GetQueryString("userno");
	//上传剪裁的图片
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType: "application/json; charset=utf-8",
		url : "ajaxUploderAction!addUserHeadCropperPic.action?dataX="+ $("#dataX").val() 
				+ "&&dataY=" + $("#dataY").val() 
				+ "&&dataWidth="+$("#dataWidth").val()
				+ "&&picPath="+GetQueryString("path")
				+ "&&dataHeight="+$("#dataHeight").val(),
		success : function(result) {
			if(result.issuccess)
				window.location.href="personal/user-setting.jsp?userno="+userno;
		}
	});
}
function ajaxFileUpload(){
	var fileSelect = document.getElementById('fileUpload');
	var files = fileSelect.files;
	 for (var i = 0; i < files.length; i++) {
		 getAjaxData(files,i);
	 }
}
function getAjaxData(files,i){
	var userno=GetQueryString("userno");
//	// 实例化一个表单数据对象  
	var formData = new FormData();
	formData.append('uploadFile',files[i], files[i].name);
	formData.append('filename',files[i].name);
//	// 实例化一个AJAX对象
	var request = new XMLHttpRequest();
//	// 发送表单数据
	request.open("POST", "ajaxUploderAction!uploadUserHead.action", true);
//	// 请求完成时建立一个处理程序。
//	
	request.onload = function () {
		console.log(request.responseText);
		var jsonObj = JSON.parse(request.responseText);
		window.location.href="personal/user-uploadPic.jsp?userno="+userno+"&&path="+jsonObj.path;
	}
//	
	request.send(formData);
	/*alert("hello");*/
}
function cancel(){
	history.go(-1);
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return null;
}
$(function () {
	initialize();//初始化页面（不包括正文内容的其他部分）
	$("#activityimg").html("");
	$("#activityimg").html("<img src="+GetQueryString("path")+" alt='Picture'>");
	$("#activityimg").css("max-width","370px");
	$("#activityimg").css("max-height","370px");
	$("#activityimg").css("width","auto");
	$("#activityimg").css("height","auto");
	
  'use strict';
  var console = window.console || { log: function () {} },
      $alert = $('.docs-alert'),
      $message = $alert.find('.message'),
      showMessage = function (message, type) {
        $message.text(message);

        if (type) {
          $message.addClass(type);
        }

        $alert.fadeIn();

        setTimeout(function () {
          $alert.fadeOut();
        }, 3000);
      };

  // Demo
  // -------------------------------------------------------------------------

  (function () {
    var $image = $('.img-container > img'),
        $dataX = $('#dataX'),
        $dataY = $('#dataY'),
        $dataHeight = $('#dataHeight'),
        $dataWidth = $('#dataWidth'),
        $dataRotate = $('#dataRotate'),
        options = {
          aspectRatio: 1 / 1,
          preview: '.img-preview',
          crop: function (data) {
            $dataX.val(Math.round(data.x));
            $dataY.val(Math.round(data.y));
            $dataHeight.val(Math.round(data.height));
            $dataWidth.val(Math.round(data.width));
            $dataRotate.val(Math.round(data.rotate));
          }
        };

    $image.on({
      'build.cropper': function (e) {
        console.log(e.type);
      },
      'built.cropper': function (e) {
        console.log(e.type);
      },
      'dragstart.cropper': function (e) {
        console.log(e.type, e.dragType);
      },
      'dragmove.cropper': function (e) {
        console.log(e.type, e.dragType);
      },
      'dragend.cropper': function (e) {
        console.log(e.type, e.dragType);
      },
      'zoomin.cropper': function (e) {
        console.log(e.type);
      },
      'zoomout.cropper': function (e) {
        console.log(e.type);
      }
    }).cropper(options);


    // Methods
    $(document.body).on('click', '[data-method]', function () {
      var data = $(this).data(),
          $target,
          result;

      if (data.method) {
        data = $.extend({}, data); // Clone a new one

        if (typeof data.target !== 'undefined') {
          $target = $(data.target);

          if (typeof data.option === 'undefined') {
            try {
              data.option = JSON.parse($target.val());
            } catch (e) {
              console.log(e.message);
            }
          }
        }

        result = $image.cropper(data.method, data.option);

        if (data.method === 'getCroppedCanvas') {
          $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);
        }

        if ($.isPlainObject(result) && $target) {
          try {
            $target.val(JSON.stringify(result));
          } catch (e) {
            console.log(e.message);
          }
        }

      }
    }).on('keydown', function (e) {

      switch (e.which) {
        case 37:
          e.preventDefault();
          $image.cropper('move', -1, 0);
          break;

        case 38:
          e.preventDefault();
          $image.cropper('move', 0, -1);
          break;

        case 39:
          e.preventDefault();
          $image.cropper('move', 1, 0);
          break;

        case 40:
          e.preventDefault();
          $image.cropper('move', 0, 1);
          break;
      }

    });

//
//    // Import image
//    var $inputImage = $('#inputImage'),
//        URL = window.URL || window.webkitURL,
//        blobURL;
//
//    if (URL) {
//      $inputImage.change(function () {
//        var files = this.files,
//            file;
//
//        if (files && files.length) {
//          file = files[0];
//
//          if (/^image\/\w+$/.test(file.type)) {
//            blobURL = URL.createObjectURL(file);
//            $image.one('built.cropper', function () {
//              URL.revokeObjectURL(blobURL); // Revoke when load complete
//            }).cropper('reset', true).cropper('replace', blobURL);
//            $inputImage.val('');
//          } else {
//            showMessage('Please choose an image file.');
//          }
//        }
//      });
//    } else {
//      $inputImage.parent().remove();
//    }
//
//
//    // Options
//    $('.docs-options :checkbox').on('change', function () {
//      var $this = $(this);
//
//      options[$this.val()] = $this.prop('checked');
//      $image.cropper('destroy').cropper(options);
//    });
//
//
//    // Tooltips
//    $('[data-toggle="tooltip"]').tooltip();
//
// 
    }());

});
