Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.form.*',
    'Ext.ux.form.SearchField'
    
]);

Ext.onReady(function(){
    // Define our data model
    Ext.define('Employee', {
        extend: 'Ext.data.Model',
        proxy: {
            type: 'ajax',
            reader: 'xml'
        },
        fields: [
            'nickname',
            { name: 'verified', type: 'bool' },
            { name: 'start_activity', type: 'bool' },
            'realname',
            'cellphone',
            'corp',
            'corpdetail',
            'department',
            'position',
            'qq',
            'email',
            'pemail',
            'address',
            'remark1',
            'remark2',
            'remark3',
            { name: 'reg_time', type: 'date', dateFormat: 'n/j/Y' },
        ]
    });

    // Generate mock employee data
    var data = (function() {
        var lasts = ['Jones', 'Smith', 'Lee', 'Wilson', 'Black', 'Williams', 'Lewis', 'Johnson', 'Foot', 'Little', 'Vee', 'Train', 'Hot', 'Mutt'],
            firsts = ['Fred', 'Julie', 'Bill', 'Ted', 'Jack', 'John', 'Mark', 'Mike', 'Chris', 'Bob', 'Travis', 'Kelly', 'Sara'],
            lastLen = lasts.length,
            firstLen = firsts.length,
            usedNames = {},
            data = [],
            eDate = Ext.Date,
            now = new Date(),
            s = new Date(now.getFullYear() - 4, 0, 1),
            end = Ext.Date.subtract(now, Ext.Date.MONTH, 1),
            getRandomInt = Ext.Number.randomInt,

            generateName = function() {
                var name = firsts[getRandomInt(0, firstLen - 1)] + ' ' + lasts[getRandomInt(0, lastLen - 1)];
                if (usedNames[name]) {
                    return generateName();
                }
                usedNames[name] = true;
                return name;
            };
            
        while (s.getTime() < end) {
            var ecount = getRandomInt(0, 3);
            for (var i = 0; i < ecount; i++) {
                var name = generateName();
                data.push({
                	reg_time : eDate.add(eDate.clearTime(s, true), eDate.DAY, getRandomInt(0, 27)),
                	nickname : name,
                	verified : 0,
                	start_activity : 0,
                	realname : '',
                	cellphone : '',
                	corp : '',
                    email: name.toLowerCase().replace(' ', '.') + '@ficc.com'
                });
            }
            s = eDate.add(s, eDate.MONTH, 1);
        }
        
        //alert(JSON.stringify(data));

        return data;
    })();
    

    // create the Data Store
    var store = Ext.create('Ext.data.Store', {
        // destroy the store if the grid is destroyed
        autoDestroy: true,
        model: 'Employee',
        proxy: {
            // load using HTTP
            type: 'ajax',
            url: 'dataprovider/user.jsp'
        },
        //data: data,
        sorters: [{
            property: 'reg_time',
            direction: 'DESC'
        }]
    });
    
    

    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToMoveEditor: 1,
        autoCancel: false
    });

    // create the grid and specify what field you want
    // to use for the editor at each column.
    
    /*
     * 'nickname',
    { name: 'verified', type: 'bool' },
    { name: 'start_activity', type: 'bool' },
    'realname',
    'cellphone',
    'corp',
    'email',
    { name: 'reg_time', type: 'date', dateFormat: 'n/j/Y' },
     */
    
    
    
    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        columns: [{
            header: '微信名',
            dataIndex: 'nickname',
            width: 100,
            editor: {
                // defaults to textfield if no xtype is supplied
                allowBlank: false
            }
        }, {
            xtype: 'checkcolumn',
            header: '实名认证',
            dataIndex: 'verified',
            width: 80,
            editor: {
                xtype: 'checkbox',
                cls: 'x-grid-checkheader-editor'
            }
        }, {
            xtype: 'checkcolumn',
            header: '发起活动',
            dataIndex: 'start_activity',
            width: 80,
            editor: {
                xtype: 'checkbox',
                cls: 'x-grid-checkheader-editor'
            }
        },{
            header: '真实姓名',
            dataIndex: 'realname',
            width: 80,
           
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },{
            header: '手机',
            dataIndex: 'cellphone',
            width: 110,
            
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        
        {
            header: '公司',
            dataIndex: 'corp',
            width: 80,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        {
            header: '公司全称',
            dataIndex: 'corpdetail',
            width: 110,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        {
            header: '部门',
            dataIndex: 'department',
            width: 80,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        {
            header: '职位',
            dataIndex: 'position',
            width: 80,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },{
            header: 'QQ',
            dataIndex: 'qq',
            width: 80,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },{
            header: 'Email',
            dataIndex: 'email',
            width: 110,
            editor: {
                vtype: 'email'
            }
        },{
            header: '私人Email',
            dataIndex: 'pemail',
            width: 110,
            editor: {
                vtype: 'email'
            }
        },{
            header: '公司地址',
            dataIndex: 'address',
            width: 110,
            editor: {
            	
            }
        },{
            header: '备注1',
            dataIndex: 'remark1',
            width: 80,
            editor: {
            	
            }
        },{
            header: '备注2',
            dataIndex: 'remark2',
            width: 80,
            editor: {
            	
            }
        },{
            header: '备注3',
            dataIndex: 'remark3',
            width: 80,
            editor: {
            	
            }
        }, {
            xtype: 'datecolumn',
            header: '注册时间',
            dataIndex: 'reg_time',
            width: 100,
            editor: {
                xtype: 'datefield',
                allowBlank: false,
                format: 'm/d/Y',
                minValue: '01/01/2006',
                minText: 'Cannot have a start date before the company existed!',
                maxValue: Ext.Date.format(new Date(), 'm/d/Y')
            }
        }
        ],
        tbar: [{
            text: '添加用户',
            iconCls: 'employee-add',
            handler : function() {
                rowEditing.cancelEdit();
                // Create a model instance
                var r = Ext.create('Employee', {
                	/*
                	 * reg_time : eDate.add(eDate.clearTime(s, true), eDate.DAY, getRandomInt(0, 27)),
                	nickname : name,
                	verified : 0,
                	start_activity : 0,
                	realname : '',
                	cellphone : '',
                	corp : '',
                    email: name.toLowerCase().replace(' ', '.') + '@ficc.com'
                	 */
                	reg_time : Ext.Date.clearTime(new Date()),
                	nickname : 'new user',
                	verified : false,
                	start_activity : false,
                	realname : '',
                	cellphone : '',
                	corp : '',
                    email: 'new@ficc.com'
                });

                store.insert(0, r);
                rowEditing.startEdit(0, 0);
            }
        }, {
            itemId: 'removeEmployee',
            text: '删除用户',
            iconCls: 'employee-remove',
            handler: function() {
                var sm = grid.getSelectionModel();
                rowEditing.cancelEdit();
                store.remove(sm.getSelection());
                if (store.getCount() > 0) {
                    sm.select(0);
                }
            },
            disabled: true
        }
        
        
        ],
        plugins: [rowEditing],
        listeners: {
            'selectionchange': function(view, records) {
                grid.down('#removeEmployee').setDisabled(!records.length);
            }
        }
    });
    new Ext.window.Window({
    	renderTo: 'content_grid',
    	constrain : true,
    	draggable : false,
    	x:0,
    	y:0,
        width: 960,
        height: 600,
        title: '用户认证与管理',
        items: grid,
        layout: 'fit',
        //maximized : true,
        closable: false
    }).show();
    
    store.load();
});