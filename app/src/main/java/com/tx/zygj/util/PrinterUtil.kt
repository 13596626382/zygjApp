package com.tx.zygj.util

import java.io.UnsupportedEncodingException

object PrinterUtil {

    @Throws(UnsupportedEncodingException::class)
    fun String.byteToHex() = toByteArray(charset("gb2312"))


    /**
     * 编码表
     */
    private fun surface(): Map<String, Byte> {
        val surface: MutableMap<String, Byte> = HashMap()
        surface["a1"] = 0xa1.toByte()
        surface["a2"] = 0xa2.toByte()
        surface["a3"] = 0xa3.toByte()
        surface["a4"] = 0xa4.toByte()
        surface["a5"] = 0xa5.toByte()
        surface["a6"] = 0xa6.toByte()
        surface["a7"] = 0xa7.toByte()
        surface["a8"] = 0xa8.toByte()
        surface["a9"] = 0xa9.toByte()
        surface["b0"] = 0xb0.toByte()
        surface["b1"] = 0xb1.toByte()
        surface["b2"] = 0xb2.toByte()
        surface["b3"] = 0xb3.toByte()
        surface["b4"] = 0xb4.toByte()
        surface["b5"] = 0xb5.toByte()
        surface["b6"] = 0xb6.toByte()
        surface["b7"] = 0xb7.toByte()
        surface["b8"] = 0xb8.toByte()
        surface["b9"] = 0xb9.toByte()
        surface["ba"] = 0xba.toByte()
        surface["bb"] = 0xbb.toByte()
        surface["bc"] = 0xbc.toByte()
        surface["bd"] = 0xbd.toByte()
        surface["be"] = 0xbe.toByte()
        surface["bf"] = 0xbf.toByte()
        surface["c0"] = 0xc0.toByte()
        surface["c1"] = 0xc1.toByte()
        surface["c2"] = 0xc2.toByte()
        surface["c3"] = 0xc3.toByte()
        surface["c4"] = 0xc4.toByte()
        surface["c5"] = 0xc5.toByte()
        surface["c6"] = 0xc6.toByte()
        surface["c7"] = 0xc7.toByte()
        surface["c8"] = 0xc8.toByte()
        surface["c9"] = 0xc9.toByte()
        surface["ca"] = 0xca.toByte()
        surface["cb"] = 0xcb.toByte()
        surface["cc"] = 0xcc.toByte()
        surface["cd"] = 0xcd.toByte()
        surface["ce"] = 0xce.toByte()
        surface["cf"] = 0xcf.toByte()
        surface["d0"] = 0xd0.toByte()
        surface["d1"] = 0xd1.toByte()
        surface["d2"] = 0xd2.toByte()
        surface["d3"] = 0xd3.toByte()
        surface["d4"] = 0xd4.toByte()
        surface["d5"] = 0xd5.toByte()
        surface["d6"] = 0xd6.toByte()
        surface["d7"] = 0xd7.toByte()
        surface["d8"] = 0xd8.toByte()
        surface["d9"] = 0xd9.toByte()
        surface["da"] = 0xda.toByte()
        surface["db"] = 0xdb.toByte()
        surface["dc"] = 0xdc.toByte()
        surface["dd"] = 0xdd.toByte()
        surface["de"] = 0xde.toByte()
        surface["df"] = 0xdf.toByte()
        surface["e0"] = 0xe0.toByte()
        surface["e1"] = 0xe1.toByte()
        surface["e2"] = 0xe2.toByte()
        surface["e3"] = 0xe3.toByte()
        surface["e4"] = 0xe4.toByte()
        surface["e5"] = 0xe5.toByte()
        surface["e6"] = 0xe6.toByte()
        surface["e7"] = 0xe7.toByte()
        surface["e8"] = 0xe8.toByte()
        surface["e9"] = 0xe9.toByte()
        surface["ea"] = 0xea.toByte()
        surface["eb"] = 0xeb.toByte()
        surface["ec"] = 0xec.toByte()
        surface["ed"] = 0xed.toByte()
        surface["ee"] = 0xee.toByte()
        surface["ef"] = 0xef.toByte()
        surface["f0"] = 0xf0.toByte()
        surface["f1"] = 0xf1.toByte()
        surface["f2"] = 0xf2.toByte()
        surface["f3"] = 0xf3.toByte()
        surface["f4"] = 0xf4.toByte()
        surface["f5"] = 0xf5.toByte()
        surface["f6"] = 0xf6.toByte()
        surface["f7"] = 0xf7.toByte()
        return surface
    }

