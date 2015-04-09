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
    Ext.define('Activity', {
        extend: 'Ext.data.Model',
        proxy: {
            type: 'ajax',
            reader: 'xml'
        },
        fields: [
            'title',
            { name: 'state', type: 'bool' },
            { name: 'start_time', type: 'date', dateFormat: 'n/j/Y' },
            'lasttime',
            'province',
            'city',
            'short_addr',
            'detail_addr',
            'total_people',
            'fee',
            'content',
            { name: 'publish_time', type: 'date', dateFormat: 'n/j/Y' }
        ]
    });

    

    // create the Data Store
    var store = Ext.create('Ext.data.Store', {
        // destroy the store if the grid is destroyed
        autoDestroy: true,
        model: 'Activity',
        proxy: {
            // load using HTTP
            type: 'ajax',
            url: 'dataprovider/activity.jsp'
        },
        //data: data,
        sorters: [{
            property: 'publish_time',
            direction: 'DESC'
        }]
    });
    
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToMoveEditor: 1,
        autoCancel: false
    });
    
    
    /*
     * 'title',
            { name: 'state', type: 'bool' },
            { name: 'start_time', type: 'date', dateFormat: 'n/j/Y' }
            'lasttime',
            'province',
            'city',
            'short_addr',
            'detail_addr',
            'total_people',
            'fee',
            'content',
            { name: 'publish_time', type: 'date', dateFormat: 'n/j/Y' }
     * 
     * */

    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        columns: [{
            header: '活动名称',
            dataIndex: 'title',
            width: 120,
            editor: {
                // defaults to textfield if no xtype is supplied
                allowBlank: false
            }
        }, {
            xtype: 'checkcolumn',
            header: '审核通过',
            dataIndex: 'state',
            width: 80,
            editor: {
                xtype: 'checkbox',
                cls: 'x-grid-checkheader-editor'
            }
        }, {
            xtype: 'datecolumn',
            header: '开始时间',
            dataIndex: 'start_time',
            width: 80,
            editor: {
                xtype: 'datefield',
                allowBlank: false,
                format: 'm/d/Y',
                minValue: '01/01/2006',
                minText: 'Cannot have a start date before the company existed!',
                maxValue: '01/01/2016'
            }
        },
        {
            header: '活动时长',
            dataIndex: 'lasttime',
            width: 80,
           
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },{
            header: '省',
            dataIndex: 'province',
            width: 60,
            
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        
        {
            header: '市',
            dataIndex: 'city',
            width: 60,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        {
            header: '简明地址',
            dataIndex: 'short_addr',
            width: 110,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        {
            header: '详细地址',
            dataIndex: 'detail_addr',
            width: 110,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        {
            header: '总人数',
            dataIndex: 'total_people',
            width: 80,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },{
            header: '费用',
            dataIndex: 'fee',
            width: 80,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },{
            header: '活动内容',
            dataIndex: 'content',
            width: 110,
            editor: {
               
            }
        },{
            xtype: 'datecolumn',
            header: '发布时间',
            dataIndex: 'publish_time',
            width: 100,
            editor: {
                xtype: 'datefield',
                allowBlank: false,
                format: 'm/d/Y',
                minValue: '01/01/2006',
                minText: 'Cannot have a start date before the company existed!',
                maxValue: '01/01/2016'
            }
        }
        ],
        tbar: [{
            text: '添加活动',
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
            text: '删除活动',
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
        title: '活动管理',
        items: grid,
        layout: 'fit',
        //maximized : true,
        closable: false
    }).show();
    
    store.load();
});