/**
 * @file
 * Created by hejianchuan on 16/10/27.
 */

var cy;
var nodes;
var groups;
var groupNodeMap = {};
var nodeGroupMap = {};
var groupNodeContent = {};
var DataStream = {
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
        if (pGroupId !== 0) {
            groupView.data.parent = pGroupId;
        }
        cy.add(groupView);
    },
    saveLayout: function () {
        var nodeArray = [];
        for (var i = 0; i < nodes.length; i++) {
            var nodeId = 'node' + nodes[i].nodeId;
            var viewInfo = nodes[i].viewInfo;

            var position = cy.$('#' + nodeId).position();
            viewInfo.x = position.x;
            viewInfo.y = position.y;
            var viewInfoStr = JSON.stringify(viewInfo);
            var node = {};
            node.nodeId = nodes[i].nodeId;
            node.viewInfo = viewInfoStr;
            nodeArray[i] = node;
        }

        $.ajax({
            method: 'POST',
            url: '/jf_mcht/monitor/node/saveViewInfos',
            data: {
                nodeListStr: JSON.stringify(nodeArray)
            }
        }).done(function (data) {
            if (data.status === 1) {
                alert('页面设置保存成功!');
            } else {
                alert('页面设置保存失败!');
            }
        });
    },
    addNode: function (node) {
        var nodeId = 'node' + node.nodeId;
        var viewInfo = node.viewInfo;
        var indexs = node.indexs;
        var groupId = 'group' + node.groupId;

        var containerView = {data: {id: nodeId, degree: 70, title: node.nodeName}, classes: 'node-container'};
        if (groupId !== 0) {
            containerView.data.parent = groupId;
            nodeGroupMap[nodeId] = groupId;
            if (!groupNodeMap.hasOwnProperty(groupId)) {
                groupNodeMap[groupId] = [];
            }
            groupNodeMap[groupId].push(nodeId);
        }
        cy.add(containerView);
        cy.$('#' + nodeId).on('click', function (e) {
            window.open('/jf_mcht/monitor/node/logPage?nodeId=' + node.nodeId);
        });

        var currentX = viewInfo.x;
        var currentY = viewInfo.y + 30;

        var lineView = {data: {parent: nodeId}, classes: 'node-line', position: {x: currentX, y: currentY}};
        cy.add(lineView);

        currentY += 10;
        for (var i = 0; i < indexs.length; i++) {
            if (indexs[i].display == 0) {
                continue;
            }
            var indexView = {data: {parent: nodeId, title: indexs[i].nodeIndexName}, classes: 'node-index',
                position: {x: currentX - 60, y: currentY}};
            cy.add(indexView);

            var indexValueView = {data: {id: nodeId + '_' + indexs[i].nodeIndexCode,
                parent: nodeId, title: ''},
                classes: 'node-index-value', position: {x: currentX + 40, y: currentY}};
            cy.add(indexValueView);

            currentY += 20;
        }
    },
    addEdge: function (edge) {
        var beginNodeId = 'node' + edge.beginNodeId;
        var endNodeId = 'node' + edge.endNodeId;

        cy.add({data: {id: beginNodeId + '_' + endNodeId, source: beginNodeId, target: endNodeId}, classes: 'edge'});
    },
    getIndexValues: function () {
        for (var i = 0; i < nodes.length; i++) {
            var node = nodes[i];
            var nodeId = node.nodeId;
            var refreshGap = node.viewInfo.refreshGap;

            var currentTime = Date.parse(new Date()) / 1000;
            if (node.fetchTime !== null) {
                var gap = currentTime - node.fetchTime;
                if (gap < refreshGap) {
                    continue;
                }
            }

            node.fetchTime = currentTime;

            var indexs = '';
            for (var j = 0; j < node.indexs.length; j++) {
                if (j === 0) {
                    indexs = node.indexs[j].nodeIndexCode;
                } else {
                    indexs += ',' + node.indexs[j].nodeIndexCode;
                }
            }

            $.ajax({
                method: 'POST',
                url: '/jf_mcht/monitor/nodeIndex/getIndexContent',
                data: {
                    layoutId: layoutId,
                    nodeId: nodeId,
                    indexs: indexs
                }
            }).done(function (data) {
                if (data.status === 1) {
                    var content = data.content;
                    DataStream.refreshNodeContent(content);
                }
            });
        }
    },
    refreshNodeContent: function (content) {
        $.each(content, function (key, value) {
            if (key !== 'nodeId') {
                var currentNode = cy.$('#node' + content.nodeId);
                groupNodeContent['node' + content.nodeId] = content;
                if (currentNode.id() == null) {
                    DataStream.changeLevel(cy.$('#' + nodeGroupMap['node' + content.nodeId]));
                } else {
                    cy.$('#node' + content.nodeId + '_' + key).data('title', value);
                    if (key === 'level') {
                        if (currentNode) {
                            currentNode.data('level', value);
                        }
                        var edges = currentNode.connectedEdges();
                        for (var i = 0; i < edges.length; i++) {
                            if (edges[i].source().id() === currentNode.id()) {
                                edges[i].data('level', value);
                            }
                        }

                        DataStream.changeLevel(currentNode.parent());
                    }
                }
            }
        });
    },
    changeLevel: function (node) {
        if (!groupNodeMap.hasOwnProperty(node.id())) {
            return;
        }

        var children = groupNodeMap[node.id()];

        var level = '0';
        for (var i = 0; i < children.length; i++) {
            if (!groupNodeContent.hasOwnProperty(children[i])) {
                continue;
            }
            var childLevel = groupNodeContent[children[i]].level;
            if (level < childLevel) {
                level = childLevel;
            }
        }

        node.data('level', level);
        var edges = node.connectedEdges();
        for (var i = 0; i < edges.length; i++) {
            if (edges[i].source().id() === node.id()) {
                edges[i].data('level', level);
            }
        }

        if (node.isChild()) {
            DataStream.changeLevel(node.parent());
        }

        return;
    },
    initGroupCollapse: function () {
        cy.expandCollapse({
            layoutBy: {
                name: 'preset',
                animate: true,
                randomize: false,
                fit: false
            },
            fisheye: false,
            animate: true
        });

        for (var i = 0; i < groups.length; i++) {
            var groupContainer = cy.$('#group' + groups[i].groupId);
            if (groupContainer.data('isExpand')) {
                groupContainer.expand();
            } else {
                groupContainer.collapse();
            }
        }
        cy.$('.group-container').on('beforeCollapse', function (event) {
            var group = this;
            group.data('groupWidth', group.width());
            group.data('groupHeight', group.height());
        });
        cy.$('.group-container').on('afterCollapse', function (event) {
            var group = this;
            var currentGroupPosition = group.position();
            var groupWidth = group.data('groupWidth');
            var groupHeight = group.data('groupHeight');
            for (var i = 0; i < groups.length; i++) {
                var groupContainer = cy.$('#group' + groups[i].groupId);
                if (groupContainer.id() == group.id() || groupContainer.data('isExpand')) {
                    continue;
                }
                var groupPosition = groupContainer.position();
                var groupX = groupPosition.x - (200 - groupWidth) / 2;
                if (groupPosition.x > currentGroupPosition.x) {
                    groupX = groupPosition.x  + (200 - groupWidth) / 2;
                }
                var groupY = groupPosition.y - (100 - groupHeight) / 2;
                if (groupPosition.y > currentGroupPosition.y) {
                    groupY = groupPosition.y  + (100 - groupHeight) / 2;
                }
                groupContainer.position({
                    x: groupX,
                    y: groupY
                })
            }

            for (var i = 0; i < nodes.length; i++) {
                var nodeContainer = cy.$('#node' + nodes[i].nodeId);
                if (nodeContainer.id() == null || nodeContainer.parent().id() == group.id()) {
                    continue;
                }

                var nodePosition = nodeContainer.position();
                var offsetX = (groupWidth - 200) / 2;
                if (nodePosition.x > currentGroupPosition.x) {
                    offsetX = (200 - groupWidth) / 2;
                }
                var offsetY = (groupHeight - 100) / 2;
                if (nodePosition.y > currentGroupPosition.y) {
                    offsetY = (100 - groupHeight) / 2;
                }
                var indexNodes = nodeContainer.children();
                for (var j = 0; j < indexNodes.length; j++) {
                    var indexNode = indexNodes[j];
                    indexNode.position({
                        x: indexNode.position().x + offsetX,
                        y: indexNode.position().y + offsetY 
                    });
                }
            }
        });
        cy.$('.group-container').on('afterExpand', function (event) {
            var group = this;

            var currentGroupPosition = group.position();
            
            var children = group.children();
            for (var i = 0; i < children.length; i++) {
                if (groupNodeContent.hasOwnProperty(children[i].id())) {
                    DataStream.refreshNodeContent(groupNodeContent[children[i].id()]);
                }
            }
            
            for (var i = 0; i < groups.length; i++) {
                var groupContainer = cy.$('#group' + groups[i].groupId);
                if (groupContainer.id() == group.id() || groupContainer.data('isExpand')) {
                    continue;
                }
                var groupPosition = groupContainer.position();
                var groupX = groupPosition.x - (group.width() - 200) / 2;
                if (groupPosition.x > currentGroupPosition.x) {
                    groupX = groupPosition.x  + (group.width() - 200) / 2;
                }
                var groupY = groupPosition.y - (group.height() - 100) / 2;
                if (groupPosition.y > currentGroupPosition.y) {
                    groupY = groupPosition.y  + (group.height() - 100) / 2;
                }
                groupContainer.position({
                    x: groupX,
                    y: groupY
                })
            }

            for (var i = 0; i < nodes.length; i++) {
                var nodeContainer = cy.$('#node' + nodes[i].nodeId);
                if (nodeContainer.id() == null || nodeContainer.parent().id() == group.id()) {
                    continue;
                }

                var nodePosition = nodeContainer.position();
                var offsetX = (200 - group.width()) / 2;
                if (nodePosition.x > currentGroupPosition.x) {
                    offsetX = (group.width() - 200) / 2;
                }
                var offsetY = (100 - group.height()) / 2;
                if (nodePosition.y > currentGroupPosition.y) {
                    offsetY = (group.height() - 100) / 2;
                }
                var indexNodes = nodeContainer.children();
                for (var j = 0; j < indexNodes.length; j++) {
                    var indexNode = indexNodes[j];
                    indexNode.position({
                        x: indexNode.position().x + offsetX,
                        y: indexNode.position().y + offsetY 
                    });
                }
            }
        });
    }
};

