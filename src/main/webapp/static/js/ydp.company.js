$(function() {
	initDistrictSelector('bankDistrict');
	initDistrictSelector('licenseDistrict');
	initDistrictSelector('personDistrict');
	setTelephone();

	// 设置下拉框不可选
	function getSelectorReadonly(target) {
		if (target == 'bankDistrict') {
			return true;
		}
		return false;
	}

	// 初始化地区select value = root-11|root-11|root-11
	function initDistrictSelector(id) {
		var target = $("#" + id);
		if (target.length == 0)
			return;
		// 从自身节点获取数据
		var value = target.attr("data");
		if (YouGou.Util.isEmpty(value) && value.indexOf('|') == -1) {
			return;
		}
		var array = value.split("|");
		if (array.length < 3)
			return;
		var readonly = getSelectorReadonly(id);
		var province = array[0];
		var city = array[1];
		var area = array[2];
		target.areaSelecor({
			readonly : readonly,
			valueType : 'id',
			province : province,
			city : city,
			area : area,
			change : function(province, city, area) {
				target.attr("data", province + '|' + city + '|' + area);
			}
		});
	}

	// 获取省市区的值
	function getDistrictValue(target) {
		var target = $("#" + target);
		if (target.length == 0)
			return;
		var value = target.attr("data");
		return value.split("|");
	}

	// 设置固定电话
	function setTelephone() {
		var telephone = $("#telephone").val();
		if (YouGou.Util.isEmpty(telephone))
			return;
		var array = telephone.split("-");
		if (array.length != 2)
			return;
		$("#telephoneLeft").val(array[0]);
		$("#telephoneRight").val(array[1]);
	}

	jQuery.validator.addMethod("containSpecial", function(value, element) {
		var containSpecial = /([#$%^*+=<>?]+)/;
		return this.optional(element) || !containSpecial.test(value);
	}, "请输入正确格式字符");

	// 增加手机号码验证
	jQuery.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		var mobile = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|170)\d{8}$/;
		var testMobile = (length == 11 && mobile.test(value));
		return this.optional(element) || testMobile;
	}, "手机号不正确");

	jQuery.validator.addMethod("telephone", function(value, element) {
		var telephoneLeft = $("#telephoneLeft").val();// 区号
		var telephoneRight = $("#telephoneRight").val();// 号码
		var leftRule = /^\d{3,4}$/;
		var rightRule = /^\d{7,9}$/;

		// 允许全部都不填
		if (YouGou.Util.isEmpty(telephoneLeft) && YouGou.Util.isEmpty(telephoneRight)) {
			return true;
		}

		if (!YouGou.Util.isEmpty(telephoneLeft)) {
			if (!leftRule.test(telephoneLeft)) {
				$("#telephone_tip").removeClass("successHint").addClass("errorHint").text('区号是3到4位数字');
				return false;
			}
			if (YouGou.Util.isEmpty(telephoneRight)) {
				$("#telephone_tip").removeClass("successHint").addClass("errorHint").text('请填写电话');
				return false;
			}
			if (!rightRule.test(telephoneRight)) {
				$("#telephone_tip").removeClass("successHint").addClass("errorHint").text('电话是7到9位数字');
				return false;
			}
		} else if (!YouGou.Util.isEmpty(telephoneRight)) {
			if (!rightRule.test(telephoneRight)) {
				$("#telephone_tip").removeClass("successHint").addClass("errorHint").text('电话是7到9位数字');
				return false;
			}
			if (YouGou.Util.isEmpty(telephoneLeft)) {
				$("#telephone_tip").removeClass("successHint").addClass("errorHint").text('请填写区号');
				return false;
			}
			if (!leftRule.test(telephoneLeft)) {
				$("#telephone_tip").removeClass("successHint").addClass("errorHint").text('区号是3到4位数字');
				return false;
			}
		}
		$("#telephone_tip").addClass("successHint").text('');
		return true;
	}, "ignore");

	$("#profileForm").validate({
		rules : {
			userName : {
				required : true,
				minlength : 2
			},
			mobilePhone : {
				required : true,
				mobile : true
			},
			email : {
				required : true,
				email : true
			},
			address : {
				required : false,
				maxlength : 70,
				containSpecial : []
			},
			/**telephoneLeft : {
				required : false,
				telephone : []
			},
			telephoneRight : {
				required : false,
				telephone : []
			}**/
		},
		messages : {
			userName : {
				required : "请输入真实姓名",
				minlength : "姓名至少2位汉字"
			},
			address : {
				required : "请输入详细地址",
				maxlength : jQuery.format("详细地址长度最多不能超过{0}位")
			},
			mobilePhone : {
				required : "请输入联系手机",
				mobile : "手机号码格式不正确"
			},
			email : {
				required : "请输入电子邮箱",
				email : "邮箱格式不正确"
			}
		},
		success : "rightMsg",
		errorElement : "span",
		errorPlacement : function(error, element) {
			if (error.text() == "ignore") {
				return;
			}
			element.parent().parent().find('dt').html(error.attr('class', 'errorMsg'));
		},
		submitHandler : function(form) {
			submitPartnerForm();

		}
	});

	// 判断地区是否选择
	function isPositionValid(target) {
		var position = getDistrictValue(target);
		if (position == null)
			return false;
		for ( var i = 0; i < position.length; i++) {
			if (YouGou.Util.isEmpty(position[i])) {
				$("#" + target).parent().next().html("<span class='errorMsg'>请选择地区</span>");
				return false;
			} else {
				$("#" + target).parent().next().html("");
			}
		}
		return true;
	}

	// 组装表单数据
	function getFormData() {
		var gender = $('input:radio:checked').val();
		//var telephone = $("#telephoneLeft").val() + '-' + $("#telephoneRight").val();
		// 个人信息里面的地区
		var position = getDistrictValue('personDistrict');
		var provinceNo = position[0];
		var cityNo = position[1];
		var districtNo = position[2];
		// 开户行地区
		var bankPosition = getDistrictValue('bankDistrict');
		var bankProvinceNo = '';
		var bankCityNo = '';
		var bankDistrictNo = '';
		if (bankPosition != null) {
			bankProvinceNo = bankPosition[0];
			bankCityNo = bankPosition[1];
			bankDistrictNo = bankPosition[2];
		}
		// 营业执照地区
		var licensePosition = getDistrictValue('licenseDistrict');
		var licenseProvinceNo = '';
		var licenseCityNo = '';
		var licenseDistrictNo = '';
		if (licensePosition != null) {
			licenseProvinceNo = licensePosition[0];
			licenseCityNo = licensePosition[1];
			licenseDistrictNo = licensePosition[2];
		}

		var data = {
			"id" : $("#id").val(),
			// 个人信息
			"userName" : $("#userName").val(),
			"mobilePhone" : $("#mobilePhone").val(),
			"email" : $("#email").val(),
			"provinceNo" : provinceNo,
			"cityNo" : cityNo,
			"districtNo" : districtNo,
			"address" : $("#address").val(),
			//"telephone" : telephone,
			"telephone" : $("#telephone").val(),
			"bankProvinceNo" : bankProvinceNo,
			"bankCityNo" : bankCityNo,
			"bankDistrictNo" : bankDistrictNo,
			"licenseNo" : $("#licenseNo").val(),
			"licenseProvinceNo" : licenseProvinceNo,
			"licenseCityNo" : licenseCityNo,
			"licenseDistrictNo" : licenseDistrictNo,
			"licenseAviableDate" : $("#licenseAviableDate").val(),
			"taxNo" : $("#taxNo").val(),
			"organizationNo" : $("#organizationNo").val(),
			"taxPayerNo" : $("#taxPayerNo").val(),
		};
		return data;
	}

	// 提交合作伙伴表单
	function submitPartnerForm() {
		/*if (!isPositionValid('bankDistrict')) {
			return;
		}*/
		if (!isPositionValid('personDistrict')) {
			return;
		}
		submitForm(getFormData(), '/my/updatePersonInfo.jhtml');
	}

	// 提交表单
	function submitForm(formData, url) {
		$.ajax({
			type : "POST",
			data : formData,
			url : url + "?" + Math.random(),
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				alert(false, '保存资料失败', null);
			},
			success : function(data) {
				var result = eval('(' + data + ')');
				var state = result.state;
				var msg = result.msg;
				if (state == 'error') {
					showCommonDialog(false, msg);
					return;
				}
				showCommonDialog(true, msg);
			}
		});
	}

});