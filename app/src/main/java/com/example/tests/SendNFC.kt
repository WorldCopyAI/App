package com.example.tests

import android.app.Activity
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import java.nio.charset.Charset
import java.util.*

open class SendNFC:AppCompatActivity() {
    var nfcAdapter: NfcAdapter = NfcAdapter.getDefaultAdapter(null)


    var mNdeMessage = NdefMessage( createNewTextRecord("이름 : 홍길동", Locale.ENGLISH, true))

    fun createNewTextRecord(text: String, locale: Locale, encodelnUtf8: Boolean): NdefRecord? {
        val langBytes: ByteArray = locale.getLanguage().toByteArray(Charset.forName("US-ASCII"))
        val utfEncoding: Charset =
            if (encodelnUtf8) Charset.forName("UTF-8") else Charset.forName("UTF-16")
        val textBytes: ByteArray = text.toByteArray(utfEncoding)
        val utfBit = if (encodelnUtf8) 0 else 1 shl 7
        val status = (utfBit + langBytes.size).toChar()
        val data = ByteArray(1 + langBytes.size + textBytes.size)
        data[0] = status.toByte()
        System.arraycopy(langBytes, 0, data, 1, langBytes.size)
        System.arraycopy(textBytes, 0, data, 1 + langBytes.size, textBytes.size)
        return NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, ByteArray(0), data)
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableForegroundNdefPush(this, mNdeMessage)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundNdefPush(this)
    }

}