var levelMapData = function (ele) {
    var level = ele.data('level');
    if (level === '0') {
        return '#71C671'; // 绿色
    } else if (level === '1') {
        return '#FFD39B'; // 黄色
    } else if (level === '2') {
        return '#EE5C42'; // 红色
    } else {
        return '#A3A3A3'; // 灰色
    }
};

var initTimer = function () {
    setInterval(function () {
        DataStream.getIndexValues();
    }, 60000);
};

var waitingDialog = (function ($) {
    'use strict';

    // Creating modal dialog's DOM
    var $dialog = $(
        '<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" '
        + 'role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">'
        + '<div class="modal-dialog modal-m">'
        + '<div class="modal-content">'
        + '<div class="modal-header"><h3 style="margin:0;"></h3></div>'
        + '<div class="modal-body">'
        + '<div class="progress progress-striped active" style="margin-bottom:0;">'
        + '<div class="progress-bar" style="width: 100%"></div></div>'
        + '</div>'
        + '</div></div></div>');

    return {

        /**
         * Opens our dialog
         * @param {string} message Custom message
         * @param {Object} options Custom options:
         *          options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
         *          options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
         */
        show: function (message, options) {
            // Assigning defaults
            if (typeof options === 'undefined') {
                options = {};
            }
            if (typeof message === 'undefined') {
                message = 'Loading';
            }
            var settings = $.extend({
                dialogSize: 'm',
                progressType: '',
                onHide: null // This callback runs after the dialog was hidden
            }, options);

            // Configuring dialog
            $dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
            $dialog.find('.progress-bar').attr('class', 'progress-bar');
            if (settings.progressType) {
                $dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
            }
            $dialog.find('h3').text(message);
            // Adding callbacks
            if (typeof settings.onHide === 'function') {
                $dialog.off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
                    settings.onHide.call($dialog);
                });
            }
            // Opening dialog
            $dialog.modal();
        },

        /**
         * Closes dialog
         */
        hide: function () {
            $dialog.modal('hide');
        }
    };

})(jQuery);


