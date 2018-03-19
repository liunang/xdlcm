var util = new NT.utilObj.util();
/*
 * 用户列表显示
 */
var queryGridUrl = "../firm/findByCondition.action";

var addDataUrl = "../firm/addFirm.action";
var editDataUrl = "../firm/updateFirm.action";
var removeDataUrl = "../firm/delFirm.action";

/*
 *查询用户资料 
 */
var querDetialUrl = "../firm/findById.action";

var nowOperate;

var store_old={};
var store_new={};
function compareStore(){
	var returnValue=1;
	$.each(store_new,function(key,ele){
			
			var valueOld=store_old[key];
			if(ele!=valueOld){
				returnValue=0;
			}
			
		}
	);
	return returnValue;
}

function sortArr(arr){
	for(var i=0;i<arr.length;i++){
		for(var j=i+1;j<arr.length;j++){
			if(arr[i]>arr[j]){
				var temp=arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
			}
		}
	}
	return arr;
}

/**
 * 加载数据
 */
var loadData = function(){
	var headParam = [];
	headParam.push("firmNum");
	headParam.push("firmName");
	headParam.push("contact");
	headParam.push("telephone");
	headParam.push("mobilePhone");
	//headParam.push("fax");
	headParam.push("email");
	//headParam.push("address");
	headParam.push("firmDate");
	//headParam.push("remark");
	
	var url = queryGridUrl;
	
	var defaultBtns = {"viewBtn":"show","editBtn":"show","removeBtn":"show"};
	var operateBtns = [];
	
	var querParam = getQueryGridParam();
	
	var gridObj = {};
	gridObj["url"] = url;
	gridObj["headParam"] = headParam;
	gridObj["queryParam"] = querParam;
	gridObj["defaultBtns"] = defaultBtns;
	gridObj["operateBtns"] = operateBtns;
	gridObj["pk"] = "firmNum";
	gridObj["page"] = true;
	gridObj["checked"]=false;
	
	var nTGridBean = new NTGridBean();
	nTGridBean.init(gridObj);
	nTGridBean.loadGrid();
};


/**
 * 获得pk
 */
var getPk = function(btn){
	var pk = $(btn).parent().parent().parent().attr("pk");
	return pk;
};

/**
 * 显示模态框
 */
var  showModel = function(){
	resetAddOrEditForm();
	$("#addOrEditModal").modal("show");
//	AddOrEditCheck();
};

/**
 * 关闭模态框
 */
var hideModel = function(){
	$("#addOrEditModal").modal("hide");
};

/**
 * 清空form
 */
var resetAddOrEditForm = function(){
	$("#addOrEditForm")[0].reset();
	$('span[id="errormsg"]').remove();
};

/**
 * 新增或修改成功之后的事件
 * @return
 */
var addOrEditCompleteFun = function(){
	hideModel();
	loadData();
};


/**
 * 新增
 * @return
 */
var addData = function(param){
	operateUtil.addData(addDataUrl,param,addOrEditCompleteFun);
};

/**
 * 详情
 */
var viewData = function(param,viewSuccessFun){
	operateUtil.viewData(querDetialUrl,param,viewSuccessFun);
};

/**
 *编辑
 */
var editData = function(param){
	operateUtil.editData(editDataUrl,param,addOrEditCompleteFun);
};


/**
 * 删除
 */
var removeData = function(param){
	operateUtil.removeData(removeDataUrl,param,addOrEditCompleteFun);
};



/**
 * 删除
 */
var removeBtn = function(btn){
	var pk = getPk(btn);
	
	util.emmAjax({
		url : querDetialUrl,
		data:{'firmNum':pk},
		type : 'post',
		success : function(data) {
			var obj = util.str2Json(data);
			var firmObj=obj.data;
			if(obj.success=='true')
				{
					var param='{'
						param=param+('\"firmNum\":\"'+firmObj.firmNum+ '\",');
						param=param+('\"firmName\":\"'+firmObj.firmName+ '\",');
						param=param+('\"contact\":\"'+firmObj.firmContact+ '\",');
						param=param+('\"telephone\":\"'+firmObj.telephone+ '\",');
						param=param+('\"mobilePhone\":\"'+firmObj.mobilePhone+ '\",');
						param=param+('\"fax\":\"'+firmObj.fax+ '\",');
						param=param+('\"email\":\"'+firmObj.email+ '\",');
						param=param+('\"address\":\"'+firmObj.address+ '\",');
						param=param+('\"firmDate\":\"'+firmObj.firmDate+ '\",');
						param=param+('\"remark\":\"'+firmObj.remark+ '\"');
					
						param += '}';
						param=str2Json(param);
					removeData(param);
				}
			else{
				util.sysAlert(obj.data);
			}
			
		}
	});
};



/**
 * 编辑回填
 */
var editDataFill=function(pk){
	var param = {"firmNum":pk};
	viewData(param,dataFill);
};

/**
 * 编辑
 */
