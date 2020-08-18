package com.skio.coroutines.entity.user

class UserInfoEntity {
  var authStatus: String? = null
  var companyCode: String? = null
  var realname: String? = null
  var phone: String? = null
  var authType = 0
  override fun toString(): String {
    return "UserInfoEntity(authStatus=$authStatus, companyCode=$companyCode, realname=$realname, phone=$phone, authType=$authType)"
  }

}
