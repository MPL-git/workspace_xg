/**
 * @file Created by hejianchuan on 16/11/16.
 */
var cy;
var layoutId = 0;
var cyObj = {
    boxSelectionEnabled: false,
    autounselectify: false,
    zoom: 1,
    zoomingEnabled: false,

    style: [
        {
            selector: '.group-container',
            css: {
                'shape': 'roundrectangle',
                'background-opacity': '1',
                'background-color': 'white',
                'content': 'data(title)',
                'text-valign': 'top',
                'text-halign': 'center',
                'color': 'dark',
                'font-size': 16,
                'border-width': 1,
                'border-color': 'dark',
                'text-margin-y': 22,
                'padding-top': 40,
                'width': 200
            }
        },
        {
            selector: '.node-container',
            css: {
                'shape': 'roundrectangle',
                'background-color': 'gray',
                'background-blacken': '0.1',
                'shadow-color': '#c0c0c0',
                'shadow-offset-x': '3',
                'shadow-offset-y': '3',
                'shadow-blur': '0',
                'shadow-opacity': '0.5',
                'content': 'data(title)',
                'text-valign': 'center',
                'text-halign': 'center',
                'color': 'white',
                'font-size': 12,
                'width': 125
            }
        },
        {
            selector: '.close-button',
            css: {
                'content': 'data(title)',
                'color': 'white',
                'font-size': 12,
                'text-valign': 'center',
                'text-halign': 'center',
                'shape': 'ellipse',
                'background-color': 'red',
                'width': 20,
                'height': 20
            }
        },
        {
            selector: '.edge',
            css: {
                'curve-style': 'bezier',
                'target-arrow-shape': 'triangle',
                'line-color': 'data(color)',
                'width': 1,
                'target-arrow-color': 'data(color)'
            }
        },
        // some style for the ext

        {
            selector: '.edgehandles-hover',
            css: {
                'background-color': 'red'
            }
        },
        {
            selector: '.edgehandles-source',
            css: {
                'border-width': 2,
                'border-color': 'red'
            }
        },
        {
            selector: '.edgehandles-target',
            css: {
                'border-width': 2,
                'border-color': 'red'
            }
        },
        {
            selector: '.edgehandles-preview, .edgehandles-ghost-edge',
            css: {
                'line-color': 'red',
                'target-arrow-color': 'red',
                'source-arrow-color': 'red'
            }
        }],

    elements: {
        nodes: [],
        edges: []
    },

    layout: {
        name: 'preset',
        padding: 5
    },
    ready: function () {
        window.cy = cy = this;
        // resetEdgehandles();
    }
};

var nodes = [];


