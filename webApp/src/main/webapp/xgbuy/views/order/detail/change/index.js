var orderDetailChange = $('.page[data-id="order_detail_change"]:visible')
odcAddress = orderDetailChange.find('.odc-address'),
odcContent = orderDetailChange.find('.odc-change-content');

var orderDetailChangeApi = {
  data: {
    combineOrderId: '',
    subOrderId: '',
    oldAddress: {},
    newAddress: {}
  },
  init: function () {
    var _this = this;
    if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'combineOrderId': _this.data.combineOrderId = _b; break;
					case 'subOrderId': _this.data.subOrderId = _b; break;
				}
      }
    }
    new ajax(getApi.orderAddressPageInfo, {
      data: {
        combineOrderId: _this.data.combineOrderId
      },
      success: function (res) {
        _this.data.oldAddress = {
          addressId: res.returnData.addressId,
          receiverName: res.returnData.receiverName,
          receiverPhone: res.returnData.receiverPhone,
          receiverAddress: res.returnData.receiverAddress
        }
        odcAddress.html(template('orderDetailChangeAddress', res.returnData));
        odcContent.html(template('orderDetailChangeList', res.returnData));
      }
    })
  },
  changeAddress: function() {
    var addressData = getStorage('address_list') && JSON.parse(getStorage('address_list')),
    newData = this.data.newAddress;
    newData.addressId = addressData.addressId;
    newData.receiverName = addressData.recipientName;
    newData.receiverPhone = addressData.recipientPhone;
    newData.receiverAddress = addressData.recipientAddressFullName;
    odcAddress.html(template('orderDetailChangeAddress', newData));
  },
  // 提交
  submit: function () {
    var _this = this,
    oldData = _this.data.oldAddress,
    newData = _this.data.newAddress,
    delId = getStorage('address_del'),
    isChanged = oldData.addressId == newData.addressId && oldData.receiverName == newData.receiverName && oldData.receiverPhone == newData.receiverPhone && oldData.receiverAddress == newData.receiverAddress;
    if (delId == oldData.addressId) {
      return delayTip('收货地址已被删除，请重新添加收货地址');
    }
    if (!newData.addressId || isChanged) {
      return delayTip('抱歉，该地址与原地址相同');
    }
    new ajax(getApi.orderAddressChange, {
      data: {
        combineOrderId: _this.data.combineOrderId,
        addressId: newData.addressId
      },
      success: function () {
        delayTip('修改成功');
        getUrl(`order/detail/index.html?combineOrderId=${_this.data.combineOrderId}&subOrderId=${_this.data.subOrderId}`, true);
      }
    })
  },
  // 选择地址
  choseAddress: function () {
    getUrl(`order/detail/address/list.html?combineOrderId=${this.data.combineOrderId}&subOrderId=${this.data.subOrderId}&addressId=${this.data.oldAddress.addressId}`, 'self');
  }
}

getTpl(function () {
  orderDetailChangeApi.init();
});