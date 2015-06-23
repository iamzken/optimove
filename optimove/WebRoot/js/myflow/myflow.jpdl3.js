(function($){
var myflow = $.myflow;
$.extend(true,myflow.config.props.props,{
	name : {name:'name', label:'��������', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	key : {name:'key', label:'���̱�ʶ', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	desc : {name:'desc', label:'��������', value:'', editor:function(){return new myflow.editors.inputEditor();}},
	constant : {name:'constant', label:'��������', value:'', editor:function(){return new myflow.editors.selectEditor([{'name':'Ԥ������','value':1},{'name':'��������','value':2}]);}}
});

$.extend(true,myflow.config.tools.states,{
	start : {
				showType: 'image&text',
				type : 'start',
				name : {text:'<<start>>'},
				text : {text:'��ʼ'},
				img : {src : '/jnys/images/myflow/img/48/start_event_empty.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'��ʼ'},
					temp1: {name:'temp1', label : '���� ', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'�Ʊ�Ա','value':1},{'name':'���ž���','value':2}]);}},
					temp3: {name:'temp3', label : '��������', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'���','value':1},{'name':'����','value':2}]);}}
				}},
			end : {
				showType: 'image&text',
				type : 'end',
				name : {text:'<<end>>'},
				text : {text:'����'},
				img : {src : '/jnys/images/myflow/img/48/end_event_terminate.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'����'},
					temp1: {name:'temp1', label : '����', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'�Ʊ�Ա','value':1},{'name':'���ž���','value':2}]);}}
				}},
			'end-cancel' : {type : 'end-cancel',
				showType: 'image&text',
				name : {text:'<<end-cancel>>'},
				text : {text:'ȡ��'},
				img : {src : '/jnys/images/myflow/img/48/end_event_cancel.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'ȡ��'},
					temp1: {name:'temp1', label : '����', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			'end-error' : {type : 'end-error',
				showType: 'image&text',
				name : {text:'<<end-error>>'},
				text : {text:'����'},
				img : {src : '/jnys/images/myflow/img/48/end_event_error.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'����'},
					temp1: {name:'temp1', label : '����', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			state : {type : 'state',
				showType: 'image&text',
				name : {text:'<<state>>'},
				text : {text:'״̬'},
				img : {src : '/jnys/images/myflow/img/48/task_empty.png',width : 48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text',label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'״̬'},
					temp1: {name:'temp1', label : '����', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{name:'aaa',value:1},{name:'bbb',value:2}]);}}
				}},
			fork : {
				showType: 'image&text',
				type : 'fork',
				name : {text:'<<fork>>'},
				text : {text:'��֧'},
				img : {src : '/jnys/images/myflow/img/48/gateway_exclusive.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'��֧'},
					temp1: {name:'temp1', label: '����', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'�Ʊ�Ա','value':1},{'name':'���ž���','value':2}]);}}
				}},
			join : {
				showType: 'image&text',
				type : 'join',
				name : {text:'<<join>>'},
				text : {text:'�ϲ�'},
				img : {src : '/jnys/images/myflow/img/48/gateway_parallel.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'�ϲ�'},
					temp1: {name:'temp1', label: '����', value:'', editor: function(){return new myflow.editors.selectEditor('select.json');}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'�Ʊ�Ա','value':1},{'name':'���ž���','value':2}]);}}
				}},
			task : {
				showType: 'image&text',
				type : 'task',
				name : {text:'<<task>>'},
				text : {text:'����'},
				img : {src : '/jnys/images/myflow/img/48/task_empty.png',width :48, height:48},
				attr : {width:50 ,heigth:50 },
				props : {
					text: {name:'text', label: '��ʾ', value:'', editor: function(){return new myflow.editors.textEditor();}, value:'����'},
					temp1: {name:'temp1', label: '����', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'������','value':66666666},{'name':'�����г�','value':88888888}]);}},
					temp2: {name:'temp2', label : '��ɫ', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'�г�','value':1},{'name':'�ƲƲ�����','value':2}]);}},
					temp3: {name:'temp3', label : '��������', value:'', editor: function(){return new myflow.editors.selectEditor([{'name':'���','value':1},{'name':'����','value':2}]);}}
				}}
});
})(jQuery);