var Manager = {
        checkCode: function (id, code, type) {
            var url = '';
            if ('group' === type) {
                url = '/jf_mcht/monitor/group/checkCode?layoutId=' + id + '&groupCode=' + code;
            } else {
                url = '/jf_mcht/monitor/node/checkCode?layoutId=' + id + '&nodeCode=' + code;
            }
            var tag = true;
            $.ajax({
                method: 'GET',
                async: false,
                url: url
            }).done(function (data) {
                if (data.status === 1) {
                    if (data.content === '1') {
                        tag = true;
                    } else {
                        tag = false;
                    }
                }
            });
            return tag;
        },
        setGroup: function (id) {
            var pGroup = $('#' + id);
            pGroup.empty();
            pGroup.append('<option value="0">==请选择==</option>');
            $.ajax({
                method: 'GET',
                url: '/jf_mcht/monitor/group/getList?layoutId=' + layoutId
            }).done(function (data) {
                if (data.status === 1) {
                    var groups = data.content;
                    if (groups && groups.length > 0) {
                        for (var i = 0; i < groups.length; i++) {
                            pGroup.append('<option value="'
                                    + groups[i].groupId + '">'
                                    + groups[i].groupName
                                    + '</option>');
                        }
                    }
                }
            });
        },
        saveLayout: function () {
            var nodeArray = [];
            var nodes =  cy.$('.node-container');
            for (var i = 0; i < nodes.length; i++) {
                var nodeId = parseInt((nodes[i].id()).replace('node', ''), 10);
                var viewInfo = {};

                var position = nodes[i].position();
                viewInfo.x = position.x;
                viewInfo.y = position.y;
                var viewInfoStr = JSON.stringify(viewInfo);
                var node = {};
                node.nodeId = nodeId;
                node.viewInfo = viewInfoStr;
                nodeArray[i] = node;
            }
            var groupArray = [];
            var groups = cy.$('.group-container');
            var y = 0;
            $.each(groups, function (index, group) {
                var renderedPosition = group.renderedPosition();
                var isExpand = group.data('isExpand');
                var groupId = parseInt(group.id().replace('group', ''), 10);
                var g = {};
                var viewInfo = {};
                g.groupId = groupId;
                viewInfo.x = renderedPosition.x;
                viewInfo.y = renderedPosition.y;
                viewInfo.expand = isExpand;
                var viewInfoStr = JSON.stringify(viewInfo);
                g.viewInfo = viewInfoStr;
                groupArray[y] = g;
                y++;
            });

            $.ajax({
                method: 'POST',
                url: '/jf_mcht/monitor/group/saveViewInfos',
                data: {
                    groupListStr: JSON.stringify(groupArray)
                }
            }).done(function (data) {
                if (data.status === 1) {
                    $.ajax({
                        method: 'POST',
                        url: '/jf_mcht/monitor/node/saveViewInfos',
                        data: {
                            nodeListStr: JSON.stringify(nodeArray)
                        }
                    }).done(function (data) {
                        if (data.status === 1) {
                            var layoutName = $('#layoutName').val();
                            $.ajax({
                                method: 'POST',
                                url: '/jf_mcht/monitor/layout/update',
                                data: {
                                    layoutName: layoutName,
                                    layoutId: layoutId
                                }
                            }).done(function (data) {
                                if (data.status === 1) {
                                    alert('页面设置保存成功!');
                                } else {
                                    alert('页面设置保存失败!');
                                }
                            });
                        } else {
                            alert('页面设置保存失败!');
                        }
                    });
                } else {
                    alert('页面设置保存失败!');
                }
            });
        },
        saveGroup: function () {
            var group = {};
            group.layoutId = layoutId;
            var code = $('#groupCode').val();
            if (code.length === 0) {
                alert('请输入任务组编号!');
                return;
            }
            var groupStatus = $('#groupStatus').val();
            if (groupStatus == 'add' && Manager.checkCode(layoutId, code, 'group')) {
                alert('任务组编号已存在,请重新输入!');
                $('#groupCode').val('');
                return;
            }
            group.groupName = $('#groupName').val();
            if (code.length === 0 || group.groupName.length === 0) {
                alert('请输入必填字段!');
                return false;
            }
            group.pGroupId = $('#pGroupId').val();
            group.description = $('#description').val();
            var viewInfo = {};
            var expand = $('#expand').val();
            viewInfo.expand = false;
            if (expand === 1) {
                viewInfo.expand = true;
            }
            var url = '/jf_mcht/monitor/group/save';
            if (groupStatus != 'add') {
                url = '/jf_mcht/monitor/group/update';
                group.groupId = $('#groupid').val();
                var groupObject = cy.$('#group' + group.groupId);
                var renderedPosition = groupObject.renderedPosition();
                viewInfo.x = renderedPosition.x;
                viewInfo.y = renderedPosition.y;

            } else {
                group.groupCode = code;
                viewInfo.x = parseInt($('#relX').val(), 10);
                viewInfo.y = parseInt($('#relY').val(), 10);
            }
            var infoStr = JSON.stringify(viewInfo);
            group.viewInfo = infoStr;
            $.ajax({
                method: 'POST',
                url: url,
                data: group
            }).done(function (data) {
                if (data.status === 1) {
                    if (groupStatus === 'add') {
                        group.groupId = data.content;
                        group.viewInfo = viewInfo;
                        Layout.addGroup(group);
                        Manager.setGroup();
                    } else {
                        // 修改group属性
                        cy.$('#group' + group.groupId).data('isExpand', viewInfo.expand);
                        cy.$('#group' + group.groupId).data('title', group.groupName);
                    }
                    $('#groupDiv').modal('hide');
                    alert('保存成功!');
                } else {
                    alert('保存失败!');
                }
            });

        },
        checkValue: function (value, msg) {
            if (value.length === 0) {
                alert(msg + '不能为空!');
                return true;
            }
            return false;
        },
        addNode: function () {

            var param = {};
            var nodeStatus = $('#nodeStatus').val();
            var type = $('#dataType  option:selected').text();
            param.type = type;
            param.layoutId = layoutId;
            param.groupId = 0;
            param.nodeCode = $('#nodeCode').val();
            if (nodeStatus == 'add' && Manager.checkValue(param.nodeCode, '节点编号')) {
                return;
            }
            if (nodeStatus == 'add' && Manager.checkCode(layoutId, $('#nodeCode').val(), 'node')) {
                alert('节点编号已存在,请重新输入!');
                $('#nodeCode').val('');
                return false;
            }
            param.jobId = 10;
            param.nodeName = $('#nodeName').val();
            if (Manager.checkValue(param.nodeName, '节点名')) {
                return false;
            }
            param.threshold = $('#threshold').val();
            if (Manager.checkValue(param.threshold, '阈值')) {
                return false;
            }
            param.n_description = $('#n_description').val();
            param.jobName = $('#nodeCode').val();
            param.cronExpression = $('#cronExpression').val();
            if (Manager.checkValue(param.cronExpression, '启动时间')) {
                return false;
            }
            param.jobClass = $('#dataType').val();
            if ('HDFS' === type) {
                param.hdfsLink = $('#hdfsLink').val();
            } else if ('FTP' === type || 'MYSQL' === type) {
                param.host = $('#host').val();
                if (Manager.checkValue(param.host, '主机地址')) {
                    return false;
                }
                param.port = $('#port').val();
                if (Manager.checkValue(param.port, '端口号')) {
                    return false;
                }
                param.user = $('#user').val();
                if (Manager.checkValue(param.user, '用户名')) {
                    return false;
                }
                param.password = $('#password').val();
                if (Manager.checkValue(param.password, '密码')) {
                    return false;
                }
                if ('FTP' === type) {
                    param.timeout = $('#timeout').val();
                    if (Manager.checkValue(param.timeout, '超时时间')) {
                        return false;
                    }
                }
                if ('MYSQL' === type) {
                    param.database = $('#database').val();
                    if (Manager.checkValue(param.database, '数据库名')) {
                        return false;
                    }
                    param.query = $('#query').val();
                    if (Manager.checkValue(param.query, '查询语句')) {
                        return false;
                    }
                }
            }
            if (type != 'MYSQL') {
                param.filePath = $('#filePath').val();
                if (Manager.checkValue(param.filePath, '检测路径')) {
                    return false;
                }
            }
            var type = $('#rotate_type').val();
            if (Manager.checkValue(type, '检测周期')) {
                return false;
            }
            var num = $('#rotate_type_num').val();
            var base = 0;
            param.rotate_type = num + type;
            if (type === 'd') {
                base = 86400;
            } else if (type === 'h') {
                base = 3600;
            } else {
                base = 60;
            }

            param.rotate_begin_num = $('#rotate_begin_num').val();
            param.rotate_end_num = $('#rotate_end_num').val();
            if (Manager.checkValue(param.rotate_begin_num, '检测起始时间')
                    || Manager.checkValue(param.rotate_end_num, '检测起始时间')) {
                return false;
            }
            param.retryInterval = $('#retryInterval').val();
            param.retryCount = $('#retryCount').val();
            if (Manager.checkValue(param.retryInterval, '重试间隔')) {
                return false;
            }
            param.smsRec = $('#smsRec').val();
            param.emailRec = $('#emailRec').val();
            param.s_description = $('#s_description').val();
            var url = '/jf_mcht/monitor/node/save';
            var viewInfo = {};
            if (nodeStatus == 'add') {
                viewInfo.refreshGap = num * base;
                viewInfo.x = parseInt($('#relX').val(), 10);
                viewInfo.y = parseInt($('#relY').val(), 10);
                var infoStr = JSON.stringify(viewInfo);
                param.viewInfo = infoStr;
                var groups = cy.$('.group-container');
                var groupId = null;
                $.each(groups, function (index, group) {
                    var renderedPosition = group.renderedPosition();
                    var width = group.width();
                    var height = group.height();
                    if (viewInfo.x >= renderedPosition.x - width / 2 - 15
                            && viewInfo.x <= renderedPosition.x + width / 2 + 15
                            && viewInfo.y >= renderedPosition.y - height / 2 - 25
                            && viewInfo.y <= renderedPosition.y + height / 2 + 25) {
                        groupId = group.id();
                        return;
                    }
                });
                if (null != groupId) {
                    param.groupId = parseInt(groupId.replace('group', ''), 10);
                } else {
                }
            } else {
                url = '/jf_mcht/monitor/node/updateNode';
                param.nodeId = $('#nodeid').val();
            }
            $.ajax({
                method: 'POST',
                url: url,
                data: param
            }).done(function (data) {
                if (data.status === 1) {
                    if (nodeStatus == 'add') {
                        param.nodeId = data.content;
                        param.viewInfo = viewInfo;
                        Layout.addNode(param);
                    } else {
                        cy.$('#node' + param.nodeId).data('title', param.nodeName);
                    }
                    $('#nodeDiv').modal('hide');
                    alert('保存成功！');
                } else {
                    alert('保存失败！');
                }
            });

        },
        deleteGroup: function (groupId) {
            $.ajax({
                method: 'GET',
                url: '/jf_mcht/monitor/group/delete?groupId=' + groupId
            }).done(function (data) {
                if (data.status === 1) {
                  //  alert('删除成功！');
                } else {
                   // alert('删除失败！');
                }
            });
        },
        deleteNode: function (nodeId) {
            $.ajax({
                method: 'GET',
                url: '/jf_mcht/monitor/node/delete?nodeId=' + nodeId
            }).done(function (data) {
                if (data.status === 1) {
                   // alert('删除成功！');
                } else {
                   // alert('删除失败！');
                }
            });
        },
        saveEdge: function (begin, end) {
            var param = {};
            param.beginNodeId = begin;
            param.endNodeId = end;
            param.edgeCode = '';
            param.edgeName = '';
            param.viewInfo = '';
            param.layoutId = layoutId;
            $.ajax({
                method: 'POST',
                url: '/jf_mcht/monitor/edge/save',
                data: param
            }).done(function (data) {
                if (data.status === 1) {

                } else {

                }
            });
        },
        deleteEdge: function (begin, end) {
            var param = {};
            param.beginNodeId = begin;
            param.endNodeId = end;
            $.ajax({
                method: 'POST',
                url: '/jf_mcht/monitor/edge/deleteEdgeByBeginEnd',
                data: param
            }).done(function (data) {
                if (data.status === 1) {
                   // alert('删除成功！');
                } else {
                   // alert('删除失败！');
                }
            });
        }
    };