var editBtn = function(btn){
	var pk = getPk(btn);
	nowOperate = "edit";
	$("#action").html('编辑产品厂商信息');
	showModel();
	$("#addFirmNum").attr("disabled",true);
	editDataFill(pk);
	
};


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
$(function(){
	window.checkForm.openFun().init({path:webPath,form:'addOrEditForm'}); 
	$("#addFirmDate").val(GetDateStr(-30));
	$("#addFirmDate").datetimepicker({
		format: "yyyy-mm-dd",
		autoclose:true,
		minView:2,
		language:'cn'
	});
	
	$("#addBtn").click(function(){
		nowOperate = "add";
		$("#addFirmNum").attr("disabled",false);
		$("#action").html('新增产品厂商信息');
		showModel();
	});
	
	$("#removeOfNumbersBtn").click(function(){
		var param = getRemoveDataOfNumbersParam();
		if(param!=undefined){
			removeDataOfNumbers(param);
		}
	});
	
	
	$("#addOrEditSaveBtn").click(function(){
		var returnValue=window.checkForm.openFun().onSubmit();
		if(nowOperate=="add"&&returnValue ){
			var param = getAddParam();
			if(param!=undefined){
				addData(param);
			}
		}else if(nowOperate=="edit"&&returnValue ){
			var param = getEditParam();
			if(param!=undefined){
				var returnValue=compareStore();
				if(returnValue==0){
					if(param!=null){
						editData(param);
					}
				}else{
					util.sysAlert("您尚未更改任何内容，保存失败！");
				}
			}
		}

	});
	
	$("#queryGridBtn").click(function(){
		loadData();
	});
	$("#upload").click(function(){
		$("#upload_form").submit();
	});
	loadData();	
});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




/**
 * 详情
 */
var viewBtn = function(btn){
	var firmNum = getPk(btn);
	var url = "./detailsFirm.html?firmNum=" + firmNum;
	window.location.href = encodeURI(url);
};


/**
 * 获得查询列表参数
 */
var getQueryGridParam = function(){
	var QNum = $("#query_firmNum").val();
	var QName = $("#query_firmName").val();
	var param = {
		'firmNum' : QNum,
		'firmName' : QName
	};
	return param;
};


/**
 * 获得增加的参数
 * @return
 */
var getAddParam = function(){
	var addFirmNum = $("#addFirmNum").val();
	var addFirmName = $("#addFirmName").val();
	var addContact = $("#addContact").val();
    var addTelephone = $("#addTelephone").val();
    var addMobile = $("#addMobile").val();
    var addFax = $("#addFax").val();
	var addEmail = $("#addEmail").val();
	var addAddress = $("#addAddress").val();
	var addFirmDate = $("#addFirmDate").val();
	var addRemark = $("#addRemark").val();
	var param='{'
	param=param+('\"firmNum\":\"'+addFirmNum+ '\",');
	param=param+('\"firmName\":\"'+addFirmName+ '\",');
	param=param+('\"contact\":\"'+addContact+ '\",');
	param=param+('\"telephone\":\"'+addTelephone+ '\",');
	param=param+('\"mobilePhone\":\"'+addMobile+ '\",');
	param=param+('\"fax\":\"'+addFax+ '\",');
	param=param+('\"email\":\"'+addEmail+ '\",');
	param=param+('\"address\":\"'+addAddress+ '\",');
	param=param+('\"firmDate\":\"'+addFirmDate+ '\",');
	param=param+('\"remark\":\"'+addRemark+ '\"');
	//param = param.substring(0, param.length - 1);
	param += '}';
	param=str2Json(param);
	return param;
};

/**
 * 获得编辑的参数
 * @return
 */
var getEditParam = function(){
	var addFirmNum = $("#addFirmNum").val();
	var addFirmName = $("#addFirmName").val();
	var addContact = $("#addContact").val();
    var addTelephone = $("#addTelephone").val();
    var addMobile = $("#addMobile").val();
    var addFax = $("#addFax").val();
	var addEmail = $("#addEmail").val();
	var addAddress = $("#addAddress").val();
	var addFirmDate = $("#addFirmDate").val();
	var addRemark = $("#addRemark").val();
	var param='{'
	param=param+('\"firmNum\":\"'+addFirmNum+ '\",');
	param=param+('\"firmName\":\"'+addFirmName+ '\",');
	param=param+('\"contact\":\"'+addContact+ '\",');
	param=param+('\"telephone\":\"'+addTelephone+ '\",');
	param=param+('\"mobilePhone\":\"'+addMobile+ '\",');
	param=param+('\"fax\":\"'+addFax+ '\",');
	param=param+('\"email\":\"'+addEmail+ '\",');
	param=param+('\"address\":\"'+addAddress+ '\",');
	param=param+('\"firmDate\":\"'+addFirmDate+ '\",');
	param=param+('\"remark\":\"'+addRemark+ '\"');
	//param = param.substring(0, param.length - 1);
	param += '}';
	param=str2Json(param);
				
	store_new['firmName']=addFirmName;
	store_new['contact']=addContact;
	store_new['telephone']=addTelephone;
	store_new['mobile']=addMobile;
	store_new['email']=addEmail;
	store_new['fax']=addFax;
	store_new['address']=addAddress;
	store_new['firmDate']=addFirmDate;
	store_new['remark']=addRemark;
	return param;
};

/**
 * 编辑回调回填
 * @param data
 * @return
 */
var update_old_data;
var dataFill = function(data){
	var roles=[];
	var object = util.str2Json(data).data;
	update_old_data=object;
	
	$("#addFirmNum").val(object.firmNum);
	$("#addFirmName").val(object.firmName);
	$("#addContact").val(object.contact);
    $("#addTelephone").val(object.telephone);
    $("#addMobile").val(object.mobilePhone);
    $("#addFax").val(object.fax);
	$("#addEmail").val(object.email);
	$("#addAddress").val(object.address);
	$("#addFirmDate").val(object.firmDate);
	$("#addRemark").val(object.remark);
	
	store_old['firmName']=object.firmName;
	store_old['contact']=object.contact;
	store_old['telephone']=object.telephone;
	store_old['mobile']=object.mobile;
	store_old['email']=object.email;
	store_old['fax']=object.fax;
	store_old['address']=object.address;
	store_old['firmDate']=object.firmDate;
	store_old['remark']=object.remark;
};


/**
 *将json字符串转化为json对象
 */
function str2Json(jsonStr){
	var json = eval("(" + jsonStr + ")"); 
	return json;
};