$(function () { // on dom ready
    cy = cytoscape({
        container: document.getElementById('cy'),

        boxSelectionEnabled: false,
        autounselectify: false,
        zoom: 1,
        zoomingEnabled: true,

        style: [
            {
                selector: '.group-container',
                css: {
                    'shape': 'roundrectangle',
                    'background-opacity': '1',
                    'background-color': '#E0FFFF',
                    'content': 'data(title)',
                    'text-valign': 'top',
                    'text-halign': 'center',
                    'color': 'dark',
                    'font-size': 16,
                    'border-width': 1,
                    'text-margin-y': 22,
                    'width': 200,
                    'padding-top': 40,
                    'border-color': function (ele) { return levelMapData(ele); }
                }
            },
            {
                selector: '.node-container',
                css: {
                    'shape': 'roundrectangle',
                    'background-color': function (ele) { return levelMapData(ele); },
                    'background-blacken': '0.1',
                    'shadow-color': '#c0c0c0',
                    'shadow-offset-x': '3',
                    'shadow-offset-y': '3',
                    'shadow-blur': '0',
                    'shadow-opacity': '0.5',
                    'content': 'data(title)',
                    'text-valign': 'top',
                    'text-halign': 'center',
                    'color': 'white',
                    'font-size': 12,
                    'text-margin-y': 20,
                    'width': 200
                }
            },
            {
                selector: '.node-line',
                css: {
                    'label': '----------------------------------------',
                    'text-halign': 'center',
                    'text-valign': 'center',
                    'font-size': 10,
                    'color': 'white',
                    'background-opacity': '0',
                    'shape': 'rectangle',
                    'padding-bottom': 10,
                    'width': 200,
                    'height': 30
                }
            },
            {
                selector: '.node-index',
                css: {
                    'label': 'data(title)',
                    'text-halign': 'center',
                    'text-valign': 'center',
                    'font-size': 10,
                    'width': 80,
                    'height': 14,
                    'background-opacity': '0'
                }
            },
            {
                selector: '.node-index-value',
                css: {
                    'label': 'data(title)',
                    'text-halign': 'center',
                    'text-valign': 'center',
                    'font-size': 10,
                    'width': 120,
                    'height': 14,
                    'background-opacity': '0'
                }
            },
            {
                selector: '.edge',
                css: {
                    'curve-style': 'bezier',
                    'target-arrow-shape': 'triangle',
                    'line-color': function (ele) { return levelMapData(ele); },
                    'width': 1,
                    'target-arrow-color': function (ele) { return levelMapData(ele); }
                }
            }
        ],

        layout: {
            name: 'preset',
            padding: 5
        }
    });

    waitingDialog.show('正在渲染视图,请耐心等待...');
    $.ajax({
        method: 'POST',
        url: '/jf_mcht/monitor/group/getGroupList',
        data: {
            layoutId: layoutId
        }
    }).done(function (data) {
        if (data.status === 1) {
            groups = data.content;
            for (var i = 0; i < groups.length; i++) {
                DataStream.addGroup(groups[i]);
            }

            $.ajax({
                method: 'POST',
                url: '/jf_mcht/monitor/node/getNodeList',
                data: {
                    layoutId: layoutId
                }
            }).done(function (data) {
                if (data.status === 1) {
                    nodes = data.content;
                    for (var i = 0; i < nodes.length; i++) {
                        DataStream.addNode(nodes[i]);
                    }

                    cy.nodes().nonorphans()
                        .on('grab', function () {
                            if (!this.hasClass('node-container') && !this.hasClass('group-container')) {
                                this.ungrabify();
                            }
                        })
                        .on('free', function () {
                            if (!this.hasClass('node-container') && !this.hasClass('group-container')) {
                                this.grabify();
                            }
                        });

                    $.ajax({
                        method: 'POST',
                        url: '/jf_mcht/monitor/edge/getEdgeList',
                        data: {
                            layoutId: layoutId
                        }
                    }).done(function (data) {
                        if (data.status === 1) {
                            edges = data.content;
                            for (var i = 0; i < edges.length; i++) {
                                DataStream.addEdge(edges[i]);
                            }
                            initTimer();

                            DataStream.initGroupCollapse();

                            waitingDialog.hide();
                            DataStream.getIndexValues();
                        }
                    });
                }
            });
        }
    });

}); // on dom ready