var resetEdgehandles = function () {
    cy.edgehandles('destroy');
    cy.edgehandles({
        toggleOffOnLeave: true,
        handleNodes: 'node.node-container',
        handleSize: 10,
        handleColor: '#ff0000',
        handleLineType: 'ghost',
        handleLineWidth: 1,
        handleIcon: false,
        edgeType: function (sourceNode, targetNode) {
            if (sourceNode.hasClass('node-container')
                    && targetNode.hasClass('node-container')) {
                return 'flat';
            }

            return null;
        },
        loopAllowed: function (node) {
            // for the specified node, return whether edges from itself to
            // itself are allowed
            return false;
        },
        edgeParams: function (sourceNode, targetNode, i) {
            if (sourceNode.hasClass('node-container') && targetNode.hasClass('node-container')) {
                return {data: {id: sourceNode.id() + '_' + targetNode.id(), color: 'gray',
                    source: sourceNode.id(), target: targetNode.id()}, classes: 'edge'};
            }

            return {};
        },
        start: function (sourceNode) {
            // fired when edgehandles interaction starts (drag on handle)
        },
        complete: function (sourceNode, targetNodes, addedEntities) {
            $.each(addedEntities, function (index, addedEntity) {
                // 保存
                var sourceNodeId = addedEntity.source().id();
                var targetNodeId = addedEntity.target().id();
                var begin = parseInt(sourceNodeId.replace('node', ''), 10);
                var end = parseInt(targetNodeId.replace('node', ''), 10);
                Manager.saveEdge(begin, end);
                Layout.bindEdgeEvent(addedEntity);
            });
        },
        stop: function (sourceNode) {
            // fired when edgehandles interaction is stopped (either complete with added edges or incomplete)
        }
    });
};

