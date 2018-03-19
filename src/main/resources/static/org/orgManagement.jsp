<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
<meta charset="utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="description" content="">
 <meta name="author" content="ThemeBucket">
<title>机构管理</title>
	<link href="../UI/orgTree/orgTree.css" rel="stylesheet"/>
	<script>var webPath = "${pageContext.request.contextPath}";</script>
	<script src="${pageContext.request.contextPath}/UI/common/loadCommonCss.js"></script>
	<style>
	    .updatesuperorgCOde{background-color:#eee !important}
	    #orginfodata .cross,#orginfodata .tick{margin-right: 15px;}
	</style>
</head>
<body style="background: #fff;">
	<div class="page-main">
		<div class="row page-heading" >
			<div class="row page-heading-inner" >
				<div class="col-xs-6 " id="nav_header" style="margin-left:10px;">
					<ol class="list-inline" id="navContent">
						<li ><a href="javascript:void(0);"onclick="window.parent.backMain(); ">主页</a></li> <span>/</span>
						<li><a id="parentNavName">###</a></li><span>/</span>
						<li><a id="thisNavName">###</a></li>
					</ol>
				</div>
			</div>
		</div>
		
        <div class="row page-body" >
        
        	<div class="col-sm-12">
        		<div class="panel-heading">
				  <strong style="line-height:30px;">机构管理</strong> 
		          <div class="panel-heading-button pull-right" id="opbutton" hidden="hidden">
		          <div class="btn-group">
				    <button type="button" class="btn" id="addBtn" title="新增">
				    	<i class="glyphicon glyphicon-plus" ></i>
				    </button>
				  </div>
				    <button type="button" class="btn" id="editOrgInfoBtn" title="机构信息编辑" onclick="editOrg();"><i class="glyphicon glyphicon-edit" ></i></button> 
				  <!--   <button type="button" class="btn" id="removalOrg" title="机构迁移" onclick="removalOrg();"><i class="glyphicon glyphicon-share" ></i></button>--> 
				     <button type="button" class="btn" id="removeOfNumbersBtn" title="删除"><i class="glyphicon glyphicon-trash" ></i> </button>&nbsp; &nbsp;
	               </div>
			 	</div>
        		<hr/>
        	</div>
        	
        	<div class="row"   >
        		<div class="col-sm-4">
        			<div class="orgTreeDiv" id="orgTree" style="width:250px;height:450px">
						<input class="orgIdClass" id="orgId" hidden>
        				<input id="orgAsyncId" hidden>
						<ul id="treeDemo" class="ztree"></ul>
					</div>
        		</div>
        		<div class="col-xs-8" id="orginfodata" hidden="hidden">
					<div class="form-horizontal">
						<form role="form" id="editForm">
						 <div class="row">
							<div class="form-group" style="padding-top:45px" id="orgsuper" hidden="hidden" >
							<label class="col-xs-3 control-label">上级机构编码：</label>
								<div class="col-xs-8">  
									<input class=" orgCodeClass updatesuperorgCOde" name="isTel" parentOrgPath="" parentOrgId="" parentOrgName="" id="updatesuperorgCOde"  disabled="disabled"  maxlength="9" readonly="readonly"/> 
                           		 </div>
                            </div>
							<div class="form-group" >
								<label class="col-xs-3 control-label">本级机构名称<span style="color:red">*</span>：</label>
								<div class="col-xs-8">
									<input type="text" id="updateorgName" check="required" disabled="disabled" >
								</div>
								
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">机构编号<span style="color:red">*</span>：</label>
								<div class="col-xs-8">
									<input type="text" id="updateorgCode"  check="required Number9" disabled="disabled" maxlength="9">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">区域编号<span style="color:red">*</span>：</label>
								<div class="col-xs-8">
									<input type="text" id="updateAreaCode"  check="required Number" disabled="disabled" maxlength="30">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">联系人<span style="color:red">*</span>：</label>
								<div class="col-xs-8">
									<input type="text" id="updateorgContacts"  check="required EnOrChinese"  disabled="disabled">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">联系电话<span style="color:red">*</span>：</label>
								<div class="col-xs-8">
									<input type="text" id="updateorgPhoneNumber"   check="required MobilePhone" disabled="disabled" maxlength="11">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">机构地址<span style="color:red">*</span>：</label>
								<div class="col-xs-8">
									<textarea id="updateorgAddress"  check="required" disabled="disabled"  style="height:85px;"></textarea>
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="row">
						<div class="col-xs-7">
							<div class="text-center" style="margin-top:10px;" >
								<button id="save"  class="btn btn-primary" disabled="disabled" >
									<i class="fa fa-rotate-left"></i>&nbsp;保存
								</button>
							</div>
						</div>
					</div>
				</div>
        	</div>
   		</div>
	 </div>
	
   <!-- 增加 -->
    
	<div class="modal fade inmodal" id="addOrEditModal" tabindex="-1" role="dialog" aria-hidden="true"  >
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
			     <div class="modal-header nt-model-head">
			          <h5>添加机构</h5>	                                         
			     </div>
			     <div class="modal-body">
			     
			     		<form role="form" id="addOrEditForm">
			     		
			     			<input id ="id" hidden />
			     		 <div class="row">
							  <div class="form-group">
							    <label class="col-sm-4 control-label text-right">上级机构名称<span style="color:red">*</span>：</label>
							     <div class="col-sm-8">
							    	<input type="text" id="parentOrgName_input" class="form-control"  placeholder="请输入上级机构名称！"disabled="disabled">
							     </div>
							  </div>
						  </div>
						  <br>
						  <div class="row">
							  <div class="form-group">
							    <label class="col-sm-4 control-label text-right">本级机构名称<span style="color:red">*</span>：</label>
							    <div class="col-sm-8">
							  	  <input type="text" check="required Chinese" id="addorgName" placeholder="请输入本级机构名称!" maxlength="32">
							    </div>
							  </div>
						   </div>
						     <br>
						  <div class="row">
							  <div class="form-group">
							    <label class="col-sm-4 control-label text-right">本级机构编码<span style="color:red">*</span>：</label>
							    <div class="col-sm-8">
							   	 <input type="text" check="required Number9" id="addorgCode" placeholder="请输入本级机构编码！" maxlength="9">
							    </div>
							  </div>
						   </div>
						     <br>
						  <div class="row">
							  <div class="form-group">
							    <label class="col-sm-4 control-label text-right">区域编码<span style="color:red">*</span>：</label>
							    <div class="col-sm-8">
							   	 <input type="text" check="required Number" id="addAreaCode" placeholder="请输入区域编码！" maxlength="30">
							    </div>
							  </div>
						   </div>
						     <br>
						  <div class="row">
							  <div class="form-group">
							    <label class="col-sm-4 control-label text-right ">联系人<span style="color:red">*</span>：</label>
							    <div class="col-sm-8">
							   		 <input type="text" check="required EnOrChinese" id="addorgContacts" placeholder="请输入联系人！" maxlength="10">
							    </div>
							  </div>
						    </div>
						      <br>
						  <div class="row">
							  <div class="form-group">
							    <label class="col-sm-4 control-label text-right">联系电话<span style="color:red">*</span>：</label>
							    <div class="col-sm-8">
							  		  <input type="text" check="required MobilePhone" id="addorgPhoneNumber" placeholder="请输入联系电话!" maxlength="11">
							    </div>
							  </div>
						  </div>
						  <br>
						  <div class="row">
							  <div class="form-group">
							    <label class="col-sm-4 control-label text-right">本级机构地址<span style="color:red">*</span>：</label>
							    <div class="col-sm-8">
							  	  <textarea id="addorgAddress" check="required String256" placeholder="请输入本级机构地址!"
										 style="height:85px;" maxlength="32" ></textarea>
							    </div>
							  </div>
						   </div>
						     
						</form>
			     
			     </div>
			     <div class="modal-footer text-center" >
			          <button type="button" class="btn btn-white btn-sm" data-dismiss="modal"><i></i>&nbsp;取消</button>
			          <button type="button" class="btn btn-primary btn-sm" id="addOrEditSaveBtn"><i class="fa fa-check"></i>&nbsp;保存</button>
			     </div>
			 </div>
		</div>
	</div>
	
	<!-- 增加 END-->
  	
  		<!-- 批量增加 -->
	<div class="modal inmodal" id="importModal" tabindex="-1" >
		<div class="modal-dialog">
		  <div class="modal-content animated bounceInRight" >
			   <form action="../importData!importOrgModel.action" id="upload_form" method="post" target="hidden_frame1" enctype="multipart/form-data" onsubmit="return checkFileUploadForm();">
				    <div class="modal-header" style="text-align:left;padding:5px;background:#eee">
					    <h3>上传数据文件</h3>
				    </div>
				   <div class="modal-body" style="height:150px;">
						<div class="form-group">
							<label class="col-sm-4 control-label">上传机构表格：</label>
							<div class="col-sm-7">
								<a href="javascript:;" class="a-upload" style="width:250px;">
								    <input id="importFile" name="importModelBean.file" type="file" style="margin-top:5px;">点击这里上传文件
								</a>
								<p id="importFileShow" style="font-size:18px;"><p>
							</div>
						</div>
						
						<div class="form-group" style="margin-top:100px;">
							<p>导入的只可以是.xls或者.xlsx结尾</p>
						</div>
			    	</div>
			    	<div class="modal-footer" >
						<button class="btn" id="close" data-dismiss="modal" style="width:95px">
								&nbsp;关闭
						</button>
						<button class="btn btn-primary" id="upload"  style="width:95px" >
							&nbsp;上传
						</button> 
					</div>
				</form> 
				<iframe style="width:100%;height:40px;"  hidden id="hidden_frame1" name="hidden_frame1">
					<body id="xxx"></body>
				</iframe>
			</div>
		</div>
	</div>
	<!-- 批量增加End -->
  
  
	<script src="${pageContext.request.contextPath}/UI/common/loadCommonJs.js"></script>
	<script src="../UI/orgTree/orgTree.js"></script>
	<script src="./orgManagement.js"></script>

</body>
</html>