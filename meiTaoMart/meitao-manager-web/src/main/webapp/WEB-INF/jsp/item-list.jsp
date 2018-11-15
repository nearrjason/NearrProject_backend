<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="商品列表"
	data-options="singleSelect:false,collapsible:true,pagination:true, url:'/item/list',method:'get',pageSize:1000,toolbar:toolbar">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'barcode',width:200,align:'center'">商品条形码 </th>
			<th data-options="field:'name',width:200,align:'center'">商品名称</th>
			<th data-options="field:'caption',width:200,align:'center'">商品副标题</th>
			<!-- <th data-options="field:'categoryId',width:100,align:'center'">商品类型
				id</th> -->
			<th data-options="field:'categoryName',width:200,align:'center'">商品分类名称</th>
			<th data-options="field:'specialCategoryName',width:200,align:'center'">商品特殊分类</th>
			<th data-options="field:'statusName',width:200,align:'center'">商品状态</th>
			<th data-options="field:'brandName',width:200,align:'center'">商品商标</th>
			<th data-options="field:'supplier',width:200,align:'center'">商品供应商</th>
			<th data-options="field:'sellPoint',width:100,align:'center'">商品卖点</th>
			<th
				data-options="field:'salePrice',width:100,align:'center',formatter:E3.formatPrice"">商品单价($)</th>
			<th
				data-options="field:'cost',width:100,align:'center',formatter:E3.formatPrice"">商品成本($)</th>
			<th data-options="field:'discount',width:100,align:'center'">商品折扣(%)off</th>
			<th data-options="field:'netWeight',width:100,align:'center'">商品净含量(g)</th>
			<th data-options="field:'stockNumber',width:100,align:'center'">库存数量</th>
			<th data-options="field:'images',width:100,align:'center'">图片</th>
			<th
				data-options="field:'createdTime',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
			<th
				data-options="field:'updatedTime',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
			<th data-options="field:'adminUserId',width:100,align:'center'">管理员Id</th>


			<!-- <th data-options="field:'price',width:70,align:'right',formatter:E3.formatPrice">价格</th>
			<th data-options="field:'num',width:70,align:'right'">库存数量</th>
			<th data-options="field:'barcode',width:100">条形码</th>
			<th
				data-options="field:'status',width:60,align:'center',formatter:E3.formatItemStatus">状态</th>
			<th
				data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
			<th
				data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
				 -->


		</tr>
	</thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item-edit'"
	style="width: 80%; height: 80%; padding: 10px;"></div>
<script>
	$("#imagesButton").bind("click", function() {
		alert("hehe");
	});
			
	function getSelectionsIds() {
		var itemList = $("#itemList");
		var sels = itemList.datagrid("getSelections");
		console.log("hehe" + sels);
		var ids = [];
		for ( var i in sels) {
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}

	var toolbar = [
			{
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					$(".tree-title:contains('新增商品')").parent().click();
				}
			},
			{
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var ids = getSelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '必须选择一个商品才能编辑!');
						return;
					}
					if (ids.indexOf(',') > 0) {
						$.messager.alert('提示', '只能选择一个商品!');
						return;
					}

					// window start
					$("#itemEditWindow").window(
							{
								onLoad : function() {
									var data = $("#itemList").datagrid("getSelections")[0];
									data.salePriceView = data.salePrice / 100;
									data.costView = data.cost / 100;
									data.discountView = data.discount;
									$("#currentCategoryName").html(data.categoryName);
									$("#itemEditForm").form("load",
											data);
								}
							}).window("open");
					// window end
					
				}
			},
			{
				text : '删除',
				iconCls : 'icon-cancel',
				handler : function() {
					var ids = getSelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '未选中商品!');
						return;
					}
					$.messager
							.confirm(
									'确认',
									'确定删除ID为 ' + ids + ' 的商品吗？',
									function(r) {
										if (r) {
											var params = {
												"ids" : ids
											};
											$
													.post(
															"/rest/item/delete",
															params,
															function(data) {
																if (data.status == 200) {
																	$.messager
																			.alert(
																					'提示',
																					'删除商品成功!',
																					undefined,
																					function() {
																						$(
																								"#itemList")
																								.datagrid(
																										"reload");
																					});
																}
															});
										}
									});
				}
			},
			'-',
			{
				text : '下架',
				iconCls : 'icon-remove',
				handler : function() {
					var ids = getSelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '未选中商品!');
						return;
					}
					$.messager
							.confirm(
									'确认',
									'确定下架ID为 ' + ids + ' 的商品吗？',
									function(r) {
										if (r) {
											var params = {
												"ids" : ids
											};
											$
													.post(
															"/item/offShelf",
															params,
															function(data) {
																if (data.status == 200) {
																	$.messager
																			.alert(
																					'提示',
																					'下架商品成功!',
																					undefined,
																					function() {
																						$(
																								"#itemList")
																								.datagrid(
																										"reload");
																					});
																}
															});
										}
									});
				}
			},
			{
				text : '上架',
				iconCls : 'icon-remove',
				handler : function() {
					var ids = getSelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '未选中商品!');
						return;
					}
					$.messager
							.confirm(
									'确认',
									'确定上架ID为 ' + ids + ' 的商品吗？',
									function(r) {
										if (r) {
											var params = {
												"ids" : ids
											};
											$
													.post(
															"/item/onShelf",
															params,
															function(data) {
																if (data.status == 200) {
																	$.messager
																			.alert(
																					'提示',
																					'上架商品成功!',
																					undefined,
																					function() {
																						$(
																								"#itemList")
																								.datagrid(
																										"reload");
																					});
																}
															});
										}
									});
				}
			} ];
</script>