var Layout = {
    addGroup: function (group) {
        var groupId = 'group' + group.groupId;
        var pGroupId = 'group' + group.pGroupId;
        var viewInfo = group.viewInfo;
        var groupView = {
            data: {
                id: groupId,
                isExpand: viewInfo.expand,
                title: group.groupName
            },
            classes: 'group-container'
        };
        if (viewInfo.x != null && viewInfo.y != null) {
            groupView.renderedPosition = {
                x: viewInfo.x,
                y: viewInfo.y
            };
        }
        if (pGroupId !== 0) {
            groupView.data.parent = pGroupId;
        }
        cy.add(groupView);

        var closeBtn = {
            data: {
                id: 'close-' + groupId,
                linkId: groupId,
                title: 'X'
            },
            classes: 'close-button',
            renderedPosition: {
                x: viewInfo.x + 100,
                y: viewInfo.y - 35
            }
        };

        cy.add(closeBtn);

        cy.$('#' + groupId).on('drag', function () {
            Layout.changeGroupCloseButtonPosition(this);
        }).on('click', function (e) {
            e.stopPropagation();
            var node = this;

            var expandcollapseRenderedStartX = node._private.data.expandcollapseRenderedStartX;
            var expandcollapseRenderedStartY = node._private.data.expandcollapseRenderedStartY;
            var expandcollapseRenderedRectSize = node._private.data.expandcollapseRenderedCueSize;
            var expandcollapseRenderedEndX = expandcollapseRenderedStartX + expandcollapseRenderedRectSize;
            var expandcollapseRenderedEndY = expandcollapseRenderedStartY + expandcollapseRenderedRectSize;

            var cyRenderedPosX = e.cyRenderedPosition.x;
            var cyRenderedPosY = e.cyRenderedPosition.y;
            // factor = (options().expandCollapseCueSensitivity - 1) / 2;
            var factor = 0;

            if (cyRenderedPosX >= expandcollapseRenderedStartX - expandcollapseRenderedRectSize * factor
                && cyRenderedPosX <= expandcollapseRenderedEndX + expandcollapseRenderedRectSize * factor
                && cyRenderedPosY >= expandcollapseRenderedStartY - expandcollapseRenderedRectSize * factor
                && cyRenderedPosY <= expandcollapseRenderedEndY + expandcollapseRenderedRectSize * factor) {
                return;
            }

            $('#groupStatus').val('modify');
            $('#groupid').val(group.groupId);
            $('#groupDiv').modal('show');
            $.ajax({
                method: 'GET',
                url: '/jf_mcht/monitor/group/getById?groupId=' + group.groupId
            }).done(function (data) {
                if (data.status === 1) {
                    var content = data.content;
                    $('#groupCode').attr('disabled', true);
                    $('#groupCode').val(content.groupCode);
                    $('#groupName').val(content.groupName);
                    $('#pGroupId').val(content.pGroupId);
                    var vi = JSON.parse(content.viewInfo);
                    if (vi.expand) {
                        $('#expand').val(1);
                    } else {
                        $('#expand').val(0);
                    }
                    $('#description').val(content.description);
                }
            });
        });

        cy.$('#close-' + groupId).on('click', function () {
            if (confirm('您确定要删除吗?')) {
                var linkNode = cy.$('#' + this.data('linkId'));
                cy.remove(linkNode.union(linkNode.connectedEdges()));
                cy.remove(this);

                if (linkNode.isParent()) {
                    var nodeContainers = linkNode.children();
                    $.each(nodeContainers, function (index, nodeContainer) {
                        cy.remove(cy.$('#close-' + nodeContainer.id()));
                    });
                }
                Manager.deleteGroup(group.groupId);
            }
        }).on('grab', function () {
            this.ungrabify();
        }).on('free', function () {
            this.ungrabify();
        });
    },
    dispalyItem: function (type) {
        if ('HDFS' === type) {
            $('#jq').css('display', 'block');
            $('#lj').css('display', 'block');
            $('#hs').css('display', 'none');
            $('#p').css('display', 'none');
            $('#un').css('display', 'none');
            $('#pw').css('display', 'none');
            $('#to').css('display', 'none');
            $('#db').css('display', 'none');
            $('#qy').css('display', 'none');
        } else if ('FTP' === type) {
            $('#jq').css('display', 'none');
            $('#lj').css('display', 'block');
            $('#hs').css('display', 'block');
            $('#p').css('display', 'block');
            $('#un').css('display', 'block');
            $('#pw').css('display', 'block');
            $('#to').css('display', 'block');
            $('#db').css('display', 'none');
            $('#qy').css('display', 'none');
        } else if ('MYSQL' === type) {
            $('#jq').css('display', 'none');
            $('#lj').css('display', 'none');
            $('#hs').css('display', 'block');
            $('#p').css('display', 'block');
            $('#un').css('display', 'block');
            $('#pw').css('display', 'block');
            $('#to').css('display', 'none');
            $('#db').css('display', 'block');
            $('#qy').css('display', 'block');
        }
    },
    addNode: function (node) {
        var nodeId = 'node' + node.nodeId;
        var viewInfo = node.viewInfo;

        var containerView = {
            data: {
                id: nodeId,
                title: node.nodeName
            },
            classes: 'node-container',
            renderedPosition: {
                x: viewInfo.x,
                y: viewInfo.y
            }
        };
        if (node.groupId !== null) {
            containerView.data.parent = 'group' + node.groupId;
        }
        cy.add(containerView);

        cy.$('#' + nodeId).on('click', function (e) {
            e.stopPropagation();
            $('#nodeStatus').val('modify');
            $('#nodeid').val(node.nodeId);
            $('#nodeDiv').modal('show');
            $.ajax({
                method: 'GET',
                url: '/jf_mcht/monitor/node/getNodeInfo?nodeId=' + node.nodeId
            }).done(function (data) {
                if (data.status === 1) {
                    var content = data.content;
                    $('#nodeCode').attr('disabled', true);
                    $('#nodeCode').val(content.nodeCode);
                    $('#nodeName').val(content.nodeName);
                    $('#threshold').val(content.threshold);
                    $('#cronExpression').val(content.cronExpression);
                    $('#n_description').val(content.n_description);
                    $('#dataType').val(content.jobClass);
                    $('#dataType').attr('disabled', true);
                    Layout.dispalyItem(content.type);
                    $('#hdfsLink').val(content.hdfsLink);
                    $('#filePath').val(content.filePath);
                    $('#rotate_type_num').val(content.interval);
                    $('#rotate_type').val(content.unit);
                    $('#rotate_begin_num').val(content.rotateBeginNum);
                    $('#rotate_end_num').val(content.rotateEndNum);
                    $('#retryCount').val(content.retryCount);
                    $('#retryInterval').val(content.retryInterval);
                    $('#smsRec').val(content.sms);
                    $('#emailRec').val(content.email);
                    $('#s_description').val(content.s_description);
                    $('#host').val(content.host);
                    $('#port').val(content.port);
                    $('#user').val(content.user);
                    $('#password').val(content.password);
                    $('#database').val(content.database);
                    $('#query').val(content.query);
                    $('#timeout').val(content.timeout);
                }
            });
        });

        var closeBtn = {
            data: {
                id: 'close-' + nodeId,
                linkId: nodeId,
                title: 'X'
            },
            classes: 'close-button',
            renderedPosition: {
                x: viewInfo.x + 50,
                y: viewInfo.y - 17
            }
        };

        if (node.groupId !== null) {
            closeBtn.data.parent = 'group' + node.groupId;
        }
        cy.add(closeBtn);

        cy.$('#close-' + nodeId).on('click', function () {
            if (confirm('您确定要删除吗?')) {
                var linkNode = cy.$('#' + this.data('linkId'));
                cy.remove(linkNode.union(linkNode.connectedEdges()));
                cy.remove(this);

                if (linkNode.isChild()) {
                    Layout.changeGroupCloseButtonPosition(linkNode.parent());
                }
                Manager.deleteNode(node.nodeId);
            }
        }).on('grab', function () {
            this.ungrabify();
        }).on('free', function () {
            this.ungrabify();
        });

        cy.$('#' + nodeId).on('drag', function () {
            var position = this.renderedPosition();
            var width = this.width();
            cy.$('#close-' + this.id()).renderedPosition({
                x: position.x + width / 2,
                y: position.y - 17
            });

            if (this.isChild()) {
                Layout.changeGroupCloseButtonPosition(this.parent());
            }
        });

        if (node.groupId !== null) {
            cy.$('#' + node.groupId).trigger('drag');
        }

        resetEdgehandles();
    },
    addEdge: function (sourceId, targetId) {
        var sourceNodeId = 'node' + sourceId;
        var targetNodeId = 'node' + targetId;
        cy.add({data: {id: sourceNodeId + '_' + targetNodeId, color: 'gray',
            source: sourceNodeId, target: targetNodeId}, classes: 'edge'});

        Layout.bindEdgeEvent(cy.$('#' + sourceNodeId + '_' + targetNodeId));
    },
    bindEdgeEvent: function (edge) {
        var begin = parseInt(edge.source().id().replace('node', ''), 10);
        var end = parseInt(edge.target().id().replace('node', ''), 10);
        edge.unbind('click');
        edge.unbind('mouseover');
        edge.unbind('mouseout');
        edge.on('click', function () {
            if (confirm('您确定要删除吗?')) {
                cy.remove(this);
                // 删除
                Manager.deleteEdge(begin, end);
            }

        }).on('mouseover', function () {
            this.data('color', 'red');
        }).on('mouseout', function () {
            this.data('color', 'gray');
        });
    },
    changeGroupCloseButtonPosition: function (group) {
        var position = group.renderedPosition();
        var width = group.width();
        var height = group.height();
        cy.$('#close-' + group.id()).renderedPosition({
            x: position.x + width / 2,
            y: position.y - height / 2 - 20
        });
    },
    initGroupVisable: function () {
        var groups = cy.$('.group-container');
        for (var i = 0; i < groups.length; i++) {
            if (groups[i].data('isExpand')) {
                groups[i].expand();
            } else {
                groups[i].collapse();
            }
            Layout.changeGroupCloseButtonPosition(groups[i]);
        }
    }
};

