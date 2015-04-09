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
    Ext.define('Signup', {
        extend: 'Ext.data.Model',
        proxy: {
            type: 'ajax',
            reader: 'json'
        },
        fields: [
            { name: 'verified', type: 'bool' },
            'user_id',
            'nickname',
            'activity_id',
            'activity_name',
            { name: 'ctime', type: 'date', dateFormat: 'n/j/Y' },
        ]
    });
    

    // create the Data Store
    var store = Ext.create('Ext.data.Store', {
        // destroy the store if the grid is destroyed
        autoDestroy: true,
        model: 'Signup',
        proxy: {
            // load using HTTP
            type: 'ajax',
            url: 'dataprovider/signup.jsp'
        },
        //data: data,
        sorters: [{
            property: 'ctime',
            direction: 'DESC'
        }]
    });
    
    

    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToMoveEditor: 1,
        autoCancel: false
    });

   
    
    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        columns: [{
            xtype: 'checkcolumn',
            header: '审核通过',
            dataIndex: 'verified',
            width: 100,
            editor: {
                xtype: 'checkbox',
                cls: 'x-grid-checkheader-editor'
            }
        },{
            header: '用户ID',
            dataIndex: 'user_id',
            width: 100,
           
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },{
            header: '用户微信名',
            dataIndex: 'nickname',
            width: 120,
            
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        
        {
            header: '活动ID',
            dataIndex: 'activity_id',
            width: 100,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        },
        {
            header: '活动名称',
            dataIndex: 'activity_name',
            width: 200,
            editor: {
                // defaults to textfield if no xtype is supplied
                //allowBlank: false
            }
        }, {
            xtype: 'datecolumn',
            header: '报名时间',
            dataIndex: 'ctime',
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
            text: '添加报名',
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
            text: '删除报名',
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
        title: '报名审核',
        items: grid,
        layout: 'fit',
        //maximized : true,
        closable: false
    }).show();
    
    store.load();
});