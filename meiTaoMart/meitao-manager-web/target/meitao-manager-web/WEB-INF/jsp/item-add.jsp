<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
	            	<input type="hidden" name="categoryId" style="width: 280px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>商品特殊类目:</td>
	            <td>
	            	<select name="specialCategoryId">
	            		<option value="">--</option>
	            		<option value="-1">优选商品</option>
	            		<option value="-2">最新上架</option>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <td>商品名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品副标题:</td>
	            <td><input class="easyui-textbox" type="text" name="caption" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品商标:</td>
	            <td><input class="easyui-textbox" type="text" name="brandName" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品供应商:</td>
	            <td><input class="easyui-textbox" type="text" name="supplier" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品卖点:</td>
	            <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	         <tr>
	            <td>商品单价:</td>
	            <td>
	  				$
	            	<input class="easyui-numberbox" type="text" name="salePriceView" data-options="min:0,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="salePrice"/>
	            	eg:123.45
	            </td>
	        </tr>
	        <tr>
	            <td>商品成本:</td>
	            <td>
	            	$
	            	<input class="easyui-numberbox" type="text" name="costView" data-options="min:0,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="cost"/>
	            	eg:123.00
	            </td>
	        </tr>
	         <tr>
	            <td>商品折扣:</td>
	            <td>
	            	<input class="easyui-numberbox" type="text" name="discountView" maxlength="2" style="width: 100px;"/>
	            	<input type="hidden" name="discount"/>
	            	单位:% off
	            </td>
	        </tr>
	        <tr>
	            <td>商品净含量:</td>
	            <td>
	            	<input class="easyui-numberbox" type="text" name="netWeight" data-options="required:true" style="width: 100px;"/>
	            	单位:g
	            </td>
	      	</tr>
	        
	        <tr>
	            <td>库存数量:</td>
	            <td><input class="easyui-numberbox" type="text" name="stockNumber" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>条形码:</td>
	            <td>
	                <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]',required:true" />
	            </td>
	        </tr>
	        <tr style="height:20px !important">
	        	<td colspan="2"></td>
	        </tr>
	        <tr>
	            <td>商品图片:</td>
	            <td>
	            	 <a href="javascript:void(0)" id="uploadItemPicture" class="easyui-linkbutton picFileUpload">上传图片</a>
	            	 <span>请上传只带英文路径的图片</span>
	                 <input type="hidden" name="images"/>
	            </td>
	        </tr>
	       <tr style="height:20px !important">
	        	<td colspan="2"></td>
	        </tr>
	       <tr>
	            <td>商品描述图片:</td>
	            <td>
	            	 <a href="javascript:void(0)" id="uploadItemDescPicture" class="easyui-linkbutton picFileUpload">上传图片</a>
	            	 <span>请上传只带英文路径的图片</span>
	                 <input type="hidden" name="descImages"/>
	            </td>
	        </tr>
	        <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
	            </td>
	        </tr>
	        
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="adminUserId"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//创建富文本编辑器
		itemAddEditor = E3.createEditor("#itemAddForm [name=desc]");
		//初始化类目选择和图片上传器
		E3.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。
			//E3.changeItemParam(node, "itemAddForm");
		}});
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#itemAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//取商品价格，单位为“分”
		//$("#itemAddForm [name=salePrice]").val(eval($("#itemAddForm [name=salePriceView]").val()) * 100);
		//$("#itemAddForm [name=cost]").val(eval($("#itemAddForm [name=costView]").val()) * 100);
		//$("#itemAddForm [name=salePrice]").val(100);
		//$("#itemAddForm [name=cost]").val(1); 
		//同步文本框中的商品描述
		var cost = Math.round($("#itemAddForm [name=costView]").val() * 100);
		$("#itemAddForm [name=cost]").val(cost);
		var salePrice = Math.round($("#itemAddForm [name=salePriceView]").val() * 100);
		$("#itemAddForm [name=salePrice]").val(salePrice);
		var discount = Math.round($("#itemAddForm [name=discountView]").val());
		$("#itemAddForm [name=discount]").val(discount);
		$("#itemAddForm [name=adminUserId]").val(0);
		console.log($("#itemAddForm [name=adminUserId]").val());
		itemAddEditor.sync();
		//取商品的规格
		/*
		var paramJson = [];
		$("#itemAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		//把json对象转换成字符串
		paramJson = JSON.stringify(paramJson);
		$("#itemAddForm [name=itemParams]").val(paramJson);
		*/
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		
		$.post("/item/save", $("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增商品成功!');
				
			} else {
				$.messager.alert('提示',data.msg);
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
