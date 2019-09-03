//获取验证码对象
var verificationCode = $("#verificationCode");
// 保存随机生成的验证码
var cachCode = '';

// 页面初始化加载
$(function() {
	var code = verCode();
	cachCode = code;// 将随机码保存
	verificationCode.text(code);

	// 验证form表单的数据，提交form表单
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						// 自定义验证规则
						form.verify({
							title : function(value) {
								if (value.length == 0) {
									return '不能为空';
								}
							},
							yzm : function(value) {// 验证验证码
								if (value != cachCode) {
									var code = verCode();
									cachCode = code;// 将随机码保存
									verificationCode.text(code);
									return '验证码不正确';
								}
							}
						});
						// 提交form表单
						form.on('submit(submitForm)', function(data) {

						});

					});

	// 点击按钮随机生成验证码
	verificationCode.click(function() {
		var code = verCode();
		cachCode = code;// 将随机码保存
		verificationCode.text(code);
	});

	
	
	// 防止浏览器后退按钮
//	if (window.history && window.history.pushState) {
//		$(window).on('popstate', function() {
//			window.history.pushState('forward', null, '#');
//			window.history.forward(1);
//		});
//	}
//
//	window.history.pushState('forward', null, '#'); // 在IE中必须得有这两行
//	window.history.forward(1);

});

// 随机生成验证码
function verCode() {
	var str = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ1234567890';
	var value = '';

	for (var i = 0; i < 4; i++) {
		var num = Math.ceil((Math.random() * 61) + 1);
		value += str.substring(num, num + 1);
		// console.log(str.substring( num,num+1 ));
	}

	var c1 = Math.ceil((Math.random() * 255) + 1);
	var c2 = Math.ceil((Math.random() * 255) + 1);
	var c3 = Math.ceil((Math.random() * 255) + 1);

	$("#verificationCode").css({
		'color' : 'rgb(' + c1 + ',' + c2 + ',' + c3 + ')'
	});

	return value;
}
