(function($){
var myflow = $.myflow;
$.extend(true,myflow.config.props.props,{
	name : {name:'name', label:'流程名称', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	key : {name:'key', label:'流程标识', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	desc : {name:'desc', label:'流程描述', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	constant : {name:'constant', label:'流程类型', value:'', editor:function(){return new myflow.editors.selectEditor([{'name':'预算审批','value':1},{'name':'方案审批','value':2}]);}}
});

$.extend(true,myflow.config.tools.states,{
	start : {
				showType: 'image&text',
				type : 'start',
				name : {text:'<<start>>'},
				text : {text:'开始'},
				img : {src : '/jnys/images/myflow/img/48/start_event_empty.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'开始'},
					temp1: {name:'temp1', label : '姓名 ', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'制表员','value':1},{'name':'部门经理','value':2}]);}},
					temp3: {name:'temp3', label : '操作类型', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'审核','value':1},{'name':'办理','value':2}]);}}
				}},
			end : {
				showType: 'image&text',
				type : 'end',
				name : {text:'<<end>>'},
				text : {text:'结束'},
				img : {src : '/jnys/images/myflow/img/48/end_event_terminate.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'结束'},
					temp1: {name:'temp1', label : '姓名', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'制表员','value':1},{'name':'部门经理','value':2}]);}}
				}},
			'end-cancel' : {type : 'end-cancel',
				showType: 'image&text',
				name : {text:'<<end-cancel>>'},
				text : {text:'取消'},
				img : {src : '/jnys/images/myflow/img/48/end_event_cancel.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'取消'},
					temp1: {name:'temp1', label : '姓名', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			'end-error' : {type : 'end-error',
				showType: 'image&text',
				name : {text:'<<end-error>>'},
				text : {text:'错误'},
				img : {src : '/jnys/images/myflow/img/48/end_event_error.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'错误'},
					temp1: {name:'temp1', label : '姓名', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			state : {type : 'state',
				showType: 'image&text',
				name : {text:'<<state>>'},
				text : {text:'状态'},
				img : {src : '/jnys/images/myflow/img/48/task_empty.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'状态'},
					temp1: {name:'temp1', label : '姓名', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			fork : {
				showType: 'image&text',
				type : 'fork',
				name : {text:'<<fork>>'},
				text : {text:'分支'},
				img : {src : '/jnys/images/myflow/img/48/gateway_exclusive.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'分支'},
					temp1: {name:'temp1', label: '姓名', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'制表员','value':1},{'name':'部门经理','value':2}]);}}
				}},
			join : {
				showType: 'image&text',
				type : 'join',
				name : {text:'<<join>>'},
				text : {text:'合并'},
				img : {src : '/jnys/images/myflow/img/48/gateway_parallel.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'合并'},
					temp1: {name:'temp1', label: '姓名', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'制表员','value':1},{'name':'部门经理','value':2}]);}}
				}},
			task : {
				showType: 'image&text',
				type : 'task',
				name : {text:'<<task>>'},
				text : {text:'任务'},
				img : {src : '/jnys/images/myflow/img/48/task_empty.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '显示', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'任务'},
					temp1: {name:'temp1', label: '姓名', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'财务经理','value':66666666},{'name':'总行行长','value':88888888}]);}},
					temp2: {name:'temp2', label : '角色', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'行长','value':1},{'name':'计财部经理','value':2}]);}},
					temp3: {name:'temp3', label : '操作类型', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'审核','value':1},{'name':'办理','value':2}]);}}
				}}
});
})(jQuery);