var resetGroupEvent = function () {
    cy.$('.group-container').unbind('afterCollapse');
    cy.$('.group-container').unbind('afterExpand');
    cy.$('.group-container').on('afterCollapse', function (event) {
        var node = this;
        node.data('isExpand', false);
        Layout.changeGroupCloseButtonPosition(node);
    });
    cy.$('.group-container').on('afterExpand', function (event) {
        var node = this;
        node.data('isExpand', true);
        Layout.changeGroupCloseButtonPosition(node);
        var edges = node.children().connectedEdges();
        $.each(edges, function (index, edge) {
            Layout.bindEdgeEvent(edge);
        });
    });
};

$('#cy').cytoscape(cyObj);

$(function () {
    Manager.setGroup('pGroupId');
    $('#saveGroupBt').on('click', function (event) {
        Manager.saveGroup();
    });
    $('#saveNodeBt').on('click', function (event) {
        Manager.addNode();
    });
    $('#saveLayout').on('click', function (event) {
        Manager.saveLayout();
    });

    $('#dataType').change(function () {
        var value = $('#dataType  option:selected').text();
        Layout.dispalyItem(value);
     });

    $.ajax({
        method: 'POST',
        url: '/jf_mcht/monitor/group/getGroupList',
        data: {
            layoutId: layoutId
        }
    }).done(function (data) {
        if (data.status === 1) {
            var groups = data.content;
            for (var i = 0; i < groups.length; i++) {
                Layout.addGroup(groups[i]);
            }
            $.ajax({
                method: 'POST',
                url: '/jf_mcht/monitor/node/getNodeList',
                data: {
                    layoutId: layoutId
                }
            }).done(function (data) {
                if (data.status === 1) {
                    var nodes = data.content;
                    for (var i = 0; i < nodes.length; i++) {
                        Layout.addNode(nodes[i]);
                    }
                    $.ajax({
                        method: 'POST',
                        url: '/jf_mcht/monitor/edge/getEdgeList',
                        data: {
                            layoutId: layoutId
                        }
                    }).done(function (data) {
                        if (data.status === 1) {
                            var edges = data.content;
                            for (var i = 0; i < edges.length; i++) {
                                Layout.addEdge(edges[i].beginNodeId, edges[i].endNodeId);
                            }
                            Layout.initGroupVisable();
                            resetGroupEvent();
                        }
                    });
                }
            });
        }
    });
    cy.expandCollapse({
        layoutBy: null,
        fisheye: false,
        animate: false
    });
});
