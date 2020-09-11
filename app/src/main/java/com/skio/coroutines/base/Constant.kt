package com.skio.coroutines.base

class Constant {
  object Net {
    const val BASE_URL = "http://www.jihuabiao.net:8890"
    const val AUTH_URL = "https://oauth.secure.pixiv.net"
    const val ACCOUNT_URL = "https://accounts.pixiv.net"
    const val EXT_URL = "http://149.28.73.52:8888"
    const val SWITCH_HEADER = "SWITCH-HEADER" // 用于分辨是否需要切换baseUrl
    const val TAG_AUTH = "TAG_AUTH"
    const val TAG_ACCOUNT = "TAG_ACCOUNT"
    // 自己的服务
    const val TAG_EXT = "TAG_EXT"
    const val HEADER_TOKEN = "token"

  }

}
