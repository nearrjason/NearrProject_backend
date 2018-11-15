<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>商品类目:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
	            	<input type="hidden" name="categoryId" style="width: 280px;"></input>
	            </td>
	            <td>
	            	Previous 类目:
	            	<div id="currentCategoryName"></div>
	            </td>
	        </tr>
	        <tr>
	            <td>商品特殊类目(若选择了此类目，则不会出现在商品类目分类):</td>
	            <td>
	            	<select name="specialCategory">
	            		<option value="0">--</option>
	            		<option value="-1">优选商品</option>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <td>商品名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品副标题:</td>
	            <td><input class="easyui-textbox" type="text" name="caption" data-options="required:true" style="width: 280px;"></input></td>
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
	                <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'" />
	            </td>
	        </tr>
	        <tr style="height:20px !important">
	        	<td colspan="2"></td>
	        </tr>
	        <tr>
	            <td>商品图片:</td>
	            <td>
	            	 <a href="javascript:void(0)" id="uploadItemPictureEdit" class="easyui-linkbutton picFileUpload">上传图片</a>
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
	            	 <a href="javascript:void(0)" id="uploadItemDescPictureEdit" class="easyui-linkbutton picFileUpload">上传图片</a>
	            	 <span>请上传只带英文路径的图片</span>
	                 <input type="hidden" name="descImages"/>
	            </td>
	        </tr>
	        <!-- <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
	            </td>
	        </tr> -->
	        
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	var itemEditEditor ;
	$(function(){
		//实例化编辑器
		//itemEditEditor = E3.createEditor("#itemEditForm [name=desc]");
		//itemEditEditor = E3.createEditor("#itemEditForm");
		E3.init({fun:function(node){
		
		}});
	});
	
	function submitForm(){
		if(!$('#itemEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var cost = Math.round($("#itemEditForm [name=costView]").val() * 100);
		$("#itemEditForm [name=cost]").val(cost);
		var salePrice = Math.round($("#itemEditForm [name=salePriceView]").val() * 100);
		$("#itemEditForm [name=salePrice]").val(salePrice);
		var discount = Math.round($("#itemEditForm [name=discountView]").val());
		$("#itemEditForm [name=discount]").val(discount);
		$("#itemEditForm [name=adminUserId]").val(0);
		console.log($("#itemEditForm [name=adminUserId]").val());
		//itemAddEditor.sync();
		
		$.post("/item/update",$("#itemEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改商品成功!','info',function(){
					$("#itemEditWindow").window('close');
					$("#itemList").datagrid("reload");
				});
			}
		});
	}
</script>