    /**
     * 编码表对应的汉字
     */
    private fun bytecode(): Map<String, Byte> {
        val bytecode: MutableMap<String, Byte> = HashMap()
        bytecode["a1"] = 0xa1.toByte()
        bytecode["a2"] = 0xa2.toByte()
        bytecode["a3"] = 0xa3.toByte()
        bytecode["a4"] = 0xa4.toByte()
        bytecode["a5"] = 0xa5.toByte()
        bytecode["a6"] = 0xa6.toByte()
        bytecode["a7"] = 0xa7.toByte()
        bytecode["a8"] = 0xa8.toByte()
        bytecode["a9"] = 0xa9.toByte()
        bytecode["aa"] = 0xba.toByte()
        bytecode["ab"] = 0xbb.toByte()
        bytecode["ac"] = 0xbc.toByte()
        bytecode["ad"] = 0xbd.toByte()
        bytecode["ae"] = 0xbe.toByte()
        bytecode["af"] = 0xbf.toByte()
        bytecode["b0"] = 0xb0.toByte()
        bytecode["b1"] = 0xb1.toByte()
        bytecode["b2"] = 0xb2.toByte()
        bytecode["b3"] = 0xb3.toByte()
        bytecode["b4"] = 0xb4.toByte()
        bytecode["b5"] = 0xb5.toByte()
        bytecode["b6"] = 0xb6.toByte()
        bytecode["b7"] = 0xb7.toByte()
        bytecode["b8"] = 0xb8.toByte()
        bytecode["b9"] = 0xb9.toByte()
        bytecode["ba"] = 0xba.toByte()
        bytecode["bb"] = 0xbb.toByte()
        bytecode["bc"] = 0xbc.toByte()
        bytecode["bd"] = 0xbd.toByte()
        bytecode["be"] = 0xbe.toByte()
        bytecode["bf"] = 0xbf.toByte()
        bytecode["c0"] = 0xc0.toByte()
        bytecode["c1"] = 0xc1.toByte()
        bytecode["c2"] = 0xc2.toByte()
        bytecode["c3"] = 0xc3.toByte()
        bytecode["c4"] = 0xc4.toByte()
        bytecode["c5"] = 0xc5.toByte()
        bytecode["c6"] = 0xc6.toByte()
        bytecode["c7"] = 0xc7.toByte()
        bytecode["c8"] = 0xc8.toByte()
        bytecode["c9"] = 0xc9.toByte()
        bytecode["ca"] = 0xca.toByte()
        bytecode["cb"] = 0xcb.toByte()
        bytecode["cc"] = 0xcc.toByte()
        bytecode["cd"] = 0xcd.toByte()
        bytecode["ce"] = 0xce.toByte()
        bytecode["cf"] = 0xcf.toByte()
        bytecode["d0"] = 0xd0.toByte()
        bytecode["d1"] = 0xd1.toByte()
        bytecode["d2"] = 0xd2.toByte()
        bytecode["d3"] = 0xd3.toByte()
        bytecode["d4"] = 0xd4.toByte()
        bytecode["d5"] = 0xd5.toByte()
        bytecode["d6"] = 0xd6.toByte()
        bytecode["d7"] = 0xd7.toByte()
        bytecode["d8"] = 0xd8.toByte()
        bytecode["d9"] = 0xd9.toByte()
        bytecode["da"] = 0xda.toByte()
        bytecode["db"] = 0xdb.toByte()
        bytecode["dc"] = 0xdc.toByte()
        bytecode["dd"] = 0xdd.toByte()
        bytecode["de"] = 0xde.toByte()
        bytecode["df"] = 0xdf.toByte()
        bytecode["e0"] = 0xe0.toByte()
        bytecode["e1"] = 0xe1.toByte()
        bytecode["e2"] = 0xe2.toByte()
        bytecode["e3"] = 0xe3.toByte()
        bytecode["e4"] = 0xe4.toByte()
        bytecode["e5"] = 0xe5.toByte()
        bytecode["e6"] = 0xe6.toByte()
        bytecode["e7"] = 0xe7.toByte()
        bytecode["e8"] = 0xe8.toByte()
        bytecode["e9"] = 0xe9.toByte()
        bytecode["ea"] = 0xea.toByte()
        bytecode["eb"] = 0xeb.toByte()
        bytecode["ec"] = 0xec.toByte()
        bytecode["ed"] = 0xed.toByte()
        bytecode["ee"] = 0xee.toByte()
        bytecode["ef"] = 0xef.toByte()
        bytecode["f0"] = 0xf0.toByte()
        bytecode["f1"] = 0xf1.toByte()
        bytecode["f2"] = 0xf2.toByte()
        bytecode["f3"] = 0xf3.toByte()
        bytecode["f4"] = 0xf4.toByte()
        bytecode["f5"] = 0xf5.toByte()
        bytecode["f6"] = 0xf6.toByte()
        bytecode["f7"] = 0xf7.toByte()
        bytecode["f8"] = 0xf8.toByte()
        bytecode["f9"] = 0xf9.toByte()
        bytecode["fa"] = 0xfa.toByte()
        bytecode["fb"] = 0xfb.toByte()
        bytecode["fc"] = 0xfc.toByte()
        bytecode["fd"] = 0xfd.toByte()
        bytecode["fe"] = 0xfe.toByte()
        return bytecode
    }
}