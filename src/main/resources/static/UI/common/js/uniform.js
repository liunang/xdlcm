var EmmFormStyle = function(){
	
	/**
	 * �ⲿ��
	 */
	this.setCheckboxClass = function(checkboxClass){
		var emmCheckbox = $("."+checkboxClass);
		$(emmCheckbox).each(function(index,ele){
			if($(this).is(":checked")){
				$(this).parent().addClass("checked");
			}else{
				$(this).parent().removeClass("checked");
			}
		});
	};
	
	
	/**
	 * ����radio��ʽ
	 */
	
};